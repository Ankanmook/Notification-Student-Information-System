/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.notification.bean;

import edu.rit.notification.entity.Enrollment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless
public class EnrollmentFacade extends AbstractFacade<Enrollment> {
    @PersistenceContext(unitName = "SISPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnrollmentFacade() {
        super(Enrollment.class);
    }
    
}
