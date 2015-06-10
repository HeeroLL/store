package com.zebone.dip.release;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class MethodTest {
	@Test
	public void testIterator(){
		Set mainDataSet = new HashSet<String>();
		mainDataSet.add("a");
		mainDataSet.add("b");
		mainDataSet.add("c");
		mainDataSet.add("a");
		Iterator it = mainDataSet.iterator();
		while(it.hasNext()){
			String name = it.next().toString();
			System.out.println(name);
		}
	}
}
