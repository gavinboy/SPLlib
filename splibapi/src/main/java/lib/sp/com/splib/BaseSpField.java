package lib.sp.com.splib;

import android.content.SharedPreferences;

/**
 * Author:yuanzeyao<br/>
 * Date:16/9/9 下午5:13
 * Email:yuanzeyao@qiyi.com
 */
public abstract class BaseSpField<T> {
    protected final SharedPreferences mSharedPreferences;
    protected final String _key;

    public BaseSpField(SharedPreferences sharedPreferences,String _key){
        this.mSharedPreferences=sharedPreferences;
        this._key=_key;
    }

    public final boolean exists(){
        return mSharedPreferences.contains(_key);
    }

    public final void remove(){
         editor().remove(_key).apply();
    }

    public final String key(){
        return this._key;
    }

    public final T get(){
        return get(null);
    }

    public abstract T get(T defaultValue);

    public abstract void put(T value);

    protected SharedPreferences.Editor editor(){
        return mSharedPreferences.edit();
    }

    protected final void apply(SharedPreferences.Editor mEditor){
        mEditor.apply();
    }


}
