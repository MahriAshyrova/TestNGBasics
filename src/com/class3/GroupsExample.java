package com.class3;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsExample {

	@BeforeGroups ({"Smoke", "Regression"})
	
	public void beforeGroup() {
		System.out.println("Before Groups");		
		
	}
	
	@Test (groups="Smoke")
	
	public void one() {
		System.out.println("Test One");		
		
	}
	
	@Test (groups="Regression")
	
	public void two() {
		
	System.out.println("Test two");			
	}
	
	@Test  (groups="Smoke")

	public void  three() {
	
	System.out.println("Test three");		
}


	@Test (groups="Regression")

	public void four () {
	System.out.println("Test four");			
}

	@AfterGroups  ({"Smoke", "Regression"})
	
	public void afterGroup() {
		System.out.println("after Groups ");		
		
	}
	
}
