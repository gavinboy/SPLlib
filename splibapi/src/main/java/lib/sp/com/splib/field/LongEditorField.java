package lib.sp.com.splib.field;

import lib.sp.com.splib.BaseEditorField;
import lib.sp.com.splib.EditorHelper;

/**
 * Author:yuanzeyao<br/>
 * Date:16/9/9 下午6:20
 * Email:yuanzeyao@qiyi.com
 */
public class LongEditorField <E extends EditorHelper> extends BaseEditorField<Long,E> {

    public LongEditorField(E editorHelper,String key){
        super(editorHelper,key);
    }

    @Override
    public E put(Long value) {
        return put(value);
    }
}
