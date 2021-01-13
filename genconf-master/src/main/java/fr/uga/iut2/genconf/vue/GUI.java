package fr.uga.iut2.genconf.vue;

import fr.uga.iut2.genconf.controleur.Commande;
import fr.uga.iut2.genconf.controleur.Controleur;
import fr.uga.iut2.genconf.modele.Conference;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CountDownLatch;


public class GUI extends IHM {
    private final Controleur controleur;
    private final CountDownLatch eolBarrier;

    private final VuePrincipale vuePrincipale;
    private final VueCreationConference vueCreationConf;
    private final VueCreationUtilisateur vueCreationUser;
    private final VueConference vueConf;
    private final VueSession vueSession;
    private final VueModifierConference vueModifierConf;
    private final VueModifierSession vueModifierSession;
    private final VueEtat vueEtat;

    // identifiants uniques pour les vues composant la vue principale
    private static final String VUE_ETAT = "etat";
    private static final String VUE_CREATION_CONF = "creation_conf";
    private static final String VUE_CREATION_USER = "creation_user";
    private static final String VUE_CONFS = "liste_des_conferences";
    private static final String VUE_SESSIONS = "liste_des_sessions";
    private static final String VUE_MODIFIER_CONFERENCE = "modifier_une_conf";
    private static final String VUE_MODIFIER_SESSION = "modifier_une_session";

    public GUI(Controleur controleur) {
        this.controleur = controleur;

        // initialisé à 1 pour attendre l'évènement correspondant à la fin de vie de GUI
        this.eolBarrier = new CountDownLatch(1);
        
        // création de l'interface
        this.vueEtat = new VueEtat(this);
        this.vueCreationConf = new VueCreationConference(this);
        this.vueCreationUser = new VueCreationUtilisateur(this);
        this.vueConf = new VueConference(this);
        this.vueSession = new VueSession(this);
        this.vueModifierConf = new VueModifierConference(this);
        this.vueModifierSession = new VueModifierSession(this);

        this.vuePrincipale = new VuePrincipale(this);
        this.vuePrincipale.ajouterVue(this.vueEtat, GUI.VUE_ETAT);
        this.vuePrincipale.ajouterVue(this.vueCreationConf, GUI.VUE_CREATION_CONF);
        this.vuePrincipale.ajouterVue(this.vueCreationUser, GUI.VUE_CREATION_USER);
        this.vuePrincipale.ajouterVue(this.vueConf, GUI.VUE_CONFS);
        this.vuePrincipale.ajouterVue(this.vueSession, GUI.VUE_SESSIONS);
        this.vuePrincipale.ajouterVue(this.vueModifierConf, GUI.VUE_MODIFIER_CONFERENCE);
        this.vuePrincipale.ajouterVue(this.vueModifierSession, GUI.VUE_MODIFIER_SESSION);
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
    }

//-----  Éléments du dialogue  -------------------------------------------------

    protected void actionCreerConference() {
        this.controleur.gererDialogue(Commande.CREER_CONFERENCE);
    }

    protected void actionCreerUtilisateur() {
        this.controleur.gererDialogue(Commande.CREER_UTILISATEUR);
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
    
    protected void modifierConference(Optional<InfosConference> conf){
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        conf.ifPresentOrElse(
                infos -> this.controleur.modifierConference(infos),
                () -> this.vueEtat.setEtat("")
        );
    }
    
    protected void toModifierConference(String nomConf){
        this.vuePrincipale.afficherVue(GUI.VUE_MODIFIER_CONFERENCE);        
    }
    
    protected void returnVueConference(){
        
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

    protected void creerSession(Optional<InfosSession> nlleSession) {
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        nlleSession.ifPresentOrElse(
                infos -> this.controleur.creerSession(infos),
                () -> this.vueEtat.setEtat("")
        );
    }
    
    protected void toModifierSession(String nomSession){
        this.vuePrincipale.afficherVue(GUI.VUE_MODIFIER_CONFERENCE);        
    }
    
    protected void toVoirPlusSession(String nomConf){
        this.vueSession.setSessionsExistantes(this.controleur.getListeSessions(nomConf));   
        this.vueSession.setConf(nomConf);
        this.vuePrincipale.afficherVue(GUI.VUE_SESSIONS);
//        this.controleur.voirPlusConference(nomConf);
    }
    
    protected void supprimerSession(String nomConf){
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        this.controleur.supprimerSession(nomConf);
    }
    
    protected void toVoirPlusCommunications(String nomSession){
        this.vueCommunication.setComsExistantes(this.controleur.getListeCommunications(nomSession));
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
    
}
