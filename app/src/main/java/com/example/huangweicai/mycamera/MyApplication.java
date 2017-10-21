package com.example.huangweicai.mycamera;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by hermithermit on 16/3/3.
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initAppDir();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    public static MyApplication getInstance(){
        return mInstance;
    }


    /* 文件缓存的目录 */
    public String mAppDir;
    public String mPicturesDir;
    public String mVoicesDir;
    public String mVideosDir;
    public String mFilesDir;

    private void initAppDir() {
        try {
            File file = getExternalFilesDir(null);
            if (!file.exists()) {
                file.mkdirs();
            }
            mAppDir = file.getAbsolutePath();

            file = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            if (!file.exists()) {
                file.mkdirs();
            }
            mPicturesDir = file.getAbsolutePath();

            file = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
            if (!file.exists()) {
                file.mkdirs();
            }
            mVoicesDir = file.getAbsolutePath();

            file = getExternalFilesDir(Environment.DIRECTORY_MOVIES);
            if (!file.exists()) {
                file.mkdirs();
            }
            mVideosDir = file.getAbsolutePath();

            file = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            if (!file.exists()) {
                file.mkdirs();
            }
            mFilesDir = file.getAbsolutePath();
        }catch (Exception e){

        }

    }

}
