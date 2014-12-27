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
@Table(name = "course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findByCourseNumber", query = "SELECT c FROM Course c WHERE c.courseNumber = :courseNumber"),
    @NamedQuery(name = "Course.findByDescribtion", query = "SELECT c FROM Course c WHERE c.describtion = :describtion")})
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "COURSE_NUMBER")
    private String courseNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "DESCRIBTION")
    private String describtion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<ClassSchedule> classScheduleList;
    
    
    public Course() {
    }

    public Course(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Course(String courseNumber, String describtion) {
        this.courseNumber = courseNumber;
        this.describtion = describtion;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    @XmlTransient
    public List<ClassSchedule> getClassScheduleList() {
        return classScheduleList;
    }

    public void setClassScheduleList(List<ClassSchedule> classScheduleList) {
        this.classScheduleList = classScheduleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseNumber != null ? courseNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.courseNumber == null && other.courseNumber != null) || (this.courseNumber != null && !this.courseNumber.equals(other.courseNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.rit.notification.entity.Course[ courseNumber=" + courseNumber + " ]";
    }
    
}
