package Exe.Ex4.geo;

import java.util.LinkedList;

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 * 
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable{

	//data
	private LinkedList<Point2D> kodkod;
 
	//constractor
	public Polygon2D (LinkedList<Point2D> kodkod) {

		this.kodkod = new LinkedList<Point2D>();

		for (int i=0; i<kodkod.size(); i++) {
			this.kodkod.add(kodkod.get(i));
		}
	}

	//functions
	@Override
	public boolean contains(Point2D ot) {
		
		int counter = 0;
		int size = kodkod.size();
		
		// loop to go over every point and the point next to it
		for (int i = 0; i<size-1; i++) {
			Point2D p1 = new Point2D(kodkod.get(i));
			Point2D p2 = new Point2D(kodkod.get(i+1));
			double maxY = Math.max(p1.y(), p2.y());
			double minY = Math.min(p1.y(), p2.y());
			
			if (minY<=ot.y() && ot.y()<=maxY) {
				// defining values for a linear equation
				double dx = p1.x()-p2.x();
				double dy = p1.y()-p2.y();
				double m = dy/dx;				// incline of the line
				double b = p1.y()-m*p1.x(); 	// the 'free variable'
				double x = (ot.y()-b)/m;

				if (ot.x()<=x) {
					counter += 1;
				}
			}
		}
		
		// go over the last and the first points
		Point2D p1 = new Point2D(kodkod.get(0));
		Point2D p2 = new Point2D(kodkod.get(size-1));
		double maxY = Math.max(p1.y(), p2.y());
		double minY = Math.min(p1.y(), p2.y());
		
		if (minY<=ot.y() && ot.y()<=maxY) {
			// defining values for a linear equation
			double dx = p1.x()-p2.x();
			double dy = p1.y()-p2.y();
			double m = dy/dx;				// incline of the line
			double b = p1.y()-m*p1.x(); 	// the 'free variable'
			double x = (ot.y()-b)/m;

			if (ot.x()<=x) {
				counter += 1;
			}
		}
		
		if (counter%2 == 1) {return true;}
		
		return false;
	}

	/**
	 * in this function we used the information on that link below
	 * https://he.wikipedia.org/wiki/%D7%A0%D7%95%D7%A1%D7%97%D7%AA_%D7%94%D7%A9%D7%A8%D7%95%D7%9A
	 */
	@Override
	public double area() {
		
		double ans = 0;

		if (kodkod==null || kodkod.size()==1) {return 0;}
		
		Point2D[] polyPoints = this.getPoints();
		double [] xArr = new double[kodkod.size()];
		double [] yArr = new double[kodkod.size()];

		for (int i=0; i<polyPoints.length; i++) {
			xArr[i] = polyPoints[i].x();
			yArr[i] = polyPoints[i].y();
		}
		
		double tmp1 = 0;
		double tmp2 = 0;
		
		for (int i=0; i<polyPoints.length-1; i++) {
			tmp1 += xArr[i]*yArr[i+1];
			tmp2 += xArr[i+1]*yArr[i];
		}
		
		ans = Math.abs(tmp1-tmp2)/2;		
		return ans;
	}

	@Override
	public double perimeter() {

		double ans = 0;

		if (kodkod==null || kodkod.size()==1) {return 0;}

		Point2D p1, p2;

		for (int i=0; i<kodkod.size()-1; i++) {
			p1 = new Point2D(kodkod.get(i));
			p2 = new Point2D(kodkod.get(i+1));
			double dist = p1.distance(p2);
			ans += dist;
		}

		p1 = kodkod.getFirst();
		p2 = kodkod.getLast();
		double dist = p1.distance(p2);
		ans += dist;

		return ans;
	}

	@Override
	public void move(Point2D vec) {

		for (int i=0; i<kodkod.size(); i++) {
			kodkod.get(i).move(vec);
		}		
	}

	@Override
	public GeoShapeable copy() {
		LinkedList<Point2D> tmp = new LinkedList<>();
		for (int i=0; i<kodkod.size(); i++) {
			tmp.add(new Point2D(kodkod.get(i)));
		}
		return new Polygon2D(tmp);
	}

	@Override
	public void scale(Point2D center, double ratio) {

		for (int i=0; i<kodkod.size(); i++) {
			kodkod.get(i).scale(center, ratio);
		}		
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {

		for (int i=0; i<kodkod.size(); i++) {
			kodkod.get(i).rotate(center, angleDegrees);
		}		
	}

	@Override
	public Point2D[] getPoints() {

		Point2D[] ans = new Point2D[kodkod.size()];

		for (int i=0; i<kodkod.size(); i++) {
			ans[i] = new Point2D(((LinkedList<Point2D>) kodkod).get(i));
		}

		return ans;
	}
	
	public String toString() {
		String str="Polygon2D,";
		for (int i =0; i<getPoints().length-1;i++) {
			str =str + getPoints()[i]+",";//each point except last 
		}	
		str =str+ getPoints()[ getPoints().length-1];//last point
		return str;
		
	}

}
