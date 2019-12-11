import java.io.Serializable;

public class Course implements Serializable {
	private String id, name, description, credits;

	// Getters and Setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}
	
	/**
	 * Default Constructor
	 */
	public Course() {
		this.setId("");
		this.setName("");
		this.setDescription("");
		this.setCredits("");
	}
	
	/**
	 * Override Constructor
	 */
	public Course(String id, String name, String description, String credits) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setCredits(credits);
	}
	
	/**
	 * Tab-deliminited format.
	 * @return String output for tab-delimited format
	 */
	public String tabDelimOutput() {
		return  getId() + "\t" +
				getName() + "\t" +
				getCredits()  + "\t" +
				getDescription();
	}
	
	/**
	 * Screen format.
	 * @return String format to display member data on a screen
	 */
	@Override
	public String toString() {
		return String.format(
				"%s: %s\n"  // Course Id, Course Name
				+ "%s Credits\n"  // Credits
				+ "%s\n" // Description
				+ "-----------------------",
				getId(), getName(),
				getCredits(),
				getDescription()
				);
	}
}
