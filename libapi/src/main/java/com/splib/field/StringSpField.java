package com.splib.field;

import android.content.SharedPreferences;

import com.splib.BaseSpField;


/**
 * Author:yuanzeyao<br/>
 * Date:16/9/9 下午6:31
 * Email:yuanzeyao@qiyi.com
 */
public class StringSpField extends BaseSpField<String> {
    public StringSpField(SharedPreferences sharedPreferences,String key){
        super(sharedPreferences,key);
    }

    @Override
    public String get(String defaultValue) {
        if(defaultValue==null){
            defaultValue="";
        }
        return mSharedPreferences.getString(_key,defaultValue);
    }

    @Override
    public void put(String value) {
        edit().putString(_key,value).apply();
    }


}
