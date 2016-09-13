package com.splib.compile;

import com.splib.EditorHelper;
import com.splib.SpHelper;
import com.splib.field.BooleanEditorField;
import com.splib.field.BooleanSpField;
import com.splib.field.FloatEditorField;
import com.splib.field.FloatSpField;
import com.splib.field.IntegerEditorField;
import com.splib.field.IntegerSpField;
import com.splib.field.LongEditorField;
import com.splib.field.LongSpField;
import com.splib.field.StringEditorField;
import com.splib.field.StringSpField;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.lang.model.element.Modifier;

/**
 * Author:yuanzeyao<br/>
 * Date:16/9/12 下午2:13
 * Email:yuanzeyao@qiyi.com
 */
public class CodeGenerator {
    private static final String CLASS_PREFIX="SharedPreference";
    private static final String CLASS_EDITOR_PREFIX="Editor";

    private static final String METHOD_CREATE = "create";
    private static final String METHOD_EDIT = "edit";

    private final String className;
    private final String generatedClassName;

    private final ArrayList<HashMap<Class,String>> methodList;

    private ClassName contextClass,spClass,editorClass;

    public CodeGenerator(String packageName,String className,ArrayList<HashMap<Class,String>> methodList){
        this.className=className;
        this.generatedClassName=CLASS_PREFIX+className;
        this.methodList=methodList;

        contextClass=ClassName.get("android.content","Context");
        editorClass=ClassName.get(packageName,generatedClassName,CLASS_EDITOR_PREFIX+className);
        spClass=ClassName.get(packageName,generatedClassName);
    }

    public TypeSpec generateClass(){
        return TypeSpec.classBuilder(generatedClassName)
                .addModifiers(Modifier.PUBLIC,Modifier.FINAL)
                .superclass(SpHelper.class)
                .addMethod(construct())
                .addMethod(create())
                .addMethods(fieldMethods())
                .addType(classEditor())
                .addMethod(edit())
                .build();

    }

    private MethodSpec edit() {
        return MethodSpec.methodBuilder(METHOD_EDIT)
                .addModifiers(Modifier.PUBLIC)
                .returns(editorClass)
                .addCode("return new $T(getEditor());\n",editorClass)
                .build();
    }

    private TypeSpec classEditor() {
        return TypeSpec.classBuilder(CLASS_EDITOR_PREFIX+className)
                .addModifiers(Modifier.PUBLIC,Modifier.FINAL)
                .superclass(ClassName.get(EditorHelper.class))
                .addMethods(editorFieldMethods())
                .addMethod(editorConstruct())
                .build();
    }

    private MethodSpec editorConstruct() {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ClassName.get("android.content.SharedPreferences","Editor"),"editor")
                .addCode("super(editor);\n")
                .build();
    }

    private Iterable<MethodSpec> editorFieldMethods() {
        List<MethodSpec> methodSpecs = new ArrayList<MethodSpec>();
        for(HashMap<Class,String> map: methodList){
            Class clazz = map.keySet().iterator().next();
            String fieldName = map.get(clazz);

            Class typeClass=null;

            if(clazz== String.class){
                typeClass = StringEditorField.class;
            }else if(clazz == Integer.class){
                typeClass= IntegerEditorField.class;
            }else if (clazz == Long.class){
                typeClass= LongEditorField.class;
            }else if(clazz == Float.class){
                typeClass= FloatEditorField.class;
            }else if(clazz == Boolean.class){
                typeClass= BooleanEditorField.class;
            }

            if(typeClass!=null){
                MethodSpec methodSpec=getEditorFieldsMethodSpec(fieldName,typeClass);
                methodSpecs.add(methodSpec);
            }
        }
        return methodSpecs;
    }

    private MethodSpec getEditorFieldsMethodSpec(String fieldName, Class typeClass) {
        MethodSpec methodSpec;
        methodSpec=MethodSpec.methodBuilder(fieldName)
                .addModifiers(Modifier.PUBLIC)
                .returns(ParameterizedTypeName.get(ClassName.get(typeClass),editorClass))
                .addCode("return new $T(this,$S);\n",typeClass,fieldName)
                .build();
        return methodSpec;
    }


    private Iterable<MethodSpec> fieldMethods() {
        List<MethodSpec> methodSpecs=new ArrayList<MethodSpec>();
        for(HashMap<Class,String> map : methodList){
            Class clazz= map.keySet().iterator().next();
            String fieldName=map.get(clazz);
            Class typeClass=null;
            if(clazz == String.class){
                typeClass= StringSpField.class;
            } else if(clazz == Integer.class){
                typeClass= IntegerSpField.class;
            } else if(clazz == Long.class){
                typeClass= LongSpField.class;
            } else if(clazz == Float.class){
                typeClass = FloatSpField.class;
            }else if(clazz == Boolean.class){
                typeClass = BooleanSpField.class;
            }

            if(typeClass !=null){
                MethodSpec methodSpec = getSpFieldMethodSpec(fieldName,typeClass);
                methodSpecs.add(methodSpec);
            }
        }
        return methodSpecs;
    }

    private MethodSpec getSpFieldMethodSpec(String fieldName, Class typeClass) {
        return MethodSpec.methodBuilder(fieldName)
                .addModifiers(Modifier.PUBLIC)
                .returns(typeClass)
                .addCode("return new $T(mSharedPreferences,$S);\n",typeClass,fieldName)
                .build();
    }

    private MethodSpec create() {
        return MethodSpec.methodBuilder(METHOD_CREATE)
                .addModifiers(Modifier.PUBLIC,Modifier.STATIC)
                .returns(spClass)
                .addParameter(contextClass,"context")
                .addCode("return new $T(context);\n",spClass)
                .build();
    }

    private MethodSpec construct() {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PRIVATE)
                .addParameter(contextClass,"context")
                .addCode("super(context,$S);\n",className)
                .build();
    }


}
