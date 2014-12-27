package edu.rit.notification.binding;

import java.util.List;

import edu.rit.notification.entity.ClassSchedule;

public interface PollingLoopback {
	public void putScheduleEvent(ClassSchedule classSchedule);
	public void putScheduleList(List<ClassSchedule> classScheduleList);

}
