package com.example.umberto.gallerydb.business;

import com.example.umberto.gallerydb.business.interfaces.GenericDataManager;
import com.example.umberto.gallerydb.business.interfaces.GenericGalleryController;
import com.example.umberto.gallerydb.business.interfaces.GenericImageLoader;
import com.example.umberto.gallerydb.business.interfaces.GenericObject;
import com.example.umberto.gallerydb.business.interfaces.GenericServiceLocator;
import com.example.umberto.gallerydb.business.interfaces.GenericSqlConfig;
import com.example.umberto.gallerydb.controller.LoadDataRetainFragmentController;
import com.example.umberto.gallerydb.db.GalleryDatabaseManager;
import com.example.umberto.gallerydb.db.GallerySqlConfig;

/**
 * Created by Umberto Sidoti on 22/06/2015.
 */
public class GalleryServiceLocator implements GenericServiceLocator {

    @Override
    public GenericGalleryController getGalleryControllerImplementation() {
        return new LoadDataRetainFragmentController();
    }

    @Override
    public GenericImageLoader getImageLoaderImplementation() {
        return new GalleryPicassoImageLoader();
    }

    @Override
    public GenericDataManager getDataManagerImplementation() {
        return new GalleryDatabaseManager();
    }

    @Override
    public GenericObject getObjectImplementation() {
        return new GalleryObject();
    }

    @Override
    public GenericSqlConfig getSqlConfig() {
        return new GallerySqlConfig();
    }
}
