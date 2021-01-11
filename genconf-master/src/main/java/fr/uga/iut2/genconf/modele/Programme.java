/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.iut2.genconf.modele;

import fr.uga.iut2.genconf.vue.CLI;
import java.time.LocalDate;
import java.util.Set;

/**
 *
 * @author leola
 */
public class Programme {
    
    private Set<LocalDate> pauses;
    private Conference conf;

    public Programme(Conference conf) {
        this.conf = conf;
        String s = ("Quelle date horraire voulait vous pour le d�jeuner?");
       // CLI.informerUtilisateur(s, true);
    }

    protected void update(){
        for (LocalDate pause : this.pauses){
            System.out.println(pause);
            
            //A faire afficher toute les pauses sur une interface graphique et pouvoir r�cuperer une. 
            
            //if (bouton selectionn�){
                //demande de rentrer la nouvelle heure voulu pour la pause s�lection�
                //mofifier en cons�quence
       // }
        }
    }
    
    public void addPause(LocalDate date){
        this.pauses.add(date);
    }

    public Set<LocalDate> getPauses() {
        return pauses;
    }
    
    
}
