package com.splib.field;

import android.content.SharedPreferences;

import com.splib.BaseSpField;

/**
 * Author:yuanzeyao<br/>
 * Date:16/9/9 下午5:42
 * Email:yuanzeyao@qiyi.com
 */
public class BooleanSpField extends BaseSpField<Boolean> {

    public BooleanSpField(SharedPreferences mSharedPreference,String key){
        super(mSharedPreference,key);
    }
    @Override
    public Boolean get(Boolean defaultValue) {
        if(defaultValue==null){
            defaultValue=false;
        }
        return mSharedPreferences.getBoolean(_key,defaultValue);
    }

    @Override
    public void put(Boolean value) {
        edit().putBoolean(_key,value).apply();
    }
}
