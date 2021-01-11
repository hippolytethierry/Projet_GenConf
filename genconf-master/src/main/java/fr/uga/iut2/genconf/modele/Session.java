/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.iut2.genconf.modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import fr.uga.iut2.genconf.util.Type;

/**
 *
 * @author hippo
 */
public class Session implements Serializable{
    
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private String nom;
    private LocalDate dateDebut; 
    private LocalDate dateFin;
    private Type type;
    private Map<String, Utilisateur> animateurs;
    private Conference conf;
    
    public Session(String nom, Type type, LocalDate dateDebut, LocalDate dateFin){
        
    }
    
    private Map<String, Utilisateur> getAnimateurs(){
        return this.animateurs;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        assert !dateDebut.isAfter(conf.getDateDebut());
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        assert !conf.getDateFin().isAfter(dateFin);
        this.dateFin = dateFin;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    public void ajouterAnimateur(Utilisateur anim){
        assert !getAnimateurs().containsKey(anim.getEmail());
        getAnimateurs().put(anim.getEmail(), anim);
    }
}
