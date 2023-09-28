package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {

	//data
	private Point2D p1, p2, p3, p4;
	
	//cunstructur
	public Rect2D(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
		this.p1 = new Point2D(p1);
		this.p2 = new Point2D(p2);
		this.p3 = new Point2D(p3);
		this.p4 = new Point2D(p4);
	}
	
	//functions
	@Override
	public boolean contains(Point2D ot) {
		
		Triangle2D t13 = new Triangle2D(ot, p1, p3);
		Triangle2D t14 = new Triangle2D(ot, p1, p4);
		Triangle2D t23 = new Triangle2D(ot, p2, p3);
		Triangle2D t24 = new Triangle2D(ot, p2, p4);
		double area = area();
		double allArea = t13.area() + t14.area() + t23.area() + t24.area();
		
		if (Math.abs(allArea-area)<=Ex4_Const.EPS) {return true;}

		return false;
	}

	@Override
	public double area() {		
		double a = p1.distance(p3);
		double b = p2.distance(p3);

		return a*b;
	}

	@Override
	public double perimeter() {
		double a = p1.distance(p3);
		double b = p2.distance(p3);
		return 2*(a+b);
	}

	@Override
	public void move(Point2D vec) {

		p1.move(vec);
		p2.move(vec);
		p3.move(vec);
		p4.move(vec);
	}

	@Override
	public GeoShapeable copy() {

		return new Rect2D(this.p1, this.p2, this.p3, this.p4);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		
		p1.scale(center, ratio);
		p2.scale(center, ratio);
		p3.scale(center, ratio);
		p4.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		
		p1.rotate(center,angleDegrees);
		p2.rotate(center,angleDegrees);	
		p3.rotate(center,angleDegrees);	
		p4.rotate(center,angleDegrees);	
	}

	@Override
	public Point2D[] getPoints() {

		Point2D[] points = new Point2D[4];
		points[0]= new Point2D(this.p1);
		points[1]= new Point2D(this.p3);
		points[2]= new Point2D(this.p2);
		points[3]= new Point2D(this.p4);

		return points;
	}
	
	public String toString() {
		
		return "Rect2D," + this.p1+","+this.p2+","+this.p3+","+this.p4;
	}

}
