# import module
import cv2 as cv
import mediapipe as mp
mp_hands = mp.solutions.hands
mp_drawing = mp.solutions.drawing_utils 

# read from webcam
# you can choose the used webcam (if you have other than one) by changing the number (0 default)
camera_num = 1
capture = cv.VideoCapture(camera_num)
while True:
    isTrue, frame = capture.read() 
    with mp_hands.Hands(
    static_image_mode=True,
    max_num_hands=2,
    min_detection_confidence=0.1) as hands:
        results = hands.process(cv.cvtColor(frame, cv.COLOR_BGR2RGB))
        annotated_image = frame.copy()
        height, width, _ = frame.shape
        # Check if theres hand available
        if not results.multi_hand_landmarks:
            continue
        for hand_landmarks in results.multi_hand_landmarks:
        # Print index finger tip coordinates.
            mp_drawing.draw_landmarks(
                annotated_image, hand_landmarks, mp_hands.HAND_CONNECTIONS)
        cv.imshow('Video Web', annotated_image)
        if cv.waitKey(20) & 0xFF == ord('d'):
            break

capture.release()
cv.destroyAllWindows()
