package com.splib.field;


import com.splib.BaseEditorField;
import com.splib.EditorHelper;

/**
 * Author:yuanzeyao<br/>
 * Date:16/9/9 下午5:45
 * Email:yuanzeyao@qiyi.com
 */
public class FloatEditorField<E extends EditorHelper> extends BaseEditorField<Float,E> {
    public FloatEditorField(E  mSharedPreferences,String key){
        super(mSharedPreferences,key);
    }

    @Override
    public E put(Float value) {
        _editorHelper.getEditor().putFloat(_key,value);
        return _editorHelper;
    }
}
