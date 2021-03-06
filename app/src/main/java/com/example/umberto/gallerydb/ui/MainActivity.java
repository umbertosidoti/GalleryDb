package com.example.umberto.gallerydb.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.umberto.gallerydb.GalleryApplication;
import com.example.umberto.gallerydb.R;
import com.example.umberto.gallerydb.business.interfaces.GenericControllerListener;
import com.example.umberto.gallerydb.business.interfaces.GenericGalleryController;
import com.example.umberto.gallerydb.business.interfaces.GenericObject;
import com.example.umberto.gallerydb.business.interfaces.RecycleViewFragment;
import com.example.umberto.gallerydb.business.interfaces.RecyclerViewFragmentListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        GenericControllerListener, RecyclerViewFragmentListener {

    private GenericGalleryController controller;
    private RecycleViewFragment recycleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        recycleFragment = (RecycleViewFragment) fm.findFragmentById(R.id.fragment);
        controller = (GenericGalleryController) fm.findFragmentByTag(GenericGalleryController.TAG_CONTROLLER);

        if (controller == null)
            createController(fm);
    }

    private void createController(FragmentManager fm) {
        controller = GalleryApplication.getInstance().getServiceLocator().
                getGalleryControllerImplementation();
        if (!(controller instanceof Fragment))
            throw new IllegalStateException("Controller must be instance of fragment");
        fm.beginTransaction()
                .add((Fragment) controller, GenericGalleryController.TAG_CONTROLLER)
                .commit();

        fm.executePendingTransactions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataReady(ArrayList<GenericObject> data) {
        recycleFragment.onDataReceived(data);
    }

    @Override
    public void onAddButtonClick() {
        controller.onAddButtonPressed(this);
    }

    @Override
    public void onRecycleViewReady() {
        controller.start();
    }

    @Override
    public void onActionModeDeleteRequest(ArrayList<Integer> positionToRemove) {
        controller.onDeleteElementRequest(positionToRemove);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        controller.onResultReceived(requestCode, resultCode, data);
    }
}
