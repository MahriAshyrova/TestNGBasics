package com.class2;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class enableAndDisable {
	
	 @Test (enabled=false)
	    public void first() {
	        System.out.println("First-Test");
	    }
	    
	    @Test (enabled=true, priority=1)
	    public void second() {
	        System.out.println("Second-Method");
	    }

	    @Test (enabled=false)
	    public void third() {
	        System.out.println("Third-Method");
	    }
	    
	    @Test (enabled=true, priority=2)
	    public void forth() {
	        System.out.println("Forth-Class");
	    }

}
