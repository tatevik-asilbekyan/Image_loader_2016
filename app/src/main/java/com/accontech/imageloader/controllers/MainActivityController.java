package com.accontech.imageloader.controllers;

import com.accontech.imageloader.app.ApiManager;
import com.accontech.imageloader.app.ImageLoaderApplication;
import com.accontech.imageloader.messages.EmptyMessages;
import com.accontech.imageloader.messages.ImagesMessage;
import com.accontech.imageloader.models.Images;

import rx.Subscriber;

public class MainActivityController {

    protected ImageLoaderApplication application;

    public MainActivityController(ImageLoaderApplication application) {
        this.application = application;
    }

    @SuppressWarnings("unused")
    public void onEvent(final EmptyMessages.GetImagesMessage getImagesMessage) {

        application.eventBus.post(new EmptyMessages.ShowProgress());

        ApiManager.getImages(new Subscriber<Images>() {
            @Override
            public void onCompleted() {
                application.eventBus.post(new EmptyMessages.HideProgress());
            }

            @Override
            public void onError(Throwable e) {
                application.eventBus.post(new EmptyMessages.HideProgress());
            }

            @Override
            public void onNext(Images images) {
                application.eventBus.post(new ImagesMessage(images.images));
            }
        });
    }
}
