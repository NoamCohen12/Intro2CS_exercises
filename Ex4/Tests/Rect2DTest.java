package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;

class Rect2DTest {
	/*All tests also include "tests to get points"*/

	@Test
	void test() {//test for "area"
		Point2D p1=new Point2D(1.71875,8.15625);
		Point2D p2=new Point2D(5.734375,4.640625);
		Point2D p3=new Point2D(p1.x(),p2.y());
		Point2D p4=new Point2D(p1.y(),p2.x());
		double d=p1.distance(p3);
		double di=p3.distance(p2);
		double area = d*di;
		Rect2D rec	=new Rect2D(p1, p2,p3,p4);
		assertEquals(area, rec.area());
	}
	@Test
	void test1() {//test for "perimeter"
		Point2D p1=new Point2D(1.71875,8.15625);
		Point2D p2=new Point2D(5.734375,4.640625);
		Point2D p3=new Point2D(p1.x(),p2.y());
		Point2D p4=new Point2D(p1.y(),p2.x());

		double d=p1.distance(p3);
		double di=p3.distance(p2);
		double perimeter = (d+di)*2;
		Rect2D rec	=new Rect2D(p1, p2,p3,p4);
		assertEquals(perimeter, rec.perimeter());
	}
	@Test
	void test2() {///test for "move"
		Point2D p1=new Point2D(7,1);
		Point2D p2=new Point2D(1,8);
		Point2D p3=new Point2D(1,1);
		Point2D p4=new Point2D(7,8);

		Rect2D rec	=new Rect2D(p1,p2, p3,p4);
		Point2D vec =new Point2D(2,2);
		rec.move(vec);
		assertEquals(rec.getPoints()[0],new Point2D (9,3));
		assertEquals(rec.getPoints()[1],new Point2D (3,3));
	}
	@Test
	void test3() {///test for "copy"
		Point2D p1=new Point2D(2,2.661151);
		Point2D p2=new Point2D(1,4.54544);
		Point2D p3=new Point2D(1,2.661151);
		Point2D p4=new Point2D(2,4.54544);


		Rect2D rec	=new Rect2D(p1, p2,p3,p4);
		rec.copy();
		Point2D p1new= rec.getPoints()[0];
		Point2D p3new= rec.getPoints()[1];

		assertEquals(p1new,p1);
		assertEquals(p3new,p3);
	}

	@Test////////////////test for scale 90
	void test4() {

		Point2D p1=new Point2D(0,5);
		Point2D p2=new Point2D(5,0);
		Point2D p3=new Point2D(0,0);
		Point2D p4=new Point2D(5,5);
		
		Rect2D rec	=new Rect2D(p1, p2,p3,p4);
		double eps = 0.01;
		double ratio = 0.9;
		Point2D cen = new Point2D(6, 6);
		double dist1 = rec.getPoints()[0].distance(cen);
		double area1 = rec.area();	
		
		rec.scale(cen, ratio);
		double area2 = rec.area();
		
		assertNotEquals(area1, area2);		
		
		double dist2 = rec.getPoints()[0].distance(cen);

		assertEquals(dist1*ratio, dist2, eps);
		
	}
	@Test////////////////test for scale 110
	void test5() {

		Point2D p1=new Point2D(0,5);
		Point2D p2=new Point2D(5,0);
		Point2D p3=new Point2D(0,0);
		Point2D p4=new Point2D(5,5);
		
		Rect2D rec	=new Rect2D(p1, p2,p3,p4);
		double eps = 0.01;
		double ratio = 1.1;
		Point2D cen = new Point2D(6,6);
		double dist1 = rec.getPoints()[0].distance(cen);
		double area1 = rec.area();	
		
		rec.scale(cen, ratio);
		double area2 = rec.area();
		
		assertNotEquals(area1, area2);		
		
		double dist2 = rec.getPoints()[0].distance(cen);

		assertEquals(dist1*ratio, dist2, eps);
		
	}


	@Test////////////////test for rotate with negtive number
	void test6() {
		Point2D p1=new Point2D(0,2.661151);
		Point2D p2=new Point2D(-1,4.54544);
		Point2D p3=new Point2D(1,2.661151);
		Point2D p4=new Point2D(2,4.54544);


		Rect2D rec	=new Rect2D(p1, p2,p3,p4);
		rec.rotate(p3, 90);
		p1.rotate(p3, 90);
		p2.rotate(p3, 90);
		double p1NXS =rec.getPoints()[0].x();
		double p1NYS =rec.getPoints()[0].y();
		double p1NX =p1.x();
		double p1NY =p1.y();
		double p2NXS =rec.getPoints()[2].x();
		double p2NYS =rec.getPoints()[2].y();
		double p2NX =p2.x();
		double p2NY =p2.y();

		assertEquals(p1NXS, p1NX);
		assertEquals(p1NYS, p1NY);
		assertEquals(p2NXS, p2NX);
		assertEquals(p2NYS, p2NY);
	}

	@Test
	void test7() {/////////////test for "area"
		Point2D p1=new Point2D(2,2);
		Point2D p2=new Point2D(4,4);
		Point2D p3=new Point2D(p1.y(),p2.x());
		Point2D p4=new Point2D(p1.x(),p2.y());
		Rect2D rec	= new Rect2D(p1, p2,p3,p4);
		double	area= rec.area();

		assertEquals(area,4);;
	}





	@Test
	void test8() {///test for "to string"
		Point2D p1=new Point2D(5.54,8.66);
		Point2D p2=new Point2D(1.46464,4.54544);
		Point2D p3=new Point2D(p1.y(),p2.x());
		Point2D p4=new Point2D(p1.x(),p2.y());

		Rect2D rec	= new Rect2D(p1, p2,p3,p4);
		rec.toString();

		String str =  "Rect2D," + p1+","+"1.46464,4.54544"+","+p3+","+p4;

		assertEquals(str, rec.toString());
	}









}
