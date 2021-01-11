/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.iut2.genconf.modele;

import fr.uga.iut2.genconf.util.Type;

/**
 *
 * @author leola
 */
public class Communication {
    
    private String titre;
    private Type type;
    private Utilisateur correspondant;
    private Session session;
    
    public static Communication initialiseCommunication(String titre, Type type, Utilisateur corres, Session session) {
        Communication com = new Communication(titre, type, corres, session);
        com.ajouterCorrespondant(corres);
        return com;
    }

    public Communication(String titre, Type type,Utilisateur Correspondant, Session session) {
        setTitre(titre);
        setType(type);
        setCorrespondant(Correspondant);
        setSession(session);
    }
    
    public void ajouterCorrespondant(Utilisateur corres){
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

    public void setCorrespondant(Utilisateur correspondant) {
        this.correspondant = correspondant;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    public void update(){
        
    }
    
    
    
    
    
    
    
}
