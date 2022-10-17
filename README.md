
Libraries used for the application
------------------------------------
* [AppCompat, CardView, RecyclerView an Material](http://developer.android.com/intl/es/tools/support-library/index.html)
* [Data binding](https://erikjhordan-rey.github.io/blog/2015/12/15/ANDROID-databinding-android.html)
* [Retrofit 2](http://square.github.io/retrofit/)
* [RxJava & RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [junit, mockito](http://mockito.org/)
* [Glide](https://bumptech.github.io/glide/)

1. What features & aspects of the app did you focus on and why? 
   1. I focused mainly on setting up a foundation for the architecture to make it extensible and prioritized on the 
       separation on concerns with the application.
   2. Used Glide as the image library. Customized caching of images with it.
   3. Used the MVVM architecture with dependency injection to make testing easier and avoid other object initializing objects unless needed.
   4. The user can refresh application by swiping
   5. Used data binding and Mutable live data to auto update views and avoid crashes on device orientation changes. This also makes the code cleaner
   6. Used the cardview library for the user profile

    
2. What sort of trade offs did you make to fit the constraints of this assignment? (you’re
   limited by time, amount of data provided, etc.). 
   1. Could have added extra caching to the network calls by storing hits in a local DB. I used retrofit
      that has some level of built in caching already. This would give the application of working in an offline mode too.
   2. I added very basic unit tests. Given more time would have definitely wanted to add full coverage.
   3. I would have like to define different screen sizes to accommodate for different screen sizes. I did add landscape mode
   4. I would have liked to add custom exception classes to make the error handling more elegant.
   
 

