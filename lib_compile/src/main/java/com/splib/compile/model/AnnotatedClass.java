package com.splib.compile.model;

import com.splib.compile.CodeGenerator;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * Author:yuanzeyao<br/>
 * Date:16/9/12 上午10:46
 * Email:yuanzeyao@qiyi.com
 */
public class AnnotatedClass {
    public TypeElement mClassElement;
    public SPClass mClasss;
    public Elements mElementUtils;
    public Messager messager;

    public AnnotatedClass(TypeElement classElement, Elements elementUtils, Messager messager){
        this.mClassElement=classElement;
        this.mElementUtils=elementUtils;
        this.messager=messager;
    }

    public String getFullClassName(){
        return mClassElement.getQualifiedName().toString();
    }

    public void setSPClass(SPClass mSPClass){
        this.mClasss=mSPClass;
    }

    public JavaFile generateFinder(){
        String packageName= getPackageName(mClassElement);
        ArrayList<HashMap<Class,String>> methodList =new ArrayList<HashMap<Class,String>>();
        for(Element element : mClassElement.getEnclosedElements()){
            HashMap<Class,String> hashMap=new HashMap<Class,String>();
            if(element.getKind() == ElementKind.FIELD){
                TypeKind typekind= element.asType().getKind();
                if(typekind == TypeKind.INT){
                    hashMap.put(Integer.class,element.toString());
                }else if(typekind == TypeKind.LONG){
                    hashMap.put(Long.class,element.toString());
                }else if(typekind == TypeKind.FLOAT){
                    hashMap.put(Float.class,element.toString());
                }else if(typekind == TypeKind.BOOLEAN){
                    hashMap.put(Boolean.class,element.toString());
                }else if(typekind == TypeKind.DECLARED){
                    String type=element.asType().toString();
                    if(type.equals(String.class.getName())){
                        hashMap.put(String.class,element.toString());
                    }else if(type.equals(Integer.class.getName())){
                        hashMap.put(Integer.class,element.toString());
                    }else if(type.equals(Long.class.getName())){
                        hashMap.put(Long.class,element.toString());
                    }else if(type.equals(Float.class.getName())){
                        hashMap.put(Float.class,element.toString());
                    }else if(type.equals(Boolean.class.getName())){
                        hashMap.put(Boolean.class,element.toString());
                    }else{
                        errorField(mClassElement);
                    }
                }else{
                    errorField(mClassElement);
                }
            }

            if(!hashMap.isEmpty()){
                methodList.add(hashMap);
            }
        }

        CodeGenerator codeGenerator = new CodeGenerator(packageName,mClassElement.getSimpleName().toString(),methodList);
        TypeSpec generatedClass = codeGenerator.generateClass();
        JavaFile javaFile = JavaFile.builder(packageName,generatedClass).build();
        return javaFile;
    }

    private void errorField(TypeElement typeElement){
        error(typeElement,"Fields of classes annotated with %s must be String/int/long/float/boolean.","@Spf");
    }

    private void error(Element element,String message,Object... args){
        if(args.length>0){
            message= String.format(message,args);
        }
        messager.printMessage(Diagnostic.Kind.ERROR,message,element);
    }

    private String getPackageName(TypeElement typeElement){
        return mElementUtils.getPackageOf(typeElement).getQualifiedName().toString();
    }


}
