package rsvier.workshop.utility;

import java.util.logging.*;
import java.io.*;

public class LogConnection {

	/*
	 * Create a Logger object named logger using the Logger's static method
	 * getLogger(), not capitalized because it has not a final name. As you are
	 * going to use it in different classes its going to have different names
	 */

	private final static Logger logger = Logger.getLogger(LogConnection.class.getName());

	public LogConnection() {
	}

	public static void initLogger() {

		LogManager.getLogManager().reset();
		logger.setLevel(Level.ALL);
		/*
		 * If you want the log to be in xml format u can use this statement instead of
		 * the SimpleFormatter | XMLFormatter xmlFormatter = new XMLFormatter();
		 */
		FileHandler fh = null;
		SimpleFormatter sf = new SimpleFormatter();
		/*
		 * If we want to output important messages we could also use a consoleHandler
		 * that will output messages to the console. We could set the level of the
		 * ConsoleHandler @ SEVERE or WARNING and the FileHandeler to @ INFO , So all
		 * log below warning will go to the xml and above in red color to the console
		 * now the only output off all messages is handled by the fileHandler and it is
		 * in the xml file. ConsoleHandler ch = new ConsoleHandler();
		 * ch.setLevel(Level.WARNING);
		 */

		try {

			fh = new FileHandler("log.xml", true); // Remove true to get a new xmlFile on every run. true means append

			// to the previous logs
			fh.setLevel(Level.ALL);
			fh.setFormatter(sf);
			/* fh.setFormatter(xmlFormatter); */
			// logger.addHandler(ch);
			logger.addHandler(fh);
		} catch (SecurityException ex) {
			logger.log(Level.WARNING, "Security error ocured", ex.getMessage());

		} catch (IOException e) {
			logger.log(Level.SEVERE, "IO exception occured, check log.xml", e.getMessage());

		}

	}
	/*
	 * Other classes can only create a logger with a call to
	 * LogConnection.getLogger();So all classes are going to have the same logger
	 * with the same specifications like, logLevels and File handlers.
	 */

	public static Logger getLogger() {

		initLogger();
		return logger;
	}

}
