package com.triangle;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TriangleTest {
	private double a;
	private double b;
	private double c;
	private String expected;
	private Triangle t;
	
	@Before
	public void setUp() throws Exception {
		t = new Triangle();
	}
	
	public TriangleTest(double a, double b, double c, String expected){
		this.a = a;
		this.b = b;
		this.c = c;
		this.expected =expected;
	}

	@Parameters
	public static Collection<Object[]> getData() {
		return Arrays.asList(new Object[][] { 
			{ 1, 1, 2, "not a triangle" }, 
			{ 2, 3, 2, "isosceles" }, 
			{ 3, 3, 3, "equilateral"},
			{ 3,4,5,"scalene"}
			});
	}

	@Test
	public void testTestTriangle() {
		assertEquals(this.expected,t.testTriangle(a, b, c));
		
	}

}
