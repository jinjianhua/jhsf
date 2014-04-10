package com.jhsf.asm;

import java.io.IOException;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import com.jhsf.asm.model.ClassByteCodeDO;
import com.jhsf.asm.model.MethodByteCodeDO;
/**
 * 方法访问
 * @author jinjianhua
 *
 */
public class ClassPrinter implements ClassVisitor {
	
	private static ClassByteCodeDO  classByteCodeDO = new ClassByteCodeDO();

	@Override
	public void visit(int arg0, int arg1, String arg2, String arg3, String arg4, String[] arg5) {
		System.out.println(arg2+" "+arg3+" "+arg4+" "+arg5);
	}

	@Override
	public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
		
		return null;
	}

	@Override
	public void visitAttribute(Attribute arg0) {
		
		
	}

	@Override
	public void visitEnd() {
		
	}

	@Override
	public FieldVisitor visitField(int arg0, String arg1, String arg2, String arg3, Object arg4) {
		return null;
	}

	@Override
	public void visitInnerClass(String arg0, String arg1, String arg2, int arg3) {
		
		
	}

	@Override
	public MethodVisitor visitMethod(int arg0, String arg1, String arg2, String arg3, String[] arg4) {
		MethodByteCodeDO methodByteCodeDO = new MethodByteCodeDO(arg1,arg2);
		classByteCodeDO.add(methodByteCodeDO);
		return null;
	}

	@Override
	public void visitOuterClass(String arg0, String arg1, String arg2) {
		
		
	}

	@Override
	public void visitSource(String arg0, String arg1) {
		
		
	}
	
	public static ClassByteCodeDO  getMethodByteCodeDO(Class clazz){
		try{
			classByteCodeDO.clear();
			ClassPrinter classPrinter = new ClassPrinter();
			ClassReader classReader = new ClassReader(clazz.getName());
			classReader.accept(classPrinter, 0);
		}catch(Exception e){
			
		}
		return classByteCodeDO;
	}
	
	public static void main(String[] args) throws IOException {
		ClassPrinter classPrinter = new ClassPrinter();
		ClassReader classReader = new ClassReader("com.jhsf.asm.HsfService");
		classReader.accept(classPrinter, 0);
		System.out.println(22);
	}

	
}
