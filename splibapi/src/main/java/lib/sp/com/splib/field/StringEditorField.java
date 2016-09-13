package lib.sp.com.splib.field;

import lib.sp.com.splib.BaseEditorField;
import lib.sp.com.splib.EditorHelper;

/**
 * Author:yuanzeyao<br/>
 * Date:16/9/9 下午6:29
 * Email:yuanzeyao@qiyi.com
 */
public class StringEditorField<E extends EditorHelper> extends BaseEditorField<String,E> {

    public StringEditorField(E editorHelper,String key){
        super(editorHelper,key);
    }

    @Override
    public E put(String value) {
        return put(value);
    }
}
