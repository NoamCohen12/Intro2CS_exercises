package Exe.Ex4;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable{
	
	// data
	private ArrayList<GUI_Shapeable> _shapes;

	// constractor
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shapeable>();
	}
	
	// functions
	@Override
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shapeable removeElementAt(int i) {
		return _shapes.remove(i);		
	}

	@Override
	public void addAt(GUI_Shapeable s, int i) {
		_shapes.add(i, s);
	}
	
	@Override
	public void add(GUI_Shapeable s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	
	@Override
	public ShapeCollectionable copy() {
		//////////add your code below ///////////
		ShapeCollection copy= new ShapeCollection();
		for(GUI_Shapeable gs : _shapes) {//for each gui_shapable in this.shapes
			copy.add(gs.copy());
		}
		return  copy;
	}
	//////////////////////////////////////////

	@Override
	//////////add your code below ///////////
	public void sort(Comparator<GUI_Shapeable> comp) {
		_shapes.sort(comp);
	}
	//////////////////////////////////////////

	@Override
	public void removeAll() {
		//////////add your code below ///////////
		_shapes.removeAll(_shapes);
	}
		//////////////////////////////////////////

	/**
	 * in the 'save' and 'load' functions we helped with the 'ChatGPT' on next link
	 * https://chat.openai.com/chat
	 */
	@Override
	public void save(String file) {

		// Create a new file
		File f = new File(file);

		try {
			// Create a new BufferedWriter with a FileWriter
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));

			for (int i =0;i<_shapes.size();i++) {
				//_shapes.get(i).toString();
				bw.write(_shapes.get(i).toString()+"\n");
			}

			// Close the BufferedWriter
			bw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//////////////////////////////////////////
	}

	@Override
	public void load(String file) {
		////////// add your code below ///////////
		removeAll();
		File savedFile = new File(file);

		try {
			// Create a FileReader object
			FileReader reader = new FileReader(savedFile);

			// Create a BufferedReader object
			BufferedReader bufferedReader = new BufferedReader(reader);

			// Read the file line by line
			String line;
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				String[] info = line.split(",");
				//System.out.println(Arrays.toString(info));

				GeoShapeable gs = CreateShape(info);
				boolean fill = Boolean.parseBoolean(info[2]);
				Color color = new Color(Integer.parseInt(info[1]));
				int tag = Integer.parseInt(info[3]);

				if(gs != null) {
					GUIShape sh = new GUIShape(gs, fill, color, tag);
					_shapes.add(sh);
				}

				//public GUIShape(GeoShapeable g, boolean f, Color c, int t) {
				else {
					System.out.println("Shape was not created successfully");
				}
			}

			// Close the BufferedReader
			bufferedReader.close();
		}
		catch(Exception ex) {
			System.out.println("Shape was not created successfully");
		}

		//////////////////////////////////////////
	}

	private GeoShapeable CreateShape(String[] info) {
		String shapeName = info[4];
		int pointsCount = (info.length - 5)/2;
		Point2D[] shapePoints = new Point2D[pointsCount];
		int index = 0;
		for(int i = 5; i <= info.length-2; i = i + 2) {
			double x = Double.parseDouble(info[i]);
			double y = Double.parseDouble(info[i+1]);
			shapePoints[index] = new Point2D(x,y);
			index++;
		}
		//System.out.println(Arrays.toString(shapePoints));

		if(shapeName.equals("Circle2D")) {
			Circle2D circle = new Circle2D(shapePoints[0], Double.parseDouble(info[info.length-1]));
			return circle;
		}
		else if(shapeName.equals("Polygon2D")) {
			LinkedList<Point2D> kodkod = new LinkedList<Point2D>();
			for(Point2D p : shapePoints) {
				kodkod.add(p);
			}
			Polygon2D poly = new Polygon2D(kodkod);
			return poly;
		}
		else if(shapeName.equals("Rect2D")) {
			Rect2D rect = new Rect2D(shapePoints[0], shapePoints[1], shapePoints[2], shapePoints[3]);
			return rect;
		}
		else if(shapeName.equals("Segment2D")) {
			Segment2D segment = new Segment2D(shapePoints[0], shapePoints[1]);
			return segment;
		}
		else if(shapeName.equals("Triangle2D")) {
			Triangle2D tri = new Triangle2D(shapePoints[0], shapePoints[1], shapePoints[2]);
			return tri;
		}

		return null;
	}

	/**	Checking the shapes "on the screen" and their points
	and makes a rect from the most extreme points
	*/
	@Override
	public Rect2D getBoundingBox() {
		Rect2D ans = null;
		//////////add your code below ///////////
		double maxX = 0;
		double minX = 0;
		double maxY = 0;
		double minY = 0;
		
		for (int i=0; i<_shapes.size()-1; i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			Point2D[] points = g.getPoints();
			
			if(g instanceof Circle2D) {
				Circle2D circle = (Circle2D) g;
				double r = circle.getRadius();
				double cx = points[0].x();
				double cy = points[0].y();
				
				if (cx+r>maxX) {maxX = cx+r;}
				if (cx-r<minX) {minX = cx-r;}
				if (cy+r>maxY) {maxY = cy+r;}
				if (cy-r<minY) {minY = cy-r;}								
			}
			
			for (int j=0; j<points.length; j++) {
				double x = points[j].x();
				double y = points[j].y();
				if (x>maxX) {maxX = x;}
				if (x<minX) {minX = x;}
				if (y>maxY) {maxY = y;}
				if (y<minY) {minY = y;}
			}
		}
		
		Point2D p1 = new Point2D(minX, maxY);
		Point2D p2 = new Point2D(maxX, minY);
		Point2D p3 = new Point2D(minX, minY);
		Point2D p4 = new Point2D(maxX, maxY);
		
		ans = new Rect2D(p1, p2, p3, p4);
		
		//////////////////////////////////////////
		return ans;
	}
	
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}
	
}
