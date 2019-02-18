package com.rqphp.publib.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.rqphp.publib.dailog.remind.SweetAlertDialog;

/**
 * Created by asus on 2018/3/26.
 */

public class LocationUtil {
    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }


    public static void openLocation(final Activity activity, final int requestCode){
        boolean locationAble = isLocationEnabled(activity);
        if (!locationAble){
            if (!locationAble){
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(activity,SweetAlertDialog.NORMAL_TYPE);
                sweetAlertDialog.setTitleText("提示");
                sweetAlertDialog.setContentText("需要开启定位服务");
                sweetAlertDialog.setConfirmText("确定");
                sweetAlertDialog.setCancelText("取消");
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        // 转到手机设置界面，用户设置GPS
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        activity.startActivityForResult(intent, requestCode); // 设置完成后返回到原来的界面
                    }
                });
                sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                });

                sweetAlertDialog.show();
            }
        }
    }

}
