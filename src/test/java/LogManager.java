import org.apache.log4j.Logger;

public class LogManager {
    private static ThreadLocal<Logger> logger = null;
    
    private LogManager(String callingClass) {
        
    }

    public  static Logger getLogger(Class callingClass){
        if (logger == null) {
        	synchronized(callingClass) {
        		logger = new ThreadLocal<Logger>();
        		logger.set(Logger.getLogger(callingClass));
        		return logger.get();
        	}
        }
		return logger.get();
    }
}
