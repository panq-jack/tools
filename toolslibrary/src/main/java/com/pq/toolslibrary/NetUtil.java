package com.pq.toolslibrary;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import static com.pq.toolslibrary.TelephoneUtil.SDK_23_WIFI_MAC;

/**
 * created by panqian on 2018/5/3
 * description:
 */

public class NetUtil {


//    public static String getLocalIpAddress(Context context) {
//        try {
//            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); null!=en && en.hasMoreElements();) {
//                NetworkInterface intf = en.nextElement();
//                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
//                    InetAddress inetAddress = enumIpAddr.nextElement();
//                    if (!inetAddress.isLoopbackAddress()) {
//                        return inetAddress.getHostAddress().toString();
//                    }
//                }
//            }
//        } catch (SocketException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }

    // 获取本地IP函数
    public static String getLocalIPAddress() {
        String localIPAddress="";
        try {
            for (Enumeration<NetworkInterface> mEnumeration = NetworkInterface
                    .getNetworkInterfaces(); mEnumeration.hasMoreElements();) {
                NetworkInterface intf = mEnumeration.nextElement();
                for (Enumeration<InetAddress> enumIPAddr = intf
                        .getInetAddresses(); enumIPAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIPAddr.nextElement();
                    // 如果不是回环地址
                    if (!inetAddress.isLoopbackAddress()) {
                        // 直接返回本地IP地址
                        if (inetAddress instanceof Inet4Address) {
                            localIPAddress= inetAddress.getHostAddress().toString();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            localIPAddress="";
        }
        return localIPAddress;
    }

    public static String getNetworkTypeName(Context myContext) {
        String netString = null;
        try {
            ConnectivityManager cm = (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            TelephonyManager tm = (TelephonyManager) myContext.getSystemService(Context.TELEPHONY_SERVICE);
            NetworkInfo[] netinfo = cm.getAllNetworkInfo();
            for (int i = 0; i < netinfo.length; i++) {
                if (netinfo[i].isConnected()) {
                    if (netinfo[i].getTypeName().toUpperCase().contains("MOBILE")) {
                        netString = tm.getNetworkType() + "   , "+netinfo[i].getTypeName();
                    } else if (netinfo[i].getTypeName().toUpperCase().contains("WIFI")) {
                        netString = tm.getNetworkType() +netinfo[i].getTypeName();
                    } else {
                        netString = "UNKNOWN";
                    }
                }
            }
        } catch (Throwable e) {
            netString = "UNKNOWN";
        }
        if (netString == null) {
            netString = "UNKNOWN";
        }

        return netString;

    }


    /**
     * 通用获取WifiMac的方法，api>=23之后，增加新的获取方式
     * @param context
     * @return
     * @throws Exception
     */
    public static String getMacAddressStr(Context context) {
        // 网络权限 access wifi state
        String macAddress = "";
        try {
            if (context == null) {
                throw new NullPointerException("getMacAddressStr(), context is null");
            }
            final WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (null == wifi) {// 没有wifi模块，直接返回空
                throw new IllegalStateException("Can't get WifiManager.");
            }
            final WifiInfo info = wifi.getConnectionInfo();
            macAddress = info.getMacAddress();
            if (Build.VERSION.SDK_INT >= 23 || macAddress.equals(SDK_23_WIFI_MAC)) {
                macAddress = getWifiMacAddressOver23();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return macAddress;
    }

    public static String getWifiMacAddressOver23() {
        try {
            String interfaceName = "wlan0";
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (!intf.getName().equalsIgnoreCase(interfaceName)) {
                    continue;
                }

                byte[] mac = intf.getHardwareAddress();
                if (mac == null) {
                    return "";
                }

                StringBuilder buf = new StringBuilder();
                for (byte aMac : mac) {
                    buf.append(String.format("%02x:", aMac));
                }
                if (buf.length() > 0) {
                    buf.deleteCharAt(buf.length() - 1);
                }
                return buf.toString();
            }
        } catch (Exception ex) {
        } // for now eat exceptions
        return "";
    }



    public static String demo(Context context){
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("NetInfo : \n")
                .append("net type").append("   =").append("\t").append(getNetworkTypeName(context)).append("\n")
                .append("mac address").append("   =").append("\t").append(getMacAddressStr(context)).append("\n")
                .append("ip address").append("   =").append("\t").append(getLocalIPAddress()).append("\n");

        return stringBuffer.toString();
    }
}
