package com.shj.y18shj;

import android.content.Context;

import com.lidroid.xutils.BitmapUtils;

/**
 * Created by jash
 * Date: 15-7-17
 * Time: 下午5:11
 */
public class BitmapHelper {
    private static BitmapUtils utils;
    public static void init(Context context){
        utils = new BitmapUtils(context);
        utils.configDefaultBitmapMaxSize(50, 50);//设置图片的大小
    }

    public static BitmapUtils getUtils() {
        return utils;
    }
}
