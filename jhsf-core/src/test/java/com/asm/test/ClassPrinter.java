package com.asm.test;

import java.io.IOException;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import com.jhsf.asm.model.MethodByteCodeDO;
/**
 * 方法访问
 * @author jinjianhua
 *
 */
public class ClassPrinter implements ClassVisitor {
	
	private MethodByteCodeDO  methodByteCodeDO;

	@Override
	public void visit(int arg0, int arg1, String arg2, String arg3, String arg4, String[] arg5) {
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
		System.out.println("}");
		
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
		System.out.println(arg0+" "+arg1+" "+arg2+" "+arg3+" "+arg4);
		methodByteCodeDO = new MethodByteCodeDO(arg1,arg2);
		return null;
	}

	@Override
	public void visitOuterClass(String arg0, String arg1, String arg2) {
		
		
	}

	@Override
	public void visitSource(String arg0, String arg1) {
		
		
	}
	
	public MethodByteCodeDO  getMethodByteCodeDO(Class clazz){
		try{
			ClassPrinter classPrinter = new ClassPrinter();
			ClassReader classReader = new ClassReader(clazz.getName());
			classReader.accept(classPrinter, 0);
		}catch(Exception e){
			
		}
		return methodByteCodeDO;
	}
	
	public static void main(String[] args) throws IOException {
		ClassPrinter classPrinter = new ClassPrinter();
		ClassReader classReader = new ClassReader("com.asm.test.HsfServiceImpl");
		classReader.accept(classPrinter, 0);
	}

	
}
