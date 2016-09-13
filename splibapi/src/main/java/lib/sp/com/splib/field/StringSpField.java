package lib.sp.com.splib.field;

import android.content.SharedPreferences;

import lib.sp.com.splib.BaseSpField;

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
        return get(defaultValue);
    }

    @Override
    public void put(String value) {
        put(value);
    }


}
