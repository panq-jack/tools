package com.pq.toolslibrary;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;

/**
 * Created by pan on 2018/3/17.
 */

public class MetadataUtil {

    public MetadataUtil() {
    }

    public static Bundle getApplicationMetaData(Context context) {
        if(context == null) {
            return null;
        } else {
            Bundle bundle = null;
            ApplicationInfo applicationInfo = null;

            try {
                applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            } catch (PackageManager.NameNotFoundException exception) {
                exception.printStackTrace();
            }

            if(null != applicationInfo) {
                bundle = applicationInfo.metaData;
            }

            return bundle;
        }
    }
    public static String getMetaDataValue(Context context,String key){
        String value="unDefined";
        Bundle bundle=getApplicationMetaData(context);
        if (null!=bundle && !TextUtils.isEmpty(key)&& bundle.containsKey(key)){
            value=bundle.getString(key);
        }
        return value;
    }




    public static String demo(Context context){
        StringBuffer stringBuffer=new StringBuffer();
        String key="test";
        stringBuffer.append("metadata  find: \t")
                .append(key).append("   =").append("\t")
                .append(getMetaDataValue(context,key));

        return stringBuffer.toString();
    }

}
