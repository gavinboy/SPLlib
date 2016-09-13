package com.splib;

import android.content.SharedPreferences;

/**
 * Author:yuanzeyao<br/>
 * Date:16/9/9 下午5:08
 * Email:yuanzeyao@qiyi.com
 */
public class EditorHelper {
    protected final SharedPreferences.Editor mEditor;

    public EditorHelper(SharedPreferences.Editor mEditor){
        this.mEditor=mEditor;
    }

    public final void clear(){
        mEditor.clear().apply();
    }

    public final void apply(){
        mEditor.apply();
    }

    public final boolean commit(){
        return mEditor.commit();
    }

    public SharedPreferences.Editor getEditor(){
        return mEditor;
    }
}
