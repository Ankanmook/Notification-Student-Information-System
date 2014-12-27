/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.notification.bean;

import edu.rit.notification.entity.ClassSchedule;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Administrator
 */
@Stateless
public class ClassScheduleFacade extends AbstractFacade<ClassSchedule> {
    @PersistenceContext(unitName = "SISPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassScheduleFacade() {
        super(ClassSchedule.class);
    }
    
    public List<ClassSchedule> getClassScheduleForToday(){
        System.out.println("In ClassScheduleFacade.getClassScheduleForToday()...");
        TypedQuery<ClassSchedule> queryClassScheduleForToday = em.createNamedQuery("ClassSchedule.findTodaysClasses", ClassSchedule.class);
        queryClassScheduleForToday.setParameter("day", getToday());
        List<ClassSchedule> classList = queryClassScheduleForToday.getResultList();
        System.out.println("ClassSchedule list size:" +classList.size());
        System.out.println("returning from ClassScheduleFacade.getClassScheduleForToday()...");
        for(ClassSchedule c:classList){
            System.out.println(c);
        }
        return classList;
    }
    
     private String getToday() {
        return "Mo"; //change to compute today's day
    }
    
}

   
