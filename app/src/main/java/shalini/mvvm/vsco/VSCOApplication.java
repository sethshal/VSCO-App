package shalini.mvvm.vsco;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

/**
 * Hilt needs the Application class to be overidden in order for dependency injection to work
 */
@HiltAndroidApp
public class VSCOApplication extends Application { }
