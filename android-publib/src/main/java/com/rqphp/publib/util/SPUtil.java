package com.rqphp.publib.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Harvey on 2017/11/26.
 */

public class SPUtil {

    private static SharedPreferences getInstance(Context context, String name) {
        return context.getSharedPreferences(name, Activity.MODE_PRIVATE);
    }
}
