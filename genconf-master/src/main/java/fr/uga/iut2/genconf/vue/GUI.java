package fr.uga.iut2.genconf.vue;

import fr.uga.iut2.genconf.controleur.Commande;
import fr.uga.iut2.genconf.controleur.Controleur;
import fr.uga.iut2.genconf.modele.*;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CountDownLatch;


public class GUI extends IHM {
    private final Controleur controleur;
    private final CountDownLatch eolBarrier;

    private final VuePrincipale vuePrincipale;
    private final VueCreationConference vueCreationConf;
    private final VueCreationCommunication vueCreationCom;
    private final VueCreationSession vueCreationSession;
    private final VueCreationUtilisateur vueCreationUser;
    private final VueConference vueConf;
    private final VueSession vueSession;
    private final VueCommunication vueCommunication;
    private final VueModifierConference vueModifierConf;
    private final VueModifierCommunication vueModifierCom;
    private final VueModifierSession vueModifierSession;
    private final VueAjouterAnimateur vueAjouterAnim;
    private final VueSupprimerAnimateur vueSupprimerAnim;
    private final VueEtat vueEtat;

    // identifiants uniques pour les vues composant la vue principale
    private static final String VUE_ETAT = "etat";
    private static final String VUE_CREATION_CONF = "creation_conf";
    private static final String VUE_CREATION_SESSION = "creation_session";
    private static final String VUE_CREATION_USER = "creation_user";
    private static final String VUE_CONFS = "liste_des_conferences";
    private static final String VUE_SESSIONS = "liste_des_sessions";
    private static final String VUE_COMS = "liste_des_communications";
    private static final String VUE_MODIFIER_CONFERENCE = "modifier_une_conf";
    private static final String VUE_CREATION_COMMUNICATION = "creation_communication";
    private static final String VUE_MODIFIER_COMMUNICATION = "modifier_une_com";
    private static final String VUE_MODIFIER_SESSION = "modifier_une_session";
    private static final String VUE_AJOUTER_ANIMATEUR = "ajouter_un_animateur";
    private static final String VUE_SUPPRIMER_ANIMATEUR = "supprimer_un_animateur";

    public GUI(Controleur controleur) {
        this.controleur = controleur;

        // initialisé à 1 pour attendre l'évènement correspondant à la fin de vie de GUI
        this.eolBarrier = new CountDownLatch(1);
        
        // création de l'interface
        this.vueEtat = new VueEtat(this);
        this.vueCreationConf = new VueCreationConference(this);
        this.vueCreationCom = new VueCreationCommunication(this);
        this.vueCreationUser = new VueCreationUtilisateur(this);
        this.vueCreationSession = new VueCreationSession(this);       
        this.vueConf = new VueConference(this);
        this.vueSession = new VueSession(this);
        this.vueCommunication = new VueCommunication(this);
        this.vueModifierConf = new VueModifierConference(this);
        this.vueModifierCom = new VueModifierCommunication(this);
        this.vueModifierSession = new VueModifierSession(this);
        this.vueAjouterAnim = new VueAjouterAnimateur(this);
        this.vueSupprimerAnim = new VueSupprimerAnimateur(this);

        this.vuePrincipale = new VuePrincipale(this);
        this.vuePrincipale.ajouterVue(this.vueEtat, GUI.VUE_ETAT);
        this.vuePrincipale.ajouterVue(this.vueCreationConf, GUI.VUE_CREATION_CONF);
        this.vuePrincipale.ajouterVue(this.vueCreationSession, GUI.VUE_CREATION_SESSION);
        this.vuePrincipale.ajouterVue(this.vueCreationUser, GUI.VUE_CREATION_USER);
        this.vuePrincipale.ajouterVue(this.vueConf, GUI.VUE_CONFS);
        this.vuePrincipale.ajouterVue(this.vueSession, GUI.VUE_SESSIONS);
        this.vuePrincipale.ajouterVue(this.vueModifierConf, GUI.VUE_MODIFIER_CONFERENCE);
        this.vuePrincipale.ajouterVue(this.vueCreationCom, GUI.VUE_CREATION_COMMUNICATION);
        this.vuePrincipale.ajouterVue(this.vueModifierCom, GUI.VUE_MODIFIER_COMMUNICATION);
        this.vuePrincipale.ajouterVue(this.vueCommunication, GUI.VUE_COMS);
        this.vuePrincipale.ajouterVue(this.vueModifierSession, GUI.VUE_MODIFIER_SESSION);
        this.vuePrincipale.ajouterVue(this.vueAjouterAnim, GUI.VUE_AJOUTER_ANIMATEUR);
        this.vuePrincipale.ajouterVue(this.vueSupprimerAnim, GUI.VUE_SUPPRIMER_ANIMATEUR);
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
    }

//-----  Éléments du dialogue  -------------------------------------------------

    protected void actionCreerConference() {
        this.controleur.gererDialogue(Commande.CREER_CONFERENCE);
    }

    protected void actionCreerUtilisateur() {
        this.controleur.gererDialogue(Commande.CREER_UTILISATEUR);
    }
    
    protected void actionModifierConference(){
        this.controleur.gererDialogue(Commande.MODIFIER_CONFERENCE);
    }

    protected void actionTerminer() {
        this.controleur.gererDialogue(Commande.QUITTER);
    }

    protected void creerUtilisateur(Optional<InfosUtilisateur> nouvelUtilisateur){
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        nouvelUtilisateur.ifPresentOrElse(
                infos -> this.controleur.creerUtilisateur(infos),   // suite a bouton creer
                () -> this.vueEtat.setEtat("")                      // suite a bouton annuler
        );
    }

    protected void creerConference(Optional<InfosConference> nlleConf) {
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        nlleConf.ifPresentOrElse(
                infos -> this.controleur.creerConference(infos),    
                () -> this.vueEtat.setEtat("")                      
        );
    }
    
    protected void modifierConference(Optional<InfosConference> conf, String nomConf){
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        conf.ifPresentOrElse(
                infos -> this.controleur.modifierConference(infos, nomConf),
                () -> this.vueEtat.setEtat("")
        );
    }
    
    protected void toModifierConference(String nomConf){
        this.vueModifierConf.setConfsExistantes(this.controleur.getListeConferences());
        this.vueModifierConf.setConf(selectionnerConference(nomConf));
        this.vuePrincipale.afficherVue(GUI.VUE_MODIFIER_CONFERENCE);         
    }
    
    protected void supprimerConference(String nomConf){
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        this.controleur.supprimerConference(nomConf);
    }   

    protected void creerCommunication(Optional<InfosCommunication> nlleComm) {
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        nlleComm.ifPresentOrElse(
                infos -> this.controleur.creerCommunication(infos),
                () -> this.vueEtat.setEtat("")
        );
    }
    
    protected void modifierCommunication(Optional<InfosCommunication> com, String nomCom){
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        com.ifPresentOrElse(
                infos -> this.controleur.modifierCommunication(infos, nomCom),
                () -> this.vueEtat.setEtat("")
        );
    }
    
    protected Conference selectionnerConference (String nomConf){
        return this.controleur.selectionnerConference(nomConf);
    }
    
    protected void toModifierCommunication(String nomSession, String nomConf, String nomCom){
        this.vueModifierCom.setCommunication(selectionnerCommunication(nomCom, nomSession, nomConf));
        this.vueModifierCom.setSession(selectionnerSession(nomSession, nomConf));
        this.vueModifierCom.setComsExistantes(this.controleur.getListeCommunications(nomSession, nomConf));
        this.vuePrincipale.afficherVue(GUI.VUE_MODIFIER_COMMUNICATION);        
    }
    
    protected Communication selectionnerCommunication (String nomCom, String nomSession, String nomConf){
       return this.controleur.selectionnerCommunication(nomCom, nomSession, nomConf);
    }

    protected void supprimerCommunication(String nomCom){
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        this.controleur.supprimerCommunication(nomCom);
    }
    
    protected void creerSession(Optional<InfosSession> nlleSession) {
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        nlleSession.ifPresentOrElse(
                infos -> this.controleur.creerSession(infos),
                () -> this.vueEtat.setEtat("")
        );
    }
   
    protected Session selectionnerSession (String nomSession, String nomConf){
       return this.controleur.selectionnerSession(nomSession, nomConf);
    }

    protected void modifierSession(Optional<InfosSession> session, String nomSession){
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        session.ifPresentOrElse(
                infos -> this.controleur.modifierSession(infos, nomSession),
                () -> this.vueEtat.setEtat("")
        );
    }
    
    protected void toModifierSession(String nomSession, String nomConf){
        this.vueModifierSession.setSessionsExistantes(this.controleur.getListeSessions(nomConf));
        this.vueModifierSession.setConf(selectionnerConference(nomConf));
        this.vueModifierSession.setSession(selectionnerSession(nomSession, nomConf));
        this.vuePrincipale.afficherVue(GUI.VUE_MODIFIER_SESSION);        
    }
    
    protected void toVoirPlusSession(String nomConf){
        this.vueSession.setSessionsExistantes(this.controleur.getListeSessions(nomConf));   
        this.vueSession.setConf(nomConf);
        this.vuePrincipale.afficherVue(GUI.VUE_SESSIONS);
    }
    
    protected void supprimerSession(String nomSession, String nomConf){
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        this.controleur.supprimerSession(nomSession, nomConf);
    }
    
    protected void toAjouterAnimateur(String nomSession, String nomConf){
        this.vueAjouterAnim.setSession(selectionnerSession(nomSession, nomConf));
        this.vuePrincipale.afficherVue(GUI.VUE_AJOUTER_ANIMATEUR);        
    }
    
    protected void toSupprimerAnimateur(String nomSession, String nomConf){
        this.vueSupprimerAnim.setAnimsExistants(selectionnerSession(nomSession, nomConf));
        this.vueSupprimerAnim.setSession(selectionnerSession(nomSession, nomConf));
        this.vuePrincipale.afficherVue(GUI.VUE_SUPPRIMER_ANIMATEUR);        
    }
    protected void supprimerAnimSession(String infosAnim, String nomSession, String nomConf){
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        this.controleur.supprimerAnimSession(infosAnim, nomSession, nomConf);         
    }
    
    protected void ajouterAnim(String infosAnim, String nomSession, String nomConf){
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        this.controleur.ajouterAnim(infosAnim, nomSession, nomConf);
    }
    
    protected void toVoirPlusCommunications(String nomSession, String nomConf){
        this.vueCommunication.setSession(selectionnerSession(nomSession, nomConf));
        this.vueCommunication.setComsExistantes(this.controleur.getListeCommunications(nomSession, nomConf));
        this.vuePrincipale.afficherVue(GUI.VUE_COMS);
    }
    
    protected boolean freeEmail(String email){
        return this.controleur.freeEmail(email);
    }

//-----  Implémentation des méthodes abstraites  -------------------------------

    @Override
    public void afficherInterface() {
        this.vuePrincipale.afficher();

        // On attend que GUI ait fini avant de rendre la main au contrôleur
        // (c'est à dire au moment de l'appel de `fermerInterface`)
        try {
            this.eolBarrier.await();
        }
        catch (InterruptedException exc) {
            System.err.println("Erreur d'exécution de l'interface.");
            System.err.flush();
        }
    }

    @Override
    public void fermerInterface() {
        this.vuePrincipale.fermer();

        // On notifie la fin de vie de GUI pour rendre la main au contrôleur
        this.eolBarrier.countDown();
    }

    @Override
    public void informerUtilisateur(final String msg, final boolean succes) {
        this.vueEtat.setEtat(msg + (succes ? " [OPERATION VALIDE]" : " [ÉCHEC]"));
    }

    @Override
    public void saisirUtilisateur() {
        this.vuePrincipale.afficherVue(GUI.VUE_CREATION_USER);
    }

    @Override
    public void saisirNouvelleConference(final Set<String> nomsExistants) {
        this.vueCreationConf.setNomsExistants(nomsExistants);
        this.vuePrincipale.afficherVue(GUI.VUE_CREATION_CONF);
    }
    
    @Override
    public void choisirConference(final Set<String> nomsConfsExistantes){
        this.vueConf.setConfsExistantes(nomsConfsExistantes);
        this.vuePrincipale.afficherVue(GUI.VUE_CONFS);
    }
    
    @Override
    public void saisirNouvelleCommunication(String nomSession, String nomConf) {
        this.vueCreationCom.setComsExistantes(this.controleur.getListeCommunications(nomSession, nomConf));
        this.vueCreationCom.setSession(this.controleur.selectionnerSession(nomSession, nomConf));
        this.vuePrincipale.afficherVue(GUI.VUE_CREATION_COMMUNICATION);
    }

    @Override
    public void saisirNouvelleSession(String nomConf) {
        this.vueCreationSession.setSessionsExistantes(this.controleur.getListeSessions(nomConf));
        this.vueCreationSession.setConf(this.controleur.selectionnerConference(nomConf));
        this.vuePrincipale.afficherVue(GUI.VUE_CREATION_SESSION);
    }
}
