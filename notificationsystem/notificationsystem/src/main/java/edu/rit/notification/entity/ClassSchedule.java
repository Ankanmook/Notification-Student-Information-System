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
import javax.persistence.ManyToOne;
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
@Table(name = "class_schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClassSchedule.findAll", query = "SELECT c FROM ClassSchedule c"),
    @NamedQuery(name = "ClassSchedule.findByClassNumber", query = "SELECT c FROM ClassSchedule c WHERE c.classNumber = :classNumber"),
    @NamedQuery(name = "ClassSchedule.findByDays", query = "SELECT c FROM ClassSchedule c WHERE c.days = :days"),
    @NamedQuery(name = "ClassSchedule.findByStartTime", query = "SELECT c FROM ClassSchedule c WHERE c.startTime = :startTime"),
    @NamedQuery(name = "ClassSchedule.findByEndTime", query = "SELECT c FROM ClassSchedule c WHERE c.endTime = :endTime"),
    @NamedQuery(name = "ClassSchedule.findByClassFormat", query = "SELECT c FROM ClassSchedule c WHERE c.classFormat = :classFormat"),
    @NamedQuery(name = "ClassSchedule.findByInstructor", query = "SELECT c FROM ClassSchedule c WHERE c.instructor = :instructor"),
    @NamedQuery(name = "ClassSchedule.findTodaysClasses", query = "SELECT c FROM ClassSchedule c WHERE LOCATE(c.days, :day) = 0")})
public class ClassSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CLASS_NUMBER")
    private String classNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "DAYS")
    private String days;
    @Basic(optional = false)
    @NotNull
    @Column(name = "START_TIME")
    private String startTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "END_TIME")
    private String endTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CLASS_FORMAT")
    private String classFormat;
    @Size(max = 45)
    @Column(name = "INSTRUCTOR")
    private String instructor;
    @JoinColumn(name = "COURSE_NUMBER", referencedColumnName = "COURSE_NUMBER")
    @ManyToOne(optional = false)
    private Course course;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classSchedule")
    private List<Enrollment> enrollmentList;
    @ManyToMany
    @JoinTable(name = "ENROLLMENT",
            joinColumns = {
        @JoinColumn(name = "CLASS_NUMBER")},
            inverseJoinColumns = {
        @JoinColumn(name = "UID")})
    private List<Student> studentEnrollmentList;
    
    @ManyToMany
    @JoinTable(name = "SUBSCRIPTION",
            joinColumns = {
        @JoinColumn(name = "CLASS_NUMBER")},
            inverseJoinColumns = {
        @JoinColumn(name = "UID")})
    private List<Student> studentSubscriptionList;

    public ClassSchedule() {
    }

    public ClassSchedule(String classNumber) {
        this.classNumber = classNumber;
    }

    public ClassSchedule(String classNumber, String days, String startTime, String endTime, String classFormat) {
        this.classNumber = classNumber;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classFormat = classFormat;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getClassFormat() {
        return classFormat;
    }

    public void setClassFormat(String classFormat) {
        this.classFormat = classFormat;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @XmlTransient
    public List<Enrollment> getEnrollmentList() {
        return enrollmentList;
    }

    public void setEnrollmentList(List<Enrollment> enrollmentList) {
        this.enrollmentList = enrollmentList;
    }

    @XmlTransient
    public List<Student> getStudentEnrollmentList() {
        return studentEnrollmentList;
    }

    public void setStudentEnrollmentList(List<Student> studentEnrollmentList) {
        this.studentEnrollmentList = studentEnrollmentList;
    }

    @XmlTransient
    public List<Student> getStudentSubscriptionList() {
        return studentSubscriptionList;
    }

    public void setStudentSubscriptionList(List<Student> studentSubscriptionList) {
        this.studentSubscriptionList = studentSubscriptionList;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classNumber != null ? classNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassSchedule)) {
            return false;
        }
        ClassSchedule other = (ClassSchedule) object;
        if ((this.classNumber == null && other.classNumber != null) || (this.classNumber != null && !this.classNumber.equals(other.classNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.rit.notification.entity.ClassSchedule[ classNumber=" + classNumber + " ]";
    }
}
