package com.school.noticeboard.utils;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    private static final String TAG = "Utils";
    public static final String POSITION = "position";

    public static String getJsonFromAssets(String fileName) {
        String jsonString = null;
        InputStream is = null;
        try {
            is = MyApplication.getAppContext().getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            Log.e(TAG, "getJsonFromAssets: Exception handled ===");
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                Log.e(TAG, "getJsonFromAssets: Exception handled ===");
            }
        }

        return jsonString;
    }

}
