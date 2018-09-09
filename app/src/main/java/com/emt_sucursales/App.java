package com.emt_sucursales;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static App app;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        App.context = getApplicationContext();
    }

    public static App getApp() {
        return app;
    }

    public static Context getAppContext() {
        return App.context;
    }
}
