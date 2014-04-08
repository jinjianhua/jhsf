package com.asm.test;

import java.io.IOException;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassPrinter implements ClassVisitor {

	@Override
	public void visit(int arg0, int arg1, String arg2, String arg3, String arg4, String[] arg5) {
		 System.out.println(arg2 + " "+arg3+" " + arg4 +" "+ arg5);
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
		System.out.println(arg0 + " "+arg2+" "+arg3+" "+arg4);
		return null;
	}

	@Override
	public void visitInnerClass(String arg0, String arg1, String arg2, int arg3) {
		
		
	}

	@Override
	public MethodVisitor visitMethod(int arg0, String arg1, String arg2, String arg3, String[] arg4) {
		System.out.println(arg0 + " "+arg2+" "+arg3+" "+arg4);
		return null;
	}

	@Override
	public void visitOuterClass(String arg0, String arg1, String arg2) {
		
		
	}

	@Override
	public void visitSource(String arg0, String arg1) {
		
		
	}
	
	public static void main(String[] args) throws IOException {
		ClassPrinter classPrinter = new ClassPrinter();
		ClassReader classReader = new ClassReader("java.lang.Object");
		classReader.accept(classPrinter, 0);
	}

	
}
