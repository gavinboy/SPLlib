package com.splib.field;


import com.splib.BaseEditorField;
import com.splib.EditorHelper;

/**
 * Author:yuanzeyao<br/>
 * Date:16/9/9 下午5:29
 * Email:yuanzeyao@qiyi.com
 */
public class BooleanEditorField<E extends EditorHelper> extends BaseEditorField<Boolean,E> {

    BooleanEditorField(E editorHelper,String key){
        super(editorHelper,key);
    }

    @Override
    public E put(Boolean value) {
         _editorHelper.getEditor().putBoolean(_key,value);
        return _editorHelper;
    }
}
