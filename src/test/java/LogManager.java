import org.apache.log4j.Logger;

public class LogManager {
    private static LogManager LogManager = null;
    private static ThreadLocal<Logger> logger = new ThreadLocal<Logger>();
    public Logger log;
    private LogManager(String callingClass) {
        logger.set(Logger.getLogger(callingClass));
    }

    public static Logger getLogger(String callingClass){
        if (LogManager == null) {
            return new LogManager(callingClass).logger.get();
        }else{
            return LogManager.logger.get();
        }
    }
}
