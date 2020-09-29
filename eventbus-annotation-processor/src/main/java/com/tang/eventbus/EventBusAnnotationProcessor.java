package com.tang.eventbus;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeVariableName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
@SupportedAnnotationTypes("org.greenrobot.eventbus.Subscribe")
public class EventBusAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        /**
         * private static final Map<Class<?>, List<SubscribeMethod>> CACHE_MAP = new HashMap<>();
         */
        ParameterizedTypeName listParameterizedTypeName = ParameterizedTypeName.get(List.class, SubscribeMethod.class);
        ClassName mapClassName = ClassName.get("java.util", "Map");//这种写法可以不引入包名
        FieldSpec.builder(ParameterizedTypeName.get(mapClassName, TypeVariableName.get("?"), listParameterizedTypeName), "CACHE_MAP")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("new $T<>()", HashMap.class)
                .build();



        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

}