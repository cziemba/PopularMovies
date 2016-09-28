package com.udacity.czziemba.popularmovies.application;

import android.app.Application;

import com.udacity.czziemba.popularmovies.BuildConfig;

import timber.log.Timber;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
