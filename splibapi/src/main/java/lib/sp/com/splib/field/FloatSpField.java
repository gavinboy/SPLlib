package lib.sp.com.splib.field;

import android.content.SharedPreferences;

import lib.sp.com.splib.BaseSpField;

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
        return get(defaultValue);
    }

    @Override
    public void put(Float value) {
        put(value);
    }
}
