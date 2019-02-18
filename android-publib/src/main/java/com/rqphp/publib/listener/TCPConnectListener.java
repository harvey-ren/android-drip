package com.rqphp.publib.listener;

/**
 * Created by Harvey on 2017/8/16.
 */

public interface TCPConnectListener {

    /**
     * @param data
     */
    void onSuccess(byte[] data);


    /**
     *
     */
    void onFail();

}
