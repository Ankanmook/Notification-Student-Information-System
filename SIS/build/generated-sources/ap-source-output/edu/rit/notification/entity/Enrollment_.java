package edu.rit.notification.entity;

import edu.rit.notification.entity.ClassSchedule;
import edu.rit.notification.entity.EnrollmentPK;
import edu.rit.notification.entity.Student;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-10T16:29:50")
@StaticMetamodel(Enrollment.class)
public class Enrollment_ { 

    public static volatile SingularAttribute<Enrollment, ClassSchedule> classSchedule;
    public static volatile SingularAttribute<Enrollment, Short> subscribe;
    public static volatile SingularAttribute<Enrollment, Student> student;
    public static volatile SingularAttribute<Enrollment, EnrollmentPK> enrollmentPK;

}