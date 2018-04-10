package rsvier.workshop.utility;

import java.io.*;
import java.sql.*;
import java.util.logging.*;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DatabaseConnectionXML {

	private static Logger logger = LogConnection.getLogger();

	private static String URL;
	private static String USER;
	private static String PASSWORD;

	/*
	 * Create a function that is parsing the xml file. Getting the right values of
	 * the xml for database connection. We can also declare this class as a
	 * anonymous static method and just call it once to create the file that should
	 * be more efficient. Consider this later on in big projects
	 */

	public static void initializeXml() {

		/*
		 * Finding the right path for the xml file was a big problem. So when you are
		 * trying to find the relative path. use the getAbsolutePath() function on that
		 * xmlFile. You can see there where its looking to your file. Now add the
		 * missing parts to the new File() and it should find the file. btw don't forget
		 * to use double backslashes to make it compatible with all Operating Systems.
		 */
		File xmlFile = new File("src/main/java/rsvier/workshop/utility/DCXML.xml");

		if (xmlFile.exists()) {

			try {

				DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document document = dBuilder.parse(xmlFile);
				document.getDocumentElement().normalize();
				URL = document.getElementsByTagName("url").item(0).getTextContent();
				USER = document.getElementsByTagName("user").item(0).getTextContent();
				PASSWORD = document.getElementsByTagName("password").item(0).getTextContent();
				logger.log(Level.CONFIG,"Xml file exist, parsing is succesfull.");
			} catch (ParserConfigurationException | SAXException | IOException e) {
				logger.log(Level.WARNING, "Parser/Sax/IOexception occured check log", e);

			}
		} else {
			logger.log(Level.INFO, "xmlFile is not existing.");
		}

	}

	public static Connection getConnection() throws SQLException {

		/*
		 * First perform a check. if one of this values is null call the initialize
		 * method otherwise skip it.
		 */
		if (URL == null | USER == null | PASSWORD == null) {
			initializeXml();
		}
		Connection conn = null;
		try {

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			logger.log(Level.INFO, "Connected to Database");
			System.out.println("Connected to the Database.");

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exeception ocurred. Connection with database failed.", e);

		}

		return conn;
	}
}
