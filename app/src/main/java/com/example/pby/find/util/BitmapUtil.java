package com.example.pby.find.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.pby.find.control.BitmapCache;

import java.io.IOException;

public class BitmapUtil {
    private static BitmapCache mBitmapCache = null;

    public static Bitmap getBitmap(Context context, String key) {
        if (mBitmapCache == null) {
            mBitmapCache = new BitmapCache();
        }
        Bitmap bitmap = null;
        if (mBitmapCache.contains(key)) {
            bitmap = mBitmapCache.get(key);
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inDensity = 350;
            options.inScaled = true;
            try {
                bitmap = BitmapFactory.decodeStream(context.getAssets().open("expression/" + key), null, options);
                mBitmapCache.put(key, bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public static void clearBitmapCache() {
        if (mBitmapCache != null) {
            mBitmapCache.recycle();
        }
    }

}