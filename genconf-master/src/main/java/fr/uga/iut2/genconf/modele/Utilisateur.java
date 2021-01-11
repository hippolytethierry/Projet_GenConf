package fr.uga.iut2.genconf.modele;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.validator.routines.EmailValidator;



public class Utilisateur implements Serializable, Comparable<Utilisateur>{

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private final String email;
    private String nom;
    private String prenom;
    private final Map<String, Conference> conferencesAdministrees;  // association qualifiée par le nom
    private Map<String, Session> sessionsAnimées;                   // association qualifiée par le nom
    private Map<String, Communication> communicationsAdministrees;  // association qualifiée par le titre

    public Utilisateur(String email, String nom, String prenom) {
        assert EmailValidator.getInstance(false, false).isValid(email);
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.conferencesAdministrees = new HashMap<>();
        this.sessionsAnimées = new HashMap();
        this.communicationsAdministrees = new HashMap();
    }

    public String getEmail() {
        return this.email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public Map<String, Conference> getConfs(){
        return this.conferencesAdministrees;
    }
    
    public Map<String, Session> getSessions() {
        return this.sessionsAnimées;
    }
    
    public Map<String, Communication> getCommunications() {
        return this.communicationsAdministrees;
    }

    public void addSessionAnimee(Session session) {
        assert !getSessions().containsKey(session.getNom());
        getSessions().put(session.getNom(), session);
    }

    public void addCommunications(Communication communication) {
        assert !getCommunications().containsKey(communication.getTitre());
        getCommunications().put(communication.getTitre(), communication);
    }

    public void addConferenceAdministree(Conference conf) {
        assert !getConfs().containsKey(conf.getNom());
        getConfs().put(conf.getNom(), conf);
    }

    @Override
    public int compareTo(Utilisateur user) {
        return this.getNom().compareTo(user.getNom());
    }
}
