package lib.sp.com.splib.field;

import lib.sp.com.splib.BaseEditorField;
import lib.sp.com.splib.EditorHelper;

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
        return put(value);
    }
}
