# import module
import cv2 as cv 
import mediapipe as mp
import numpy as np
import tensorflow as tf
from sklearn.preprocessing import LabelBinarizer
from tensorflow.python.ops.gen_lookup_ops import initialize_table_from_text_file_eager_fallback

# function declaration
# extracting 21 features
def extract(result):
    handed = []
    for res in results.multi_handedness:
        if res.classification[0].label == 'Right':
            handed.append([0,res.classification[0].score])
        elif res.classification[0].label == 'Left':
            handed.append([1,res.classification[0].score])

    position = []
    for hand_landmarks in results.multi_hand_landmarks:
    # Print index finger tip coordinates.
        #looping 21 landmark *3 (x,y,z) 126
        for point in list(hand_landmarks.landmark):
            position.append(point.x)
            position.append(point.y)
            position.append(point.z)
    return handed, position

# assigning landmark to list
def assign(handed, position):
    temp = np.zeros(130).tolist()
    temp[0:2] = [0,0]
    # if we detect one hands only
    if len(handed) == 1:
        if handed[0][0] == 0:
            temp[2:3] = [handed[0][1]]
            temp[4:67] = position[0:63]
        elif handed[0][0] == 1:
            temp[3:4] = [handed[0][1]]
            temp[67:130] = position[0:63]
    # if we detect both hand with different handedness
    elif handed[0][0] != handed [1][0]:
        for i, hand in enumerate(handed):  
            if hand[0] == 0:
                temp[2:3] = [hand[1]]
                if i == 0:
                    temp[4:67] = position[0:63]
                elif i == 1:
                    temp[4:67] = position[63:130]
            elif hand[0] == 1:
                temp[3:4] = [hand[1]]
                if i == 0:
                    temp[67:130] = position[0:63]
                elif i == 1:
                    temp[67:130] = position[63:130]
    # if we detect both hands but in the same handedness
    # we'll pick the one with greater probability
    else:
        if handed[0][1] > handed[1][1]:
            if handed[0][0] == 0:
                temp[2:3] = [handed[0][1]]
                temp[4:67] = position[0:63]

            elif handed[0][0] == 1:
                temp[3:4] = [handed[0][1]]
                temp[67:130] = position[0:63]
        else:
            if handed[1][0] == 0:
                temp[2:3] = [handed[1][1]]
                temp[4:67] = position[63:130]
            elif handed[1][0] == 1:
                temp[3:4] = [handed[1][1]]
                temp[67:130] = position[63:130]
    return np.array(temp[2:]).reshape(-1, 128)

def predict(model, x, encoder):
    return str(encoder.inverse_transform(model.predict(x)))

def disp_text(image, text):
    font = cv.FONT_HERSHEY_SIMPLEX 
    # org
    org = (50, 50)
    # fontScale
    fontScale = 1
    # Blue color in BGR
    color = (255, 0, 0)
    # Line thickness of 2 px
    thickness = 2
    # Using cv2.putText() method
    image = cv.putText(image, text, org, font, 
                    fontScale, color, thickness, cv.LINE_AA)
    return image
# initiate MediaPipe API 
mp_hands = mp.solutions.hands
mp_drawing = mp.solutions.drawing_utils 

# initialize tf model and encoder
label_list = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10']
label_list.extend(list('ABCDEFGHIJKLMNOPQRSTUVWXYZ'))
encoder = LabelBinarizer()
encoder.fit(label_list)
model = tf.keras.models.load_model('class37.h5')

# var needed
isBreak = False

# you can choose the used webcam (if you have other than one) by changing the number (0 default)
camera_num = 1

# read from webcam
capture = cv.VideoCapture(camera_num)

# main loop
while True:
    _, frame = capture.read()
    frame = cv.flip(frame,1) 
    with mp_hands.Hands(
    static_image_mode=True,
    max_num_hands=2,
    min_detection_confidence=0.5) as hands:
        # inferencing with MediaPipe API
        results = hands.process(cv.cvtColor(frame, cv.COLOR_BGR2RGB))
        annotated_image = frame.copy()
        
        # Check if theres hand available
        if not results.multi_hand_landmarks:
            cv.imshow('Hand Recognition', annotated_image)
        # Draw landmarks if available
        else:
            handed, position = extract(results)
            x = assign(handed, position)
            sign_lang = predict(model, x, encoder)
            annotated_image = disp_text(annotated_image, sign_lang)
            for hand_landmarks in results.multi_hand_landmarks:
            # Print index finger tip coordinates.
                mp_drawing.draw_landmarks(
                    annotated_image, hand_landmarks, mp_hands.HAND_CONNECTIONS)
                cv.imshow('Hand Recognition', annotated_image)

        # input key
        keyPressed = cv.waitKey(1) & 0xFF
            
        # Press q to quit 
        if keyPressed == ord('q'):
            isBreak = True
        
        # break if needed
        if isBreak:
            break

capture.release()
cv.destroyAllWindows()
