/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.iut2.genconf.vue;

import java.util.HashSet;
import java.util.Set;
import fr.uga.iut2.genconf.modele.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

/**
 *
 * @author hippo
 */
public class VueModifierConference extends javax.swing.JPanel {
    private final GUI gui;
    private Set<String> confsExistantes;
    private boolean valideConf;
    private Conference conf;
    
    /**
     * Creates new form VueModifierConference
     */
    public VueModifierConference(GUI gui) {
        this.gui = gui;
        this.confsExistantes = new HashSet();
        this.valideConf = false;
        
        initComponents();
        
        this.valider.setEnabled(false);
    }
    
    public void setConf(Conference conf){
        this.conf = conf;
        this.labelConf.setText(this.labelConf.getText()+this.conf.getNom());
    }
    
    public void setConfsExistantes(final Set<String> confsExistantes) {
        assert confsExistantes != null;
        this.confsExistantes = confsExistantes;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField2 = new java.awt.TextField();
        labelConf = new java.awt.Label();
        jPanel1 = new javax.swing.JPanel();
        label3 = new java.awt.Label();
        textFieldNomConf = new java.awt.TextField();
        label4 = new java.awt.Label();
        jSpinnerDateDebut = new javax.swing.JSpinner();
        label5 = new java.awt.Label();
        jSpinnerDateFin = new javax.swing.JSpinner();
        annuler = new javax.swing.JButton();
        valider = new javax.swing.JButton();
        precedent = new javax.swing.JButton();

        textField2.setText("textField2");

        setPreferredSize(new java.awt.Dimension(400, 360));

        labelConf.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelConf.setText("Modifier la conférence");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Conférence"));
        jPanel1.setToolTipText("Modifier");
        jPanel1.setName("Modifier"); // NOI18N

        label3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label3.setText("Nom :");

        textFieldNomConf.setBackground(new java.awt.Color(255, 255, 255));
        textFieldNomConf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                verificationConf(evt);
            }
        });

        label4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label4.setText("Date de début : ");

        jSpinnerDateDebut.setModel(new javax.swing.SpinnerDateModel());

        label5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label5.setText("Date de fin : ");

        jSpinnerDateFin.setModel(new javax.swing.SpinnerDateModel());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSpinnerDateFin, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSpinnerDateDebut, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 67, Short.MAX_VALUE))
                            .addComponent(textFieldNomConf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldNomConf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerDateDebut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerDateFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jSpinnerDateDebut.getAccessibleContext().setAccessibleName("liste date début");
        jSpinnerDateFin.getAccessibleContext().setAccessibleName("liste date fin");

        annuler.setText("Annuler");
        annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerActionPerformed(evt);
            }
        });

        valider.setText("Valider");
        valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validerActionPerformed(evt);
            }
        });

        precedent.setText("Precedent");
        precedent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precedentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelConf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 259, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(precedent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(annuler)
                        .addGap(18, 18, 18)
                        .addComponent(valider)
                        .addGap(14, 14, 14))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelConf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(annuler)
                    .addComponent(valider)
                    .addComponent(precedent))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        labelConf.getAccessibleContext().setAccessibleName("Modifier la conférence");
        jPanel1.getAccessibleContext().setAccessibleName("Modifier");
    }// </editor-fold>//GEN-END:initComponents

    private void annulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerActionPerformed
        this.gui.modifierConference(Optional.empty(), conf.getNom());
    }//GEN-LAST:event_annulerActionPerformed

    private void validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validerActionPerformed
        // Traitement des dates de début et fin
        Date debut = (Date) this.jSpinnerDateDebut.getModel().getValue();
        LocalDate dateDebut = Instant.ofEpochMilli(debut.getTime())
                                     .atZone(ZoneId.systemDefault())
                                     .toLocalDate();
        
        debut = (Date) this.jSpinnerDateFin.getModel().getValue();
        LocalDate dateFin = Instant.ofEpochMilli(debut.getTime())
                                     .atZone(ZoneId.systemDefault())
                                     .toLocalDate();
        
        IHM.InfosUtilisateur admin = new IHM.InfosUtilisateur(
               this.conf.getAdmin().getEmail().trim().toLowerCase(),
               this.conf.getAdmin().getNom().trim(),
               this.conf.getAdmin().getPrenom().trim()
        );
        
        IHM.InfosConference conference = new IHM.InfosConference(
                this.textFieldNomConf.getText().trim(),
                dateDebut,
                dateFin,
                admin
         );
        
        this.gui.modifierConference(Optional.of(conference), conf.getNom());
    }//GEN-LAST:event_validerActionPerformed

    private void verificationConf(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_verificationConf
        boolean valideSaisie;
        valideSaisie = (textFieldNomConf.getText() != null) && (textFieldNomConf.getText().length() > 0);
        valideConf = (!this.confsExistantes.contains(textFieldNomConf.getText().trim().toLowerCase()) || (textFieldNomConf.getText().trim() == this.conf.getNom()));
        this.valider.setEnabled(valideConf&&valideSaisie);
    }//GEN-LAST:event_verificationConf

    private void precedentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precedentActionPerformed
        this.gui.choisirConference(confsExistantes);
    }//GEN-LAST:event_precedentActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annuler;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinnerDateDebut;
    private javax.swing.JSpinner jSpinnerDateFin;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label labelConf;
    private javax.swing.JButton precedent;
    private java.awt.TextField textField2;
    private java.awt.TextField textFieldNomConf;
    private javax.swing.JButton valider;
    // End of variables declaration//GEN-END:variables
}