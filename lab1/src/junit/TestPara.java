package junit;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import lab1.Lab1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@RunWith(Parameterized.class)

public class TestPara {

	private int input;
	private boolean expected;
	private Lab1 lab = null;
	
	public TestPara(int input,boolean expected){
		this.input=input;
		this.expected=expected;
	}
	
	@Before
	public void setUp() throws Exception {
		lab=new Lab1();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Parameters
	public static Collection<Object[]>getData(){
		//Lab1 lab = new Lab1();
		return Arrays.asList(new Object[][]{
			{0,true},
			{22,true},
			{83,true},
			{84,false},
			{-1,false},
			{74,false}
		});
	}

	@Test
	public void test() {
		assertEquals(this.expected,lab.fun(this.input));
	}

}
