package edu.rit.notification.binding;

import java.util.List;

import edu.rit.notification.entity.Student;

public interface SISSOAPInterface {
	 public List<Student> getClassSubscription(String classNumber);

}
