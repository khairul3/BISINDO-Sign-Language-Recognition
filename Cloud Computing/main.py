from google.cloud import storage
import numpy as np
import os
import tensorflow as tf
import json

def download_model(bucket_name, source_dir, destination_folder, model_version):
    
    download_directory = os.path.join(destination_folder, "models", model_version)
    variables_directory = os.path.join(destination_folder, "models", model_version, "variables")
    if not os.path.exists(download_directory):
        os.makedirs(download_directory)

    if not os.path.exists(variables_directory):
        os.makedirs(variables_directory)
        
    storage_client = storage.Client()
    
    bucket = storage_client.get_bucket(bucket_name)
    print(bucket)
    sub_folders = bucket.list_blobs(prefix=source_dir)
    print(sub_folders)    
    for file in sub_folders:
        download_path = os.path.join(download_directory, os.path.basename(file.name))
        if "variables" in file.name:
            download_path = os.path.join(variables_directory, os.path.basename(file.name))
        file.download_to_filename(download_path)

    print('Model downloaded to {}.'.format(download_directory))

def request_handler(request):
    letter = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','Y','Z']
    #global model

    #if model is None:
    # Set CORS headers for the preflight request
    if request.method == 'OPTIONS':
        # Allows GET requests from any origin with the Content-Type
        # header and caches preflight response for an 3600s
        headers = {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'POST',
            'Access-Control-Allow-Headers': 'Content-Type',
        }

        return ('', 204, headers)

    # Set CORS headers for the main request
    headers = {
        'Access-Control-Allow-Origin': '*'
    }
    request_json = request.get_json()
    if request is None:
        print("nda ada data")
    #print(type(json.loads(request_json)))
    #print(json.load(request_json))
    #print(type(request_json))
    download_model(bucket_name='bisindo-deploy-bucket', 
                       source_dir='MODEL', 
                       destination_folder = '/tmp', 
                       model_version='v0002')
    #print(request.get_json['instances'])                   
    model = tf.keras.models.load_model('/tmp/models/v0002')

    request_json = request.get_json()
    predictions = model.predict(request_json['instances'])
    header = {
        'Access-Control-Allow-Origin': '*'
    }
    result_predict = letter[np.argmax(predictions)]

    result = {
        "predictions": result_predict
    }

    
    
    return (result, 200, headers)
