package lib.sp.com.splib.field;

import android.content.SharedPreferences;

import lib.sp.com.splib.BaseSpField;

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
        return get(defaultValue);
    }

    @Override
    public void put(Long value) {
        put(value);

    }


}
