package Exe.EX3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

class MyMap2DTest {

	MyMap2D _map_test = new MyMap2D(20);

	int white = Color.WHITE.getRGB();
	int black = Color.BLACK.getRGB();
	int yellow = Color.YELLOW.getRGB();
	int green = Color.GREEN.getRGB();
	int blue = Color.BLUE.getRGB();
	int red = Color.RED.getRGB();
	
	
	@Test
	void testdrawSegment () {  // Test for a case base                    
		_map_test.init(160,160);
		_map_test.fill(white);

		Point2D s =new Point2D (2.0,153.25);
		Point2D e =new Point2D (154.5,4.1875);

		_map_test.drawSegment(s, e, yellow);

		int maxX = Math.max(s.ix(), e.ix());
		int minX = Math.min(s.ix(), e.ix());
		int maxY = Math.max(s.iy(), e.iy());
		int minY = Math.min(s.iy(), e.iy());

		double dx = s.x()-e.x();
		double dy = s.y()-e.y();
		double m = dy/dx;		
		double b = s.iy()-m*s.ix();

		if (Math.abs(dx)>=Math.abs(dy)) {			
			for (int i=minX; i<=maxX; i++) {
				double y = Math.round(m*i+b);
				if ( !(i<0 || y<0 || i>=_map_test.getWidth() || y>=_map_test.getHeight()) ){	
					assertEquals(_map_test.getPixel(i,(int)y), yellow);
				}
			}
		}
		else {										
			for (int i=minY; i<=maxY; i++) {
				double x = Math.round((i-b)/m);
				if ( !(x<0 || i<0 || x>=_map_test.getWidth() || i>=_map_test.getHeight()) ) {		
					assertEquals(_map_test.getPixel((int)x,i), yellow);
				}
			}
		}

		//////////////////////////////
		_map_test.init(40,40);	//test for a case where a rect gets in the way
		_map_test.fill(white);

		_map_test.drawRect(new Point2D(7.15625,31.765625), new Point2D(21.0625,20.671875), black);

		Point2D s1 =new Point2D (9.96875,36.296875);
		Point2D e1 =new Point2D (17.546875,16.0625);

		_map_test.drawSegment(s1, e1, green);

		int maxX1 = Math.max(s1.ix(), e1.ix());
		int minX1 = Math.min(s1.ix(), e1.ix());
		int maxY1 = Math.max(s1.iy(), e1.iy());
		int minY1 = Math.min(s1.iy(), e1.iy());

		double dx1 = s1.x()-e1.x();
		double dy1 = s1.y()-e1.y();
		double m1 = dy1/dx1;		
		double b1 = s1.iy()-m1*s1.ix();

		if (Math.abs(dx1)>=Math.abs(dy1)) {			
			for (int i=minX1; i<=maxX1; i++) {
				double y = Math.round(m1*i+b1);
				if ( !(i<0 || y<0 || i>=_map_test.getWidth() || y>=_map_test.getHeight()) ){	
					assertEquals(_map_test.getPixel(i,(int)y),green);
				}
			}
		}
		else {										
			for (int i=minY1; i<=maxY1; i++) {
				double x = Math.round((i-b1)/m1);
				if ( !(x<0 || i<0 || x>=_map_test.getWidth() || i>=_map_test.getHeight()) ) {		
					assertEquals(_map_test.getPixel((int)x,i), green);
				}
			}
		}

		//////////////////////////////
		//Test for a case of standing still
		_map_test.init(20,20);
		_map_test.fill(white);

		Point2D s3 = new Point2D (3.2,6);
		Point2D e3 = new Point2D (3.1,6);

		_map_test.drawSegment(s3, e3, red);
		assertEquals(_map_test.getPixel(s3), red);
	}


	@Test
	void testRectangle() {
		_map_test.fill(white); // Clreanig the map

		Point2D p1 = new Point2D(5.0859375,14.0703125);
		Point2D p2 = new Point2D(11.0234375,9.109375);

		_map_test.drawRect(p1, p2, green);

		for (int y=Math.max(p1.iy(), p2.iy()); y>=Math.min(p1.iy(), p2.iy()); y--) {
			for (int x = Math.min(p1.ix(), p2.ix()); x<=Math.max(p1.ix(), p2.ix()); x++) {
				assertEquals(_map_test.getPixel(x,y), green);
			}
		}

		//////////////////////////////
		_map_test.init(40,40);//Test for a case where there is a segmant inside the rect
		_map_test.fill(white); // Clreanig the map

		Point2D g = new Point2D(7.546875,33.328125);
		Point2D g1 = new Point2D(15.984375,29.34375);
		_map_test.drawSegment(new Point2D (8.953125,36.453125), new Point2D(13.640625,26.609375), black);
		_map_test.drawRect(g, g1, green);

		for (int y=Math.max(g.iy(), g1.iy()); y>=Math.min(g.iy(), g1.iy()); y--) {
			for (int x = Math.min(g.ix(), g1.ix()); x<=Math.max(g.ix(), g1.ix()); x++) {
				assertEquals(_map_test.getPixel(x,y), green);
			}
		}
	}

	
	@Test
	void testDrawCircle () {  //Base test

		_map_test.init(20,20);
		_map_test.fill(white); // Clreanig the map

		Point2D p3 = new Point2D (8.25,9.734375);

		_map_test.drawCircle(p3, 3, green);
		double rad = 3;
		Point2D start = new Point2D(p3.x()-rad ,p3.y()+rad);
		Point2D end = new Point2D(p3.x()+rad ,p3.y()-rad);
		
		for(int y=start.iy();y>=end.iy();y--) {
			for (int x=start.ix();x<=end.ix();x++) {
				if ((x<0)||(y<0)||(x>=_map_test.getWidth())||(y>=_map_test.getHeight())) {continue;}
				Point2D tmp = new Point2D(x,y);
				double d = tmp.distance(p3);
				if(d<=rad) {
					assertEquals(_map_test.getPixel(x,y), green);// check the color of every point inside the circle
				}
				else {
					assertEquals(_map_test.getPixel(x,y), white);// rest of the blocking rect
				}
			}
		}

		//////////////////////////////
		_map_test.init(80,80);  //test values between 0 and 1
		_map_test.fill(white); // Clreanig the map

		Point2D p4 = new Point2D (-0.03125,0.4375);

		_map_test.drawCircle(p4, 5, black);

		double rad1 = 5;
		Point2D start1 = new Point2D(p4.x()-rad1 ,p4.y()+rad1);
		Point2D end1 = new Point2D(p4.x()+rad1 ,p4.y()-rad1);
		for(int y=start1.iy();y>=end1.iy();y--) {
			for (int x=start1.ix();x<=end1.ix();x++) {
				if ((x<0)||(y<0)||(x>=_map_test.getWidth())||(y>=_map_test.getHeight())) {continue;}
				Point2D tmp = new Point2D(x,y);
				double d = tmp.distance(p4);
				if(d<=rad) {
					assertEquals(_map_test.getPixel(x,y), black);
				}
			}
		}

		//////////////////////////////
		_map_test.init(50,50);                       //test without radius

		_map_test.fill(white); // Clreanig the map

		Point2D p5 = new Point2D (1,1);

		_map_test.drawCircle(p5, 0, green);

		assertEquals(_map_test.getPixel(p5), green);
	}


	@Test
	void testFill() {
		_map_test.init(160, 160);	// Test for stack overflow
		_map_test.fill(white);	// Clreanig the map
		
		_map_test.fill(new Point2D(5,4), yellow);

		for (int i = 0 ;i< _map_test.getWidth(); i++) {
			for (int j = 0; j< _map_test.getHeight(); j++ ) {
				assertEquals(_map_test.getPixel(i,j), yellow);
			}
		}

		//////////////////////////////
		_map_test.init(20, 20);	//Testing if an object is painted
		_map_test.fill(new Point2D(5,4), green);

		_map_test.drawRect(new Point2D(5.203125,7.1171875), new Point2D(11.0625,12.0), black);

		_map_test.fill(7,8, blue);

		for (int y=7; y>=12; y--) {
			for (int x = 5; x<=11; x++) {
				assertEquals(_map_test.getPixel(x,y), blue);
			}
		}
		assertEquals(_map_test.getPixel(5,6), green);//////Test if rest of the map not changed

		//////////////////////////////
		_map_test.init(40,40);	//Test for a case that segmant crosses the map and we fills the upper half with color
		_map_test.fill(white); // Clreanig the map

		Point2D half = new Point2D(11.609,35.984);

		_map_test.drawSegment(new Point2D(-0.265625,21.765625), new Point2D(39.109375,19.890625), black);
		_map_test.fill(half, green);


		assertEquals(_map_test.getPixel(half), green);//Chack if pixel from upper half printed
		assertEquals(_map_test.getPixel(16,8), white);//Chack if pixel from lower half not printed
		assertEquals(_map_test.getPixel(0,22), black);//Chack if pixel from segmant not printed

		boolean visit[][] = new boolean [_map_test.getWidth()][_map_test.getHeight()];
		Queue<Point2D> queue = new LinkedList<>();
		int firstColor = white;
		queue.add(half);
		visit[half.ix()][half.iy()] = true;

		while(queue.size() != 0) {
			Point2D outPoint = queue.remove();	
			assertEquals(_map_test.getPixel(outPoint),green);//Chack if pixel from all upper half printed
			int x = outPoint.ix();
			int y = outPoint.iy();
			visit[x][y] = true;
			for (int i=x-1; i<=x+1; i++) {
				for (int j=y-1 ; j<=y+1 ; j++) {
					if(i!=x&&j!=y) {continue;}
					if(i<0||j<0||i>=_map_test.getWidth()||j>=_map_test.getHeight()){continue;}

					if(!visit[i][j] && _map_test.getPixel(i, j)==firstColor) {
						queue.add(new Point2D(i, j));
						visit[i][j] = true;
					}
				}
			}
		}
	}
	
	
	@Test
	void testshortestPathDist() {

		_map_test.init(80,80);///Test for a case base 
		_map_test.fill(white);

		Point2D start5 = new Point2D(6.609375,15.9453125);

		Point2D end5 = new Point2D(6.8046875,10.7109375);

		_map_test.drawRect(new Point2D (4.9296875,14.34375), new Point2D (7.8984375,12.234375), black);

		int j = _map_test.shortestPathDist(start5, end5);
		_map_test.shortestPath(start5, end5);
		assertEquals(10, j);

		//////////////////////////////
		_map_test.init(40,40);	// Test for a case of standing still
		_map_test.fill(white);


		Point2D start8 = new Point2D (3.2,6);
		Point2D end8 = new Point2D (3.1,6);

		int i = _map_test.shortestPathDist(start8, end8);

		assertEquals(1, i);
		
		//////////////////////////////
		_map_test.init(80,80);	//Test for clicking on 2 pixels with different colors
		_map_test.fill(white);

		Point2D start7 = new Point2D (12.46875,40.90625);

		Point2D end7 = new Point2D (53.5625,39.96875);


		_map_test.drawCircle(new Point2D(12.46875,40.90625), 5, green);
		_map_test.drawCircle(new Point2D(53.5625,39.96875), 5, black);

		_map_test.shortestPathDist(start7, end7);

		int k =_map_test.shortestPathDist(start7, end7);
		assertEquals(k,0);
	}

	
	@Test
	void testshortestPath() {

		_map_test.init(80,80);
		_map_test.fill(white);

		Point2D s2 = new Point2D(4.265, 8.503);
		Point2D e2 = new Point2D(3.777, 1.648);

		_map_test.drawRect(new Point2D (2.412,7.039), new Point2D (5.652,3.308), black);

		Point2D [] ans1 = _map_test.shortestPath(s2, e2);

		for(int j=0; j<ans1.length; j++) {
			_map_test.setPixel(ans1[j], blue);
		}

		assertEquals (_map_test.getPixel(s2), blue);
		assertEquals (_map_test.getPixel(e2), blue);
		assertEquals (_map_test.getPixel(4,5), black);
		
		//////////////////////////////
		// Testing case when the way totally blocked by painted pixels 
		_map_test.init(20,20);
		_map_test.fill(white);
		
		Point2D p11 = new Point2D(10,0);
		Point2D p12 = new Point2D(11,19);
		Point2D p13 = new Point2D(6,10);
		Point2D p14 = new Point2D(15,10);

		_map_test.drawSegment(p11, p12, black);
				
		Point2D [] ans2 = _map_test.shortestPath(p13, p14);

		assertEquals(null, ans2);
	}

	
	@Test
	void testNextGenGol() {
		
		MyMap2D _map_test2 = new MyMap2D(20);		// new map that's gonna look like the old map
		_map_test.init(20,20);
		_map_test.fill(white);
		_map_test2.fill(white);
		
		Point2D p15 = new Point2D(5,15);
		Point2D p16 = new Point2D(15,5);
		
		_map_test.drawRect(p15, p16, black);
		_map_test2.drawRect(p15, p16, black);
		
		_map_test.nextGenGol();
		
		for (int i=0; i<_map_test2.getWidth(); i++) {
			for (int j=0; j<_map_test2.getHeight(); j++) {

				Point2D tmp = new Point2D(i,j);
				int aliveNeighbors = _map_test2.neighbors(i, j);

				if ( (_map_test2.getPixel(i, j) != -1) && (aliveNeighbors<2) ) {		// cell dies becouse it's lonlyness
					assertEquals(_map_test.getPixel(tmp), -1);
				}
				else if ( (_map_test2.getPixel(i, j) != -1) && (aliveNeighbors>3) ) {	// cell dies due to over population
					assertEquals(_map_test.getPixel(tmp), -1);
				}
				else if ( (_map_test2.getPixel(i, j) == -1) && (aliveNeighbors==3) ) {	// new cell is born
					assertEquals(_map_test.getPixel(tmp), -16777216);
				}
				else {												// remain the same as before
					assertEquals(_map_test.getPixel(tmp), _map_test2.getPixel(tmp));
				}
			}
		}	
		
	}
	
	
}