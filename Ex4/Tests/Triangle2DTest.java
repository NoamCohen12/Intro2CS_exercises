package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Triangle2D;

class Triangle2DTest {
	/*All tests also include "tests to get points"*/

	@Test
	void test() {/////test for "countains"
		Point2D p1=new Point2D(4.03125,7.859375);
		Point2D p2=new Point2D(8.390625,8.109375);
		Point2D p3=new Point2D(6.40625,5.234375);
		Triangle2D tr =new Triangle2D(p1, p2, p3);
		Point2D p4 =new Point2D(6.21875,6.953125);
		Point2D p5=new Point2D(7.71875,5.5625);
		tr.contains(p5);

		tr.contains(p4);

		assertEquals(tr.contains(p4), true);
		assertEquals(tr.contains(p5),false);
	}
	@Test
	void test1() {/////test for "area"
		Point2D p1=new Point2D(0,0);
		Point2D p2=new Point2D(9,9);
		Point2D p3=new Point2D(20,9);
		Triangle2D tr =new Triangle2D(p1, p2, p3);
		double p=tr.area();
		double higth = Math.abs(p1.y()-p2.y());
		double base = Math.abs(p3.x()-p2.x());
		assertEquals(p,(higth*base)/2);
	}
	

	@Test
	void test2() {/////test for "perimeter",calculates the off-screen perimeter
		Point2D p1=new Point2D(5,5);
		Point2D p2=new Point2D(9,9);
		Point2D p3=new Point2D(10,10);
		Triangle2D tr =new Triangle2D(p1, p2, p3);
		double p=tr.perimeter();
		assertEquals(p, p1.distance(p2)+p2.distance(p3)+p3.distance(p1));
	}

	@Test
	void test3() {/////test for "move"
		Point2D p1=new Point2D(10,5);
		Point2D p2=new Point2D(27,9);
		Point2D p3=new Point2D(10,100);
		Triangle2D tr =new Triangle2D(p1, p2, p3);
		Point2D p4=new Point2D(150,100);
		tr.move(p4);
		double p1xnew =tr.getPoints()[0].x();
		double p1ynew =tr.getPoints()[0].y();


		assertEquals(p1xnew,160);
		assertEquals(p1ynew,105);
	}


	@Test
	void test4() {/////test for "copy"
		Point2D p1=new Point2D(5,5);
		Point2D p2=new Point2D(9,9);
		Point2D p3=new Point2D(10,10);
		Triangle2D tr =new Triangle2D(p1, p2, p3);
		tr.copy();
		double p1NewX= tr.getPoints()[0].x();
		double p1NewY= tr.getPoints()[0].y();

		assertEquals(p1.x(),p1NewX);
		assertEquals(p1.y(),p1NewY);

	}
	
	
	@Test
	void test5() {////////////test for "scale 90"
		Point2D p1=new Point2D(2,2);
		Point2D p2=new Point2D(9,9);
		Point2D p3=new Point2D(10,10);
		Triangle2D tr =new Triangle2D(p1, p2, p3);
		tr.scale(p3, 0.9);
		
		Point2D p1new= tr.getPoints()[0];
		Point2D p2new= tr.getPoints()[1];
		

	assertEquals(new Point2D(2.8,2.8),p1new);
	assertEquals(new Point2D(9.1,9.1),p2new);

	}
	@Test
	void test6() {//////////////test for "scale 110"
		Point2D p1=new Point2D(5,5);
		Point2D p2=new Point2D(9,9);
		Point2D p3=new Point2D(10,10);
		Triangle2D tr =new Triangle2D(p1, p2, p3);
		tr.scale(p3, 1.1);
		double p1NewX= tr.getPoints()[0].x();
		double p1NewY= tr.getPoints()[1].y();

		assertEquals(4.5,p1NewX);
		assertEquals(8.9,p1NewY);

	}
	@Test
	void test7() {//////////////test for "rotate"///////////////////////////////////////////
		Point2D p1=new Point2D(5,5);
		Point2D p2=new Point2D(9,9);
		Point2D p3=new Point2D(10,10);
		Triangle2D tr =new Triangle2D(p1, p2, p3);
		tr.scale(p3, 1.1);
		double p1NewX= tr.getPoints()[0].x();
		double p1NewY= tr.getPoints()[1].y();

		assertEquals(4.5,p1NewX);
		assertEquals(8.9,p1NewY);

	}
	
	@Test
	void test8() {//////////////test for "get points"
		Point2D p1=new Point2D(5,5);
		Point2D p2=new Point2D(9,9);
		Point2D p3=new Point2D(10,10);
		Triangle2D tr =new Triangle2D(p1, p2, p3);
	double p1xn=tr.getPoints()[0].x();
	double p1yn=tr.getPoints()[0].y();
	double p2xn=tr.getPoints()[1].x();
	double p2yn=tr.getPoints()[1].y();

		assertEquals(p1.x(),p1xn);
		assertEquals(p1.y(),p1yn);
		assertEquals(p2.x(),p2xn);
		assertEquals(p2.y(),p2yn);
	
	}

	@Test
	void test9() {//////////////test for "to string"
		Point2D p1=new Point2D(5,5);
		Point2D p2=new Point2D(9,9);
		Point2D p3=new Point2D(10,10);
		Triangle2D tr =new Triangle2D(p1, p2, p3);
        tr.toString();

		assertEquals(tr.toString(), "Triangle2D," + p1+","+"9.0,9.0"+","+p3);
	}

}
