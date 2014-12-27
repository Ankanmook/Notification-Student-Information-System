package edu.rit.notification.entity;

import edu.rit.notification.entity.ClassSchedule;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-10T16:29:50")
@StaticMetamodel(Course.class)
public class Course_ { 

    public static volatile ListAttribute<Course, ClassSchedule> classScheduleList;
    public static volatile SingularAttribute<Course, String> describtion;
    public static volatile SingularAttribute<Course, String> courseNumber;

}