/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByUid", query = "SELECT s FROM Student s WHERE s.uid = :uid"),
    @NamedQuery(name = "Student.findByFirstname", query = "SELECT s FROM Student s WHERE s.firstname = :firstname"),
    @NamedQuery(name = "Student.findByLastname", query = "SELECT s FROM Student s WHERE s.lastname = :lastname"),
    @NamedQuery(name = "Student.findByEmail", query = "SELECT s FROM Student s WHERE s.email = :email"),
    @NamedQuery(name = "Student.findByPhone", query = "SELECT s FROM Student s WHERE s.phone = :phone"),
    @NamedQuery(name = "Student.findByMajor", query = "SELECT s FROM Student s WHERE s.major = :major")})
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "UID")
    private String uid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "LASTNAME")
    private String lastname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "EMAIL")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PHONE")
    private String phone;
    @Size(max = 45)
    @Column(name = "MAJOR")
    private String major;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Subscription> subscriptionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Enrollment> enrollmentList;
    
    @ManyToMany
    @JoinTable(name = "enrollment",
            joinColumns = {
        @JoinColumn(name = "UID")},
            inverseJoinColumns = {
        @JoinColumn(name = "CLASS_NUMBER")})
    private List<ClassSchedule> classEnrollmentList;
    
    @ManyToMany
    @JoinTable(name = "subscription",
            joinColumns = {
        @JoinColumn(name = "UID")},
            inverseJoinColumns = {
        @JoinColumn(name = "CLASS_NUMBER")})
    private List<ClassSchedule> classSubscriptionList;

    public Student() {
    }

    public Student(String uid) {
        this.uid = uid;
    }

    public Student(String uid, String firstname, String lastname, String email, String phone) {
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
    public List<ClassSchedule> getClassEnrollmentList() {
        return classEnrollmentList;
    }

    public void setClassEnrollmentList(List<ClassSchedule> classEnrollmentList) {
        this.classEnrollmentList = classEnrollmentList;
    }

    @XmlTransient
    public List<ClassSchedule> getClassSubscriptionList() {
        return classSubscriptionList;
    }

    public void setClassSubscriptionList(List<ClassSchedule> classSubscriptionList) {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.rit.notification.entity.Student[ uid=" + uid + " ]";
    }
    
}
