package com.example.pby.find.control;

import android.graphics.Bitmap;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BitmapCache {

    private LinkedHashMap<String, Bitmap> map = null;

    public BitmapCache() {
        map = new LinkedHashMap<>(10, 0.75f, true);
    }

    public void put(String key, Bitmap bitmap) {
        map.put(key, bitmap);
    }

    public Bitmap get(String key) {
        return map.get(key);
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public void recycle() {
        try {
            while (!map.isEmpty()) {
                Map.Entry<String, Bitmap> entry = (Map.Entry<String, Bitmap>) map.getClass().getMethod("eldest").invoke(map);
                entry.getValue().recycle();
                map.remove(entry.getKey());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}