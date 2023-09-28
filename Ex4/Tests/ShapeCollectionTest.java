package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.Color;
import java.io.File;

import org.junit.jupiter.api.Test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;

class ShapeCollectionTest {

	public Point2D p1 = new Point2D(1, 7);
	public Point2D p2 = new Point2D(5, 2);
	public Point2D p3 = new Point2D(1, 2);
	public Point2D p4 = new Point2D(5, 7);

	public Rect2D _rect = new Rect2D(p1, p2, p3, p4);
	public Circle2D _circle = new Circle2D(p2, 5);
	public Segment2D _segment = new Segment2D(p3, p4);
	public Triangle2D _triangle = new Triangle2D(p1, p2, p3);
	public Triangle2D _triangle2 = new Triangle2D(p4, p3, p2);

	GUI_Shapeable gs1 = new GUIShape(_rect, true, Color.BLUE, 0);
	GUI_Shapeable gs2 = new GUIShape(_circle, false, Color.BLACK, 1);
	GUI_Shapeable gs3 = new GUIShape(_segment, true, Color.RED, 2);
	GUI_Shapeable gs4 = new GUIShape(_triangle, false, Color.GREEN, 3);
	GUI_Shapeable gs5 = new GUIShape(_triangle2, false, Color.YELLOW, 4);

	
	public ShapeCollection _shapes1 = new ShapeCollection();
	public ShapeCollection _shapes2 = new ShapeCollection();	

	@Test
	void shapeCollectionTest() {

		_shapes1.add(gs1);
		_shapes1.add(gs2);
		_shapes1.add(gs3);
		_shapes1.add(gs4);

		_shapes2 = (ShapeCollection) _shapes1.copy();

		// checking the 'copy' and 'size' functions
		assertEquals(_shapes1.size(), _shapes2.size());

//		 checking the 'add' and 'get' functions
		for (int i = 0; i<_shapes1.size(); i++) {
			assertEquals(_shapes1.get(i).getShape().getPoints()[0], _shapes2.get(i).getShape().getPoints()[0]);
			assertEquals(_shapes1.get(i).getShape().area(), _shapes2.get(i).getShape().area());
			assertEquals(_shapes1.get(i).getShape().perimeter(), _shapes2.get(i).getShape().perimeter());
		}
		
		// checking 'addAt' and 'removeAt' functions
		
		_shapes1.addAt(gs5, 2);
		
		assertNotEquals(_shapes1.get(2).getShape().getPoints()[0], _shapes2.get(2).getShape().getPoints()[0]);
		
		_shapes1.removeElementAt(2);
		
		assertEquals(_shapes1.get(2).getShape().getPoints()[0], _shapes2.get(2).getShape().getPoints()[0]);

		// checking 'remove all', 'save' and 'load' functions
		_shapes1.save("C:\\Users\\meyu2\\Downloads\\a10");

		_shapes1.removeAll();
		
		// make sure the collection is empty
		assertEquals(_shapes1.size(), 0);

		_shapes1.load("C:\\Users\\meyu2\\Downloads\\a10");

		// running the same checks we ren above
		assertEquals(_shapes1.size(), _shapes2.size());

		for (int i=0; i<_shapes1.size(); i++) {
			assertEquals(_shapes1.get(i).getShape().getPoints()[0], _shapes2.get(i).getShape().getPoints()[0]);
			assertEquals(_shapes1.get(i).getShape().area(), _shapes2.get(i).getShape().area());
			assertEquals(_shapes1.get(i).getShape().perimeter(), _shapes2.get(i).getShape().perimeter());
		}
		
        new File("C:\\Users\\meyu2\\Downloads\\a10").delete();

        // checking the 'toString' function
       String info = _shapes1.toString();
       assertEquals(info, "GUIShape,-16776961,true,0,Rect2D,1.0,7.0,5.0,2.0,1.0,2.0,5.0,7.0"
       					+ "GUIShape,-16777216,false,1,Circle2D,5.0,2.0, 5.0"
       					+ "GUIShape,-65536,true,2,Segment2D,1.0,2.0,5.0,7.0"
       					+ "GUIShape,-16711936,false,3,Triangle2D,1.0,7.0,5.0,2.0,1.0,2.0");
       
       // checking 'getBoundingBox' function
       Point2D p10 = new Point2D(0, 7);
       Point2D p11 = new Point2D(0, -3);
       Point2D p12 = new Point2D(10, -3);
       Point2D p13 = new Point2D(10, 7);
     
       Point2D[] box = {p10, p11, p12, p13};
       Rect2D rec1 = _shapes1.getBoundingBox();

      assertArrayEquals(rec1.getPoints(), box);
       
	}

	
	// sort - we used the java comperator
	// remove all - used java array list function

}
