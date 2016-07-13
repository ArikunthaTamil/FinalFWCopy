package genericLibrary;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

/**
 * Stopwatch the elapsed/start time of the stop watch
 * 
 */
public class StopWatch {

	/**
	  * startTime : This method is to returns start time in long
	  * @return Start time
	  */
	 public static long startTime() {
		 long x = System.currentTimeMillis();
		return(x);
	 }
	
	 /**
	  * elapsedTime : This method is to returns time difference
	  * @return Time difference
	  */
	 public static long elapsedTime(long startTime) {
		 
		 return ((System.currentTimeMillis() - startTime)/1000);
		 
	 }
}
