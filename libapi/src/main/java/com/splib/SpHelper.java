package com.splib;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Author:yuanzeyao<br/>
 * Date:16/9/9 下午5:03
 * Email:yuanzeyao@qiyi.com
 */
public abstract class SpHelper {
    protected static final String PREFIX_SP="sp_";
    protected final SharedPreferences mSharedPreferences;


    public SpHelper(Context mContext, String suffixName){
        this.mSharedPreferences=mContext.getSharedPreferences(PREFIX_SP+suffixName,Context.MODE_PRIVATE);
    }

    protected final SharedPreferences.Editor getEditor(){
        return mSharedPreferences.edit();
    }

    public final void clear(){
        getEditor().clear().apply();
    }


}
