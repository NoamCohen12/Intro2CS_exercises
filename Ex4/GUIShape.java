package Exe.Ex4;
/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */
import java.awt.Color;
import java.util.LinkedList;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;


public class GUIShape implements GUI_Shapeable{
	private GeoShapeable _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;
	
	public GUIShape(GeoShapeable g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}
	public GUIShape(String line) {
		init(line.split(","));
	}
	
	@Override
	public GeoShapeable getShape() {
		return _g;
	}

	@Override
	public boolean isFilled() {
		return _fill;
	}

	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	@Override
	public Color getColor() {
		return _color;
	}

	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int tag) {
		_tag = tag;
	}

	@Override
	public GUI_Shapeable copy() {
		GUI_Shapeable cp = new GUIShape(this);
		return cp;
	}
	
	@Override
	public String toString() {
		
		return "GUIShape," + _color.getRGB() + "," + _fill + "," + _tag +"," + _g.toString();
	}
	
	/**The function accepts a string (info)
	according to "," and creates according to a given the relevant shape
	and enters it into a collection called shape collection
	*/
	private void init(String[] ww) {		
		String shapeName = ww[4];
		int pointsCount = (ww.length - 5)/2;
		Point2D[] shapePoints = new Point2D[pointsCount];
		int index = 0;
		for(int i = 5; i <= ww.length-2; i = i + 2) {
			double x = Double.parseDouble(ww[i]);
			double y = Double.parseDouble(ww[i+1]);
			shapePoints[index] = new Point2D(x,y);
			index++;
		}

		if(shapeName.equals("Circle2D")) {
			_g = new Circle2D(shapePoints[0], Double.parseDouble(ww[ww.length-1]));
		}
		else if(shapeName.equals("Polygon2D")) {
			LinkedList<Point2D> kodkod = new LinkedList<Point2D>();
			for(Point2D p : shapePoints) {
				kodkod.add(p);
			}
			_g = new Polygon2D(kodkod);
		}
		else if(shapeName.equals("Rect2D")) {
			_g = new Rect2D(shapePoints[0], shapePoints[1],shapePoints[2],shapePoints[3]);
		}
		else if(shapeName.equals("Segment2D")) {
			_g = new Segment2D(shapePoints[0], shapePoints[1]);
		}
		else if(shapeName.equals("Triangle2D")) {
			_g = new Triangle2D(shapePoints[0], shapePoints[1], shapePoints[2]);
		}
	
		_fill = Boolean.parseBoolean(ww[2]);
		_color = new Color(Integer.parseInt(ww[1]));
		_tag = Integer.parseInt(ww[3]);	
	}
	
	@Override
	public boolean isSelected() {
		return this._isSelected;
	}
	
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
	
	@Override
	public void setShape(GeoShapeable g) {
		this._g=g;
	}
}