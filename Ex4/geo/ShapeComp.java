package Exe.Ex4.geo;

import java.util.Comparator;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUI_Shapeable;

/**
 * This class represents a Comparator over GUI_Shapes - 
 * as a linear order over GUI_Shapes.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeComp implements Comparator<GUI_Shapeable>{
	//////////add your code below ///////////

	public static final Comparator<GUI_Shapeable> CompByToString = new ShapeComp(Ex4_Const.Sort_By_toString);

	private int _flag;
	public ShapeComp(int flag) {
		_flag = flag;
	}
	
	@Override
	public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
		int ans=0;
		if(_flag == Ex4_Const.Sort_By_toString) {
			ans = o1.toString().compareTo(o2.toString());
		}	
		
		//////////add your code below ///////////

		if(_flag == Ex4_Const.Sort_By_Anti_toString) {////////////////check
			ans = o1.toString().compareToIgnoreCase(o2.toString())*-1;
		}
		if(_flag == Ex4_Const.Sort_By_Area) {
			ans = Double.compare(o1.getShape().area(), o2.getShape().area());
		}		
		if(_flag == Ex4_Const.Sort_By_Anti_Area) {
			ans = Double.compare(o1.getShape().area(), o2.getShape().area())*-1;
		}		
		if(_flag == Ex4_Const.Sort_By_Perimeter) {
			ans = Double.compare(o1.getShape().perimeter(), o2.getShape().perimeter());
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Perimeter) {
			ans = Double.compare(o1.getShape().perimeter(), o2.getShape().perimeter())*-1;
		}		
		if(_flag == Ex4_Const.Sort_By_Tag) {
			ans = Integer.compare(o1.getTag(), o2.getTag());
		}		
		if(_flag == Ex4_Const.Sort_By_Anti_Tag) {
			ans = Integer.compare(o1.getTag(), o2.getTag())*-1;
		}

		//////////////////////////////////////////
		return ans;
	}
}
