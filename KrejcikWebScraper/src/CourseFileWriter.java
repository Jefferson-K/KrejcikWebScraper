import java.io.PrintWriter;
import java.util.ArrayList;
import org.json.*;


/**
This class outputs course information to the screen and to multiple file formats.
@author Jeff Krejcik
*/
public class CourseFileWriter {
	/**
	 * Outputs course data to the screen
	 * @param members, an ArrayList of all members to be displayed
	 */
	public static String writeCoursesToScreen(ArrayList<Course> courses) {
		String output = "";
		for (Course c : courses) {
			output += c.toString() + "\n\n";
		}
		return output;
	}
	
	
	/**
	 * Exports member data to a tab-delimited text file
	 * @param members, ArrayList of courses to be exported
	 * @param fname, target file destination
	 * @return success message
	 */
	public static boolean writeCoursesToTextFile(ArrayList<Course> courses, String fname) {
		try {
			PrintWriter pw = new PrintWriter(fname);
			for (Course c : courses) {
				pw.println(c.tabDelimOutput());
			}
			pw.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	
	/**
	 * Exports member data to a JSON file
	 * @param members, ArrayList of courses to be exported
	 * @param fname, target file destination
	 * @return success message
	 */
	public static boolean writeCoursesToJSON(ArrayList<Course> courses, String fname) {
		try {
			PrintWriter pw = new PrintWriter(fname);
			JSONObject courseObj;
			JSONArray courseArr = new JSONArray();
			for (Course c : courses) {
				courseObj = new JSONObject();
				courseObj.put("id", c.getId());
				courseObj.put("name", c.getName());
				courseObj.put("credits", c.getCredits());
				courseObj.put("description", c.getDescription());
				courseArr.put(courseObj);
			}
			JSONObject outer = new JSONObject();
			outer.put("course", courseArr);
			pw.println(outer.toString());
			pw.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
}
