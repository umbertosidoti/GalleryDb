package com.example.umberto.gallerydb.business.interfaces;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by Umberto Sidoti on 22/06/2015.
 */
public interface GenericGalleryController {
    String TAG_CONTROLLER = "uiController";

    void start();

    void onAddButtonPressed(Activity activity);

    void onResultReceived(int requestCode, int resultCode, Intent data);

    void onDeleteElementRequest(ArrayList<Integer> positionsToRemove);
}
