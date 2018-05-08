package com.pq.toolslibrary;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * created by panqian on 2018/5/3
 * description:
 */

public class TelephoneUtil {

    static final String SDK_23_WIFI_MAC = "02:00:00:00:00:00";

    /**
     * 取得SimSerialNumber
     */
    public static String getSimSerialNumber(Context context) {
        //  phonestate权限
        try {
            TelephonyManager tm = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getSimSerialNumber();
        }catch (Throwable e){
            return "";
        }
    }

    public static String getIMSI(Context context) {
        //  phonestate权限
        try {
            TelephonyManager mTelephonyMgr = (TelephonyManager)context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
            String imsi = mTelephonyMgr.getSubscriberId();
            return imsi;
        }catch (Throwable e){
            return "";
        }
    }


    /**
     * 取得DeviceId
     */
    public static String getDeviceId(Context context) {
            //phonestate权限
        try {
            TelephonyManager tm = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getDeviceId();
        }catch (Throwable e){
            return "";
        }
    }
    // 获取双卡双待 设备号
    public static String getDeviceIds(Context context){
        TelephonyManager telephony = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        Class<?> telephonyClass;
        Object result = null;
        Object result0 = null;
        Object result1 = null;
        try {
            telephonyClass = Class.forName(telephony.getClass().getName());
            Method m1 = telephonyClass.getMethod("getDeviceId");
            Method m2 = telephonyClass.getMethod("getDeviceId", new Class[]{int.class});
            result = m1.invoke(telephony);
            result0 = m2.invoke(telephony, 1);
            result1 = m2.invoke(telephony, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String result=telephony.getSimSerialNumber();
//        String result1=telephony.getSubscriberId(1);

        Log.i("ppp", " getSimSerialNumber : " + telephony.getSimSerialNumber() + "\n"
                + " result : " + result + "\n"
                + " result0 : " + result0 + "\n"
                + " result1 : " + result1 + "\n");

        return result+"\t"+result0+"\t"+result1;
    }

    // 获取双卡双待 SIM 卡序列号
    public static String getSimSerialNumbers(Context context) {
        TelephonyManager telephony = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        Class<?> telephonyClass;
        Object result = null;
        Object result0 = null;
        Object result1 = null;
        try {
            telephonyClass = Class.forName(telephony.getClass().getName());
            Method m1 = telephonyClass.getMethod("getSimSerialNumber");
            Method m2 = telephonyClass.getMethod("getSimSerialNumber", new Class[]{int.class});
            result = m1.invoke(telephony);
            result0 = m2.invoke(telephony, 1);
            result1 = m2.invoke(telephony, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String result=telephony.getSimSerialNumber();
//        String result1=telephony.getSubscriberId(1);

        Log.i("ppp", " getSimSerialNumber : " + telephony.getSimSerialNumber() + "\n"
                + " result : " + result + "\n"
                + " result0 : " + result0 + "\n"
                + " result1 : " + result1 + "\n");

        return result+"\t"+result0+"\t"+result1;
    }



    public static String demo(Context context){
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("PhoneState : \n")
                .append("deviceId").append("   =").append("\t").append(getDeviceId(context)).append("\n")

                .append("deviceId all").append("   =").append("\t").append(getDeviceIds(context)).append("\n")

                .append("sim 序列号").append("   =").append("\t").append(getSimSerialNumber(context)).append("\n")

                .append("sim 序列号all").append("   =").append("\t").append(getSimSerialNumbers(context)).append("\n");

//                .append("sim IMSI").append("   =").append("\t").append(getIMSI(context)).append("\n");

//                .append("mac address").append("   =").append("\t").append(getMacAddressStr(context)).append("\n");

        return stringBuffer.toString();
    }
}
