package com.asm.test;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class GenerateInterface {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		ClassWriter cw = new ClassWriter(0);
		
		/**
		 * Opcodes.V1_5：指明要生成的字节码的版本为Java1.5。
    	   Opcodes.ACC_XXX：对应Java修饰符。
    		"pkg/Comparable"：指明了当前接口的内部名字。
    		null：指明了当前接口的泛型，由于这个接口没有泛型化，所以这里这个参数为null。
    		"java/lang/Object"：当前接口的父类。
    	new String[] { "pkg/Mesurable" }：当前接口的所有父接口。
		 */
		cw.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE,
                "com/asm/test/Comparable", null, "java/lang/Object", new String[] { "java/lang/Cloneable" });
		
		/**
		 * Opcodes.ACC_XXX：对应Java修饰符。
    	"LESS"：字段的名字。
    	"I"：字段的类型。
    	null：泛型，字段没有使用泛型，因此为null。
    	new Integer(-1)：该字段的常量值。
		 */
		
		cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "LESS", "I",
                null, new Integer(-1)).visitEnd();
		
		cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "EQUAL", "I",
                null, new Integer(0)).visitEnd();

        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "GREATER", "I",
                null, new Integer(1)).visitEnd();
        
        /**
         *     Opcodes.ACC_XXX：对应Java修饰符。
    "compareTo"：方法名。
    "(Ljava/lang/Object;)I"：该方法的方法描述符。
   
    null：泛型，由于方法没有使用泛型，所以为null。
 
    null：方法声明抛出的所有异常类型，由于没有声明抛出任何异常，所以为null。
         */
        cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();
        
        cw.visitEnd();
        
        MyClassLoader classLoader = new MyClassLoader();
        Class clazz = classLoader.defineClass("com.asm.test.Comparable", cw.toByteArray());
        System.out.println();
	}
	
}
