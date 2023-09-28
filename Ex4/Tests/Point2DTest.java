package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Point2D;

class Point2DTest {

	@Test
	void test() { //test for "rotate"
		Point2D p1=new Point2D(7,7);
		p1.rotate(new Point2D(5,5), Math.PI);
		assertTrue(p1.close2equals(new Point2D(3,3)));
	}

	@Test
	void test1() { // test for "scale"
		Point2D p1=new Point2D(5,5);
		Point2D cen = new Point2D(6,6);
		double eps = 0.01;
		double ratio = 1.1;
		double dist1 = p1.distance(cen);
		
		p1.scale(cen, ratio);
		double dist2 = p1.distance(cen);

		assertEquals(p1.x(), 4.9, eps);
		assertTrue(p1.close2equals(new Point2D(4.9, 4.9)));
		assertEquals(dist1*ratio, dist2, eps);
	}
	
	

}
