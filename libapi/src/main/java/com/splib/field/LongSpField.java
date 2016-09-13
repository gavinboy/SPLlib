package com.splib.field;

import android.content.SharedPreferences;

import com.splib.BaseSpField;


/**
 * Author:yuanzeyao<br/>
 * Date:16/9/9 下午6:26
 * Email:yuanzeyao@qiyi.com
 */
public class LongSpField extends BaseSpField<Long> {
    public LongSpField(SharedPreferences sharedPreferences,String key){
        super(sharedPreferences,key);
    }

    @Override
    public Long get(Long defaultValue) {
        if(defaultValue==null){
            defaultValue=0l;
        }
        return mSharedPreferences.getLong(_key,defaultValue);
    }

    @Override
    public void put(Long value) {
       edit().putLong(_key,value).apply();

    }


}
