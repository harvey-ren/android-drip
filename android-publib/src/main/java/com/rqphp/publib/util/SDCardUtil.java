package com.rqphp.publib.util;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by Harvey on 2017/10/17.
 */

public class SDCardUtil {


    /**
     * Sdcard是否存在
     *
     * @return
     */
    public static boolean isExistSDCard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 查看SD卡的剩余空间
     *
     * @return
     */
    public static long getSDCardAvailableSize() {

        //取得SD卡文件路径

        File path = Environment.getExternalStorageDirectory();

        StatFs sf = new StatFs(path.getPath());

        //获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();

        //空闲的数据块的数量
        long freeBlocks = sf.getAvailableBlocks();

        //返回SD卡空闲大小

        //return freeBlocks * blockSize;  //单位byte

        //return (freeBlocks * blockSize)/1024;   //单位KB

        return (freeBlocks * blockSize) / 1024 / 1024; //单位MB
    }


}
