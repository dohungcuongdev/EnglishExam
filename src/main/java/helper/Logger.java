package helper;

import java.util.Date;

public class Logger {
	
    private static String getCallerClassName() { 
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(Logger.class.getName()) && ste.getClassName().indexOf("java.lang.Thread")!=0) {
                return ste.getClassName();
            }
        }
        return null;
     }
	
	public static void infor(Object message) {
		System.out.println(new Date() + " - Class: " + getCallerClassName() + " - Message: " + message.toString());
	}
}
