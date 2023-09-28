package Exe.Ex4.geo;


/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable{

	// data
	private Point2D p1;
	private Point2D p2;
	
	// constructor
	public Segment2D(Point2D p1, Point2D p2) {
		this.p1 = new Point2D(p1);
		this.p2 = new Point2D(p2);
	}
	
	//functions
	@Override
	public boolean contains(Point2D ot) {
		double d1 =	ot.distance(this.p1);
		double d2 =	ot.distance(this.p2);
		double d3 = d1+d2;
		double eps = 0.01;
		
		double dist = p1.distance(p2);
		if (Math.abs(dist-d3)<=eps) {
			return true;
		}

	return false;
	}

	@Override
	public double area() {

		return 0;
	}

	@Override
	public double perimeter() {
		
		return (p1.distance(p2))*2;
	}

	@Override
	public void move(Point2D vec) {

		p1.move(vec);
		p2.move(vec);
	}

	@Override
	public GeoShapeable copy() {

		return new Segment2D(this.p1, this.p2);
	}

	@Override
	public void scale(Point2D center, double ratio) {

		this.p1.scale(center, ratio);
		this.p2.scale(center, ratio);
		
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {

		p1.rotate(center,angleDegrees);
		p2.rotate(center,angleDegrees);
		
	}

	@Override
	public Point2D[] getPoints() {
		
		Point2D[] arr = new Point2D[2];
		arr[0] = this.p1;
		arr[1] = this.p2;
		return arr;
	}
	
	public String toString() {
		
		return "Segment2D," + p1+","+p2;
	}

}