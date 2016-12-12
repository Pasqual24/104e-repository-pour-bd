/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import classe.Commentaire;
import classe.Membre;
import classe.Restaurant;
import classe.HibernateUtil;
import classe.Typemembre;
import classe.typeMemUtil;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 1312394
 */
@Named(value = "ajouterCommentaire")
@RequestScoped
public class ajouterCommentaire {

    /**
     * Creates a new instance of ajouterCommentaire
     */
    
    private Integer idcommentaire;
    private Membre membre;
    private Restaurant restaurant;
    private String contenu;
    private Date datecreation;
    private int note;
    
    public ajouterCommentaire() {
    }
    
    public String soumettreCommentaire(Restaurant pResto) {
          
        FacesContext facesContext = FacesContext.getCurrentInstance();          
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        this.membre = (Membre) session.getAttribute("membreConnecte");
        this.restaurant = pResto;
        Date today = new Date();
        this.datecreation = today;
        
        
        Transaction tx = null;
        Session nsession = null;
        try{                       
            nsession = HibernateUtil.getSessionFactory().openSession();

            Commentaire unComm = new Commentaire();
            unComm.setContenu(this.contenu);
            unComm.setDatecreation(today);
            unComm.setMembre(this.membre);
            unComm.setRestaurant(this.restaurant);
            unComm.setNote(this.note);
            
           /* SMembre.setEmail(email);
            SMembre.setMpd(Mdp);
            typeMemUtil TypeUtil = new typeMemUtil();
            Typemembre typeMem = TypeUtil.getType(1);
            typeMem.setTypemem("membre");
            SMembre.setRestoPref(1);
            SMembre.setTypemembre(typeMem);
            SMembre.setTypecuisinePref(1);*/

            tx = nsession.beginTransaction();			           
            nsession.saveOrUpdate(unComm);
            tx.commit();
            nsession.close(); 
            return "ficheRestaurant?faces-redirect=true";
        }
        catch(Exception e)
        {
            tx.rollback();
            e.printStackTrace();
        }      
        nsession.close(); 
        return null;
    }

    /**
     * @return the idcommentaire
     */
    public Integer getIdcommentaire() {
        return idcommentaire;
    }

    /**
     * @param idcommentaire the idcommentaire to set
     */
    public void setIdcommentaire(Integer idcommentaire) {
        this.idcommentaire = idcommentaire;
    }

    /**
     * @return the membre
     */
    public Membre getMembre() {
        return membre;
    }

    /**
     * @param membre the membre to set
     */
    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    /**
     * @return the restaurant
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * @param restaurant the restaurant to set
     */
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * @return the contenu
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * @param contenu the contenu to set
     */
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    /**
     * @return the datecreation
     */
    public Date getDatecreation() {
        return datecreation;
    }

    /**
     * @param datecreation the datecreation to set
     */
    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    /**
     * @return the note
     */
    public int getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(int note) {
        this.note = note;
    }
    
}
