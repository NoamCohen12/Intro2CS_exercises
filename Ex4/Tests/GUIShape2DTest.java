package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Triangle2D;
/*In all the tests, both init and tostring tests are combined. In any case, for visibility, we split it into two tests*/
class GUIShape2DTest {
	private LinkedList<Point2D> kodkod = new LinkedList<Point2D>();
	private GUIShape gu = null;

	@Test
	void testInit() {

		String s = "GUIShape,-16776961,false,1,Triangle2D,7.375,7.109375,2.09375,6.53125,3.734375,1.921875";//information of triangle



		String [] ww0 = s.split(",");

		assertEquals(ww0[4], "Triangle2D");
		assertEquals(ww0[2], "false");

		Point2D p9=new Point2D(4.03125,7.859375);
		Point2D p10=new Point2D(8.390625,8.109375);
		Point2D p11=new Point2D(6.40625,5.234375);
		Triangle2D tr =new Triangle2D(p9, p10, p11);

		GUIShape triangle = new GUIShape(tr, false, Color.green, 0);
		

///////////////////////////////////
		
		
		
		
		String	st = ((GUI_Shapeable)triangle).toString();

		String [] ww1 = st.split(",");

		assertEquals(ww1[4], "Triangle2D");//checking if the string correct
		assertEquals(ww1[2], "false");
 ////////////////////////////////////////
		
		
		
		String str ="GUIShape,-16776961,false,1,Circle2D,2,6,7";
		String [] ww2 = str.split(",");
		assertEquals(ww2[4], "Circle2D");//checking if the string correct
		assertEquals(ww2[2], "false");

		Circle2D c = new Circle2D(new Point2D (2,6),7);   
		GUIShape  Circ  = new GUIShape(c, false, Color.green, 0);
		gu = new GUIShape(str);
		assertEquals(c.getPoints()[0], gu.getShape().getPoints()[0]);///Checking whether the gu has created the correct string
		assertEquals(c.getPoints()[1], gu.getShape().getPoints()[1]);

///////////////////////////////
		
		
		String	stri = ((GUI_Shapeable)Circ).toString();
		String [] ww3 = stri.split(",");
		assertEquals(ww3[4], "Circle2D");//checking if the string correct
		assertEquals(ww3[2], "false");

	}
	@Test
	void testTostring() {
		String string = "GUIShape,-65536,false,2,Polygon2D,1,10,2.0,7.0,7.0,7.0,4.0,4.0,8.0,50.0"; // A polygon data 
		String [] ww4 = string.split(",");
		assertEquals(ww4[2], "false");
		
		gu = new GUIShape(string);
		kodkod.add(new Point2D(1,10));
		kodkod.add(new Point2D(2,7));
		kodkod.add(new Point2D(7,7));
		kodkod.add(new Point2D(4,4));		
		kodkod.add(new Point2D(8,50));

		Polygon2D p = new Polygon2D(kodkod);
		
		for (int i = 0; i<kodkod.size(); i++) {
			assertEquals(gu.getShape().getPoints()[0],p.getPoints()[0]);////checking if the string correct
		}
		assertEquals(gu.getColor().getRGB(),Color.RED.getRGB());	//chacking color in the string
	}
	



}
