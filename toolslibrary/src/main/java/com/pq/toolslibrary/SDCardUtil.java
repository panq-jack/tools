package com.pq.toolslibrary;

import android.os.Environment;

/**
 * Created by panqian on 2018/5/8.
 */

public class SDCardUtil {
    // sd 卡是否可用
    private boolean isExternalStorageAvailable=false;
    // sd卡写操作是否可用
    private boolean isExternalStorageWritable=false;

    String state=Environment.getExternalStorageState();

    public void checkSdCardState(){
        if(Environment.MEDIA_MOUNTED.equals(state)){
            isExternalStorageAvailable = isExternalStorageWritable =true;
        }else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            isExternalStorageAvailable=true;
            isExternalStorageWritable = false;
        }else {
            isExternalStorageAvailable = isExternalStorageWritable =false;
        }
    }



}
