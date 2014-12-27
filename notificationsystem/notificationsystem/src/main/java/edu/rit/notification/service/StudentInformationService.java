package edu.rit.notification.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import edu.rit.notification.entity.ClassSchedule;
import edu.rit.notification.entity.Student;
import edu.rit.notification.model.EnrollmentRequest;
import edu.rit.notification.model.SubscriptionRequest;
import edu.rit.notification.model.SubscriptionResponse;

@WebService(serviceName = "StudentInformationService")
public interface StudentInformationService {

	@WebMethod(operationName = "getClassSubscription")
    public List<Student> getClassSubscription(@WebParam(name = "classNumber") String classNumber);
        
    @WebMethod(operationName = "getStudentSubscription")
    public List<ClassSchedule> getStudentSubscription(@WebParam(name = "uid") String uid);
    
    @WebMethod(operationName = "getStudentEnrollement")
    public List<ClassSchedule> getStudentEnrollement(@WebParam(name = "uid") String uid); 

    @WebMethod(operationName = "getClassEnrollement")
    public List<Student> getClassEnrollement(@WebParam(name = "classNumber") String classNumber);

    @WebMethod(operationName = "getStudentProfile")
    public Student getStudentProfile(@WebParam(name = "uid") String uid);
    
    @WebMethod(operationName = "getClassScheduleForToday")
    public List<ClassSchedule> getClassScheduleForToday();
    
    @WebMethod(operationName = "subscribe")
    public SubscriptionResponse subscribe(@WebParam(name = "subscriptionRequest") SubscriptionRequest request);
}
