/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.notification.service;


import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class SubscriptionRequest extends Request implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1569490395248082125L;

	
	private String studentId;
	private String classNumber;
	
	
	public SubscriptionRequest(){
		
	}
	
	public SubscriptionRequest(String studentId, String classNumber) {
		super();
		this.studentId = studentId;
		this.classNumber = classNumber;
		System.out.println("Subscription Request created: StudentId: " + studentId + " classNumber: " + classNumber);
	}
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getClassNumber() {
		return classNumber;
	}
	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}
	
        @Override
        public String toString(){
            return "ClassNumber: "+classNumber + " StudentId: "+ studentId;
        }
	
	
}
