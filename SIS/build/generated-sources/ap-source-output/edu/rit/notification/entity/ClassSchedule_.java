package edu.rit.notification.entity;

import edu.rit.notification.entity.Course;
import edu.rit.notification.entity.Enrollment;
import edu.rit.notification.entity.Student;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-10T16:29:50")
@StaticMetamodel(ClassSchedule.class)
public class ClassSchedule_ { 

    public static volatile SingularAttribute<ClassSchedule, String> startTime;
    public static volatile SingularAttribute<ClassSchedule, Course> course;
    public static volatile SingularAttribute<ClassSchedule, String> instructor;
    public static volatile SingularAttribute<ClassSchedule, String> days;
    public static volatile SingularAttribute<ClassSchedule, String> classNumber;
    public static volatile ListAttribute<ClassSchedule, Enrollment> enrollmentList;
    public static volatile SingularAttribute<ClassSchedule, String> endTime;
    public static volatile SingularAttribute<ClassSchedule, String> classFormat;
    public static volatile ListAttribute<ClassSchedule, Student> studentSubscriptionList;
    public static volatile ListAttribute<ClassSchedule, Student> studentEnrollmentList;

}