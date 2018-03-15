package com.pq.tools;

import android.app.Application;
import android.content.Context;

/**
 * Created by panqian on 2018/3/15.
 */

public class App extends Application {

    public static Application app;
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }

    public static Application getInstance(){
        return app;
    }


}
