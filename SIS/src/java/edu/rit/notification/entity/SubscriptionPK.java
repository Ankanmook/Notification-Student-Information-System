/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.notification.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Embeddable
public class SubscriptionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "UID")
    private String uid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CLASS_NUMBER")
    private String classNumber;

    public SubscriptionPK() {
    }

    public SubscriptionPK(String uid, String classNumber) {
        this.uid = uid;
        this.classNumber = classNumber;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        hash += (classNumber != null ? classNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubscriptionPK)) {
            return false;
        }
        SubscriptionPK other = (SubscriptionPK) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        if ((this.classNumber == null && other.classNumber != null) || (this.classNumber != null && !this.classNumber.equals(other.classNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.rit.notification.entity.SubscriptionPK[ uid=" + uid + ", classNumber=" + classNumber + " ]";
    }
    
}
