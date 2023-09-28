package Exe.Ex2;import java.security.AlgorithmConstraints;
import java.util.Arrays;

import org.junit.jupiter.params.shadow.com.univocity.parsers.fixed.FixedWidthFieldLengths;

/** 
 * This class represents a set of functions on a polynom - represented as array of doubles.
 * In general, such an array {-1,2,3.1} represents the following polynom 3.1x^2+2x-1=0,
 * The index of the entry represents the power of x.
 * 
 * Your goal is to complete the functions below, see the marking: // *** add your code here ***
 *
 * @author boaz.benmoshe
 *
 */
public class Ex2 {
	/** Epsilon value for numerical computation, it serves as a "close enough" threshold. */
	public static final double EPS = 0.001; // the epsilon to be used for the root approximation.
	/** The zero polynom is represented as an array with a single (0) entry. */
	public static final double[] ZERO = {0};

	/** Two polynoms are equal if and only if the have the same coefficients - up to an epsilon (aka EPS) value.
	 * @param p1 first polynom
	 * @param p2 second polynom
	 * @return true iff p1 represents the same polynom as p2.
	 */
	public static boolean equals(double[] p1, double[] p2) {
		boolean ans = true;
		// *** add your code here ***

		if (p1==null && p2==null) {return ans;}						// if the arrays are null
		if (p1==null || p2==null) {return false;}					// if one of them is null
		if (p1.length != p2.length) {return false;}					// if the length doesn't equal

		for (int i = 0; i<p1.length; i++) {
			if (Math.abs(p1[i]-p2[i])>EPS) {						// compares the i location in both of polynoms
				return false;
			}
		}

		// **************************
		return ans;
	}
	/**
	 * Computes the f(x) value of the polynom at x.
	 * @param poly
	 * @param x
	 * @return f(x) - the polynom value at x.
	 */public static double f(double[] poly, double x) {
		 double ans = 0;
		 if (poly!=null) {
			 for (int i = 0; i<poly.length; i++) {
				 ans = ans+ poly[i]*Math.pow(x, i);					// raise x to the power and multiply it by the coefficient
			 }


		 }

		 return ans;

		 // **************************
	 }
	 /** 
	  * Computes a String representing the polynom.
	  * For example the array {2,0,3.1,-1.2} will be presented as the following String  "-1.2x^3 +3.1x^2 +2.0"
	  * @param poly the polynom represented as an array of doubles
	  * @return String representing the polynom: 
	  */public static String poly(double[] poly) {
		  String ans = "";
		  // *** add your code here ***

		  if (poly==null || poly.length==0) {return "";}					// if the array is empty

		  if (poly.length==1) {											// if the array contains only one number
			  return poly[0] + "";
		  }

		  for (int i = poly.length-1; i>=1; i--) {						// go over the array and full the string
			  if (poly[i]>0) {
				  ans = ans + " +" + poly[i] + "x^" + i;					// if the value is positive
			  }
			  if (poly[i]<0) {
				  ans = ans + " " + poly[i] + "x^" + i;					// if the value is nagative
			  }
		  }

		  if (poly[poly.length-1]>0) {
			  ans = ans.substring(2, ans.length());							// removing the first "+" if exist
		  }
		  if (poly[1]!=0) {
			  ans = ans.substring(0, ans.length()-2);							// removing chars "^1" if exist
		  }

		  if (poly[0]>0) {
			  ans = ans + " +" + poly[0];
		  }
		  if (poly[0]<0) {
			  ans = ans + " " + poly[0];
		  }

		  if(ans.charAt(0) == ' ') {											// if the string beggin with " -"
			  ans = ans.substring(1, ans.length());
		  }

		  // **************************

		  return ans;
	  }

	  /**
	   * Given a polynom (p), a range [x1,x2] and an epsilon eps. 
	   * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	   * assuming p(x1)*p(x2) <= 0. 
	   * This function should be implemented iteratively (none recursive).
	   * @param p - the polynom
	   * @param x1 - minimal value of the range
	   * @param x2 - maximal value of the range
	   * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	   * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	   */public static double root(double[] p, double x1, double x2, double eps) {
		   double x12 = (x1+x2)/2;
		   // *** add your code here ***

		   double sum = 0;

		   if (f(p, x1) < f(p, x2)) {												// if the function is going up
			   do {

				   sum = f(p, x12);

				   if (Math.abs(sum)<=eps) {return x12;}					// when current x12 is the answer

				   else if (sum<0) {										// change the minimal value of range
					   x1 = x12;
					   x12 = (x12+x2)/2;
				   }

				   else if (sum>0) {										// change the maximal value of range
					   x2 = x12;
					   x12 = (x12+x1)/2;
				   }
			   }  while (Math.abs(sum)>eps);
		   }

		   else if (f(p, x1) > f(p, x2)) {										// if the function is going down
			   do {

				   sum = f(p, x12);										// when current x12 is the answer

				   if (Math.abs(sum)<=eps) {return x12;}

				   else if (sum<0) {										// change the maximal value of range
					   x2 = x12;
					   x12 = (x12+x1)/2;
				   }

				   else if (sum>0) {										// change the minimal value of range
					   x1 = x12;
					   x12 = (x12+x2)/2;
				   }
			   }  while (Math.abs(sum)>eps);	
		   }

		   // **************************

		   return x12;
	   }
	   /** Given a polynom (p), a range [x1,x2] and an epsilon eps. 
	    * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	    * assuming p(x1)*p(x2) <= 0. 
	    * This function should be implemented recursivly.
	    * @param p - the polynom
	    * @param x1 - minimal value of the range
	    * @param x2 - maximal value of the range
	    * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	    * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	    */public static double root_rec(double[] p, double x1, double x2, double eps) {
	    	double x12 = (x1+x2)/2;
	    	// *** add your code here ***

	    	if (f(p, x1) < f(p, x2)) {													// if the function is going up

	    		double sum = f(p, x12);						

	    		if (Math.abs(sum)<=eps) {return x12;}							// the stop condition

	    		else if (sum<0) {
	    			x1 = x12;													// change the minimal value of range
	    			x12 = root_rec(p, x1, x2, eps);								// and run again
	    		}

	    		else if (sum>0) {
	    			x2 = x12;													// change the maximal value of range
	    			x12 = root_rec(p, x1, x2, eps);								// and run again
	    		}
	    	}

	    	else if (f(p, x1) > f(p, x2)) {												// if the function is going down

	    		double sum = f(p, x12);

	    		if (Math.abs(sum)<=eps) {return x12;}								// the stop condition

	    		else if (sum<0) {
	    			x2 = x12;														// change the maximal value of range
	    			x12 = root_rec(p, x1, x2, eps);									// and run again
	    		}

	    		else if (sum>0) {
	    			x1 = x12;														// change the minimal value of range
	    			x12 = root_rec(p, x1, x2, eps);									// and run again
	    		}
	    	}
	    	// **************************

	    	return x12;
	    }

	    //**************************
	    /**
	     * Given two polynoms (p1,p2), a range [x1,x2] and an epsilon eps. This function computes an x value (x1<=x<=x2)
	     * for which |p1(x) -p2(x)| < eps, assuming (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0.
	     * @param p1 - first polynom
	     * @param p2 - second polynom
	     * @param x1 - minimal value of the range
	     * @param x2 - maximal value of the range
	     * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	     * @return an x value (x1<=x<=x2) for which |p1(x) -p2(x)| < eps.
	     */
	    public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps) {
	    	double x12 = (x1+x2)/2;
	    	// *** add your code here ***

	    	if (f(p1, x1) > f(p2, x1)) {											// if p1 go's down and p2 go's up

	    		do {

	    			if ( Math.abs(f(p1, x12) - f(p2, x12)) < eps ) {return x12;}	// calculate to know if this is the cut point

	    			if (f(p1, x12) < f(p2, x12)) {									// this means we passet over the cut point
	    				x2 = x12;													// change the maximal value of the range
	    				x12 = (x1+x2)/2;
	    			}

	    			else if (f(p1, x12) > f(p2, x12)) {								// this means we didn't pass over the cut point yet
	    				x1 = x12;													// change the minimal value of the range
	    				x12 = (x1+x2)/2;
	    			}

	    		} while (f(p1, x12) != f(p2, x12));
	    	}

	    	else if (f(p1, x1) < f(p2, x1)) {											// if p2 go's down and p1 go's up

	    		do {

	    			if ( Math.abs(f(p1, x12) - f(p2, x12)) < eps ) {return x12;}		// calculate to know if this is the cut point

	    			if (f(p1, x12) < f(p2, x12)) {										// this means we didn't pass over the cut point yet
	    				x1 = x12;														// change the minimal value of the range
	    				x12 = (x1+x2)/2;
	    			}

	    			else if (f(p1, x12) > f(p2, x12)) {									// this means we passet over the cut point
	    				x2 = x12;														// change the maximal value of the range
	    				x12 = (x1+x2)/2;
	    			}

	    		} while (f(p1, x12) != f(p2, x12));
	    	}

	    	// *** add your code here ***

	    	// **************************
	    	return x12;
	    }




	    /**
	     * Given two polynoms (p1,p2), a range [x1,x2] and an integer representing the number of "boxes". 
	     * This function computes an approximation of the area between the polynoms within the x-range.
	     * The area is computed using Riemann's like integral (https://en.wikipedia.org/wiki/Riemann_integral)
	     * @param p1 - first polynom
	     * @param p2 - second polynom
	     * @param x1 - minimal value of the range
	     * @param x2 - maximal value of the range
	     * @param numberOfBoxes - a natural number representing the number of boxes between xq and x2.
	     * @return the approximated area between the two polynoms within the [x1,x2] range.
	     */public static double area(double[] p1,double[]p2, double x1, double x2, int numberOfBoxes) {
	    	 double ans = 0;
	    	 // *** add your code here ***

	    	 double width = Math.abs(x2-x1);												// length of range to compute
	    	 double boxWidth = width/numberOfBoxes;								// the length of every box

	    	 for (int i=0; i<numberOfBoxes; i++) {								// loop to run over all boxes
	    		 double xi = x1 + boxWidth * i;
	    		 double sum1 = f(p1, xi);
	    		 double sum2 = f(p2, xi);
	    		 double length = Math.abs(sum1-sum2);							// the length between two polynoms
	    		 double boxArea = boxWidth * length;								// computes the area of specific box
	    		 ans = ans + boxArea;											// sum this area to the general area
	    	 }
	    	 // **************************
	    	 return ans;
	     }
	     /**
	      * This function computes the array representation of a polynom from a String
	      * representation. Note:given a polynom represented as a double array,  
	      * getPolynomFromString(poly(p)) should return an array equals to p.
	      * 
	      * @param p - a String representing polynom.
	      * @return
	      */public static double[] getPolynomFromString(String p) {
	    	  // *** add your code here ***
	    	  /**
	    	   * assuming the input does'nt inclued spaces 
	    	   */

	    	  p = p.replaceAll("-", "+-");						// replacing "-" with "+-" so we can split the String by "+"
	    	  if (p.startsWith("+")) {						// delete the first "+" if exist
	    		  p = p.substring(1);
	    	  }

	    	  String[] splited = p.split("\\+");				// splits the String by "+"

	    	  int arrLength = 0;

	    	  if (splited.length == 1) {						// that means the original String contains only one number
	    		  return new double[]{Double.parseDouble(splited[0])};
	    	  }
	    	  else {											// assuming we didn't get an empty String so it must encluding a x
	    		  if (splited[0].contains("^")) {
	    			  arrLength = (Integer.parseInt(splited[0].split("\\^")[1])) + 1;
	    		  }
	    		  else {
	    			  arrLength = 2;
	    		  }
	    	  }

	    	  double[] ans = new double[arrLength];

	    	  for (String split : splited) {											// go over the splited array

	    		  if (split.contains("x")) {

	    			  if (split.contains("^")) {
	    				  arrLength = Integer.parseInt(split.split("\\^")[1]);		// define the place by the degree of x
	    			  }
	    			  else {															// the coefficient of x
	    				  arrLength = 1;
	    			  }
	    		  }
	    		  else {																// the number that doesn't mul x
	    			  arrLength = 0;
	    		  }

	    		  if (split.startsWith("x")) {										// if the coefficient is 1
	    			  ans[arrLength] = 1;
	    		  }
	    		  else if (split.startsWith("-x")) {									// if the coefficient is -1
	    			  ans[arrLength] = -1;
	    		  }
	    		  else {
	    			  ans[arrLength] = Double.parseDouble(split.split("x")[0]);		// put the coefficient at the right place
	    		  }
	    	  }

	    	  return ans;
	    	  // **************************
	      }
	      /**
	       * This function computes the polynom which is the sum of two polynoms (p1,p2)
	       * @param p1
	       * @param p2
	       * @return
	       */ public static double[] add(double[] p1, double[] p2) {
	    	   // *** add your code here ***

	    	   double[] sum = new double[Math.max(p1.length, p2.length)];

	    	   if (p1.length==0 || p1==null) {return p2;}
	    	   if (p2.length==0 || p2==null) {return p1;}

	    	   if (p1.length==p2.length) {									// if both polynoms have the same length
	    		   for(int i=0; i<p1.length; i++) {
	    			   sum[i] = p1[i] + p2[i];
	    		   }
	    	   }

	    	   else {
	    		   int i = 0;
	    		   while (i<p1.length && i<p2.length) {					// run over the arrays and sum their value to the new array
	    			   sum [i] = p1[i] + p2[i];
	    			   i++;
	    		   }

	    		   while (i<p1.length) {									// if p1 is the longest array
	    			   sum[i] = p1[i];
	    			   i++;
	    		   }

	    		   while (i<p2.length) {									// if p2 is the longest array
	    			   sum[i] = p2[i];
	    			   i++;
	    		   }
	    	   }


	    	   return sum;
	    	   // **************************
	       }

	       /**
	        * This function computes the polynom which is the multiplication of two polynoms (p1,p2)
	        * @param p1
	        * @param p2
	        * @return
	        */public static double[] mul(double[] p1, double[] p2) {
	        	// *** add your code here ***

	        	double[] ans = new double [p1.length + p2.length - 1];

	        	for(int i = 0; i<p1.length; i++) {								// run over one index from first array
	        		for(int j = 0; j<p2.length; j++) {							// and mul it with all indexes of the second array
	        			ans[i+j] = ans[i+j] + p1[i]*p2[j];
	        		}
	        	} 

	        	return ans;
	        	// **************************
	        }
	        /**
	         * This function computes the derivative polynom of po.
	         * @param po
	         * @return
	         */public static double[] derivative (double[] po) {
	     		/**
	     		 *  This function is based on the simple formula of derivative --> f(x) = x^n --> f'(x) = n*x^n-1 
	     		 */
	     		double[] deriv = {0};

	     		if (po.length==1) {return deriv;}

	     		deriv = new double[po.length-1];

	     		for (int i = 0; i<deriv.length; i++) {
	     			deriv[i] = po[i+1]*(i+1);
	     		}

	        	 return deriv;
	        	 // **************************
	         }


	         /**
	          * This function computes a polynomial representation from a set of 2D points on the polynom.
	          * Note: this function only works for a set of points containing three points, else returns null.
	          * @param xx
	          * @param yy
	          * @return an array of doubles representing the coefficients of the polynom.
	          * Note: you can assume xx[0]!=xx[1]!=xx[2]
	          */

	         public static double[] PolynomFromPoints(double[] xx, double[] yy) {
	        	 double [] ans = null;
	        	 if(xx!=null && yy!=null && xx.length==3 && yy.length==3) {
	        		 // *** add your code here ***
	  		 /***
	   	       *We used this site to understand the calculation
	   	        *and the implementation of this function:
	   	        * http://chris35wills.github.io/parabola_python/
	   	        ***/
	        		 
	        		 double x1 = xx[2], x2 = xx[1], x3 = xx[0];
	        		 double y1 = yy[2], y2 = yy[1], y3 = yy[0];

	        		 double denom = (x1-x2) * (x1-x3) * (x2-x3);
	        		 double a = (x3 * (y2-y1) + x2 * (y1-y3) + x1 * (y3-y2)) / denom;
	        		 double b = (x3*x3 * (y1-y2) + x2*x2 * (y3-y1) + x1*x1 * (y2-y3)) / denom;
	        		 double c = (x2 * x3 * (x2-x3) * y1+x3 * x1 * (x3-x1) * y2+x1 * x2 * (x1-x2) * y3) / denom;

	        		 ans = new double [3];
	        		 ans[0] = c;
	        		 ans[1] = b;
	        		 ans[2] = a;
	        		 // **************************
	        	 }
	        	 return ans;

	         }

	         ///////////////////// Private /////////////////////
	         // you can add any additional functions (private) below
}
