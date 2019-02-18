package com.rqphp.publib.util.permission;

/**
 * Created by asus on 2018/1/11.
 */

public class PermissionData {
    String permisssionStr;
    boolean must;

    public PermissionData(String permisssionStr, boolean must) {
        this.permisssionStr = permisssionStr;
        this.must = must;
    }

    public String getPermisssionStr() {
        return permisssionStr;
    }

    public void setPermisssionStr(String permisssionStr) {
        this.permisssionStr = permisssionStr;
    }

    public boolean isMust() {
        return must;
    }

    public void setMust(boolean must) {
        this.must = must;
    }
}
