# Image_loader_2016
Requarments from app

1. Get json from http://54.172.36.94:8080/ttt/test.json (which contains only img urls)
2. Shows those images in list. (the ui is not important)
3. all images should be saved in local storage and if an image already downloaded the app should not make new request for downloading image, instead it should get and show image from local storage. 

In this project used: RxJava, RxAndroid, Retrofit, EventBus
Universal Image Loader, Photos was "disk cache" with default path to  /storage/emulated/0/Android/data/com.accontech.imageloader/cache։
