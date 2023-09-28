package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;

class Circle2DTest {

   @Test
   public void test(){ //test countains
		Point2D p1=new Point2D (5,5);
		Point2D p2=new Point2D (7,5);
	    double dist = p1.distance(p2);
		
	    Circle2D c1 = new Circle2D(p1,dist);
   
		Point2D p3=new Point2D (6,5);
		boolean counteins =c1.contains(p3);
		assertEquals(true,counteins);
	}
   
	@Test
	void test1() { //scale
		Point2D p1 = new Point2D (7,5);
		double rad1 =5;
		Circle2D c1 = new Circle2D(p1,rad1);
		double ratio1=0.9;
		double area1 = c1.area();
		Point2D cen = new Point2D(2,3);
		
		c1.scale(cen, ratio1);
		double area2 = c1.area();
		double rad2 = c1.getRadius();

		assertNotEquals(area1, area2);
		assertEquals(rad2, rad1*ratio1);
		
		double ratio2=1.1;
		c1.scale(cen, ratio2);
		double area3 = c1.area();
		double rad3 = c1.getRadius();
		
		assertNotEquals(area2, area3);
		assertEquals(rad3, rad2*ratio2);
	}	
	
	@Test //rotate
	void test2() {
		
		Point2D p1 = new Point2D (7,5);
		double rad1 = 5;
		Circle2D c1 = new Circle2D(p1,rad1);
		Point2D cen = new Point2D(7, 0);
		c1.rotate(cen, 90);
		
		Point2D p2 = new Point2D(2.53, -2.25);
		
		assertEquals(c1.getPoints()[0].x(), p2.x(), 0.01);
		assertEquals(c1.getPoints()[0].y(), p2.y(), 0.01);
	}

}
