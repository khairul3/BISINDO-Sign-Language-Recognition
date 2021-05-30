# import module
import cv2 as cv
import mediapipe as mp
import time
import numpy as np
import csv
import os.path 

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
def assign(name, label, handed, position):
    temp = np.zeros(130).tolist()
    temp[0:2] = [name, label]
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
    return temp

# initiating csv file
def initiate_csv(filename, fields):
    # check for file if exist
    if os.path.isfile(filename):
        print ("File exist")
    else:
        print ("File not exist\n making new files")
        with open(filename, 'w', newline='') as f:
            # using csv.writer method from CSV package
            write = csv.writer(f)
            write.writerow(fields)
            f.close()

# appending csv
def append_csv(filename, landmark):
    with open(filename, 'a', newline='') as f:
        # using csv.writer method from CSV package
        write = csv.writer(f)
        write.writerows(landmark)
        f.close()

# csv fields
fields = [
    'IMAGE_NAME'               ,
    'LABEL'                    ,
    'RIGHT_HAND_PROB'          ,
    'LEFT_HAND_PROB'           ,
    'RIGHT_X_WRIST'            ,
    'RIGHT_Y_WRIST'            ,
    'RIGHT_Z_WRIST'            ,
    'RIGHT_X_THUMB_CMC'        ,
    'RIGHT_Y_THUMB_CMC'        ,
    'RIGHT_Z_THUMB_CMC'        ,
    'RIGHT_X_THUMB_MCP'        ,
    'RIGHT_Y_THUMB_MCP'        ,
    'RIGHT_Z_THUMB_MCP'        ,
    'RIGHT_X_THUMB_IP'         ,
    'RIGHT_Y_THUMB_IP'         ,
    'RIGHT_Z_THUMB_IP'         ,
    'RIGHT_X_THUMB_TIP'        ,
    'RIGHT_Y_THUMB_TIP'        ,
    'RIGHT_Z_THUMB_TIP'        ,
    'RIGHT_X_INDEX_FINGER_MCP' ,
    'RIGHT_Y_INDEX_FINGER_MCP' ,
    'RIGHT_Z_INDEX_FINGER_MCP' ,
    'RIGHT_X_INDEX_FINGER_PIP' ,
    'RIGHT_Y_INDEX_FINGER_PIP' ,
    'RIGHT_Z_INDEX_FINGER_PIP' ,
    'RIGHT_X_INDEX_FINGER_DIP' ,
    'RIGHT_Y_INDEX_FINGER_DIP' ,
    'RIGHT_Z_INDEX_FINGER_DIP' ,
    'RIGHT_X_INDEX_FINGER_TIP' ,
    'RIGHT_Y_INDEX_FINGER_TIP' ,
    'RIGHT_Z_INDEX_FINGER_TIP' ,
    'RIGHT_X_MIDDLE_FINGER_MCP',
    'RIGHT_Y_MIDDLE_FINGER_MCP',
    'RIGHT_Z_MIDDLE_FINGER_MCP',
    'RIGHT_X_MIDDLE_FINGER_PIP',
    'RIGHT_Y_MIDDLE_FINGER_PIP',
    'RIGHT_Z_MIDDLE_FINGER_PIP',
    'RIGHT_X_MIDDLE_FINGER_DIP',
    'RIGHT_Y_MIDDLE_FINGER_DIP',
    'RIGHT_Z_MIDDLE_FINGER_DIP',
    'RIGHT_X_MIDDLE_FINGER_TIP',
    'RIGHT_Y_MIDDLE_FINGER_TIP',
    'RIGHT_Z_MIDDLE_FINGER_TIP',
    'RIGHT_X_RING_FINGER_MCP'  ,
    'RIGHT_Y_RING_FINGER_MCP'  ,
    'RIGHT_Z_RING_FINGER_MCP'  ,
    'RIGHT_X_RING_FINGER_PIP'  ,
    'RIGHT_Y_RING_FINGER_PIP'  ,
    'RIGHT_Z_RING_FINGER_PIP'  ,
    'RIGHT_X_RING_FINGER_DIP'  ,
    'RIGHT_Y_RING_FINGER_DIP'  ,
    'RIGHT_Z_RING_FINGER_DIP'  ,
    'RIGHT_X_RING_FINGER_TIP'  ,
    'RIGHT_Y_RING_FINGER_TIP'  ,
    'RIGHT_Z_RING_FINGER_TIP'  ,
    'RIGHT_X_PINKY_MCP'        ,
    'RIGHT_Y_PINKY_MCP'        ,
    'RIGHT_Z_PINKY_MCP'        ,
    'RIGHT_X_PINKY_PIP'        ,
    'RIGHT_Y_PINKY_PIP'        ,
    'RIGHT_Z_PINKY_PIP'        ,
    'RIGHT_X_PINKY_DIP'        ,
    'RIGHT_Y_PINKY_DIP'        ,
    'RIGHT_Z_PINKY_DIP'        ,
    'RIGHT_X_PINKY_TIP'        ,
    'RIGHT_Y_PINKY_TIP'        ,
    'RIGHT_Z_PINKY_TIP'        ,
    'LEFT_X_WRIST'            ,
    'LEFT_Y_WRIST'            ,
    'LEFT_Z_WRIST'            ,
    'LEFT_X_THUMB_CMC'        ,
    'LEFT_Y_THUMB_CMC'        ,
    'LEFT_Z_THUMB_CMC'        ,
    'LEFT_X_THUMB_MCP'        ,
    'LEFT_Y_THUMB_MCP'        ,
    'LEFT_Z_THUMB_MCP'        ,
    'LEFT_X_THUMB_IP'         ,
    'LEFT_Y_THUMB_IP'         ,
    'LEFT_Z_THUMB_IP'         ,
    'LEFT_X_THUMB_TIP'        ,
    'LEFT_Y_THUMB_TIP'        ,
    'LEFT_Z_THUMB_TIP'        ,
    'LEFT_X_INDEX_FINGER_MCP' ,
    'LEFT_Y_INDEX_FINGER_MCP' ,
    'LEFT_Z_INDEX_FINGER_MCP' ,
    'LEFT_X_INDEX_FINGER_PIP' ,
    'LEFT_Y_INDEX_FINGER_PIP' ,
    'LEFT_Z_INDEX_FINGER_PIP' ,
    'LEFT_X_INDEX_FINGER_DIP' ,
    'LEFT_Y_INDEX_FINGER_DIP' ,
    'LEFT_Z_INDEX_FINGER_DIP' ,
    'LEFT_X_INDEX_FINGER_TIP' ,
    'LEFT_Y_INDEX_FINGER_TIP' ,
    'LEFT_Z_INDEX_FINGER_TIP' ,
    'LEFT_X_MIDDLE_FINGER_MCP',
    'LEFT_Y_MIDDLE_FINGER_MCP',
    'LEFT_Z_MIDDLE_FINGER_MCP',
    'LEFT_X_MIDDLE_FINGER_PIP',
    'LEFT_Y_MIDDLE_FINGER_PIP',
    'LEFT_Z_MIDDLE_FINGER_PIP',
    'LEFT_X_MIDDLE_FINGER_DIP',
    'LEFT_Y_MIDDLE_FINGER_DIP',
    'LEFT_Z_MIDDLE_FINGER_DIP',
    'LEFT_X_MIDDLE_FINGER_TIP',
    'LEFT_Y_MIDDLE_FINGER_TIP',
    'LEFT_Z_MIDDLE_FINGER_TIP',
    'LEFT_X_RING_FINGER_MCP'  ,
    'LEFT_Y_RING_FINGER_MCP'  ,
    'LEFT_Z_RING_FINGER_MCP'  ,
    'LEFT_X_RING_FINGER_PIP'  ,
    'LEFT_Y_RING_FINGER_PIP'  ,
    'LEFT_Z_RING_FINGER_PIP'  ,
    'LEFT_X_RING_FINGER_DIP'  ,
    'LEFT_Y_RING_FINGER_DIP'  ,
    'LEFT_Z_RING_FINGER_DIP'  ,
    'LEFT_X_RING_FINGER_TIP'  ,
    'LEFT_Y_RING_FINGER_TIP'  ,
    'LEFT_Z_RING_FINGER_TIP'  ,
    'LEFT_X_PINKY_MCP'        ,
    'LEFT_Y_PINKY_MCP'        ,
    'LEFT_Z_PINKY_MCP'        ,
    'LEFT_X_PINKY_PIP'        ,
    'LEFT_Y_PINKY_PIP'        ,
    'LEFT_Z_PINKY_PIP'        ,
    'LEFT_X_PINKY_DIP'        ,
    'LEFT_Y_PINKY_DIP'        ,
    'LEFT_Z_PINKY_DIP'        ,
    'LEFT_X_PINKY_TIP'        ,
    'LEFT_Y_PINKY_TIP'        ,
    'LEFT_Z_PINKY_TIP'        ,
]

# initiate MediaPipe API 
mp_hands = mp.solutions.hands
mp_drawing = mp.solutions.drawing_utils 


# var needed
# Naming Label
isBreak = False
isCapture = False
label = None
captureTime = 0

# you can choose the used webcam (if you have other than one) by changing the number (0 default)
camera_num = 1
# name your csv files
filename = 'train.csv'
initiate_csv(filename, fields)
# read from webcam
capture = cv.VideoCapture(camera_num)
# you can set your delay time for capturing image
delay = 0

# main loop
while True:
    _, frame = capture.read()
    frame = cv.flip(frame,1) 
    with mp_hands.Hands(
    static_image_mode=True,
    max_num_hands=2,
    min_detection_confidence=0.1) as hands:
        # inferencing with MediaPipe API
        results = hands.process(cv.cvtColor(frame, cv.COLOR_BGR2RGB))
        annotated_image = frame.copy()
        
        # Check if theres hand available
        if not results.multi_hand_landmarks:
            cv.imshow('Hand Recognition', annotated_image)
        # Draw landmarks if available
        else:
            for hand_landmarks in results.multi_hand_landmarks:
            # Print index finger tip coordinates.
                mp_drawing.draw_landmarks(
                    annotated_image, hand_landmarks, mp_hands.HAND_CONNECTIONS)
                cv.imshow('Hand Recognition', annotated_image)

        timeDelay = (delay+1 > time.time() - captureTime > delay)
        # Capture image by pressing space then wait for 3-ish second to capture
        if isCapture and timeDelay  :
            # gathering data
            print('captured')
            handed, position = extract(results)
            timeStamp = time.strftime("%b %d %Y %H:%M:%S", time.gmtime())
            temp = assign(timeStamp, label, handed, position)
            
            # prompted message
            print(f'do you want to save the image as label: {label}\npress space to save and any key to cancel')
            
            # choose to append to landmark list or not
            save_or_no = cv.waitKey(0) & 0xFF 
            if save_or_no == ord(' '):
                append_csv(filename, [temp])
                print('saved')
                time.sleep(1)        
            else:
                print('not_saved')
                time.sleep(1)
            isCapture = False
            

        # input key
        keyPressed = cv.waitKey(1) & 0xFF
            
        if keyPressed == ord(' '):
            captureTime = time.time()
            isCapture = True
        # Press q to quit 
        elif keyPressed == ord('q'):
            isBreak = True
        elif keyPressed == ord('-'):
                    label = '10'
                    print(f'Label: {label}')
                    time.sleep(1)
        else:
            for key in 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789':
                    if keyPressed == ord(key):
                        label = key
                        print(f'Label: {key}')
                        time.sleep(1)

        # isCapture, isBreak, label = keyCallback(keyPressed)
        
        # break if needed
        if isBreak:
            break

capture.release()
cv.destroyAllWindows()
