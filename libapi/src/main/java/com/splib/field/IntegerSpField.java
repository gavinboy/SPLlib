package com.splib.field;

import android.content.SharedPreferences;

import com.splib.BaseSpField;


/**
 * Author:yuanzeyao<br/>
 * Date:16/9/9 下午6:11
 * Email:yuanzeyao@qiyi.com
 */
public class IntegerSpField extends BaseSpField<Integer> {
    public IntegerSpField(SharedPreferences sharedPreferences,String key){
        super(sharedPreferences,key);
    }


    @Override
    public Integer get(Integer defaultValue) {
        if(defaultValue==null){
            defaultValue=0;
        }
        return mSharedPreferences.getInt(_key,defaultValue);
    }

    @Override
    public void put(Integer value) {
        edit().putInt(_key,value).apply();
    }
}
