package edu.rit.notification.model;

import java.util.ArrayList;
import java.util.List;

import edu.rit.notification.binding.SISSOAPInterface;
import edu.rit.notification.entity.ClassSchedule;
import edu.rit.notification.entity.Student;

public class MessageBuilder {
	private SISSOAPInterface sisInterface;
	

	public SISSOAPInterface getSisInterface() {
		return sisInterface;
	}


	public void setSisInterface(SISSOAPInterface sisInterface) {
		this.sisInterface = sisInterface;
	}


	public List<AlertObject> buildAlerObjects(ClassSchedule schedule) {
		System.out.println("MessageBuilder...");
		List<AlertObject> alertObjects = new ArrayList<AlertObject>();
		List<Student> studentList = sisInterface.getClassSubscription(schedule.getClassNumber());  // schedule.getStudentSubscriptionList();

		System.out.println("StudentList: " + studentList);
		if (studentList != null) {
			for (Student s : studentList) {
				AlertObject ao = new AlertObject();
				ao.setFullName(s.getFirstname() + " " + s.getLastname());
				ao.setEmail(s.getEmail());
				ao.setClassName(schedule.getCourse().getDescribtion());
				ao.setStartTime(schedule.getStartTime());
				ao.setTimeTo(5);// this should be calculated

				alertObjects.add(ao);

			}
		}
		return alertObjects;
	}
}
