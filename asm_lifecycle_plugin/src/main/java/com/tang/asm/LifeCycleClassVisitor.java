package com.tang.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by tanghongtu on 2020/7/21.
 */
public class LifeCycleClassVisitor extends ClassVisitor {

    private String className;
    private String superName;

    public LifeCycleClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.className = name;
        this.superName = superName;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println("ClassVisitor visitMethod name-------" + name + ", superName is " + superName);

        MethodVisitor methodVisitor = cv.visitMethod(access, name, descriptor, signature, exceptions);

        if (superName.equals("androidx/appcompat/app/AppCompatActivity")){
            if (name.startsWith("onCreate")) {
                //处理onCreate()方法
                return new LifeCycleMethodVisitor(methodVisitor, className, name);
            }
        }

        return methodVisitor;
    }

}
