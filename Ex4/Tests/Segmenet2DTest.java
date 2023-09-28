package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;

class Segmenet2DTest {
	/*All tests also include "tests to get points"*/

	@Test
	void test() {/////////////test for "countains"
		Point2D p1=new Point2D(2.28125,4.328125);
		Point2D p2=new Point2D(8.125,8.234375);
		Point2D p3=new Point2D(4.859375,6.046875);

		Segment2D s=	new Segment2D(p1,p2);
		assertEquals(s.contains(p3), true);;
	}

	@Test
	void test1() {/////////////test for "perimeter"
		Point2D p1=new Point2D(2.28125,4.328125);
		Point2D p2=new Point2D(8.125,8.234375);
		double dist=	(p1.distance(p2))*2;

		Segment2D s=	new Segment2D(p1,p2);
		double perimeter =s.perimeter();
		assertEquals(dist,perimeter);
	}

	@Test
	void test2() {/////////////test for "move"
		Point2D p1=new Point2D(8,6);
		Point2D p2=new Point2D(4,7);
		Segment2D s=new Segment2D(p1,p2);
		Point2D p3=new Point2D(4,2);

		s.move(p3);
		Point2D p1new = new Point2D(s.getPoints()[0].x() , s.getPoints()[0].y() ) ;
		Point2D p2new = new Point2D(s.getPoints()[1].x() , s.getPoints()[1].y() ) ;

		assertEquals(p1new.x(),12);
		assertEquals(p1new.y(),8);
		assertEquals(p2new.x(),8);
		assertEquals(p2new.y(),9);

	}
	@Test
	void test3() {//////////////test for "copy"
		Point2D p1=new Point2D(8,6);
		Point2D p2=new Point2D(4,7);
		Segment2D s=new Segment2D(p1,p2);
		s.copy();
		double p1X=s.getPoints()[0].x();
		double p1Y=s.getPoints()[0].y();
		double p2X=	s.getPoints()[1].x();
		double p2Y=s.getPoints()[1].y();
		assertEquals(p1X, 8);
		assertEquals(p1Y, 6);
		assertEquals(p2X, 4);
		assertEquals(p2Y, 7);
	}

	@Test
	void test4() {//////////////test for "rotate"
		Point2D p1=new Point2D(2,2);
		Point2D p2=new Point2D(6,2);
		Point2D p3=new Point2D(2,4);
		Segment2D s=new Segment2D(p1,p2);
		s.rotate(p3, 90);
		p1.rotate(p3, 90);
		p2.rotate(p3, 90);
		double p1NXS =s.getPoints()[0].x();
		double p1NYS =s.getPoints()[0].y();
		double p1NX =p1.x();
		double p1NY =p1.y();
		double p2NXS =s.getPoints()[1].x();
		double p2NYS =s.getPoints()[1].y();
		double p2NX =p2.x();
		double p2NY =p2.y();

		assertEquals(p1NXS, p1NX);
		assertEquals(p1NYS, p1NY);
		assertEquals(p2NXS, p2NX);
		assertEquals(p2NYS, p2NY);


	}
	@Test
	void test5()//////test for scale 90
	{
		
		Point2D p1=new Point2D(1,1);
		Point2D p2=new Point2D(3,3);
		Segment2D s=new Segment2D(p1,p2);
		
		Point2D cen = new Point2D(6,6);
		double eps = 0.1;
		double ratio = 0.9;
		double per1 = s.perimeter();
		
		s.scale(cen, ratio);
		
		double dist = p2.distance(cen);
		double per2 = s.perimeter();

		assertEquals(4.2, dist, eps);
		assertEquals(per1*ratio, per2, eps);
		
	}
	@Test
	void test6()//////test for scale 110
	{
		
		Point2D p1=new Point2D(1,1);
		Point2D p2=new Point2D(3,3);
		Segment2D s=new Segment2D(p1,p2);
		
		Point2D cen = new Point2D(6,6);
		double eps = 0.1;
		double ratio = 1.1;
		double per1 = s.perimeter();
		
		s.scale(cen, ratio);
		
		double dist = p2.distance(cen);
		double per2 = s.perimeter();

		assertEquals(4.2, dist, eps);
		assertEquals(per1*ratio, per2, eps);

	}
	
	
	
	
	
	
	
	
	


	@Test
	void test7() {/////////////test for "area"
		Point2D p1=new Point2D(2.281311222222222222222222225,4.32455555555555555555558125);
		Point2D p2=new Point2D(8.122154444444444444444445,8.2344545445456456456464646465475);

		Segment2D s=	new Segment2D(p1,p2);
		double	area= s.area();
		assertEquals(area,0);
		
	}
	@Test
	void test8() {///test for "to string"
		Point2D p1=new Point2D(5.54,8.66);
		Point2D p2=new Point2D(1.46464,4.54544);
		Segment2D s=	new Segment2D(p1,p2);

		String str =  "Segment2D," +"5.54,8.66"+","+p2;
		
		assertEquals(str, s.toString());
		}
	@Test
	void test9() {///test for "get points"
		Point2D p1=new Point2D(5.54,8.66);
		Point2D p2=new Point2D(1.46464,4.54544);
		Segment2D s=	new Segment2D(p1,p2);

double p1xg=s.getPoints()[0].x();
double p1yg=s.getPoints()[0].y();

double p2xg=s.getPoints()[1].x();
double p2yg=s.getPoints()[1].y();

		assertEquals(p1xg, p1.x());
		assertEquals(p1yg,p1.y());
		assertEquals(p2xg, p2.x());
		assertEquals(p2yg, p2.y());

		}
	
	



}
