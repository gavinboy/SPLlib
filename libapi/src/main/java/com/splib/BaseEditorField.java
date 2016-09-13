package com.splib;

/**
 * Author:yuanzeyao<br/>
 * Date:16/9/9 下午5:22
 * Email:yuanzeyao@qiyi.com
 */
public abstract class BaseEditorField <T,E extends EditorHelper> {
    protected final E _editorHelper;
    protected final String _key;

    public BaseEditorField(E _editorHelper,String key){
        this._editorHelper=_editorHelper;
        this._key=key;
    }

    public final E remove(){
        _editorHelper.getEditor().remove(_key);
        return _editorHelper;
    }

    public abstract E put(T value);
}
