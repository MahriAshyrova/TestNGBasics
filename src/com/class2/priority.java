package com.class2;
import org.testng.annotations.Test;

public class priority {

	
@Test 
public void first() {
	System.out.println("First test annotation");
}

@Test
public void second() {
	System.out.println("Second test annotation");
}
	
@Test (priority=2)
public void third() {
	System.out.println("Third test annotation");
}
	
@Test (priority=3)
public void forth() {
	System.out.println("Forth test annotation");
}
	






	
}
