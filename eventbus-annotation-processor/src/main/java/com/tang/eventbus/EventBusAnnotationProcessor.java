package com.tang.eventbus;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import com.tang.eventbus.utils.Constants;
import com.tang.eventbus.utils.EmptyUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
@SupportedAnnotationTypes("com.tang.eventbus.Subscribe")
@SupportedOptions({"eventBusIndex"})
public class EventBusAnnotationProcessor extends AbstractProcessor {

    private Elements elementUtils;
    private Types typeUtils;
    private Messager messager;
    private Filer filer;
    private String eventBusIndex;
    private final Map<TypeElement, List<ExecutableElement>> methodByClass = new HashMap<>();

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (!EmptyUtils.isEmpty(set)) {
            Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Subscribe.class);

            if (!EmptyUtils.isEmpty(elements)) {
                //解析元素
                try {
                    parseElements(elements);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
        return false;
    }

    private void parseElements(Set<? extends Element> elements) throws IOException {
        for (Element element : elements) {
            if (element.getKind() != ElementKind.METHOD) {
                messager.printMessage(Diagnostic.Kind.ERROR, "仅解析@Subscribe作用再方法上的元素", element);
                return;
            }
            //强转方法元素
            ExecutableElement method = (ExecutableElement) element;
            //检查方法，条件，订阅方法必须是非静态的，公开的参数只能用一个
            if (checkHasNoErrors(method)) {
                //获取封装订阅方法的类
                TypeElement classElement = (TypeElement) method.getEnclosingElement();
                List<ExecutableElement> methods = methodByClass.get(classElement);
                if (methods == null) {
                    methods = new ArrayList<>();
                    methodByClass.put(classElement, methods);
                }
                methods.add(method);
                messager.printMessage(Diagnostic.Kind.NOTE, "遍历注解方法：" + classElement.getSimpleName() + method.getSimpleName());

            }
            messager.printMessage(Diagnostic.Kind.NOTE, "遍历注解方法：" + method.getSimpleName());
        }

        //通过Element工具类获取subscriberInfoIndex类型
        TypeElement subscriberIndexType = elementUtils.getTypeElement(Constants.SUBSCRIBE_INDEX);
        //生成类文件
        createFile(subscriberIndexType);
    }

    private void createFile(TypeElement subscriberIndexType) throws IOException {

        /**
         * private static final Map<Class<?>, List<SubscribeMethod>> CACHE_MAP = new HashMap<>();
         */
        ParameterizedTypeName list = ParameterizedTypeName.get(List.class, SubscribeMethod.class);
        ClassName mapClassName = ClassName.get("java.util", "Map");//这种写法可以不引入包名
        ClassName clazzName = ClassName.get("java.lang", "Class");//这种写法可以不引入包名
        ParameterizedTypeName map = ParameterizedTypeName.get(mapClassName, clazzName, list);
        FieldSpec.Builder field = FieldSpec.builder(map, "CACHE_MAP")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("new $T<>()", HashMap.class);

        //添加静态块代码List<SubscribeMethod> methods = new ArrayList<>();
        CodeBlock.Builder codeBlock = CodeBlock.builder();

        //双层循环，第一层遍历被@Subscribe注解的方法所属类。第二层遍历每个类中所有的订阅方法
        for (Map.Entry<TypeElement, List<ExecutableElement>> entry : methodByClass.entrySet()) {

            codeBlock.addStatement("$T<$T> $N = new $T<>()",
                    List.class,
                    SubscribeMethod.class,
                    Constants.METHODS + entry.hashCode(),
                    ArrayList.class);

            for (int i = 0; i < entry.getValue().size(); i++) {
                ExecutableElement element = entry.getValue().get(i);
                //获取每个方法上的@Subscribe注解中的注解值
                Subscribe subscribe = element.getAnnotation(Subscribe.class);
                //获取订阅事件方法的所有参数
                List<? extends VariableElement> parameters = element.getParameters();
                //订阅事件方法名
                String methodName = element.getSimpleName().toString();

                TypeElement parameterElement = (TypeElement) typeUtils.asElement(parameters.get(0).asType());

                //   methods.add(new SubscribeMethod(MainActivity.class, "onReceived", String.class, ThreadMode.ASYNC));
                codeBlock.addStatement("$N.add(new $T($T.class, $S, $T.class, $T.$L))",
                        Constants.METHODS + entry.hashCode(),
                        SubscribeMethod.class,
                        ClassName.get(entry.getKey()),
                        methodName,
                        ClassName.get(parameterElement),
                        ThreadMode.class,
                        subscribe.threadMode());

            }
            //   CACHE_MAP.put(MainActivity.class, methods);
            codeBlock.addStatement("$N.put($T.class, $N)",
                    Constants.CACHE_MAP,
                    ClassName.get(entry.getKey()),
                    Constants.METHODS + entry.hashCode());

        }

        ParameterizedTypeName returnType = ParameterizedTypeName.get(Map.class, Class.class);

        // public Map<Class<?>, List<SubscribeMethod>> getSubscribeMethodMap()
        MethodSpec.Builder method = MethodSpec
                .methodBuilder(Constants.GET_SUBSCRIBE_METHOD_NAME) // 方法名
                .addAnnotation(Override.class) // 重写方法注解
                .addModifiers(Modifier.PUBLIC) // public修饰符
                .returns(map); // 方法返回值

        // return CACHE_MAP;
        method.addStatement("return $N", Constants.CACHE_MAP);

        int period = eventBusIndex.lastIndexOf('.');
        String packageName = period > 0 ? eventBusIndex.substring(0, period) : null;
        String className = eventBusIndex.substring(period + 1);

        // 构建类
        TypeSpec typeSpec = TypeSpec.classBuilder(className)
                // 实现SubscribeIndex接口
                .addSuperinterface(ClassName.get(subscriberIndexType))
                // 该类的修饰符
                .addModifiers(Modifier.PUBLIC)
                // 全局属性：private static final Map<Class<?>, List<SubscribeMethod>> CACHE_MAP
                .addField(field.build())
                // 添加静态块（很少用的api）
                .addStaticBlock(codeBlock.build())
                // 获取所有订阅方法
                .addMethod(method.build())
                .build();

        // 生成类文件：EventBusIndex
        JavaFile.builder(packageName, // 包名
                typeSpec) // 类构建完成
                .build() // JavaFile构建完成
                .writeTo(filer); // 文件生成器开始生成类文件
    }

    private boolean checkHasNoErrors(ExecutableElement element) {
        if (element.getModifiers().contains(Modifier.STATIC)) {
            messager.printMessage(Diagnostic.Kind.ERROR, "订阅事件方法不能是static静态方法", element);
            return false;
        }

        if (!element.getModifiers().contains(Modifier.PUBLIC)) {
            messager.printMessage(Diagnostic.Kind.ERROR, "订阅事件方法必须是public修饰的方法", element);
            return false;
        }

        List<? extends VariableElement> parameters = element.getParameters();
        if (parameters.size() != 1) {
            messager.printMessage(Diagnostic.Kind.ERROR, "订阅事件方法有且仅有一个参数", element);
            return false;
        }
        return true;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        elementUtils = processingEnvironment.getElementUtils();
        typeUtils = processingEnvironment.getTypeUtils();
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();

        Map<String, String> options = processingEnvironment.getOptions();
        if (options != null) {
            eventBusIndex = options.get(Constants.OPTION_EVENT_BUS_INDEX);
            messager.printMessage(Diagnostic.Kind.NOTE, "eventBusIndex>>>" + eventBusIndex);
        }

        if (eventBusIndex == null) {
            messager.printMessage(Diagnostic.Kind.ERROR, "注解处理器需要的参数为空，请在对应build.gradle配置参数");
        }

    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

}