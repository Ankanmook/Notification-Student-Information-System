/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.notification.service;

import edu.rit.notification.bean.ClassScheduleFacade;
import edu.rit.notification.bean.StudentFacade;
import edu.rit.notification.bean.SubscriptionFacade;
import edu.rit.notification.entity.ClassSchedule;
import edu.rit.notification.entity.Course;
import edu.rit.notification.entity.Student;
import edu.rit.notification.entity.Subscription;
import edu.rit.notification.entity.SubscriptionPK;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Administrator
 */
@WebService(serviceName = "StudentInformationService")
public class StudentInformationService {

    @EJB
    private ClassScheduleFacade classScheduleEjbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")
    @EJB
    private StudentFacade studentEjbRef;

    @EJB
    private SubscriptionFacade subscriptionEjbRef;

    @WebMethod(operationName = "getClassSubscription")
    public List<Student> getClassSubscription(@WebParam(name = "classNumber") String classNumber) {
        ClassSchedule classSchedule = classScheduleEjbRef.find(classNumber);
        System.out.println("getEnrollmentList: " + classSchedule.getEnrollmentList().size());
        System.out.println("getStudentEnrollmentList: " + classSchedule.getStudentEnrollmentList().size());
        System.out.println("getStudentSubscriptionList: " + classSchedule.getStudentSubscriptionList().size());
        // System.out.println("getSubscriptions: " + classSchedule.getSubscriptions());
        List<Student> studentList = classSchedule.getStudentSubscriptionList();
        return studentList;
    }

    @WebMethod(operationName = "getClassSchedules")
    public List<ClassSchedule> getClassSchedules() {
        List<ClassSchedule> classScheduleList = classScheduleEjbRef.findAll();
        return classScheduleList;
    }

    @WebMethod(operationName = "getStudentSubscription")
    public List<ClassSchedule> getStudentSubscription(@WebParam(name = "uid") String uid) {
        System.out.println("*****************getStudentSubscription() called******************");
        Student student = getStudentProfile(uid);
        System.out.println("*****************Student: " + student + "******************");
        List<ClassSchedule> classScheduleList;
        if (student != null) {
            classScheduleList = student.getClassSubscriptionList();
        } else {
            classScheduleList = null;
        }
        return classScheduleList;
    }

    @WebMethod(operationName = "getStudentEnrollement")
    public List<ClassSchedule> getStudentEnrollement(@WebParam(name = "uid") String uid) {
        System.out.println("*****************getStudentEnrollement() called******************");
        Student student = getStudentProfile(uid);
        System.out.println("*****************Student: " + student + "******************");
        List<ClassSchedule> classScheduleList;
        if (student != null) {
            classScheduleList = student.getClassEnrollmentList();
        } else {
            classScheduleList = null;
        }
        return classScheduleList;
    }

    @WebMethod(operationName = "getStudentCourses")
    public List<Course> getStudentCourses(@WebParam(name = "uid") String uid) {
        System.out.println("*****************getStudentCourses() called******************");
        Student student = getStudentProfile(uid);
        System.out.println("*****************Student: " + student + "******************");

        List<Course> courses = new ArrayList<>();
        if (student != null) {
            List<ClassSchedule> classScheduleList = student.getClassEnrollmentList();
            for (ClassSchedule cs : classScheduleList) {
                Course c = cs.getCourse();
                courses.add(c);
            }
        }
        return courses;
    }

    @WebMethod(operationName = "getClassEnrollement")
    public List<Student> getClassEnrollement(@WebParam(name = "classNumber") String classNumber) {
        System.out.println("**************getClassEnrollement() called **************** ");
        ClassSchedule classSchedule = classScheduleEjbRef.find(classNumber);
        System.out.println("ClassSchedule: " + classNumber + " returned: " + classSchedule);

        List<Student> studentList;
        if (classSchedule != null) {
            studentList = classSchedule.getStudentEnrollmentList();
            System.out.println(studentList.size() + " Students found: ");
        } else {
            studentList = null;
            System.out.println("Students not found: ");
        }
        return studentList;
    }

    @WebMethod(operationName = "getStudentProfile")
    public Student getStudentProfile(@WebParam(name = "uid") String uid) {
        System.out.println("**************getStudentProfile() called**********************");
        return studentEjbRef.find(uid);
    }

    @WebMethod(operationName = "getClassScheduleForToday")
    public List<ClassSchedule> getClassScheduleForToday() {
        System.out.println("**************getClassScheduleForToday() called**********************");
        return classScheduleEjbRef.getClassScheduleForToday();

    }

    @WebMethod(operationName = "subscribe")
    public SubscriptionResponse subscribe(@WebParam(name = "subscriptionRequest") SubscriptionRequest request) {
        System.out.println("*****************subscribe() called******************");
        SubscriptionResponse response;
        if (request==null){
            response=new SubscriptionResponse("Request is empty");
            response.setStatus("Error");
            System.out.println("Request is empty");
            return response; 
        }
         response= new SubscriptionResponse("success");
        response.setStatus("OK");
        String uid = request.getStudentId();
        String classNumber = request.getClassNumber();
        System.out.println("SubscriptionRequest: " + request);
        Subscription subscription = new Subscription();

        SubscriptionPK subPK = new SubscriptionPK();
        subPK.setClassNumber(classNumber);
        subPK.setUid(uid);
        subscription.setSubscriptionPK(subPK);

        System.out.println("*****************calling studentEjbRef.find(uid) ******************");
        Student student = studentEjbRef.find(uid);
        System.out.println("Student: " + student);
        subscription.setStudent(student);

        System.out.println("*****************calling subscriptionEjbRef.create(subscription) ******************");
        subscriptionEjbRef.create(subscription);

        return response;
    }
    
    @WebMethod(operationName = "subscribe2")
    public SubscriptionResponse subscribe2(@WebParam(name = "uid") String uid,  @WebParam(name = "cno") String cno) {
        System.out.println("*****************subscribe() called******************");
        SubscriptionResponse response = new SubscriptionResponse("success");
        response.setStatus("OK");
        
        System.out.println("SubscriptionRequest: studentId:" + uid + " classNumber: "+ cno);
        Subscription subscription = new Subscription();

        SubscriptionPK subPK = new SubscriptionPK();
        subPK.setClassNumber(cno);
        subPK.setUid(uid);
        subscription.setSubscriptionPK(subPK);

        System.out.println("*****************calling studentEjbRef.find(uid) ******************");
        Student student = studentEjbRef.find(uid);
        System.out.println("Student: " + student);
        subscription.setStudent(student);

        System.out.println("*****************calling subscriptionEjbRef.create(subscription) ******************");
        subscriptionEjbRef.create(subscription);

        return response;
    }
}
