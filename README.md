# COMS3009A-Agile-Sprinters
## Social Media/Market Place Clone Project
### WhatsApp Clone Project

<img width="400" alt="Logo" src="https://user-images.githubusercontent.com/81515681/121183937-e2a9dd80-c864-11eb-97cf-7d6c70cc7bb8.png">

### Contributors

Rushil Patel, Sonia Bullah, Kayla Levy, Bhavik Govan, Abdullah Haffejee, Colin Hugo, Tristen Haverly, Jemma Sundelson

<a href="https://github.com/soniabullah1/COMS3009A-Agile-Sprinters/graphs/contributors">
  <img src="https://contributors-img.web.app/image?repo=soniabullah1/COMS3009A-Agile-Sprinters">
</a>

### Build Status
![example workflow](https://github.com/soniabullah1/COMS3009A-Agile-Sprinters/actions/workflows/main.yml/badge.svg)

<em> See [Actions](https://github.com/soniabullah1/COMS3009A-Agile-Sprinters/actions) page for more build details. </em>

### Coverage Status

[![codecov](https://codecov.io/gh/soniabullah1/COMS3009A-Agile-Sprinters/branch/main/graph/badge.svg?token=EECSK3MM0Y)](https://codecov.io/gh/soniabullah1/COMS3009A-Agile-Sprinters)

### Features Included:

* Password protected sign-in
* Option to reset password via email if the password has been forgotten
* Text message exchange between users
* Voice calls between users
* Image exchange between users with or without captions
* Unique chat lists displayed with last message exchanged between two users
* View a complete chat history of all messages exchanged with timestamps on each message
* Read receipts on last message sent
* Online/offline status
* Edit profile picture, username, contact number and password
* Option to add and delete an image status with or without captions
* View statuses from other users
* Enlarging profile pictures and other images exchanged for an easier viewing experience

### Screenshots:
### Welcome:
<img width="250" alt="Loading" src="https://user-images.githubusercontent.com/81515681/121184214-26044c00-c865-11eb-904d-0220e1c16f45.png"> <img width="250" alt="Welcome Screen" src="https://user-images.githubusercontent.com/81515681/121184266-35839500-c865-11eb-8d96-b1a520e34742.png"> 

### Registration, Sign-In & Forgot Password:
<img width="250" alt="Register" src="https://user-images.githubusercontent.com/81515681/121184287-39afb280-c865-11eb-9125-b6f41c8e831a.png"> <img width="250" alt="Login" src="https://user-images.githubusercontent.com/81515681/121184299-3ddbd000-c865-11eb-9bfb-fe165c584014.png"> <img width="250" alt="Forgot Password" src="https://user-images.githubusercontent.com/81515681/121184319-4502de00-c865-11eb-9403-c68cd3392dac.png"> 

### Chat With Other Users & Make Phone Calls:
<img width="250" alt="Unique Chat List" src="https://user-images.githubusercontent.com/81515681/121184850-c8243400-c865-11eb-94f0-244f7fc0e427.png"> <img width="250" alt="Chat" src="https://user-images.githubusercontent.com/81515681/121184891-d07c6f00-c865-11eb-8f3c-0f2f4ad5ff84.png"> <img width="250" alt="Image Dialog Box" src="https://user-images.githubusercontent.com/81515681/121184899-d1ad9c00-c865-11eb-8e83-77b1d0b27314.png"> <img width="250" alt="Image Preview" src="https://user-images.githubusercontent.com/81515681/121184941-de31f480-c865-11eb-8b53-deb826e8a0da.png"> <img width="250" alt="Phone Calls" src="https://user-images.githubusercontent.com/81515681/121184982-e68a2f80-c865-11eb-96e7-6405daa03717.png"> 

### Upload & View Stories:
<img width="250" alt="View Stories" src="https://user-images.githubusercontent.com/81515681/121185341-408af500-c866-11eb-9c3e-7fccd9be40ab.png"> <img width="250" alt="View Story" src="https://user-images.githubusercontent.com/81515681/121185349-4254b880-c866-11eb-9044-cfb629558640.png"> <img width="250" alt="Delete Story" src="https://user-images.githubusercontent.com/81515681/121185362-454fa900-c866-11eb-9e56-c3b1dc4d3cb4.png">

### Enlarge Profile Pictures & Images Exchanged For Easier Viewing:
<img width="250" alt="Enlarge Profile Pictures" src="https://user-images.githubusercontent.com/81515681/121185559-77f9a180-c866-11eb-8e87-0d38495821e7.png"> <img width="250" alt="Enlarge Images" src="https://user-images.githubusercontent.com/81515681/121185564-792ace80-c866-11eb-894f-90d20605ed85.png">

### Edit Profile Information & Logout:
<img width="250" alt="Profile" src="https://user-images.githubusercontent.com/81515681/121185511-6e703980-c866-11eb-869a-98abf632f1b8.png"> <img width="250" alt="Logout" src="https://user-images.githubusercontent.com/81515681/121186557-83999800-c867-11eb-881c-ceb59c885866.png">

### Personalised Application Icon:
<img width="320" alt="Screenshot 2021-06-08 at 19 54 58" src="https://user-images.githubusercontent.com/81515681/121234041-94f89980-c893-11eb-84cf-f2123b6ab1fc.png">

# Installation Guide:
## Firebase Database
1. Create a Firebase account.
2. Create a new Firebase project.
3. Connect the Firebase console to Android Studio.
4. Under the Firebase Realtime Database rules, change "read, write:" to "true;" (See screenshot below).
<img width="287" alt="Screenshot 2021-06-07 at 09 52 30" src="https://user-images.githubusercontent.com/81515681/120979774-38538c80-c776-11eb-802c-bb61238d7710.png">


**NOTE:**
Database tables will be created automatically once the Android Studio Project runs successfully.


## Android Studio Project
1. Open this project in Android Studio.
2. Remember to change the name of the package.
3. Run the project once the gradle sync has terminated. You can find information regarding the emulator version [here](https://github.com/soniabullah1/COMS3009A-Agile-Sprinters/wiki/Local-Machine-Information).  
