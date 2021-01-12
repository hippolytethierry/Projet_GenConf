/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.iut2.genconf.modele;

import fr.uga.iut2.genconf.util.Type;
import java.io.Serializable;


/**
 *
 * @author leola
 */
public class Communication implements Serializable, Comparable<Communication>{
    
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private String titre;
    private Type type;
    private Utilisateur correspondant;
    private Session session;
    
    public static Communication initialiseCommunication(String titre, Type type, Utilisateur corres, Session session) {
        Communication com = new Communication(titre, type, corres, session);
        com.ajouterCorrespondant(corres);
        return com;
    }

    public Communication(String titre, Type type, Utilisateur correspondant, Session session) {
        setTitre(titre);
        assert !session.getType().equals(type);
        this.type = type;
        setCorrespondant(correspondant);
        setSession(session);
    }
    
    public void ajouterCorrespondant(Utilisateur corres){
        assert !getCorrespondant().equals(null);
        corres.addCommunications(this);
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Utilisateur getCorrespondant() {
        return correspondant;
    }

    private void setCorrespondant(Utilisateur correspondant) {
        this.correspondant = correspondant;
    }

    private void setSession(Session session) {
        this.session = session;
    }
    
    protected void update(){
        
    }
    
    @Override
    public int compareTo(Communication com) {
        return this.getTitre().compareTo(com.getTitre());
    }
    
}
