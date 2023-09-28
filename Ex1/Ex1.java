/**
 * 

 * @author Noam Cohen 
 

 * This code finds GPCD of the two given input numbers.
 * First the code finds the GCD.
 * Second the code check if the GCD is a prime number.
 * If so, the answer is the GCD 
 * Otherwise,the code search the next GPCD by loops : for and while.
 * 
 *
 */

package noam;
import java.util.*;
public class Ex1 {
	static Scanner in=new Scanner(System.in);
	public static void main(String[] args) {
	    System.out.print("Enter your ID number: ");
		long id=in.nextLong();
		System.out.println("Enter the first number for max prime GCD: ");
		long x=in.nextLong();
		System.out.println("Enter the second number for max prime GCD: ");
		long y=in.nextLong();
		
		long start = System.nanoTime();

		long ans = 1;
		
		long min = x;
		long max = y;
		
		if(y < x) {
			min = y;
			max = x;
		}
		
		long devided = max % min;
		while (devided !=0) {
			long oldDevided = devided;
			devided = min % devided;
			min = oldDevided;
		}
		long gcd = min;
		ans = gcd;
		
		for(long left = 2; left <= (long)Math.sqrt(gcd); left++) {
			if(gcd % left == 0) {
				long right = gcd/left;
				ans = right;
				for(long i = 2; i <= Math.sqrt(ans); i++) {
					if(ans % i == 0) {
						ans = 1;
						break;
					}
				}
				if(ans != 1) {
					break;
				}
			}
		}
		if(ans == 1) {
			for(long left = (long)Math.sqrt(gcd); left >=2 ; left--) {
				if(gcd % left == 0) {
					ans = left;
					for(long i = 2; i <= Math.sqrt(ans); i++) {
						if(ans % i == 0) {
							ans = 1;
							break;
						}
					}
					if(ans != 1) {
						break;
					}
				}
			}
		}
		
		System.out.println("The GPCD("+x+","+y+") = " + ans);

		long end = System.nanoTime();
		double dt = (end - start)/(1000.0);
		System.out.println("The runtime took: "+dt+" micro seconds."); 
		
	}
}