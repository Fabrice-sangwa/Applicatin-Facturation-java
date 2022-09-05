/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturation;

import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fabrice
 */
public class Facture extends javax.swing.JFrame {

    /**
     * Creates new form Facture
     */
    public Facture() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        initialisationTable();
        

    }
    
    String element[] = {"Designation", "Prix", "Quantité", "sous total"};
    String[] ligne = new String[4];
    DefaultTableModel model = new DefaultTableModel(null, element);
    
    
    Double total = 0.0;
    
   private void initialisationTable(){
       
           try{ 
               
            Statement stm = connection.connectbd().createStatement();
            
            
            Statement stmdlt = connection.connectbd().createStatement();
            String requetedelete = "DELETE FROM `facturetemp`";
            stmdlt.executeUpdate(requetedelete);
            
            String requete = "SELECT * FROM facturetemp";
            ResultSet result = stm.executeQuery(requete);
            
            while(result.next()){
                ligne[0] =result.getString("Designation");
                ligne[1] =result.getString("Prix");
                ligne[2] =result.getString("Quantite");
                ligne[3] = String.valueOf(0.0);
                
                model.addRow(ligne);
                //JOptionPane.showMessageDialog(null,ligne);
                
            }
            tableauArticleFacture.setModel(model);
        } catch (SQLException ex) {
            //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   private void remplissagArticle(){
       
     
       
        String article  = idDesignationArticle.getText(); 
        if(article.isEmpty()){
            JOptionPane.showMessageDialog(null, "L'article n'existe pas ou n'a pas été renseigné");
        } else {
            try {
                
                
                //Declaration du tablau des totaux de la facture
                String elements[] = {"Code", "Client", "Total", "TVA", "TTC"};
                String[] lignes = new String[5];
                DefaultTableModel modelT = new DefaultTableModel(null, elements);
                
                 //on recherche dans la base de donnée le prix de l'article saisi
                String requete = "SELECT * FROM article where Designation=?";
                Double tva = 0.0;
                
                PreparedStatement stm = connection.connectbd().prepareStatement(requete);
                stm.setString(1, String.valueOf(article));
                ResultSet result = stm.executeQuery();
           
                
                
              
                
            while(result.next()){
                ligne[0] =result.getString("Designation");
                ligne[1] =result.getString("Prix");
                double prix = Double.parseDouble(ligne[1]);
                String qte = idQuantite.getText();
                int quantite;
                if (qte.isEmpty() || qte.contains("char")){
                    
                    //quantite = 0;
                    JOptionPane.showMessageDialog(null, "Veuillez renseigner correstement la quantité ");
                    break;
                }else {
                     quantite = Integer.parseInt(qte);
                }
                Double sousTotal = prix * quantite;
                total += sousTotal;
                tva = total * 0.16;
                
                ligne[2] = Integer.toString(quantite);
                ligne[3] = "$ " + String.valueOf(sousTotal);
                
                idPrixFacture.setText(ligne[1]);
                idTvaFacture.setText(String.valueOf(tva));
                
                model.addRow(ligne);
                tableauArticleFacture.setModel(model);
                //JOptionPane.showMessageDialog(null,ligne);
                
                if (idClientFacture.getText().isEmpty() || idClientFacture.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Veuillez renseigner le code de la facture et le nom du client ");
                    dispose();
                    Facture fenetreFacture = new  Facture();
                    fenetreFacture.setVisible(true);
                } else {
                lignes[0] = idCodeFacture.getText();
                lignes[1] = idClientFacture.getText();
                lignes[2] = String.valueOf(total);
                lignes[3] = String.valueOf(tva);
                lignes[4] = String.valueOf(total + tva );
                
                modelT.addRow(lignes);
                tableauApayerFacture.setModel(modelT);
                Statement requetStatement = connection.connectbd().createStatement();
                String commandesSql = "insert into facturetemp(Designation, Prix, Quantite) " +
                        "Values('" + ligne[0] + "', '" + prix + "',  '" + quantite + "')";
                requetStatement.executeUpdate(commandesSql);
                
                }
                
            }
                        //JOptionPane.showMessageDialog(null, "Insertion réussie");
            } catch (SQLException ex) {
                //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
         
        
            
           
       
   }

        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        idClientFacture = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        idDesignationArticle = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        idPrixFacture = new javax.swing.JLabel();
        Jtext8 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        idTvaFacture = new javax.swing.JLabel();
        idQuantite = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        idCodeFacture = new javax.swing.JTextField();
        jpanelimpro = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableauArticleFacture = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableauApayerFacture = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jButton7.setBackground(new java.awt.Color(153, 255, 0));
        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setText("Accueil");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(153, 255, 51));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("Ajouter");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(0, 255, 255));
        jLabel14.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        jLabel14.setText("Prix");

        jLabel15.setBackground(new java.awt.Color(0, 255, 255));
        jLabel15.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        jLabel15.setText("Client");

        idClientFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idClientFactureActionPerformed(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(0, 255, 255));
        jLabel16.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        jLabel16.setText("Details des Articles");

        idDesignationArticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idDesignationArticleActionPerformed(evt);
            }
        });

        jLabel18.setBackground(new java.awt.Color(0, 255, 255));
        jLabel18.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        jLabel18.setText("Code");

        jLabel19.setBackground(new java.awt.Color(0, 255, 255));
        jLabel19.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        jLabel19.setText("Désignation de l'article");

        idPrixFacture.setBackground(new java.awt.Color(0, 255, 255));
        idPrixFacture.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        idPrixFacture.setText("0.00");

        Jtext8.setBackground(new java.awt.Color(0, 255, 255));
        Jtext8.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        Jtext8.setText("Quantité");

        jLabel22.setBackground(new java.awt.Color(0, 255, 255));
        jLabel22.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        jLabel22.setText("TVA");

        idTvaFacture.setBackground(new java.awt.Color(0, 255, 255));
        idTvaFacture.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        idTvaFacture.setText("0.00");

        idQuantite.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                idQuantiteAncestorMoved(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        idQuantite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idQuantiteActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Bodoni MT", 0, 24)); // NOI18N
        jLabel13.setText("FACTITURA BOUTIQUE : Facture");

        jButton14.setBackground(new java.awt.Color(102, 255, 51));
        jButton14.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        jButton14.setText("Clients");
        jButton14.setToolTipText("");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        idCodeFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idCodeFactureActionPerformed(evt);
            }
        });

        jpanelimpro.setBackground(new java.awt.Color(0, 153, 153));

        tableauArticleFacture.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Designation", "Prix", "Quantité", "Sous Total"
            }
        ));
        tableauArticleFacture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableauArticleFactureMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableauArticleFacture);

        jLabel26.setBackground(new java.awt.Color(0, 255, 255));
        jLabel26.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        jLabel26.setText("A PAYER");

        tableauApayerFacture.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Code", "Client", "Total ", "TVA", "TTC"
            }
        ));
        jScrollPane1.setViewportView(tableauApayerFacture);

        javax.swing.GroupLayout jpanelimproLayout = new javax.swing.GroupLayout(jpanelimpro);
        jpanelimpro.setLayout(jpanelimproLayout);
        jpanelimproLayout.setHorizontalGroup(
            jpanelimproLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelimproLayout.createSequentialGroup()
                .addGroup(jpanelimproLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelimproLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jpanelimproLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE))
                        .addGroup(jpanelimproLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane2)))
                    .addGroup(jpanelimproLayout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jLabel26)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpanelimproLayout.setVerticalGroup(
            jpanelimproLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelimproLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton6.setBackground(new java.awt.Color(0, 153, 153));
        jButton6.setFont(new java.awt.Font("Bodoni MT", 0, 18)); // NOI18N
        jButton6.setText("Articles");
        jButton6.setToolTipText("");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 153, 51));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setText("Imprimer");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(idQuantite, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Jtext8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jLabel13))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jpanelimpro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idDesignationArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idCodeFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(59, 59, 59)
                                            .addComponent(idClientFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(67, 67, 67)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(68, 68, 68)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(idPrixFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(46, 46, 46)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(idTvaFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(103, 103, 103)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(jButton6)
                        .addGap(123, 123, 123)
                        .addComponent(jButton5)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idClientFacture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idCodeFacture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idDesignationArticle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idPrixFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idTvaFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Jtext8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idQuantite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(45, 45, 45)
                .addComponent(jpanelimpro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton5)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        dispose();
        Accueil accueilfenetre = new Accueil();
        accueilfenetre.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        remplissagArticle();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void idClientFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idClientFactureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idClientFactureActionPerformed

    private void idDesignationArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idDesignationArticleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idDesignationArticleActionPerformed

    private void idQuantiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idQuantiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idQuantiteActionPerformed

    private void idQuantiteAncestorMoved(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_idQuantiteAncestorMoved
        
    }//GEN-LAST:event_idQuantiteAncestorMoved

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        dispose();
        Client clientFenetre = new Client();
        clientFenetre.setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void idCodeFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idCodeFactureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idCodeFactureActionPerformed

    private void tableauArticleFactureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableauArticleFactureMouseClicked
        DefaultTableModel dm = (DefaultTableModel) tableauArticleFacture.getModel();
        int selectIndex = tableauArticleFacture.getSelectedRow();
        
        idDesignationArticle.setText(dm.getValueAt(selectIndex,0).toString());
        idPrixFacture.setText(dm.getValueAt(selectIndex,1).toString());
        idQuantite.setText(dm.getValueAt(selectIndex, 2).toString());
        
       
       try {
            String selctrqt = "SELECT * FROM facturetemp where Designation=?";
                PreparedStatement stmslc = connection.connectbd().prepareStatement(selctrqt);
                stmslc.setString(1,idDesignationArticle.getText());
                
                ResultSet resultslc = stmslc.executeQuery();
                while(resultslc.next()){
                    String qte = resultslc.getString("Quantite");
                }
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_tableauArticleFactureMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.dispose();
        Article articlefenetre = new Article();
        articlefenetre.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        Toolkit kit = Toolkit.getDefaultToolkit();
        PrintJob print = kit.getPrintJob(this, "Impression", null);
        Graphics gr = print.getGraphics();
        jpanelimpro.printAll(gr);
        print.end();
        
        this.dispose();
        Article articlefenetre = new Article();
        articlefenetre.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Facture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Facture().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jtext8;
    private javax.swing.JTextField idClientFacture;
    private javax.swing.JTextField idCodeFacture;
    private javax.swing.JTextField idDesignationArticle;
    private javax.swing.JLabel idPrixFacture;
    private javax.swing.JTextField idQuantite;
    private javax.swing.JLabel idTvaFacture;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpanelimpro;
    private javax.swing.JTable tableauApayerFacture;
    private javax.swing.JTable tableauArticleFacture;
    // End of variables declaration//GEN-END:variables
}
