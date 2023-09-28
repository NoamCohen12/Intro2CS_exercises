package Exe.Ex2;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * This JUnit class represents a very simple unit testing for Ex2.
 * This class should be improved and generalized significantly.
 * Make sure you add documentations to this Tesing class.
 * @author boaz.ben-moshe
 *
 */

class Ex2Test {
	static double[] po1={2,0,3, -1,0}, po2 = {0.1,0,1,0.1,3};


	@Test
	void testEquals() {
		double[] p1 = {2, 3, 4}, p2 = {2, 3, 4};
		double[] p3 = {2, 3, 4}, p4 = {2, 3, 4, 4};
		double[] p5 = {2, 3, 4}, p6 = {};
		double[] p7 = {}, p8 = {};
		double[] p9 = null, p10 = null;
		double[] p11 = null, p12 = {5};

		boolean ans1 = Ex2.equals(p1, p2);
		boolean ans2 = Ex2.equals(p3, p4);
		boolean ans3 = Ex2.equals(p5, p6);
		boolean ans4 = Ex2.equals(p7, p8);
		boolean ans5 = Ex2.equals(p9, p10);
		boolean ans6 = Ex2.equals(p11, p12);

		assertEquals (ans1, true);
		assertEquals (ans2, false);
		assertEquals (ans3, false);
		assertEquals (ans4, true);
		assertEquals (ans5, true);
		assertEquals (ans6, false);
	}

	@Test
	public void testPoly() {
		double [] poly = {1,2,3,4,5};
		double [] poly1 = {-1,2,3,4,-5};
		double [] poly2 = {0};
		double [] poly3 = {+1,2,3,4,+5};

		String str=Ex2.poly(poly);
		String str1=Ex2.poly(poly1);
		String str2=Ex2.poly(poly2);
		String str3=Ex2.poly(poly3);

		assertEquals("5.0x^4 +4.0x^3 +3.0x^2 +2.0x +1.0", str);  //Testing a normal polynomial
		assertEquals("-5.0x^4 +4.0x^3 +3.0x^2 +2.0x -1.0", str1);//Testing a polynomial with negative numbers at the limits
		assertEquals("0.0", str2);                                 //Testing the "zero polynomial"
		assertEquals("5.0x^4 +4.0x^3 +3.0x^2 +2.0x +1.0", str3); //Testing with numbers with a + character in the limits



	}

	@Test
	void testF() {
		double [] poly = {1,2,3,4,5};
		double [] poly1 = {};
		double [] poly3 = {1,-0.5};


		double fx0 = Ex2.f(poly, 0);
		double fx1 = Ex2.f(poly1, 1);
		double fx3 = Ex2.f(poly3, 2);


		assertEquals(fx0,1);                    //Testing a normal polynomial
		assertEquals(fx1,0);                    //Testing for an empty array
		assertEquals(fx3,0);                    //Testing a polynomial with a decimal term



	}
	@Test
	void testRoot() {

		double[] p1 = {16, 10, 1};							// test a random polynom

		double x12 = Ex2.root(po1, 0, 10, Ex2.EPS);
		double x13 = Ex2.root(p1, -10, 50, Ex2.EPS);


		assertEquals(x12, 3.1958, Ex2.EPS);
		assertEquals(x13, -2, Ex2.EPS);


	}
	
	
	@Test
	void testRoot_rec() {
		double[] p1 = {16, 10, 1};							// test a random polynom

		double x12 = Ex2.root_rec(po1, 0, 10, Ex2.EPS);
		double x13 = Ex2.root_rec(p1, -10, 50, Ex2.EPS);

		assertEquals(x12, 3.1958, Ex2.EPS);
		assertEquals(x13, -2, Ex2.EPS);
	}
	
	@Test
	public void testAdd() {
		double[] pm1 = {-1,-7,-8};
		double[] pm2 = {};
		double[] pm3 = Ex2.add(pm1, pm2);
		double[] pm4 = {5,-7,5};
		double[] pm5 = {1,2,3,5};

		double[] add = Ex2.add(pm4, pm5);
		double[] ans = {6,-5,8,5};
		assertArrayEquals(add, ans);// Testing arrays of different sizes
		assertArrayEquals(pm3, pm1);//Testing adding an empty array


	}
	@Test
	void testSameValue() {									// test if the function finds the interseption point of two polynoms 
		double eps = 0.001;
		
		double[] p1 = {10, 3};
		double[] p2 = {5, 1};
		double[] p3 = {2, 1, -0.7, -0.02, 0.02};
		double[] p4 = {-3, 0.61, 0.2};
		
		double ans1 = Ex2.sameValue(p1, p2, -10, 10, eps);
		double ans2 = Ex2.sameValue(p3, p4, 0, 5, eps);

		assertEquals(ans1, -2.5);
		assertEquals(ans2, 2.748, Ex2.EPS);					// tests the polynoms from Ex2.GUI

	}

	@Test
	void testMulDoubleArrayDoubleArray() {
		double[] p12 = Ex2.add(po1, po2);
		double dd = Ex2.f(p12, 5);
		assertEquals(dd, 1864.6, Ex2.EPS);
	}
	@Test 
	void testDerivativeArrayDoubleArray() {
		double[] p = {1,2,3}; // 3X^2+2x+1
		double[] dp1 = {2,6}; // 6x+2
		double[] dp2 = Ex2.derivative(p);
		assertEquals(dp1[0], dp2[0],Ex2.EPS);
		assertEquals(dp1[1], dp2[1],Ex2.EPS);
		assertEquals(dp1.length, dp2.length);

		double[] f = {10}; // 10
		double[] f1 = {0}; // 0
		double[] f2 = Ex2.derivative(f);
		assertEquals(f1[0], f2[0],Ex2.EPS);           // //Testing An array with one member
		assertEquals(f1.length, f2.length);  


	}
	@Test
	void testArea() {
		double[] p1 = {2, 1, -0.7, -0.02, 0.02};
		double[] p2 = {-3, 0.61, 0.2};
		
		double ans1 = Ex2.area(p1, p2, -2.343, 2.734, 10);

		assertEquals(ans1, 16.59002278872367, 0.2);
		
		
		
		
	}
	
	
	
	
	
	
	@Test
	public void testFromString() {
		double[] p = {-1.1, 2.3, 3.1};
		String sp = "3.1x^2+2.3x-1.1";
		double[] p1 = Ex2.getPolynomFromString(sp);
		assertEquals(p[0], p1[0]);
		assertEquals(p[1], p1[1]);
		assertEquals(p[2], p1[2]);
		
		double[] p3 = {3.0, 0.0, 7.0};
		String sp3 = "7.0x^2+3.0";
		double[] fp3 = Ex2.getPolynomFromString(sp3);
		assertEquals(p3[0], fp3[0]);
		assertEquals(p3[1], fp3[1]);
		assertEquals(p3[2], fp3[2]);
	}
	
	@Test 
	void testmull () {
		double [] p1 = {5};
		double [] p2 = {1,2,3,4,5};
		double [] p3 = {5,10,15,20,25};
		double [] p12 = Ex2.mul(p1, p2);
		assertArrayEquals(p12, p3);                 //Tersting multiplication a normal polinomal
		double [] p4 = {0};
		double [] p5 = {1,2,3,4,5};
		double [] p6 = {0,0,0,0,0};          
		double [] p13 = Ex2.mul(p5, p4);
		assertArrayEquals(p13, p6);                  // //Testing multiplication an empty array

	}
	@Test
	void testPolynomFromPoints ( ) {
		double[] xxx = {1,2,3};
		double[] yyy = {5,20,43};
		double[] tmp = {-2, 3, 4};
		double[] ans = Ex2.PolynomFromPoints(xxx, yyy); 

		assertArrayEquals(tmp, ans);                      //Tersting a parbola polinomal

		double[] xxx1 = {1,2,3,4};
		double[] yyy1 = {5,10,15,20};
		double[] tmp1 = {0,5};
		double[] ans1 = Ex2.PolynomFromPoints(xxx1, yyy1); 

		assertArrayEquals(null,ans1);                      //Tersting for probola polinomal too big


		double[] xxx2 = {1,2,3};
		double[] yyy2 = {3.81,5.02,6.63};
		double[] tmp2 = {03,0.61,0.2};
		double[] ans2 = Ex2.PolynomFromPoints(xxx2, yyy2); 

		assertArrayEquals(tmp2,ans2,Ex2.EPS);                      //Tersting for probola polinomal like your example



	}



}






