# Sunshine

Welcome to the Sunshine App!

This app displays todayʼs weather and a five-day forecast for
user's current location(Atlanta, GA if permissions are declined).
This will be shown in a list and tapping on a day will expand the cell to show more details.


Setting up the environment with Android Studio 3.0 or greater:
 1. Make sure JDK 11 is installed.
 2. Make sure Android Sdk is installed.
 3. Make sure that $ANDROID_HOME is set.
 4. Make sure Gradle is installed.

More environment setup information can be found at
https://developer.android.com/studio/intro/studio-config


Running the application:
1. Plug in device or initialize emulator.
2. click the play button on the top of Android Studio IDE.

More information regarding running the application can be found at
https://developer.android.com/training/basics/firstapp/running-app


Testing the application:
1. Open the project tab on the left side of Android Studio IDE.
2. Find app/src/test/java/com/example/sunshine within the project tab.
3. Right click on the directory and select 'Run Tests' option.

More information regarding testing the application can be found at
https://developer.android.com/training/testing/unit-testing/local-unit-tests#run

Dependencies:
- androidx
   - core
   - appcompat
   - constraintlayout
   - viewmodel
   - navigation fragment
   - navigation ui
   - databinding
   - swiperefreshlayout
- rxjava
- rxandroid
- retrofit
- play services location
