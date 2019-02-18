package com.rqphp.publib.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Json 工具类
 * Created by Harvey on 2017/9/6.
 */

public class JsonUtil {
    /**
     * 变成了private 如果想使用这个变量请使用 getInstance 方法
     * 方便以后重新封装，如果以前直接使用的请做修改
     */
    private static Gson gson = null;


    /**
     * 是否是json字符串
     *
     * @param str
     * @return
     */
    public static boolean isJsonStr(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            new JsonParser().parse(str);
            return true;
        } catch (JsonParseException e) {
            return false;
        }
    }


    /**
     * 是否json对象
     *
     * @param str
     * @return
     */
    public static boolean isJsonObj(String str) {
        Object jsonObj;
        try {
            jsonObj = new JSONTokener(str).nextValue();
            if (jsonObj instanceof JSONObject) {
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


    /**
     * 是否是json数组
     *
     * @param str
     * @return
     */
    public static boolean isJsonArray(String str) {
        Object jsonObj;
        try {
            jsonObj = new JSONTokener(str).nextValue();
            if (jsonObj instanceof JSONArray) {
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


    /**
     * 获取Gson
     *
     * @return
     */
    public static Gson getInstance() {
        if (gson == null) {
            gson = new GsonBuilder().create();
        }
        return gson;
    }

    /**
     * 对象转为string
     *
     * @param data
     * @return
     */
    public static <T> String objToJsonStr(T data) {
        try {
            return getInstance().toJson(data);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * json转为对象
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T jsonStrToObj(String json, Class<T> cls) {
        try {

            return getInstance().fromJson(json, cls);
        } catch (Throwable e) {
            return null;
        }
    }

    /**
     * @param json
     * @param typeOfT
     * @param <T>
     * @return
     */
    public static <T> T jsonStrToObj(String json, Type typeOfT) {
        try {
            return getInstance().fromJson(json, typeOfT);
        } catch (Throwable e) {
            return null;
        }
    }


    /**
     * json转化成List
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonStrToObjList(String json, Class<T> cls) {

        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();

        ArrayList<JsonObject> jsonObjs = getInstance().fromJson(json, type);

        List<T> objList = new ArrayList<>();

        if (jsonObjs != null && !jsonObjs.isEmpty()) {
            for (JsonObject jsonObject : jsonObjs) {
                T obj = getInstance().fromJson(jsonObject, cls);
                if (obj != null) {
                    objList.add(obj);
                }

            }
        }
        return objList;
    }
}
