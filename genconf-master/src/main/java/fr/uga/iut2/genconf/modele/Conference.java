package fr.uga.iut2.genconf.modele;

import fr.uga.iut2.genconf.util.Type;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class Conference implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private final GenConf genconf;
    private final String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private final Map<String, Utilisateur> administrateurs;  // association qualifiée par l'email
    private final Map<String, Utilisateur> inscrits;//associattion qualifié par l'email
    private final Map<String, Session> sessions;//association qualifié par les noms
    private Programme programme;
            

    // Invariant de classe : !dateDebut.isAfter(dateFin)
    //     On utilise la négation ici pour exprimer (dateDebut <= dateFin), ce
    //     qui est équivalent à !(dateDebut > dateFin).

    public static Conference initialiseConference(GenConf genconf, String nom, LocalDate dateDebut, LocalDate dateFin, Utilisateur admin) {
        Conference conf = new Conference(genconf, nom, dateDebut, dateFin);
        conf.ajouteAdministrateur(admin);
        return conf;
    }

    public Conference(GenConf genconf, String nom, LocalDate dateDebut, LocalDate dateFin) {
        assert !dateDebut.isAfter(dateFin);
        this.genconf = genconf;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.administrateurs = new HashMap<>();
    }

    public String getNom() {
        return this.nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        assert !dateDebut.isAfter(this.dateFin);
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        assert !this.dateDebut.isAfter(dateFin);
        this.dateFin = dateFin;
    }

    public void ajouteAdministrateur(Utilisateur admin) {
        assert !this.administrateurs.containsKey(admin.getEmail());
        this.administrateurs.put(admin.getEmail(), admin);
        admin.ajouteConferenceAdministree(this);
    }

    public Map<String, Utilisateur> getInscrits() {
        return inscrits;
    }
    
    public void ajouterInscrit(Utilisateur inscrit){
        this.inscrits.put(inscrit.getEmail(), inscrit);
    }

    public Map<String, Session> getSessions() {
        return sessions;
    }
    
    public void créerSession(String nom,Type type, LocalDate dateDebut, LocalDate dateFin){
        this.sessions.put(nom, new Session(nom,type,dateDebut,dateFin));
    }
         

    public Programme getProgramme() {
        return programme;
    }
    
    public void definirProgramme(){
        this.programme = new Programme(this);
    }
    
    public void update(){
        
    }
    
    
}
