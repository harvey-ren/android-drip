package com.rqphp.publib.util.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;

import java.util.ArrayList;

/**
 * Created by asus on 2018/1/11.
 */

public class PermissionUtil {
    private Activity mContext;

    private ArrayList<PermissionData> requestPermissions = null;

    int SDK_PERMISSION_REQUEST = 127;

    public PermissionUtil(Activity mContext, ArrayList<PermissionData> requestPermissions) {
        this.mContext = mContext;
        this.requestPermissions = requestPermissions;
    }

    @TargetApi(23)
    public void addMustPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<>();

            if (requestPermissions != null && requestPermissions.size()>0) {
                for (int i = 0; i < requestPermissions.size(); i++) {
                    if (requestPermissions.get(i).isMust()) {
                        if (mContext.checkSelfPermission(requestPermissions.get(i).getPermisssionStr()) != PackageManager.PERMISSION_GRANTED) {
                            permissions.add(requestPermissions.get(i).getPermisssionStr());
                        }
                    } else {
                        addOncePermission(permissions, requestPermissions.get(i).getPermisssionStr());
                    }
                }
            }

            if (permissions.size() > 0) {
                mContext.requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }


    @TargetApi(23)
    private boolean addOncePermission(ArrayList<String> permissionsList, String permission) {
        if (mContext.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (mContext.shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }


    public static boolean checkToastPermission(Context context){
        if (Build.VERSION.SDK_INT >= 23) {
            if (Settings.canDrawOverlays(context)) {
                return true;
            } else {
                //若没有权限，提示获取.
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                context.startActivity(intent);
                return false;
            }
        } else {
            //SDK在23以下，不用管.
            return true;
        }
    }

}
