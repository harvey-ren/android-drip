package com.rqphp.publib.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by Harvey on 2017/7/25.
 */

public class ImageUtil {

    /**
     * file whether  is image
     *
     * @param fileName
     * @return
     */
    public static boolean isImage(String fileName) {
        String fn = fileName.toLowerCase();
        return fn.endsWith(".jpg") || fn.endsWith(".jpeg") || fn.endsWith(".png") || fn.endsWith(".gif");
    }


    /**
     * 获取feel相册的绝对路径
     *
     * @param context
     * @return
     */
    public static String getImageSavePath(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            String path = Environment.getExternalStorageDirectory() + File.separator + "kt" + File.separator;
            File file = new File(path);
            try {
                if (!file.exists()) {
                    file.mkdirs();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return path;
        } else {
            String path;
            File cacheDir = context.getCacheDir();
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            path = cacheDir.getAbsolutePath();
            return path;
        }
    }


}
