package com.triangle;

public class Triangle {
	public String testTriangle(double a, double b, double c) {
		if (a + b > c && a + c > b && b + c > a) {
			if (a == b && b == c) {
				return "equilateral";
			} else if (a != b && b != c && a != c) {
				return "scalene";
			} else
				return "isosceles";
		} else
			return "not a triangle";
	}
}
