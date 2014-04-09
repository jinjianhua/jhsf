package com.jhsf.asm;

import java.util.ArrayList;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AsmTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		 ClassWriter cw = new ClassWriter(0);
		 cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "com/jhsf/asm/HsfServiceImpl", null, "java/lang/Object", 
				 new String[] { "com/jhsf/asm/HsfService" });
		 
		 //生成默认的构造方法
		  MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
		    "<init>",
		    "()V",
		    null,
		    null);

		  //生成构造方法的字节码指令
		  mw.visitVarInsn(Opcodes.ALOAD, 0);
		  mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
		  mw.visitInsn(Opcodes.RETURN);
		  mw.visitMaxs(1, 1);
		  mw.visitEnd();
		  
		  
		  //生成main方法
		  mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
		    "add",
		    "()V",
		    null,
		    null);

		  //生成main方法中的字节码指令
		  mw.visitFieldInsn(Opcodes.GETSTATIC,
		    "java/lang/System",
		    "out",
		    "Ljava/io/PrintStream;");

		  mw.visitLdcInsn("Hello world!");
		  mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
		    "java/io/PrintStream",
		    "println",
		    "(Ljava/lang/String;)V");
		  mw.visitInsn(Opcodes.RETURN);
		  mw.visitMaxs(2, 1);

		  //字节码生成完成
		  mw.visitEnd();
		  
		  
		  mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
				    "update",
				    "(Ljava/lang/String;)V",
				    null,
				    null);
		  
		  //生成main方法中的字节码指令
		  mw.visitFieldInsn(Opcodes.GETSTATIC,
		    "java/lang/System",
		    "out",
		    "Ljava/io/PrintStream;");

//		  mw.visitLdcInsn("Hello world!");
		  mw.visitVarInsn(Opcodes.ALOAD, 1);  
		  mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
		    "java/io/PrintStream",
		    "println",
		    "(Ljava/lang/String;)V");
		  mw.visitInsn(Opcodes.RETURN);
		  mw.visitMaxs(2, 2);

		  //字节码生成完成
		  mw.visitEnd();
		  
		  
		  mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
				    "delete",
				    "(Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;)V",
				    null,
				    null);
		  
		  mw.visitFieldInsn(Opcodes.GETSTATIC,
				    "java/lang/System",
				    "out",
				    "Ljava/io/PrintStream;");
		  
		  mw.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
		  mw.visitInsn(Opcodes.DUP);
		  
		  mw.visitMethodInsn(Opcodes.INVOKESPECIAL,
				    "java/lang/StringBuilder",
				    "<init>",
				    "()V");
		  
		  mw.visitVarInsn(Opcodes.ALOAD, 1);  
		  mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				    "java/lang/StringBuilder",
				    "append",
				    "(Ljava/lang/Object;)Ljava/lang/StringBuilder;");
		  
		  mw.visitLdcInsn("");
		  mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				    "java/lang/StringBuilder",
				    "append",
				    "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
		  
		  mw.visitVarInsn(Opcodes.ALOAD, 2);  
		  mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				    "java/lang/StringBuilder",
				    "append",
				    "(Ljava/lang/Object;)Ljava/lang/StringBuilder;");
		  
		  mw.visitLdcInsn("");
		  mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				    "java/lang/StringBuilder",
				    "append",
				    "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
		  
		  mw.visitVarInsn(Opcodes.ALOAD, 3);  
		  mw.visitMethodInsn(Opcodes.INVOKEINTERFACE,
				    "java/util/List",
				    "size",
				    "()I");
		  mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				    "java/lang/StringBuilder",
				    "append",
				    "(I)Ljava/lang/StringBuilder;");
		  
		  mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				    "java/lang/StringBuilder",
				    "toString",
				    "()Ljava/lang/String;");
		  
		  mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				    "java/io/PrintStream",
				    "println",
				    "(Ljava/lang/String;)V");
		  mw.visitInsn(Opcodes.RETURN);
		  mw.visitMaxs(3, 4);
		  
		  
		  byte[] code = cw.toByteArray();
		  
		  MyClassLoader loader = new MyClassLoader();
		  Class<?> exampleClass = loader.defineClass("com.jhsf.asm.HsfServiceImpl", code);
		  Object o = exampleClass.newInstance();
		  HsfService  s = (HsfService)o;
		  s.add();
		  s.update("111");
		  s.delete(1, "aaa", new ArrayList<String>());
		  System.out.println();
	}
	
	
}
