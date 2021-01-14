package fr.uga.iut2.genconf.vue;

import java.time.LocalDate;
import java.util.Set;
import fr.uga.iut2.genconf.util.Type;
import fr.uga.iut2.genconf.modele.*;

public abstract class IHM {
    /**
     * Classe conteneur pour les informations saisies à propos d'un
     * {@link fr.uga.iut2.genconf.modele.Utilisateur}.
     *
     * <ul>
     * <li>Tous les attributs sont `public` par commodité d'accès.</li>
     * <li>Tous les attributs sont `final` pour ne pas être modifiables.</li>
     * </ul>
     */
    public static class InfosUtilisateur {
        public final String email;
        public final String nom;
        public final String prenom;

        public InfosUtilisateur(final String email, final String nom, final String prenom) {
            this.email = email;
            this.nom = nom;
            this.prenom = prenom;
        }
    }

    /**
     * Classe conteneur pour les informations saisies pour une nouvelle
     * {@link fr.uga.iut2.genconf.modele.Conference}.
     *
     * <ul>
     * <li>Tous les attributs sont `public` par commodité d'accès.</li>
     * <li>Tous les attributs sont `final` pour ne pas être modifiables.</li>
     * </ul>
     */
    public static class InfosConference {
        public final String nom;
        public final LocalDate dateDebut;
        public final LocalDate dateFin;
        public final InfosUtilisateur admin;

        public InfosConference(final String nom, final LocalDate dateDebut, final LocalDate dateFin, final InfosUtilisateur admin) {
            assert !dateDebut.isAfter(dateFin);
            this.nom = nom;
            this.dateDebut = dateDebut;
            this.dateFin = dateFin;
            this.admin = admin;
        }
        
        public InfosConference getInfos(String nomConf){
            
        }
    }
    
    public static class InfosSession {
        public final String nom;
        public final LocalDate dateDebut;
        public final LocalDate dateFin;
        public final InfosUtilisateur anim;
        public final Type type;
        
        public InfosSession(final String nom, final Type type, final LocalDate dateDebut, final LocalDate dateFin, final InfosUtilisateur anim){
            assert !dateDebut.isAfter(dateFin);
            this.nom = nom;
            this.type = type;
            this.dateDebut = dateDebut;
            this.dateFin = dateFin;
            this.anim = anim;
        }        
    }
    
    public static class InfosCommunication {
        public final String nom;
        public final InfosUtilisateur correspondant;
        public final Type type;
        
        public InfosCommunication(final String nom, final Type type, final LocalDate dateDebut, final LocalDate dateFin, final InfosUtilisateur correspondant){
            assert !dateDebut.isAfter(dateFin);
            this.nom = nom;
            this.type = type;
            this.correspondant = correspondant;
        }        
    }    
    /**
     * Rend actif l'interface Humain-machine.
     *
     * L'appel est bloquant : le contrôle est rendu à l'appelant une fois que
     * l'IHM est fermée.
     *
     */
    public abstract void afficherInterface();

    /**
     * Rend inactif l'interface Humain-machine.
     *
     */
    public abstract void fermerInterface();

    /**
     * Affiche un message d'information à l'attention de l'utilisa·teur/trice.
     *
     * @param msg Le message à afficher.
     *
     * @param succes true si le message informe d'une opération réussie, false
     *     sinon.
     */
    public abstract void informerUtilisateur(final String msg, final boolean succes);

    /**
     * Récupère les informations à propos d'un
     * {@link fr.uga.iut2.genconf.modele.Utilisateur}.
     *
     */
    public abstract void saisirUtilisateur();

    /**
     * Récupère les informations nécessaires à la création d'une nouvelle
     * {@link fr.uga.iut2.genconf.modele.Conference}.
     *
     * @param nomsExistants L'ensemble des noms de conférences qui ne sont plus
     *     disponibles.
     *
     */
    public abstract void saisirNouvelleConference(final Set<String> nomsExistants);
    
    public abstract void choisirConference(final Set<String> nomsConfsExistantes);
    
    public abstract void saisirNouvelleSession(final String nomConf);    
    
}
