package com.pq.toolslibrary;

import android.content.Context;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

/**
 * 提供dp，sp，px的相互转化
 */

public class DPIUtil {
    private static Display defaultDisplay;
    private static Point size=null;


    /**
     * 取得getDefaultDisplay的值
     */
    private static Display getDefaultDisplay(Context context) {
        if (null == defaultDisplay) {
            WindowManager systemService = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            defaultDisplay = systemService.getDefaultDisplay();
        }
        return defaultDisplay;
    }

    /**
     * 获取屏幕宽度(in px)
     *
     * @return 屏幕宽度
     */
    public static int getWidth(Context context) {
        if (size == null) {
            synchronized (DPIUtil.class) {
                if (size == null) {
                    getPxSize(context);
                }
            }
        }
        return size.x;
    }

    /**
     * 获取屏幕高度(in px)
     *
     * @return 屏幕高度
     */
    public static int getHeight(Context context){
        if (null==size){
            synchronized (DPIUtil.class){
                if (null==size){
                    getPxSize(context);
                }
            }
        }
        return size.y;
    }

    public static int px2sp(Context context,float px){
        final float scaledDensity=context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(px/scaledDensity+0.5f);
    }

    public static int px2dp(Context context,float px){
        final float density=context.getResources().getDisplayMetrics().density;
        return (int)(px/density+0.5f);
    }

    public static int sp2px(Context context,float sp){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,context.getResources().getDisplayMetrics());
    }

    public static int dp2px(Context context,float dp){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }





    /**
     * 设置屏幕高度和宽度（in px）
     * 设定 outSize对象值
     */
    private static void getPxSize(Context context) {

        Display display = getDefaultDisplay(context);
        size = new Point();
        display.getSize(size);
    }


    public static String demo(Context context){
        int value=100;
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("screen:   ")
                .append("width = ").append(getWidth(context)).append("\t")
                .append("height = ").append(getHeight(context)).append("\n")

                .append("density = ").append(context.getResources().getDisplayMetrics().density).append("\t")
                .append("scaledDensity = ").append(context.getResources().getDisplayMetrics().scaledDensity).append("\n")

                .append(value).append(" dp = ").append(dp2px(context,value)).append("px").append("\n")
                .append(value).append(" sp = ").append(sp2px(context,value)).append("px").append("\n")
                .append(value).append(" px = ").append(px2dp(context,value)).append("dp").append("\n")
                .append(value).append(" px = ").append(px2sp(context,value)).append("sp").append("\n");
        return stringBuilder.toString();

    }
}
