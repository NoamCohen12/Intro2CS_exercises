package Exe.EX3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class implements the Map2D interface.
 * You should change (implement) this class as part of Ex3. 
 * 
 * @author
 * Noam Cohen
 
 * */
public class MyMap2D implements Map2D{
	private int[][] _map;
	private int[][] numbers;

	public MyMap2D(int w, int h) {init(w,h);}
	public MyMap2D(int size) {this(size,size);}

	public MyMap2D(int[][] data) { 
		this(data.length, data[0].length);
		init(data);
	}
	@Override
	public void init(int w, int h) {
		_map = new int[w][h];

	}
	@Override
	public void init(int[][] arr) {
		init(arr.length,arr[0].length);
		for(int x = 0;x<this.getWidth()&& x<arr.length;x++) {
			for(int y=0;y<this.getHeight()&& y<arr[0].length;y++) {
				this.setPixel(x, y, arr[x][y]);
			}
		}
	}
	@Override
	public int getWidth() {return _map.length;}
	@Override
	public int getHeight() {return _map[0].length;}
	@Override
	public int getPixel(int x, int y) { return _map[x][y];}
	@Override
	public int getPixel(Point2D p) { 
		return this.getPixel(p.ix(),p.iy());
	}

	public void setPixel(int x, int y, int v) {_map[x][y] = v;}
	public void setPixel(Point2D p, int v) { 
		setPixel(p.ix(), p.iy(), v);
	}

	@Override
	/**
	 * this function finds the linear equation
	 * than colculates the x and y values for each point on the way of the segment
	 * if the distance between the y value of two points is longer than the distance between the x value of two points
	 * so it caculate's the x of every y from beggining to end
	 * and exactky the oposite for the oposite case 
	 */
	public void drawSegment(Point2D p1, Point2D p2, int v) {

		// defining min&max values to work with
		int maxX = Math.max(p1.ix(), p2.ix());
		int minX = Math.min(p1.ix(), p2.ix());
		int maxY = Math.max(p1.iy(), p2.iy());
		int minY = Math.min(p1.iy(), p2.iy());

		// defining values for the linear equation
		double dx = p1.x()-p2.x();
		double dy = p1.y()-p2.y();
		double m = dy/dx;				// incline of the line
		double b = p1.iy()-m*p1.ix(); 	// the 'free variable'

		if (Math.abs(dx)>=Math.abs(dy)) {			// case dx is longer than dy
			for (int i=minX; i<=maxX; i++) {		// run over all the x values
				double y = Math.round(m*i+b);		// calculates y value using the linear equation
				if ( !(i<0 || y<0 || i>=this.getWidth() || y>=this.getHeight()) ){		// make sure the point is in the map range
					setPixel(i, (int)y, v);
				}
			}
		}
		else {										// case dy is longer than dx
			for (int i=minY; i<=maxY; i++) {		// run over all the y values
				double x = Math.round((i-b)/m);		// calculates x value using the linear equation
				if ( !(x<0 || i<0 || x>=this.getWidth() || i>=this.getHeight()) ) {		// make sure the point is in the map range
					setPixel((int)x, i, v);
				}
			}
		}

	}

	@Override
	/**
	 * this function defining two points.
	 * first point with the min x and max y (top left corner)
	 * second point with the max x and min y (down right corner)
	 * than run over the rect between those points and paint every point of it
	 */
	public void drawRect(Point2D p1, Point2D p2, int col) {

		int maxY = Math.max(p1.iy(), p2.iy());
		int minY = Math.min(p1.iy(), p2.iy());
		int maxX = Math.max(p1.ix(), p2.ix());
		int minX = Math.min(p1.ix(), p2.ix());

		for (int y=maxY; y>=minY; y--) {					// run over the rows
			for (int x=minX; x<=maxX; x++) {				// run over the columns
				setPixel(x, y, col);						// painting the points
			}
		}

	}

	@Override
	/**
	 * this function get two points.
	 * the first is the center of the circle and the second difinins the radius length.
	 * the function difining a matrix that "blocks" the circle.
	 * we run all over it and painting every point that the distance between it and the center
	 * is less than the radius
	 */
	public void drawCircle(Point2D p, double rad, int col) {

		Point2D start = new Point2D(p.x()-rad, p.y()+rad);	// the top left corner of the blocking matrix
		Point2D end = new Point2D(p.x()+rad, p.y()-rad);	// the down right corner of the blocking matrix

		for (int y=start.iy(); y>=end.iy(); y--) {
			for (int x=start.ix(); x<=end.ix(); x++) {

				if ( (x<0) || (y<0) || (x>=this.getWidth()) || (y>=this.getHeight()) ) {continue;}		// if this point is out of range

				Point2D tmp = new Point2D(x,y);

				double d = tmp.distance(p);			// get the distance between this point to the center of the circle

				if (d<=rad) {						// if the current point is in the circle radius
					setPixel((int)x, (int)y, col);
				}
			}
		}

	}

	@Override
	/**
	 * this function fills the area connected to the chosen point besides points which already got another color
	 * it marks every point it goe's over, so it's not gonna check it again.
	 * we creating a queue to save every point which is a neighboe of point that we check and waiting for checking.
	 * every point we chek, we run over it's 8 neighbors and fills them
	 * if and only if they painted with the color of the original point (p).
	 */
	public int fill(Point2D p, int new_v) {
		// TODO Auto-generated method stub
		boolean visit[][] = new boolean [this.getWidth()][this.getHeight()];	// an array telling us if this point has already been visitd
		Queue<Point2D> queue = new LinkedList<>();		// a queue of points waiting for check
		int firstColor = _map[p.ix()][p.iy()];		// the color of the original point (p)
		queue.add(p);
		visit[p.ix()][p.iy()] = true;

		while (!queue.isEmpty()) {			// if the queue is empty - means we finished our work
			Point2D outPoint = queue.remove();	// taking the next point we want to check
			setPixel(outPoint, new_v);		// painting this point
			int x = outPoint.ix();
			int y = outPoint.iy();
			visit[x][y] = true;
			for (int i=x-1; i<=x+1; i++) {		// run over all it's neighbors
				for (int j=y-1; j<=y+1; j++) {

					if (i!=x && j!=y) {continue;}		// skip on the current point (we don't need to check it's self)

					if ( (i<0 || j<0 || i>=this.getWidth() || j>=this.getHeight()) ){continue;}		// if this neighbor does'nt exist in our map limits

					if(!visit[i][j] && _map[i][j]==firstColor) {		// add the relevant neighbors to our queue
						queue.add(new Point2D(i, j));
						visit[i][j] = true;	
					}
				}
			}
		}
		return 0;
	}

	@Override
	/**
	 * this function get an x,y values and send's them as a point to the function above
	 */
	public int fill(int x, int y, int new_v) {
		Point2D p = new Point2D(x,y);
		this.fill(p, new_v);

		return 0;
	}

	@Override
	/**
	 * this function get two points, send them to the function below (shortestPathDist) and get back
	 * a number representing the highest number a cell got from this function.
	 * we take the second point (numbered with this highest number) and run over it's neighbors
	 * looking for the point numbered with the following number downwards and add it to the array.
	 * we keep doing that till we get to the first point.
	 * than return this array.
	 */
	public Point2D[] shortestPath(Point2D p1, Point2D p2) {
		// TODO Auto-generated method stub
		int dist =shortestPathDist(p1, p2);
		
		if (dist==0) {return null;}
		if (dist==1) {
			Point2D [] arr= new Point2D [1];
			arr[0]=p1;
			return arr  ;
		}
		
		Point2D ans[] = new Point2D[dist];		// creating an array of points to return
		int index =0;
		Point2D current = p2;
		ans[index]=current;		// add the first point into the array
		index++;

		while (current.ix()!=p1.ix() || current.iy()!=p1.iy()) {		// as long as we haven't reached the first point
			int x = current.ix();
			int y = current.iy();
			boolean exit = false;

			for (int i=x-1; i<=x+1&&!exit; i++) {		// run over the neighbors to fund the next point
				for (int j=y-1; j<=y+1&&!exit; j++) {
					
					if(i!=x&&j!=y) {continue;}			// skip on the current point (we don't need to check it's self)
					if(i<0||j<0||i>=numbers.length||j>=numbers.length){continue;}		// if this neighbor does'nt exist in our map limits

					if(numbers[i][j] == numbers[x][y]-1) {	// means this is the point we are interested in
						current = new Point2D (i ,j);
						ans[index] = current;
						index++;
						exit = true;
					}
				}
			}
		}

		return ans;
	}

	@Override
	/**
	 * this function computes the length of the shortest path between two points.
	 * first, it maps the entire map with the "blocking" points (which are not with the same
	 * color as the original point) by numbering them with "-1".
	 * than it build's a queue to save all the points we want to check.
	 * for each point it check's all it's neighbors and numbering it with higher number than the cuurent point holds.
	 * and so on and so on, till it gets to the destination point
	 * or if the queue remains empty - means there isn't way to get to destination point
	 * (becouse this way blocked by "-1" points.
	 * return the number of the dest point or 0 if there isn't an open way.
	 */
	public int shortestPathDist (Point2D p1, Point2D p2) {
		// TODO Auto-generated method stub

		Point2D first = p1;
		Point2D last = p2;
		
		if (first.ix()==last.ix()&&first.iy()==last.iy()) {		// if we presset twice on the same point
			return 1;
		}
		
		int firstColor = this.getPixel(p1);
		int secondColor = this.getPixel(p2);
		
		if (firstColor!=secondColor) {		// if those two points ain't with the same color
			return 0;
		}

		numbers = new int [this.getWidth()][this.getHeight()];
		for (int i=0; i<this.getWidth(); i++) {			// mapping the entire map with the points which not with the same col as the original point
			for (int j=0; j<this.getHeight(); j++) {
				if (_map[i][j]!=firstColor) {
					numbers[i][j]=-1;
				}
			}
		}
		
		numbers [first.ix()][first.iy()]=1;		// starts the numbering proses
		Queue<Point2D> queue = new LinkedList<>();
		queue.add(first);
		while(!queue.isEmpty()) {
			Point2D outPoint = queue.remove();

			int x = outPoint.ix();
			int y = outPoint.iy();

			for (int i=x-1; i<=x+1; i++) {		// run over the neighbors of the current point we number
				for (int j=y-1 ; j<=y+1 ; j++) {
					
					if(i!=x&&j!=y) {continue;}		// skip on the current point (we don't need to check it's self)
					if(i<0||j<0||i>=this.getWidth()||j>=this.getHeight()){continue;}		// if this neighbor does'nt exist in our map limits
					
					if(numbers[i][j] == 0) {		// if this point hasn't numbered yet
						numbers[i][j]=numbers[x][y]+1;
						if(last.ix()==i && last.iy()==j) {return numbers[i][j];}		// if we arrived to dest point
						queue.add(new Point2D(i, j));
					}
				}
			}
		}
		return 0;
	}

	@Override
	public void nextGenGol() {

		int [][] nextGen = new int [this.getWidth()][this.getHeight()];

		// run over the map to chek every cell if it's gonna be alive or not
		for (int i=0; i<this.getWidth(); i++) {
			for (int j=0; j<this.getHeight(); j++) {

				int aliveNeighbors = this.neighbors(i, j);

				// Implementing the rules of the game

				if ( (_map[i][j] != -1) && (aliveNeighbors<2) ) {		// cell dies becouse it's lonlyness
					nextGen [i][j] = -1;
				}
				else if ( (_map[i][j] != -1) && (aliveNeighbors>3) ) {		// cell dies due to over population
					nextGen [i][j] = -1;
				}
				else if ( (_map[i][j] == -1) && (aliveNeighbors==3) ) {		// new cell is born
					nextGen [i][j] = -16777216; // means alive (black)
				}
				else {												// remain the same as before
					nextGen [i][j] = _map[i][j];
					if ( (nextGen[i][j] != -1) && (nextGen[i][j] != -16777216) ) {nextGen[i][j] = -16777216;}
				}
			}
		}				// end of 'creating nextGen' loop

		_map = nextGen;		// applying the next generation

	}

	@Override
	public void fill(int c) {
		for(int x = 0;x<this.getWidth();x++) {
			for(int y = 0;y<this.getHeight();y++) {
				this.setPixel(x, y, c);
			}
		}

	}
	

	// This function counts how much alive neighbores a Point has
	public int neighbors (int x, int y) {
		int neighbors = 0;

		// run over all 8 neighbors
		for (int i=x-1; i<x+2; i++) {
			for (int j=y-1; j<y+2; j++) {

				if ( (i<0) || (j<0) || (i==this.getWidth()) || (j==this.getHeight()) ) {continue;}		// if this point is out of range

				if (i==x && j==y) {continue;}			// skip on the current point
				if (_map[i][j]==-1) {continue;}
				else {neighbors += 1;}
			}
		}
		return neighbors;
	}

}
