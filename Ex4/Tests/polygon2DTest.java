package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;

class polygon2DTest {
	
	Point2D p1 = new Point2D (0,0);
	Point2D p2 = new Point2D (3,3);
	Point2D p3 = new Point2D (6,0);
	private LinkedList<Point2D> kodkod;
	Polygon2D poly;
	
	@Test
	void polygon2DTest() {
		
		kodkod = new LinkedList<Point2D>();
		
		kodkod.add(p1);
		kodkod.add(p2);
		kodkod.add(p3);
		
		 poly = new Polygon2D(kodkod);
		 
		 assertEquals(3, kodkod.size());
		
		// check the 'contains' function
		Point2D p5 = new Point2D(3,1);
		
		boolean ab = poly.contains(p5);
		assertEquals(true, ab);
		
		// check the 'area' and 'perimeter' functions
		double eps = 0.01;
		double area = poly.area();
		double perimeter = poly.perimeter();
		
		assertEquals(9, area, eps);
		assertEquals(14.49, perimeter, eps);
		
		// check the 'move' function
		Point2D vec =new Point2D(2,2);
		poly.move(vec);
		
		Point2D p11 = new Point2D(2,2);
		Point2D p12 = new Point2D(5,5);
		Point2D p13 = new Point2D(8,2);
		assertEquals(p11, p1);
		assertEquals(p12, p2);
		assertEquals(p13, p3);
		
		// check the 'copy' and 'getPoints' functions
		Polygon2D poly2 = (Polygon2D) poly.copy();
		Point2D[] polyPoints = poly.getPoints();
		Point2D[] poly2Points = poly2.getPoints();
		
		assertArrayEquals(polyPoints, poly2Points);
		
		// init poly to the values it held before
		p1 = new Point2D (0,0);
		p2 = new Point2D (3,3);
		p3 = new Point2D (6,0);
		kodkod.removeAll(kodkod);
		kodkod.add(p1);
		kodkod.add(p2);
		kodkod.add(p3);
		poly = new Polygon2D(kodkod);
		
		// check the 'rotate' function
		Point2D cen = new Point2D(3,3);
		
		poly.rotate(cen, 180);
		p11 = new Point2D(2.4, 7.2);
		p12 = new Point2D(3,3);
		p13 = new Point2D(-1.2, 2.4);
		
		assertEquals(p1.x(), p11.x(), eps);
		assertEquals(p1.y(), p11.y(), eps);
		assertEquals(p2.x(), p12.x(), eps);
		assertEquals(p2.y(), p12.y(), eps);
		assertEquals(p3.x(), p13.x(), eps);
		assertEquals(p3.y(), p13.y(), eps);
		
		// check the 'scale' function
		double ratio = 0.9;
		Point2D p15 = new Point2D(5, 5);
		double dist1 = poly.getPoints()[0].distance(p15);
				
		poly.scale(p15, ratio);
		double area2 = poly.area();
		
		assertNotEquals(area, area2);		
		
		double dist2 = poly.getPoints()[0].distance(p15);

		assertEquals(dist1*ratio, dist2, eps);		

		// check the 'toString' function
		String str = poly.toString();
		String[] strArr = str.split(",");
		assertEquals(strArr[0], "Polygon2D");
		assertEquals(strArr[1], "2.6527300699748744");
	}

}
