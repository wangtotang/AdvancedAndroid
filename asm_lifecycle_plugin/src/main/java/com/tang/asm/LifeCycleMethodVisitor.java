package com.tang.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.ASM5;

/**
 * Created by tanghongtu on 2020/7/21.
 */
public class LifeCycleMethodVisitor extends MethodVisitor {

    private String className;
    private String methodName;

    public LifeCycleMethodVisitor(MethodVisitor methodVisitor, String className, String methodName) {
        super(Opcodes.ASM5, methodVisitor);
        this.className = className;
        this.methodName = methodName;
    }

    @Override
    public void visitCode() {
        super.visitCode();

        System.out.println("MethodVisitor visitCode------");

        mv.visitLdcInsn("tong");
        mv.visitLdcInsn(className + "---->" + methodName);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(Opcodes.POP);

    }
}
