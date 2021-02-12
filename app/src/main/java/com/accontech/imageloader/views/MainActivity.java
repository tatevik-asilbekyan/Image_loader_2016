package com.accontech.imageloader.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.accontech.imageloader.R;
import com.accontech.imageloader.adapters.ImagesAdapter;
import com.accontech.imageloader.app.ImageLoaderApplication;
import com.accontech.imageloader.controllers.MainActivityController;
import com.accontech.imageloader.messages.EmptyMessages;
import com.accontech.imageloader.messages.ImagesMessage;

public class MainActivity extends AppCompatActivity {

    protected ImageLoaderApplication application;
    private MainActivityController activityController;
    private ListView viewImages;
    private ImagesAdapter imagesAdapter;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        application = (ImageLoaderApplication) getApplication();

        activityController = new MainActivityController(application);

        application.eventBus.register(this);
        application.eventBus.register(activityController);

        application.eventBus.post(new EmptyMessages.GetImagesMessage());

        init();
    }

    private void init(){
        mProgressBar = (ProgressBar) findViewById(R.id.progress_spinner_detail);
        viewImages = (ListView) findViewById(R.id.listview_images);
        imagesAdapter = new ImagesAdapter(application.getApplicationContext());
        viewImages.setAdapter(imagesAdapter);
        viewImages.setEmptyView(findViewById(android.R.id.empty));
    }

    @Override
    protected void onDestroy() {
        application.eventBus.unregister(this);
        application.eventBus.unregister(activityController);
        super.onDestroy();
    }

    @SuppressWarnings("unused")
    public void onEvent(final ImagesMessage imagesMessage){
        imagesAdapter.updateList(imagesMessage.images);
    }


    @SuppressWarnings("unused")
    public void onEventMainThread(final EmptyMessages.ShowProgress event) {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @SuppressWarnings("unused")
    public void onEventMainThread(final EmptyMessages.HideProgress event) {
        mProgressBar.setVisibility(View.GONE);
    }
}
