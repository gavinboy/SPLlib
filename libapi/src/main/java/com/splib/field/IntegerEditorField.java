package com.splib.field;


import com.splib.BaseEditorField;
import com.splib.EditorHelper;

/**
 * Author:yuanzeyao<br/>
 * Date:16/9/9 下午6:06
 * Email:yuanzeyao@qiyi.com
 */
public class IntegerEditorField<E extends EditorHelper> extends BaseEditorField<Integer,E> {

    public IntegerEditorField(E editorHelper,String key){
        super(editorHelper,key);
    }
    @Override
    public E put(Integer value) {
        _editorHelper.getEditor().putInt(_key,value);
        return _editorHelper;
    }
}
