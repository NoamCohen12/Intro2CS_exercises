package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;
import Exe.Ex4.gui.Ex4;

class Ex4Test {

	private Ex4 ex4 =Ex4.getInstance();
	@Test
	void testInit() {
		assertNotNull(ex4);//Checking whether a copy has been created

		ex4.init(null);

		assertNotNull(ex4.getShape_Collection());//Checking whether  has been created ShapeCollection
		assertEquals(0, ex4.getShape_Collection().size());//test shape_collection size after init null

		Point2D p1=new Point2D(1.71875,8.15625);
		Point2D p2=new Point2D(5.734375,4.640625);
		Point2D p3=new Point2D(p1.x(),p2.y());
		Point2D p4=new Point2D(p1.y(),p2.x());
		Rect2D rec	=new Rect2D(p1, p2,p3,p4);

		Point2D p5=new Point2D(2.28125,4.328125);
		Point2D p6=new Point2D(8.125,8.234375);
		Segment2D s=new Segment2D(p5,p6);

		Point2D p7=new Point2D (5,5);
		Point2D p8=new Point2D (7,5);
		double dist = p7.distance(p8);
		Circle2D c = new Circle2D(p7,dist);

		ShapeCollection sc = new ShapeCollection();
		sc.add(new GUIShape(c, false, null, 0));
		sc.add(new GUIShape(s, false, null, 0));
		sc.add(new GUIShape(rec, false, null, 0));

		ex4.init(sc);

		ShapeCollectionable objSc = ex4.getShape_Collection();

		assertEquals(3, objSc.size());//testing after init sc size 3
		assertArrayEquals(c.getPoints(), objSc.get(0).getShape().getPoints());//testing for chack if the shapes into objsc
		assertArrayEquals(s.getPoints(), objSc.get(1).getShape().getPoints());
		assertArrayEquals(rec.getPoints(), objSc.get(2).getShape().getPoints());
	}

	@Test
	void testInfo() {
		ex4.init(null);

		Point2D p1=new Point2D(1.71875,8.15625);
		Point2D p2=new Point2D(5.734375,4.640625);
		Point2D p3=new Point2D(p1.x(),p2.y());
		Point2D p4=new Point2D(p1.y(),p2.x());
		Rect2D rec	=new Rect2D(p1, p2,p3,p4);

		Point2D p7=new Point2D (5,5);
		Point2D p8=new Point2D (7,5);
		double dist = p7.distance(p8);
		Circle2D c = new Circle2D(p7,dist);

		Point2D p9=new Point2D(4.03125,7.859375);
		Point2D p10=new Point2D(8.390625,8.109375);
		Point2D p11=new Point2D(6.40625,5.234375);
		Triangle2D tr =new Triangle2D(p9, p10, p11);

		ShapeCollection sc = new ShapeCollection();
		GUIShape rect=new GUIShape(rec, false, Color.black, 0);
		GUIShape circle = new GUIShape(c, false, Color.blue, 0);
		GUIShape triangle = new GUIShape(tr, false, Color.green, 0);

		sc.add(rect);
		sc.add(circle);
		sc.add(triangle);

		ex4.init(sc);

		String s = ((GUI_Shapeable)rect).toString() + '\n';
		s += ((GUI_Shapeable)circle).toString() + '\n';
		s += ((GUI_Shapeable)triangle).toString() + '\n';



		assertEquals(s, ex4.getInfo());//testing if the string is same or diffrent




	}

	@Test
	void testgetShapeCollection() {
		ex4.init(null);

		Point2D p1=new Point2D(1.71875,8.15625);
		Point2D p2=new Point2D(5.734375,4.640625);
		Point2D p3=new Point2D(p1.x(),p2.y());
		Point2D p4=new Point2D(p1.y(),p2.x());
		Rect2D rec	=new Rect2D(p1, p2,p3,p4);

		Point2D p7=new Point2D (5,5);
		Point2D p8=new Point2D (7,5);
		double dist = p7.distance(p8);
		Circle2D c = new Circle2D(p7,dist);

		Point2D p9=new Point2D(4.03125,7.859375);
		Point2D p10=new Point2D(8.390625,8.109375);
		Point2D p11=new Point2D(6.40625,5.234375);
		Triangle2D tr =new Triangle2D(p11, p9, p10);





		ShapeCollection sc = new ShapeCollection();
		GUIShape rect=new GUIShape(rec, false, Color.black, 0);
		GUIShape circle = new GUIShape(c, false, Color.blue, 0);
		GUIShape triangle = new GUIShape(tr, false, Color.green, 0);
		GUIShape poligon = new GUIShape(tr, false, Color.yellow, 0);

		sc.add(rect);
		sc.add(circle);
		sc.add(triangle);

		ex4.init(sc);

		assertEquals(sc.toString(), ex4.getShape_Collection().toString());

		ex4.init(null);
		assertEquals(ex4.getShape_Collection().toString(),"");
		
		sc.add(poligon);
		ex4.init(sc);

		assertEquals(sc.toString(), ex4.getShape_Collection().toString());//testing if the string is same or diffrent
 

	}


	//init, getShapeCollection, getInto

}
