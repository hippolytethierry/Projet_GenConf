package fr.uga.iut2.genconf.controleur;

import fr.uga.iut2.genconf.modele.*;
import fr.uga.iut2.genconf.modele.GenConf;
import fr.uga.iut2.genconf.vue.GUI;
import fr.uga.iut2.genconf.vue.IHM;
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
    
    public void modifierConference(IHM.InfosConference infos){
        
    }
    
    public void supprimerConference(String nomConf) {
        this.ihm.informerUtilisateur("La conférence "+nomConf+" à été supprimé.", true);        
        this.genconf.getConferences().remove(nomConf);
    }
    
    public void toModifierConf(String nomConf){
        
    }
    
    public Conference selectionnerConference ( String nomConf){
        return this.genconf.getConferences().get(nomConf);
    }
            
    public Set<String> getListeSessions(String nomConf){
        Conference confSelectionne = this.genconf.getConferences().get(nomConf);
        return confSelectionne.getSessions().keySet();
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

    public void supprimerSession(String nomConf) {
        this.ihm.informerUtilisateur("La conférence "+nomConf+" à été supprimé.", true);        
        this.genconf.getConferences().remove(nomConf);
    }    
}
