package com.splib.field;

import android.content.SharedPreferences;

import com.splib.BaseSpField;


/**
 * Author:yuanzeyao<br/>
 * Date:16/9/9 下午5:51
 * Email:yuanzeyao@qiyi.com
 */
public class FloatSpField extends BaseSpField<Float> {

    public FloatSpField(SharedPreferences mSharedPreferences,String key){
        super(mSharedPreferences,key);
    }

    @Override
    public Float get(Float defaultValue) {
        if(defaultValue==null){
            defaultValue=0.0f;
        }
        return mSharedPreferences.getFloat(_key,defaultValue);
    }

    @Override
    public void put(Float value) {
        edit().putFloat(_key,value).apply();
    }
}
