/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.iut2.genconf.modele;

import fr.uga.iut2.genconf.vue.CLI;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author leola
 */
public class Programme {
    
    private Set<LocalDate> planning;
    private Conference conf;
    
    
    public class durée{
        private int durée;


        public durée(int durée) {
            this.durée = durée;
        }
        
        
    }

    public Programme(Conference conf) {
        this.conf = conf;
        this.planning = new HashSet<>();
        
        this.planning.add(this.conf.getDateDebut());
        this.planning.add(this.conf.getDateFin());
                
        for ( Session s : this.conf.getSessions().values()){
            this.planning.add(s.getDateDebut());
            this.planning.add(s.getDateFin());


    protected void update(){
        for (LocalDate pause : this.planning){
            System.out.println(pause);
            
            //A faire afficher toute les pauses sur une interface graphique et pouvoir r�cuperer une. 
            
            //if (bouton selectionn�){
                //demande de rentrer la nouvelle heure voulu pour la pause s�lection�
                //mofifier en cons�quence
       // }

        }
                
    }

       
    public void addPause(LocalDate dateDebut, LocalDate dateFin){
        
        assert (!dateFin.isAfter(this.conf.getDateFin()) && (!dateDebut.isBefore(this.conf.getDateDebut())));
               
        LocalDate date1 = this.conf.getDateDebut();
        int duréeconf = this.conf.getDateFin().getDayOfYear() - this.conf.getDateDebut().getDayOfYear();
        for (int i = 0; i <= duréeconf; i++ ){           
            dateDebut.plusDays(i);
            this.planning.add(dateDebut);
            
            dateFin.plusDays(i);
            this.planning.add(dateFin);
        }    
        
    }

    public Set<LocalDate> getPlanning() {

    }
    
    public void addPause(LocalDate date){
        this.planning.add(date);
    }

    public Set<LocalDate> getPauses() {

        return planning;
    }
    
    
}
