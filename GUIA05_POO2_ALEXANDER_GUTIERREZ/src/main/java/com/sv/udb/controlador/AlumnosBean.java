/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Alumnos;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author alexander
 */
@Named(value = "alumnosBean")
@RequestScoped
public class AlumnosBean {

    private Alumnos objeAlum;
    private boolean guar;

    /**
     * Creates a new instance of AlumnosBean
     */
    public AlumnosBean() {
    }

    @PostConstruct //Despues de iniciar la vista
    public void init() {
        this.objeAlum = new Alumnos();
        this.guar = true;
    }

    public Alumnos getObjeAlum() {
        return objeAlum;
    }

    public void setObjeAlum(Alumnos objeAlum) {
        this.objeAlum = objeAlum;
    }

    public void guar() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("POOPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(this.objeAlum);
            et.commit();
            this.guar = false;
        } catch (Exception ex) {
            et.rollback();
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
