package com.rqphp.publib.util;

import android.media.MediaMetadataRetriever;
import android.text.TextUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件工具类
 */
public class FileUtil {

    //获取文件大小

    /**
     * 判断文件是否是视频文件
     *
     * @param format
     * @return
     */
    public static boolean isVideoFormat(String format) {

        if (TextUtils.isEmpty(format)) {
            return false;
        }

        Map<String, String> videoFormat = new HashMap<>();
        videoFormat.put("MP4", "video/mp4");
        videoFormat.put("3GP", "video/3gpp");
        videoFormat.put("3G2", "video/3gpp2");
        videoFormat.put("WMV", "video/x-ms-wmv");
        return videoFormat.containsValue(format);
    }


    /**
     * 获取文件格式
     *
     * @param filePath
     * @return
     */
    public static String getFileFormatByFilePath(String filePath) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        String mime = "text/plain";
        if (!TextUtils.isEmpty(filePath)) {
            try {
                mediaMetadataRetriever.setDataSource(filePath);
                mime = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
            } catch (IllegalStateException e) {
                return mime;
            } catch (IllegalArgumentException e) {
                return mime;
            } catch (RuntimeException e) {
                return mime;
            }
        }
        return mime;
    }


    /**
     * 获取文件的后缀名
     *
     * @param fileName
     * @return
     */
    public static String getFileSuffix(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return "";
        } else {
            int position = fileName.lastIndexOf(".");
            String fileSuffix = fileName.substring(position + 1, fileName.length());
            return fileSuffix;
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param fileName
     * @return
     */
    public static boolean isFileExists(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return false;
        } else {
            File file = new File(fileName);
            return file.isFile() && file.exists();
        }
    }

    /**
     * 删除指定文件
     *
     * @param fileName
     */
    public static void deleteFile(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return;
        }
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}
