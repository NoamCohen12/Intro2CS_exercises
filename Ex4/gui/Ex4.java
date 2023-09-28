package Exe.Ex4.gui;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JFrame;


import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.ShapeComp;
import Exe.Ex4.geo.Triangle2D;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 * @author
 * Noam Cohen
 */
public class Ex4 implements Ex4_GUI{
	private  ShapeCollectionable _shapes = new ShapeCollection();
	private  GUI_Shapeable _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private int tag = 1;
	private  String _mode = "";
	private  Point2D _p1;
	private  Point2D _p2;
	private double angle;
	private LinkedList<Point2D> kodkod = new LinkedList<Point2D>();	// for the polygon shape
	public static final Point2D ORIGIN = new Point2D(0,0);
	

	private  static Ex4 _winEx4 = null;

	private Ex4() {
		init(null);
	}
	public void init(ShapeCollectionable s) {
		if(s==null) {_shapes = new ShapeCollection();}
		else {_shapes = s.copy();}
		GUI_Shapeable _gs = null;
		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		Point2D _p1 = null;
	}
	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}
	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	public void drawShapes() {
		StdDraw_Ex4.clear();
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable sh = _shapes.get(i);

			drawShape(sh);
		}
		if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}
	private static void drawShape(GUI_Shapeable g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if(g.isSelected()) {StdDraw_Ex4.setPenColor(Color.gray);}
		GeoShapeable gs = g.getShape();
		boolean isFill = g.isFilled();
		if(gs instanceof Circle2D) {
			Circle2D c = (Circle2D)gs;
			Point2D cen = c.getPoints()[0];
			double rad = c.getRadius();
			if(isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			}
			else { 
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}

		}

		if(gs instanceof Segment2D) {

			Segment2D s = (Segment2D)gs;
			Point2D f = s.getPoints()[0];
			Point2D l = s.getPoints()[1];
			StdDraw_Ex4.line(f.x(), f.y(), l.x(), l.y());
		}

		// we draw the rectangle using the polygon draw function,
		// so we can use it also when the rect is rotated
		if(gs instanceof Rect2D) {
			Rect2D rec = (Rect2D)gs;
			Point2D[] points = rec.getPoints();

			// create and fill an x and y arrays for the drawing (as a polygon)
			double[] recX = new double[points.length];
			double[] recY = new double[points.length];

			for (int i=0; i<points.length; i++) {
				recX[i] = points[i].x();
				recY[i] = points[i].y();
			}

			if(isFill) {
				StdDraw_Ex4.filledPolygon(recX, recY);
			}
			else { 
				StdDraw_Ex4.polygon(recX, recY);
			}
		}
		/////////////////////////////////////////////////////
		if(gs instanceof Triangle2D) {
			Triangle2D tri = (Triangle2D)gs;
			Point2D p1 = tri.getPoints()[0];
			Point2D p2 =tri.getPoints()[1];
			Point2D p3 =tri.getPoints()[2];
			double [] pointX = {p1.x(),p2.x(),p3.x()};
			double [] pointY = {p1.y(),p2.y(),p3.y()};

			if(isFill) {
				StdDraw_Ex4.filledPolygon(pointX, pointY);
			}
			else { 
				StdDraw_Ex4.polygon(pointX, pointY);
			}

		}
		//////////////////////////
		if(gs instanceof Polygon2D) {
			Polygon2D poly = (Polygon2D)gs;
			Point2D[] polyPoints = poly.getPoints();
			double[] polyX = new double[polyPoints.length];
			double[] polyY = new double[polyPoints.length];
			for (int i=0; i<polyPoints.length; i++) {
				polyX[i] = polyPoints[i].x();
				polyY[i] = polyPoints[i].y();
			}
			if(isFill) {
				StdDraw_Ex4.filledPolygon(polyX, polyY);
			}
			else { 
				StdDraw_Ex4.polygon(polyX, polyY);
			}
		}
		//////////////////////////////
	}
	private void setColor(Color c) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}
	private void setFill() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	public void actionPerformed(String p) {
		_mode = p;
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}
		if(p.equals("Clear")) {_shapes.removeAll();}


		///////////////////////////////////////
		if(_mode.equals("None")) {
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shapeable s = _shapes.get(i);
				s.setSelected(false);
			}
		}

		if(_mode.equals("All")) {
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shapeable s = _shapes.get(i);
				s.setSelected(true);
			}
		}

		if(_mode.equals("Anti")) {
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shapeable s = _shapes.get(i);
				if(s.isSelected()==false) {
					s.setSelected(true);	
				}
				else {
					s.setSelected(false);
				}
			}
		}
		// delete all the selected shapes
		if(_mode.equals("Remove")) {

			for(int i=_shapes.size()-1; i>=0; i--) {
				GUI_Shapeable s = _shapes.get(i);
				if(s.isSelected()) {
					_shapes.removeElementAt(i);
				}			
			}
		}

		if(_mode.equals("Info")) {

			for (int i =0;i<_shapes.size();i++) {
				System.out.println(_shapes.get(i).toString());
			}

		}
		if(_mode.equals("Save")) {

			FileDialog chooser = new FileDialog(new JFrame(), "Use a .png or .jpg extension", FileDialog.SAVE);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			if (filename != null) {
				_shapes.save(chooser.getDirectory() + File.separator + chooser.getFile());
			}
		}
		if(_mode.equals("Load")) {
			FileDialog chooser = new FileDialog(new JFrame(), "Use a .png or .jpg extension", FileDialog.LOAD);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			if (filename != null) {
				_shapes.load(chooser.getDirectory() + File.separator + chooser.getFile());
			}

		}

		if(_mode.equals("ByArea")) {
			_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Area));
		}
		if(_mode.equals("ByAntiArea")) {
			_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Area));
		}
		if(_mode.equals("ByPerimeter")) {
			_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Perimeter));
		}
		if(_mode.equals("ByAntiPerimeter")) {
			_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter));
		}
		if(_mode.equals("ByTag")) {
			_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Tag));
		}
		if(_mode.equals("ByAntiTag")) {
			_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Tag));
		}
		if(_mode.equals("ByToString")) {
			_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_toString));
		}
		if(_mode.equals("ByAntiToString")) {
			_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_toString));
		}

		drawShapes();
	}
	public void mouseClicked(Point2D p) {
		System.out.println("Mode: "+_mode+"  "+p);
		if(_mode.equals("Circle")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(tag);
				tag++;
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}

		if(_mode.equals("Segment")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(tag);
				tag++;
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}

		if(_mode.equals("Rect")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(tag);
				tag++;
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}

		if(_mode.equals("Triangle")) {
			if(_gs==null) {
				_p1 = new Point2D(p);	
			}
			else if(_p2==null) {
				_p2 =new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(tag);
				tag++;
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				_p2 = null;
			}
		}

		if(_mode.equals("Polygon")) {
			if(_gs==null) {
				kodkod.add(p);
				_p1 = p;
			}
			else {
				kodkod.add(p);
			}
		}

		if(_mode.equals("Move")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				_p1 = _p1.vector(p);		// finding the vector to move the selected shapes by it
				move();
				_p1 = null;
				_gs = null;
			}
		}

		if(_mode.equals("Copy")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				_p1 = _p1.vector(p);		// finding the vector to move the copied shape from the original shape
				copy();
				_p1 = null;
				_gs = null;
			}
		}

		if(_mode.equals("Rotate")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				angle = Math.atan2(p.y()-_p1.y(), p.x()-_p1.x());			
				rotate();
				_p1 = null;
				_gs = null;
			}
		}

		// scale the selected shapes by 90%
		if(_mode.equals("Scale_90%")){
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shapeable s = _shapes.get(i);
				GeoShapeable g = s.getShape();
				if(s.isSelected() && g!=null) {
					g.scale(p, 0.9);
				}
			}
		}

		// scale the selected shapes by 110%
		if(_mode.equals("Scale_110%")){
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shapeable s = _shapes.get(i);
				GeoShapeable g = s.getShape();
				if(s.isSelected() && g!=null) {
					g.scale(p, 1.1);
				}
			}
		}

		if(_mode.equals("Point")) {
			select(p);
		}

		drawShapes();
	}
	private void select(Point2D p) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	private void copy() {

		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();

			if(s.isSelected() && g!=null) {
				GUI_Shapeable copy = s.copy();
				copy.getShape().move(_p1);
				_shapes.add(copy);
			}
		}
	}

	private void move() {

		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.move(_p1);
			}
		}
	}

	private void rotate() {

		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {	
				g.rotate(_p1, angle);
			}
		}
	}


	public void mouseRightClicked(Point2D p) {

		if (_gs!=null) {
			if(_mode.equals("Polygon")) {
				GeoShapeable gs = new Polygon2D(kodkod);
				_gs = new GUIShape(gs,_fill, _color, tag);	
				tag++;
				_shapes.add(_gs);
				kodkod = new LinkedList<Point2D>();
			}
			_p1 = null;
			_gs = null;
			drawShapes();
		}
		System.out.println("right click!");
	}
	public void mouseMoved(MouseEvent e) {
		if(_p1!=null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShapeable gs = null;
			//			System.out.println("M: "+x1+","+y1);
			Point2D p = new Point2D(x1,y1);

			if(_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle2D(_p1,r);
			}

			if(_mode.equals("Segment")) {
				gs = new Segment2D(_p1,p);
			}
			if(_mode.equals("Rect")) {
				Point2D p3 = new Point2D(_p1.x(), p.y());
				Point2D p4 = new Point2D(p.x(), _p1.y());
				gs = new Rect2D(_p1, p, p3, p4);
			}

			if(_mode.equals("Triangle")) {
				if(_p2!=null) {
					gs =new Triangle2D(_p1,_p2,p);
				}
				else {
					gs=new Segment2D(_p1,p);
				}

			}

			if(_mode.equals("Polygon")) {

				LinkedList<Point2D> tmp = kodkod;
				tmp.add(p);
				gs = new Polygon2D(tmp);
				tmp.remove(p);
			}
			_gs = new GUIShape(gs,false, Color.pink, tag);
			tag++;

			drawShapes();
		}
	}
	@Override
	public ShapeCollectionable getShape_Collection() {
		return this._shapes;
	}
	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }
	@Override
	public String getInfo() {
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
}
