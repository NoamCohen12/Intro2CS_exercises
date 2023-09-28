package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{
	//data
	private Point2D p1;
	private Point2D p2;
	private Point2D p3;

	//cunstructur 
	public Triangle2D(Point2D p1, Point2D p2, Point2D p3) {
		this.p1=new Point2D(p1);
		this.p2=new Point2D(p2);
		this.p3=new Point2D(p3);
	}

	//functions
	@Override
	public boolean contains(Point2D ot) {
		double area = area();

		Triangle2D t12 = new Triangle2D(ot, p1, p2);
		Triangle2D t13 = new Triangle2D(ot, p1, p3);
		Triangle2D t23 = new Triangle2D(ot, p2, p3);
		
		// Calculation of the area of three triangles and with a point selected
		double allArea = t12.area() + t13.area() + t23.area();
		
		// If the sum of the areas of the three small triangles is equal to the area of the large triangle,
		// then the points that created the small triangles are inside it
		if (Math.abs(allArea-area)<=Ex4_Const.EPS) {return true;}
		
		return false;
	}

	@Override
	public double area() {//Formula for finding the area of a triangle with three points
		return Math.abs(0.5 * (p1.x() * (p2.y() - p3.y())
				+ p2.x() * (p3.y() - p1.y()) + p3.x() * (p1.y() - p2.y())));
	}

	@Override
	public double perimeter() {
		double dis1=p1.distance(p2);
		double dis2=p2.distance(p3);
		double dis3=p3.distance(p1);
		return dis1 + dis2 + dis3;
	}

	@Override
	public void move(Point2D vec) {
		p1.move(vec);
		p2.move(vec);
		p3.move(vec);		
	}

	@Override
	public GeoShapeable copy() {
		return new Triangle2D(this.p1,this.p2, this.p3);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		
		p1.scale(center, ratio);
		p2.scale(center, ratio);
		p3.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {

		p1.rotate(center,angleDegrees);
		p2.rotate(center, angleDegrees);
		p3.rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		
		Point2D[] points =new Point2D [3];
		points[0]=p1;
		points[1]=p2;
		points[2]=p3;

		return points;
	}
	
	public String toString() {
		
		return "Triangle2D," + p1+","+p2+","+p3;
	}

}
