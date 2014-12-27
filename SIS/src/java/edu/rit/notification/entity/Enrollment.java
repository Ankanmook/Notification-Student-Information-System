/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.notification.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "enrollment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enrollment.findAll", query = "SELECT e FROM Enrollment e"),
    @NamedQuery(name = "Enrollment.findByUid", query = "SELECT e FROM Enrollment e WHERE e.enrollmentPK.uid = :uid"),
    @NamedQuery(name = "Enrollment.findByClassNumber", query = "SELECT e FROM Enrollment e WHERE e.enrollmentPK.classNumber = :classNumber"),
    @NamedQuery(name = "Enrollment.findBySubscribe", query = "SELECT e FROM Enrollment e WHERE e.subscribe = :subscribe")})
public class Enrollment implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EnrollmentPK enrollmentPK;
    @Column(name = "SUBSCRIBE")
    private Short subscribe;
    @JoinColumn(name = "CLASS_NUMBER", referencedColumnName = "CLASS_NUMBER", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ClassSchedule classSchedule;
    @JoinColumn(name = "UID", referencedColumnName = "UID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;

    public Enrollment() {
    }

    public Enrollment(EnrollmentPK enrollmentPK) {
        this.enrollmentPK = enrollmentPK;
    }

    public Enrollment(String uid, String classNumber) {
        this.enrollmentPK = new EnrollmentPK(uid, classNumber);
    }

    public EnrollmentPK getEnrollmentPK() {
        return enrollmentPK;
    }

    public void setEnrollmentPK(EnrollmentPK enrollmentPK) {
        this.enrollmentPK = enrollmentPK;
    }

    public Short getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Short subscribe) {
        this.subscribe = subscribe;
    }

    public ClassSchedule getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(ClassSchedule classSchedule) {
        this.classSchedule = classSchedule;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enrollmentPK != null ? enrollmentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enrollment)) {
            return false;
        }
        Enrollment other = (Enrollment) object;
        if ((this.enrollmentPK == null && other.enrollmentPK != null) || (this.enrollmentPK != null && !this.enrollmentPK.equals(other.enrollmentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.rit.notification.entity.Enrollment[ enrollmentPK=" + enrollmentPK + " ]";
    }
    
}
