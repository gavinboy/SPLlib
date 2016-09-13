package com.splib.compile;

import com.google.auto.service.AutoService;
import com.splib.annotation.Sp;
import com.splib.compile.model.AnnotatedClass;
import com.splib.compile.model.SPClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
@AutoService(Processor.class)
public class AnnotationProcessor extends AbstractProcessor {
    private Map<String,AnnotatedClass> mAnnotateClassMap = new HashMap<String,AnnotatedClass>();
    private Filer mFiler;
    private Elements mElementUtil;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        System.out.println("AnnotationProcessor...init()....");
        super.init(processingEnv);
        mFiler=processingEnv.getFiler();
        mElementUtil=processingEnv.getElementUtils();
        messager=processingEnv.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types=new LinkedHashSet<>();
        types.add(Sp.class.getCanonicalName());
        return  types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("AnnotationProcessor...process()....");
        mAnnotateClassMap.clear();
        try{
            processSPClass(roundEnv);
        }catch (IllegalArgumentException s){
            System.out.println("error:"+s.getLocalizedMessage());
            //error(s.getMessage());
        }
        System.out.println("AnnotationProcessor...start generate....size:"+mAnnotateClassMap.values().size());
        for(AnnotatedClass annotatedClass : mAnnotateClassMap.values()){
            try{
                //info("Generating file for %s",annotatedClass.getFullClassName());
                System.out.println("generating file for "+annotatedClass.getFullClassName());
                annotatedClass.generateFinder().writeTo(mFiler);
            }catch (IOException e){
                System.out.println("generating file fail,reason:%s"+e.getLocalizedMessage());
                //error("Generate file fail,reason:%s",e.getMessage());
                return true;
            }
        }
        return true;
    }

    private void processSPClass(RoundEnvironment roundEnv) {
        System.out.println("AnnotationProcessor->processSpClass size:"+roundEnv.getElementsAnnotatedWith(Sp.class).size());
        for(Element element :roundEnv.getElementsAnnotatedWith(Sp.class)){
            System.out.println("element...");
            AnnotatedClass annoatedClass= getAnnotatedClass(element);
            SPClass mSpClass=new SPClass(element);
            annoatedClass.setSPClass(mSpClass);
        }
    }

    private AnnotatedClass getAnnotatedClass(Element element) {
        System.out.println("AnnotationProcessor->getAnnotatedClass");
        TypeElement classElement=(TypeElement) element;
        String fullClassName=classElement.getQualifiedName().toString();
        System.out.println("AnnotationProcessor->getAnnotatedClass fullClassName:"+fullClassName);
        AnnotatedClass annotatedClass = mAnnotateClassMap.get(fullClassName);
        if(annotatedClass==null){
            annotatedClass=new AnnotatedClass(classElement,mElementUtil,messager);
            mAnnotateClassMap.put(fullClassName,annotatedClass);
        }
        return annotatedClass;
    }

    private void error(String msg,Object ... args){
        messager.printMessage(Diagnostic.Kind.ERROR,String.format(msg,args));
    }

    private void info(String msg,Object... args){
        messager.printMessage(Diagnostic.Kind.NOTE,String.format(msg,args));
    }
}
