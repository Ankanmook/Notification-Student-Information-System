package edu.rit.notification.model;

public class AlertObject {
	private String className;
	private String startTime;
	private int timeTo;
	private String fullName;
    private String email;
    
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(int timeTo) {
		this.timeTo = timeTo;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
