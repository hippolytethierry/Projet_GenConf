package fr.uga.iut2.genconf.controleur;

import fr.uga.iut2.genconf.modele.*;
import fr.uga.iut2.genconf.modele.GenConf;
import fr.uga.iut2.genconf.util.Type;
import fr.uga.iut2.genconf.vue.GUI;
import fr.uga.iut2.genconf.vue.IHM;
import java.time.LocalDate;
import java.util.Set;


public class Controleur {

    private final GenConf genconf;
    private final IHM ihm;

    public Controleur(GenConf genconf) {
        this.genconf = genconf;

        // choisir la classe CLI ou GUI
        // this.ihm = new CLI(this);
        this.ihm = new GUI(this);
    }

    public void run() {
        this.ihm.afficherInterface();
    }

    public void gererDialogue(Commande cmd) {
        switch (cmd) {
            case QUITTER:
                this.ihm.fermerInterface();
                break;
            case CREER_UTILISATEUR:
                this.ihm.saisirUtilisateur();
                break;
            case CREER_CONFERENCE:
                this.ihm.saisirNouvelleConference(this.genconf.getConferences().keySet());
                break;
            case CREER_SESSION:
                this.ihm.saisirNouvelleSession(this.genconf.getConferences());
            case MODIFIER_CONFERENCE:
                this.ihm.choisirConference(this.genconf.getConferences().keySet());
            default:
                assert false: "Commande inconnue.";
        }
    }

    public void creerUtilisateur(IHM.InfosUtilisateur infos) {
        boolean nouvelUtilisateur = this.genconf.ajouteUtilisateur(
                infos.email,
                infos.nom,
                infos.prenom
        );
        if (nouvelUtilisateur) {
            this.ihm.informerUtilisateur(
                    "Nouvel·le utilisa·teur/trice : " + infos.prenom + " " + infos.nom + " <" + infos.email + ">",
                    true
            );
        } else {
            this.ihm.informerUtilisateur(
                    "L'utilisa·teur/trice " + infos.email + " est déjà connu·e de GenConf.",
                    false
            );
        }
    }

    public void creerConference(IHM.InfosConference infos) {
        // création d'un Utilisateur si nécessaire
        boolean nouvelUtilisateur = this.genconf.ajouteUtilisateur(
                infos.admin.email,
                infos.admin.nom,
                infos.admin.prenom
        );
        if (nouvelUtilisateur) {
            this.ihm.informerUtilisateur("Nouvel·le utilisa·teur/trice : " + infos.admin.prenom + " " + infos.admin.nom + " <" + infos.admin.email + ">",
                    true
            );
        }

        this.genconf.nouvelleConference(
                infos.nom,
                infos.dateDebut,
                infos.dateFin,
                infos.admin.email
        );
        this.ihm.informerUtilisateur(
                "Nouvelle conférence : " + infos.nom + ", administrée par " + infos.admin.email,
                true
        );
    }
    
    public void modifierConference(IHM.InfosConference infos, String nomConf){
        Conference c = this.genconf.getConferences().get(nomConf);
        c.setDateDebut(infos.dateDebut);
        c.setDateFin(infos.dateFin);
        c.setNom(infos.nom);
        this.ihm.informerUtilisateur("La conférence "+nomConf+" à été modifié; Nom = "+infos.nom+", date de debut = "+infos.dateDebut+", date de fin = "+infos.dateFin, true);
    }
    
    public void supprimerConference(String nomConf) {
        this.ihm.informerUtilisateur("La conférence "+nomConf+" à été supprimé.", true);        
        this.genconf.getConferences().remove(nomConf);
    }
    
    public void toModifierConf(String nomConf){
        
    }
    
    public Conference selectionnerConference (String nomConf){
        return this.genconf.getConferences().get(nomConf);
    }
    
    public Set<String> getListeConferences(){
        return this.genconf.getConferences().keySet();
    }
    
    public Set<String> getListeSessions(String nomConf){
        Conference confSelectionne = this.genconf.getConferences().get(nomConf);
        return confSelectionne.getSessions().keySet();
    }
    

    public void creerSession(IHM.InfosSession infos) {
        // création d'un Utilisateur si nécessaire
        boolean nouvelUtilisateur = this.genconf.ajouteUtilisateur(
                infos.anim.email,
                infos.anim.nom,
                infos.anim.prenom
        );
        if (nouvelUtilisateur) {
            this.ihm.informerUtilisateur("Nouvel·le utilisa·teur/trice : " + infos.anim.prenom + " " + infos.anim.nom + " <" + infos.anim.email + ">",
                    true
            );
        }
        
        Session.initialiseSession(
                infos.nom,
                infos.type,
                infos.dateDebut,
                infos.dateFin,
                infos.conf,
                this.genconf.getUsers().get(infos.anim.email)
        );
        this.ihm.informerUtilisateur(
                "Nouvelle session : " + infos.nom + ", animée par " + infos.anim.email,
                true
        );
    }
        
        public void creerCommunication(IHM.InfosCommunication infos) {
        // création d'un Utilisateur si nécessaire
        boolean nouvelUtilisateur = this.genconf.ajouteUtilisateur(
                infos.correspondant.email,
                infos.correspondant.nom,
                infos.correspondant.prenom
        );
        if (nouvelUtilisateur) {
            this.ihm.informerUtilisateur("Nouve·au/lle correspondant·e : " + infos.correspondant.prenom + " " + infos.correspondant.nom + " <" + infos.correspondant.email + ">",
                    true
            );
        }
        
        Communication.initialiseCommunication(
                infos.nom,
                infos.type,
                /*infos.dateDebut,
                infos.dateFin,*/
                this.genconf.getUsers().get(infos.correspondant.email),
                infos.sess
        );
        this.ihm.informerUtilisateur(
                "Nouvelle session : " + infos.nom + ", animée par " + infos.anim.email,
                true
        );

    public void modifierSession(IHM.InfosSession infos, String nomSession){
        Session s = this.genconf.getConferences().get(infos.conf.getNom()).getSessions().get(nomSession);
        s.setNom(infos.nom);
        s.setDateDebut(infos.dateDebut);
        s.setDateFin(infos.dateFin);
        this.ihm.informerUtilisateur("La session "+nomSession+" à été modifié; Nom = "+infos.nom+", date de debut = "+infos.dateDebut+", date de fin = "+infos.dateFin, true);
    }
    
    public Session selectionnerSession (String nomSession, String nomConf){
       return this.genconf.getConferences().get(nomConf).getSessions().get(nomSession);
    }
    
    public void modifierCommunication(IHM.InfosConference infos){
        
    }
    
    public void supprimerCommunication(String nomCom) {
        this.ihm.informerUtilisateur("La communication "+nomCom+" à été supprimé.", true);        
        this.genconf.getConferences().remove(nomCom);
    }
        
    public Set<String> getListeCommunications(String nomSession, String nomConf){
        Session sessionSelectionne = this.genconf.getConferences().get(nomConf).getSessions().get(nomSession);
        return sessionSelectionne.getCommunications().keySet();
    }
        
    public void toModifierCommunication(String nomCom){
        
    }

    public void supprimerSession(String nomSession) {
        this.ihm.informerUtilisateur("La session "+nomSession+" à été supprimé.", true);        
        this.genconf.getConferences().remove(nomSession);
    }    
    
    public void supprimerAnimSession(String infoAnim, String nomSession, String nomConf){
        this.ihm.informerUtilisateur("L'animateur "+infoAnim+" de la session "+nomSession+" à été supprimé.", true);
        selectionnerSession(nomSession, nomConf).getAnimateurs().remove(infoAnim);
    }
    
    public void ajouterAnim(String infoAnim, String nomSession, String nomConf){
        this.ihm.informerUtilisateur("L'animateur "+infoAnim+" de la session "+nomSession+" à été ajouté.", true);
        selectionnerSession(nomSession, nomConf).getAnimateurs().put(infoAnim, this.genconf.getUsers().get(infoAnim));
    }
    
    public boolean freeEmail(String email){
        return !this.genconf.getUsers().containsKey(email);

    }
}
