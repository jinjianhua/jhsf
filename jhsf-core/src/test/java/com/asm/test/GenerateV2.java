package com.asm.test;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class GenerateV2 {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		
		
		 cw.visit(49, Opcodes.ACC_PUBLIC, "Person", null, "java/lang/Object", null);
//		cw.visitField(Opcodes.ACC_PUBLIC, "name", Type.getDescriptor(int.class),  
//		        null, null); //name改变为int 型  
//		cw.visitField(Opcodes.ACC_PUBLIC, "address", Type.getDescriptor(java.lang.String.class),  
//		        null, null); 
//		
//		
//		 MethodVisitor mv= cw.visitMethod(Opcodes.ACC_PUBLIC,  
//	                "<init>",  
//	                "()V",  
//	                null,  
//	                null);  
//		 mv.visitVarInsn(Opcodes.ALOAD, 0);  
//	     mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Objec", "<init>", "()V");
//	     
//	     mv.visitVarInsn(Opcodes.ALOAD, 0);  
//	     mv.visitVarInsn(Opcodes.BIPUSH, 7);  
//	     mv.visitFieldInsn(Opcodes.PUTFIELD, "com/asm/test/Person", "name", "I");
//	     
////	     mv.visitVarInsn(Opcodes.ALOAD, 0);  
////	     mv.visitLdcInsn("\u798f\u7530");  
////	     mv.visitFieldInsn(Opcodes.PUTFIELD, "com/asm/test/Person", "address", "Ljava/lang/String");
//	     
//	     mv.visitInsn(Opcodes.RETURN);  
//	     mv.visitEnd();  
		
		 cw.visitEnd();
	     MyClassLoader classLoader = new MyClassLoader();
	     Class clazz = classLoader.defineClass("Person", cw.toByteArray());
	     Object object = clazz.newInstance();
	     System.out.println();
	}
	
}
