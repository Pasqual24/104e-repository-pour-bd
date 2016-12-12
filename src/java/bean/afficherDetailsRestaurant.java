/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import classe.Restaurant;
import classe.Commentaire;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 1312394
 */
@Named(value = "afficherDetailsRestaurant")
@SessionScoped
public class afficherDetailsRestaurant implements Serializable {

    /**
     * Creates a new instance of afficherDetailsRestaurant
     */
    
    private Restaurant resto;
    
    public afficherDetailsRestaurant() {
    }
    
    public String afficherPage(Restaurant pResto)
    {
        this.resto = pResto;
        return "ficheRestaurant?faces-redirect=true";
    }
    
    public String noteMoyenne(int id){
        /*Restaurant  unResto =restoUti.getRestoId(id);*/
        String note;
        double noteMoyenne = resto.MoyenneNote();
        if (noteMoyenne == 0)
        {
            note = NoteNonCalculee();
        }
        else
         {   
        note = RetournerNoteCalculee(noteMoyenne).toString();
        }
        return note;
    }
    
    private String NoteNonCalculee()
    {
        return "La note sera calculée lorsque plus de membres auront noté ce restaurant.";
    }
    
    private Double RetournerNoteCalculee(Double noteMoyenne)
    {
        return noteMoyenne;
    }
    
    public List<Commentaire> afficherCommentaires()
    {
        return resto.ListCommentaire();
    }

    /**
     * @return the resto
     */
    public Restaurant getResto() {
        return resto;
    }

    /**
     * @param resto the resto to set
     */
    public void setResto(Restaurant resto) {
        this.resto = resto;
    }
    
}
