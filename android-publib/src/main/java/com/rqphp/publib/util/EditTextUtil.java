package com.rqphp.publib.util;

import android.content.Context;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;

/**
 * Created by Harvey on 2017/7/27.
 */

public class EditTextUtil {

    public static int EDIT_NO_LIMIT = -1;


    /**
     * get text string
     *
     * @param editText
     * @return
     */
    public static String getTextString(EditText editText) {
        if (editText == null)
            return "";
        return editText.getText().toString().trim();
    }

    /**
     * text whether is empty
     * @param editText
     * @return
     */
    public  static  boolean textIsEmpty(EditText editText){
        return TextUtils.isEmpty(getTextString(editText));
    }

    /**
     * set cursor to end
     *
     * @param editText
     */
    public static void setCursorToEnd(EditText editText) {
        if (editText != null) {
            editText.setSelection(editText.getText().length());
        }
    }

    /**
     * 隐藏密码
     *
     * @param editText
     */
    public static void hidePassword(EditText editText) {
        if (editText != null) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    /**
     * 显示密码
     *
     * @param editText
     */
    public static void showPassword(EditText editText) {
        if (editText != null) {
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }


    /**
     * 输入限制
     */
    public static boolean inputLimit(Context context, int minLength, int maxLength, EditText content, String RemindStr) {
            String contentStr = "";
            if (content != null) {
                contentStr = content.getText().toString().trim();
            }
            int StrLenth = contentStr.length();
            if (contentStr.length() == 0) {
                ToastUtil.shortShow(context, RemindStr + "不能为空");
                return false;
            } else if (minLength != EDIT_NO_LIMIT && minLength > StrLenth) {
                ToastUtil.shortShow(context, RemindStr + "不能小于" + minLength + "位");
                return false;
            } else if (maxLength != EDIT_NO_LIMIT && maxLength < StrLenth) {
                ToastUtil.shortShow(context, RemindStr + "不能大于" + "位");
                return false;
            } else {
                return true;
            }
    }

    public boolean inputLimitWithStr(Context context, int minLength, int maxLength, String contentStr, String RemindStr,String[] strs ){
                int StrLenth = contentStr.length();
                if(contentStr.length() == 0){
                    ToastUtil.shortShow(context,RemindStr+"不能为空");
                    return false;
                }else if(minLength != EDIT_NO_LIMIT && minLength > StrLenth){
                    ToastUtil.shortShow(context,RemindStr+"不能小于"+minLength+"位");
                    return false;
                }else if(maxLength != EDIT_NO_LIMIT && maxLength < StrLenth){
                    ToastUtil.shortShow(context,RemindStr+"不能大于"+"位");
                    return false;
                }else {
                    return true;
                }
     }

}
