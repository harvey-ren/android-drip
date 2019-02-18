package com.rqphp.publib.net;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.google.protobuf.GeneratedMessage;
import com.rqphp.publib.R;
import com.rqphp.publib.dailog.LoadingDialog;
import com.rqphp.publib.listener.TCPConnectListener;
import com.rqphp.publib.util.LogUtil;
import com.rqphp.publib.util.NetworkUtil;
import com.rqphp.publib.util.ThreadManageUtil;
import com.rqphp.publib.util.ToastUtil;
import com.rqphp.publib.util.Utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;


/**
 * Created by Harvey on 2017/8/16.
 */

public class TCPConnect {

    private DataInputStream dataInputStream;
    private OutputStream outputStream;
    private Socket mSocket;
    private TCPConnectListener mListener;
    private GeneratedMessage mMessage;
    private GeneratedMessage mAddMessage;
    private int mMessageType;
    private static final int HEADER_LENGTH = 56;
    private int mVersion = 1;
    private int mHeaderOffSet = 0;
    private int mActionCode = 0;
    private int mResultCode = 0;
    private int mSequence = 0;
    private int mCrc = 0;

    public static final int START = 0;
    public static final int SUCCESS = 1;
    public static final int FAIL = 2;
    public static final int NO_NETWORK = 3;
    public static final int NETWORK_ERROR = 4;
    public static final int CONNECT_ERROR = 5;

    private String mAddress = "";

    private int mPort;


    private LoadingDialog mDialog;

    /**
     * 是否显示加载对话框
     */
    private boolean mIsShow = false;

    /**
     * 是否ping ip
     */
    private boolean mIsPingIp = true;

    private Context mContext;


    private String mToken = "";


    /**
     * 构造函数
     *
     * @param context
     * @param listener
     */
    public TCPConnect(Context context, TCPConnectListener listener) {
        mContext = context;
        mListener = listener;
    }

    /**
     * 设置ip地址和端口号
     *
     * @param address
     * @param port
     */
    public void setIpAddressAndPort(String address, int port) {
        mAddress = address;
        mPort = port;
    }


    /**
     * 设置消息类型
     *
     * @param messageType
     */
    public void setMessage(int messageType) {
        this.setMessage(messageType, null);
    }

    /**
     * 设置消息类型和消息
     *
     * @param messageType
     * @param message
     */
    public void setMessage(int messageType, GeneratedMessage message) {
        mMessageType = messageType;
        mMessage = message;
    }

    public void addMessage(int messageType, GeneratedMessage message) {
        mMessageType = messageType;

    }


    /**
     * 设置对话框的提示信息
     *
     * @param tip
     */
    public void setDialogTip(String tip) {
        if (!TextUtils.isEmpty(tip)) {
            mIsShow = true;
        }
        if (mIsShow) {
            mDialog = new LoadingDialog(mContext);
            mDialog.setTipText(tip);
        }
    }

    /**
     * set token
     *
     * @param token
     */
    public void setToken(String token) {
        if (!TextUtils.isEmpty(token)) {
            mToken = token;
        }
    }

    /**
     * 是否ping ip
     *
     * @param isPingIp
     */
    public void setIsPingIp(boolean isPingIp) {
        mIsPingIp = isPingIp;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NO_NETWORK:
                    ToastUtil.shortShow(mContext, mContext.getString(R.string.no_connect_network));
                    break;

                case NETWORK_ERROR:
                    break;

                case START:
                    showDialog();
                    break;

                case SUCCESS:
                    dismissDialog();
                    setSuccessCallBack((byte[]) msg.obj);
                    break;

                case FAIL:
                    dismissDialog();
                    setFailCallBack();
                    break;

                case CONNECT_ERROR:
                    dismissDialog();
                    setFailCallBack();
                    //                    mContext.startService(new Intent(mContext, PingService.class));
                    break;
            }

        }
    };

    private boolean mIsNeedToken = true;

    /**
     * 设置是否需要token
     *
     * @param isNeedToken
     */
    public void setIsNeedToken(boolean isNeedToken) {
        mIsNeedToken = isNeedToken;
    }


    /**
     * 开始一个新的线程
     */
    public void start() {

        ThreadManageUtil.getCacheThreadExecutor().execute(new TCPConnectRunnable());
    }

    class TCPConnectRunnable implements Runnable {

        @Override
        public void run() {

            if (!NetworkUtil.isConntectNetWork(mContext)) {
                mHandler.sendEmptyMessage(NO_NETWORK);
                return;
            }

            mHandler.sendEmptyMessage(START);
            try {
                if (TextUtils.isEmpty(mAddress) || mPort == 0) {
                    mHandler.sendEmptyMessage(FAIL);
                    return;
                }
                InetAddress ipAddress = InetAddress.getByName(mAddress);

                LogUtil.error("ipAddress=====" + mAddress);

                mSocket = new Socket(ipAddress, mPort);


                LogUtil.error("mPort=====" + mPort);

                if (mSocket == null) {
                    mHandler.sendEmptyMessage(FAIL);
                    return;
                }
                /**
                 * 客户端socket在接收数据时，有两种超时：1.连接服务器超时，即连接超时；2.连接服务器成功后，接收服务器数据超时，即接收超时
                 * 设置socket 读取数据流的超时时间
                 */
                mSocket.setSoTimeout(10 * 1000);

                /***
                 * 发送数据包，默认为false，即客户端发送数据采用Nagle算法；
                 * 但是对于实时交互性高的程序，建议其改为true，即关闭Nagle算法，客户端每发送一次数据，无论数据包大小都会将这些数据发送出去
                 */
                mSocket.setTcpNoDelay(true);

                ByteBuffer reqByteBuffer = getRequestByteBuffer();
                outputStream = mSocket.getOutputStream();
                outputStream.write(reqByteBuffer.array());
                outputStream.flush();

                dataInputStream = new DataInputStream(mSocket.getInputStream());

                int responseLen = HEADER_LENGTH;

                byte[] respByteBuffer = new byte[responseLen];

                int len = 0;
                while (len < responseLen) {
                    int readSize = dataInputStream.read(respByteBuffer, len, responseLen - len);
                    if (readSize == -1) {
                        break;
                    }
                    len += readSize;
                }

                if (len == responseLen) {
                    ByteBuffer byteBuffer = ByteBuffer.wrap(respByteBuffer);

                    int version = byteBuffer.getInt();
                    int headerOffset = byteBuffer.getInt();
                    int bodyLen = byteBuffer.getInt();
                    int messageType = byteBuffer.getInt();
                    int actionCode = byteBuffer.getInt();
                    int resultCode = byteBuffer.getInt();
                    int sequence = byteBuffer.getInt();
                    long timeStamp = byteBuffer.getLong();
                    int crc = byteBuffer.getInt();

                    LogUtil.error("version===" + version);
                    LogUtil.error("headerOffset===" + headerOffset);
                    LogUtil.error("bodyLen===" + bodyLen);
                    LogUtil.error("messageType===" + messageType);
                    LogUtil.error("actionCode===" + actionCode);
                    LogUtil.error("resultCode===" + resultCode);
                    LogUtil.error("sequence===" + sequence);
                    LogUtil.error("timeStamp===" + timeStamp);
                    LogUtil.error("crc===" + crc);

                    if (bodyLen > 0) {

                        byte[] data = new byte[bodyLen];

                        int length = 0;
                        while (length < bodyLen) {
                            int readCount = dataInputStream.read(data, length, bodyLen - length);
                            if (readCount == -1) {
                                break;
                            }
                            length += readCount;
                        }
                        if (length == bodyLen) {
                            Message message = mHandler.obtainMessage(SUCCESS, data);
                            mHandler.sendMessage(message);
                        } else {
                            mHandler.sendEmptyMessage(FAIL);
                        }
                    } else {
                        mHandler.sendEmptyMessage(FAIL);
                    }
                } else {
                    mHandler.sendEmptyMessage(FAIL);
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
                LogUtil.error("e1====" + e);
                mHandler.sendEmptyMessage(FAIL);
            } catch (SocketException e) {
                e.printStackTrace();
                LogUtil.error("e2===" + e.toString());
                if (mIsPingIp) {
                    mHandler.sendEmptyMessage(CONNECT_ERROR);
                } else {
                    mHandler.sendEmptyMessage(FAIL);
                }
            } catch (IOException e) {
                e.printStackTrace();
                LogUtil.error("e3===" + e);
                mHandler.sendEmptyMessage(FAIL);
            } finally {
                if (mSocket != null) {
                    try {
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        mSocket.close();
                    } catch (Exception e) {
                        LogUtil.error("net work exception===" + e.toString());
                    }
                }
            }

        }
    }


    private ByteBuffer getRequestByteBuffer() {
        long timeStamp = System.currentTimeMillis() / 1000;

        int bodyLength = mMessage == null ? 0 : mMessage.getSerializedSize();

        int requestLen = bodyLength + HEADER_LENGTH;

        byte[] requestByteBuffer = new byte[requestLen];
        ByteBuffer byteBuffer = ByteBuffer.wrap(requestByteBuffer);
        byteBuffer.put(Utils.intToBytes(mVersion));
        byteBuffer.put(Utils.intToBytes(mHeaderOffSet));
        byteBuffer.put(Utils.intToBytes(bodyLength));
        byteBuffer.put(Utils.intToBytes(mMessageType));
        byteBuffer.put(Utils.intToBytes(mActionCode));
        byteBuffer.put(Utils.intToBytes(mResultCode));
        byteBuffer.put(Utils.intToBytes(mSequence));
        byteBuffer.put(Utils.longToBytes(timeStamp));
        byteBuffer.put(Utils.intToBytes(mCrc));


        if (!TextUtils.isEmpty(mToken) && mIsNeedToken) {
            byte[] token = Utils.hexString2Bytes(mToken);
            byteBuffer.put(token);
        } else {
            byte[] emptyToken = new byte[16];
            byteBuffer.put(emptyToken);
        }
        if (mMessage != null) {
            byteBuffer.put(mMessage.toByteArray());
        }
        return byteBuffer;
    }


    /**
     * fail call back
     */
    private void setFailCallBack() {
        if (mListener != null) {
            mListener.onFail();
        }
    }


    /**
     * success call back
     *
     * @param data
     */
    private void setSuccessCallBack(final byte[] data) {
        if (mListener != null) {
            mListener.onSuccess(data);
        }
    }

    private void showDialog() {
        if (mContext instanceof Activity) {
            Activity activity = (Activity) mContext;
            if (mIsShow && mDialog != null && !mDialog.isShowing() && !activity.isFinishing()) {
                mDialog.show();
            }
        }
    }


    private void dismissDialog() {
        if (mIsShow && mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

}
