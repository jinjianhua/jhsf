package com.asm.test;

public interface Comparable extends Mesurable{
		int LESS = -1;
	    int EQUAL = 0;
	    int GREATER = 1;
	    int  compareTo(Object o);
}
