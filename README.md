# BISINDO-Sign-Language-Recognition
   
 [![Platform](https://img.shields.io/badge/platform-Android-green.svg)](http://developer.android.com/index.html) [![Kotlin](https://img.shields.io/badge/kotlin-1.5.10-blue.svg)](http://kotlinlang.org) [![Gradle](https://img.shields.io/badge/gradle-7.0-%2366DCB8.svg)](https://developer.android.com/studio/releases/gradle-plugin) 


IndoSign is an application that can be used for learning BISINDO sign languange interactively. We provide an interactive learning platform
with hand recognition of BISINDO sign language with smartphone's camera. 


### Installation 
Clone this repository by using this one  
```
    https://github.com/khairul3/BISINDO-Sign-Language-Recognition.git
```    

## Demo



# Mobile Programming Android
### Technology Used
### UI
- [Material Design](https://material.io/develop/android)
- [Glide](https://github.com/bumptech/glide)
- [Recycler View](https://github.com/codepath/android_guides/wiki/Using-the-RecyclerView)

### Database
- [Cloud Firestore](https://firebase.google.com/docs/firestore)


# Machine Learning
Using Kaggle ISLBISINDO1 dataset, combined with self-made dataset which both of them are image dataset. Using mediapipe API to extract 21 Hand Landmarks feature from the image and generate csv dataset using python. Doing an EDA and creating 3 models using numeric, alphatebetic and both sign language characters. Deploy the model to cloud and android.

Step by Step if you want to replicate our work
A. Getting the dataset in kaggle
![image](https://user-images.githubusercontent.com/46083543/121294110-0594ca00-c917-11eb-95fc-648e8242963f.png)
In this project we use ISLBISINDO1 as our main dataset, that dataset contains a 27 classes of alphabet sign language. There's two directories in that dataset, traindir and testdir. Traindir contains 64 images per classes and testdir contains 16 images per classes. And also we create our own dataset for 




============================================================================================================================================

# Cloud Computing 

# Create Databases for Frontend 

![Database Architecture](https://user-images.githubusercontent.com/82069840/121268889-044ca880-c8e9-11eb-8721-70dfe30ed044.png)

In this section, we use Cloud Storage to store sign language image and gif data 
for Frontend purposes in the IndoSign application.
And after all data is stored in cloud storage and set permissions for all users to be 
viewer, we use Cloud Firestore to create database and connect it to Firebase so Android 
Team can easily access its data in database.

# Step 1 : Create Bucket and Upload Folder/File on Cloud Storage
In the Google Cloud Platform menu navigation, select Cloud Storage and then create a new bucket. After
the bucket is created in the permissions section add members and type all users then select
role Cloud Storage -> Storage Object Viewer, it is done so that the data can be accessed by the public.
After that, upload a file/folder containing sign language images and gifs into the selected bucket
already made.

# Step 2 : Create Databases on Firestore
Create a new database in Firestore then connect to the connected firebase
with projects on Google Cloud Platform.
• Create a collection by writing the collection id according to the data collection that you want
will be entered.

• Add Document and input document id or can select auto id

• Add field and enter field name, field type, and field value. For field value "image"
Enter the public access link for the uploaded image/gif file in Cloud Storage.


# Deploying Machine Learning Model on Google Cloud Function

![Deploy ML Architecture](https://user-images.githubusercontent.com/82069840/121268937-19293c00-c8e9-11eb-9dc0-d72abf5bab69.png)

Machine Learning models from Tensorflow are added to buckets that have been 
created in Cloud Storage. Then run the python script that has been created by 
the Machine Learning team in the cloud function to handle requests and download models 
from Cloud Storage. Next, test the model in Cloud Function and return the prediction 
results to the user in the form of a response. and lastly make a request in the Cloud 
Function using the URL from the triggers tab.

# Step 1: Create Bucket and Upload Model from Tensorflow
After the model has been prepared by the Machine Learning team at Tensorflow, the model is uploaded to
Cloud Storage. In the Google Cloud Platform menu navigation, select Cloud Storage and then create
a new bucket. After the bucket is created, upload the model into the bucket you just created.

# Step 2: Create Cloud Function for Deploy Machine Learning Model
• Create Cloud Function and use python 3, then select the http trigger

• Enter the python script to handle requests and to download models from Cloud Storage.
for code is in "cloud/main.py" and "cloud/requirements.txt".

• Perform model tests in the cloud function

• Improved prediction results to the user in the form of a response

• Use the url from the trigger tab in the Cloud Function to make a request to the Cloud Function.

===============================================================================
*using the web application*
Because we are still working on native android camera live recognition, temporarily, you can use webview to do live recognition on the web app
that we deploy on GCP App Engine
https://valid-dragon-312515.et.r.appspot.com/
