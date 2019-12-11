import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class HtmlParser {
	
	/**
	 * Parse Course ID from HTML
	 */
	public static String parseId(String line) {
		line = line.replace("&nbsp;", " ");
		line = line.substring(line.indexOf("<span>")+6);
		line = line.substring(0, line.indexOf("</span>"));
		return line.trim();
	}
	
	/**
	 * Parse Course Name from HTML
	 */
	public static String parseName(String line) {
		line = line.replace("&nbsp;", " ");
		line = line.substring(line.indexOf("</span>")+8);
		line = line.replace("</a>", "");
		return line.trim();
	}
	
	/**
	 * Parse Course Description from HTML
	 */
	public static String parseDescription(String line) {
		line = line.replace("&nbsp;", " ");
		line = line.replace("<p>", "");
		line = line.replace("<p class=\"sc-BodyTextNS\">", "");
		line = line.replace("</p>", "");
		return line.trim();
	}
	
	
	/**
	 Reads course data from a web URL.
	 @param fname, target text file path	 
	 */
	public static ArrayList<Course> parseCourseData(String html) {
		try {
			URL link = new URL(html);
			Scanner usc = new Scanner(link.openStream());
			String line, id, name, description, credits;
			Course tempCourse;
			ArrayList<Course> result = new ArrayList<Course>();
			
			while (usc.hasNextLine()) {
				try {
					if(usc.nextLine().contains("\"courseList\"")) {
						usc.nextLine();
						line = usc.nextLine();
						id = parseId(line);
						name = parseName(line);
						
						usc.nextLine();
						line = usc.nextLine();
						description = parseDescription(line);
						
						usc.nextLine();
						line = usc.nextLine();
						credits = line.trim();
						
						tempCourse = new Course(id, name, description, credits);
						result.add(tempCourse);
					}
				} catch (Exception ex) {}
			}
			usc.close();
			return result;
		} catch (Exception ex) {
			return null;
		}
	}
}
