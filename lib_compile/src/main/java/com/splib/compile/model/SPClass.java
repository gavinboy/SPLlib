package com.splib.compile.model;

import com.splib.annotation.Sp;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

/**
 * Author:yuanzeyao<br/>
 * Date:16/9/12 上午10:17
 * Email:yuanzeyao@qiyi.com
 */
public class SPClass {
    private TypeElement mTypeElement;

    public SPClass(Element element) throws IllegalArgumentException{
        if(element.getKind()!= ElementKind.CLASS){
            throw new IllegalArgumentException(String.format("Only Class can be annotated with @%s",Sp.class.getSimpleName()));
        }
        mTypeElement=(TypeElement)element;
        Sp sp=mTypeElement.getAnnotation(Sp.class);
    }

    public Name getClassName(){
        return mTypeElement.getSimpleName();
    }

    public TypeMirror getClassType(){
        return mTypeElement.asType();
    }
}
