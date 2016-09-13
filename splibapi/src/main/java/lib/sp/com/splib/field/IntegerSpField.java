package lib.sp.com.splib.field;

import android.content.SharedPreferences;

import lib.sp.com.splib.BaseSpField;

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
        return get(defaultValue);
    }

    @Override
    public void put(Integer value) {
        put(value);
    }
}
