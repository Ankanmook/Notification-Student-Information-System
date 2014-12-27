package edu.rit.notification.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

//import edu.rit.notification.service.impl.Subscription;

/**
 * 
 * @author Administrator
 */
@XmlRootElement
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String uid;
	
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private String phone;
	
	private String major;
	
	private List<Subscription> subscriptionList;
	
	private List<Enrollment> enrollmentList;

	private List<Student> classEnrollmentList;

	
	private List<Student> classSubscriptionList;

	public Student() {
	}

	public Student(String uid) {
		this.uid = uid;
	}

	public Student(String uid, String firstname, String lastname, String email,
			String phone) {
		this.uid = uid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@XmlTransient
	public List<Subscription> getSubscriptionList() {
		return subscriptionList;
	}

	public void setSubscriptionList(List<Subscription> subscriptionList) {
		this.subscriptionList = subscriptionList;
	}

	@XmlTransient
	public List<Enrollment> getEnrollmentList() {
		return enrollmentList;
	}

	public void setEnrollmentList(List<Enrollment> enrollmentList) {
		this.enrollmentList = enrollmentList;
	}

	@XmlTransient
	public List<Student> getClassEnrollmentList() {
		return classEnrollmentList;
	}

	public void setClassEnrollmentList(List<Student> classEnrollmentList) {
		this.classEnrollmentList = classEnrollmentList;
	}

	@XmlTransient
	public List<Student> getClassSubscriptionList() {
		return classSubscriptionList;
	}

	public void setClassSubscriptionList(List<Student> classSubscriptionList) {
		this.classSubscriptionList = classSubscriptionList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (uid != null ? uid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Student)) {
			return false;
		}
		Student other = (Student) object;
		if ((this.uid == null && other.uid != null)
				|| (this.uid != null && !this.uid.equals(other.uid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "edu.rit.notification.entity.Student[ uid=" + uid + " ]";
	}

}
