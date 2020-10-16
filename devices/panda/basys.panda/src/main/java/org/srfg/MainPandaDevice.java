/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.srfg;

import org.eclipse.basyx.vab.protocol.http.server.AASHTTPServer;
import org.srfg.panda.PandaDevice;
import org.srfg.panda.PandaListener;

/**
 *
 * @author dglachs, mschmoig
 */
public class MainPandaDevice extends javax.swing.JFrame {

    private PandaDevice panda;
    private AASHTTPServer server = null;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButtonRegistration;
    private javax.swing.JToggleButton jToggleButtonComponent;
    private javax.swing.JToggleButton jToggleButtonPanda;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTree jTree1;

    private javax.swing.JLabel jLabelRobotMode;
    private javax.swing.JLabel jLabelPosX;
    private javax.swing.JLabel jLabelPosY;
    private javax.swing.JLabel jLabelPosZ;
    private javax.swing.JLabel jLabelForceZ;
    private javax.swing.JLabel jLabelGripperDistance;

    private javax.swing.JTextField jTextFieldRobotMode;
    private javax.swing.JTextField jTextFieldPositionX;
    private javax.swing.JTextField jTextFieldPositionY;
    private javax.swing.JTextField jTextFieldPositionZ;
    private javax.swing.JTextField jTextFieldForceZ;
    private javax.swing.JTextField jTextFieldGripperDistance;
    // End of variables declaration//GEN-END:variables

    /*********************************************************************************************************
     * PandaDisplay: Creates new form NewJFrame
     ********************************************************************************************************/
    public MainPandaDevice() {
        initComponents();
        panda = new PandaDevice();
        PandaListener listener = new PandaListener() {

            @Override
            public void robotModeChanged() {
                jTextFieldRobotMode.setText("" + panda.getRobotMode());
            }

            @Override
            public void posXChanged() {
                jTextFieldPositionX.setText("" + panda.getPositionX());
            }

            @Override
            public void posYChanged() {
                jTextFieldPositionY.setText("" + panda.getPositionY());
            }

            @Override
            public void posZChanged() {
                jTextFieldPositionZ.setText("" + panda.getPositionZ());
            }

            @Override
            public void forceZChanged() {
                jTextFieldForceZ.setText("" + panda.getForceZ());
            }

            @Override
            public void gripperDistanceChanged() {
                jTextFieldGripperDistance.setText("" + panda.getGripperDistance());
            }

        };
        panda.setListener(listener);
    }


    /*********************************************************************************************************
     * initComponents
     *
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     ********************************************************************************************************/
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jToolBar1 = new javax.swing.JToolBar();
        jToggleButtonRegistration = new javax.swing.JToggleButton();
        jToggleButtonPanda = new javax.swing.JToggleButton();
        jToggleButtonComponent = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();

        jLabelRobotMode = new javax.swing.JLabel();
        jLabelPosX = new javax.swing.JLabel();
        jLabelPosY = new javax.swing.JLabel();
        jLabelPosZ = new javax.swing.JLabel();
        jLabelForceZ = new javax.swing.JLabel();
        jLabelGripperDistance = new javax.swing.JLabel();

        jTextFieldRobotMode = new javax.swing.JTextField();
        jTextFieldPositionX = new javax.swing.JTextField();
        jTextFieldPositionY = new javax.swing.JTextField();
        jTextFieldPositionZ = new javax.swing.JTextField();
        jTextFieldForceZ = new javax.swing.JTextField();
        jTextFieldGripperDistance = new javax.swing.JTextField();

        jScrollPane1.setViewportView(jTree1);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jToolBar1.setRollover(true);

        jToggleButtonComponent.setText("I4.0 Component");
        jToggleButtonComponent.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButton1ItemStateChanged(evt);
            }
        });
        jToolBar1.add(jToggleButtonComponent);

        jToggleButtonRegistration.setText("Register Panda");
        jToggleButtonRegistration.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButton0ItemStateChanged(evt);
            }
        });
        jToolBar1.add(jToggleButtonRegistration);

        jToggleButtonPanda.setText("Start Panda");
        jToggleButtonPanda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButton2ItemStateChanged(evt);
            }
        });
        jToggleButtonPanda.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jToggleButton2StateChanged(evt);
            }
        });
        jToggleButtonPanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButtonPanda);

        // assign text to all labels
        jLabelRobotMode.setText("Robot Mode");
        jLabelPosX.setText("Position X");
        jLabelPosY.setText("Position Y");
        jLabelPosZ.setText("Position Z");
        jLabelForceZ.setText("Force Z");
        jLabelGripperDistance.setText("Gripper Distance");

        jTextFieldRobotMode.setText("robotModeField");
        jTextFieldRobotMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRobotModeActionPerformed(evt);
            }
        });

        jTextFieldPositionX.setText("posXField");
        jTextFieldPositionX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPosXActionPerformed(evt);
            }
        });

        jTextFieldPositionY.setText("posYField");
        jTextFieldPositionY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPosYActionPerformed(evt);
            }
        });

        jTextFieldPositionZ.setText("posZField");
        jTextFieldPositionZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPosZActionPerformed(evt);
            }
        });

        jTextFieldForceZ.setText("forceZField");
        jTextFieldForceZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldForceZActionPerformed(evt);
            }
        });

        jTextFieldGripperDistance.setText("gripperDistanceField");
        jTextFieldGripperDistance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldGripperDistanceActionPerformed(evt);
            }
        });



        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelRobotMode)
                                        .addComponent(jLabelPosX)
                                        .addComponent(jLabelPosY)
                                        .addComponent(jLabelPosZ)
                                        .addComponent(jLabelForceZ)
                                        .addComponent(jLabelGripperDistance))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldRobotMode)
                                        .addComponent(jTextFieldPositionX)
                                        .addComponent(jTextFieldPositionY)
                                        .addComponent(jTextFieldPositionZ)
                                        .addComponent(jTextFieldForceZ)
                                        .addComponent(jTextFieldGripperDistance, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelRobotMode)
                                        .addComponent(jTextFieldRobotMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelPosX)
                                        .addComponent(jTextFieldPositionX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelPosY)
                                        .addComponent(jTextFieldPositionY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelPosZ)
                                        .addComponent(jTextFieldPositionZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelForceZ)
                                        .addComponent(jTextFieldForceZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelGripperDistance)
                                        .addComponent(jTextFieldGripperDistance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*********************************************************************************************************
     * ActionPerformed - Functions
     ********************************************************************************************************/
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt){}
    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt){}
    private void jTextFieldRobotModeActionPerformed(java.awt.event.ActionEvent evt){}
    private void jTextFieldPosXActionPerformed(java.awt.event.ActionEvent evt){}
    private void jTextFieldPosYActionPerformed(java.awt.event.ActionEvent evt){}
    private void jTextFieldPosZActionPerformed(java.awt.event.ActionEvent evt){}
    private void jTextFieldForceZActionPerformed(java.awt.event.ActionEvent evt){}
    private void jTextFieldGripperDistanceActionPerformed(java.awt.event.ActionEvent evt){}

    /*********************************************************************************************************
     * jToggleButton0ItemStateChanged
     ********************************************************************************************************/
    private void jToggleButton0ItemStateChanged(java.awt.event.ItemEvent evt) {

        switch (evt.getStateChange()) {
            case 1:
                panda.register();
                break;
            case 2:
                if (server != null) {
                    server.shutdown();
                    server = null;
                }
                break;
        }
    }

    /*********************************************************************************************************
     * jToggleButton1ItemStateChanged
     ********************************************************************************************************/
    private void jToggleButton1ItemStateChanged(java.awt.event.ItemEvent evt) {

        switch (evt.getStateChange()) {
            case 1:
                panda.hostComponent(server);
                break;
            case 2:
                if (server != null) {
                    server.shutdown();
                    server = null;
                }
                break;
        }
    }

    /*********************************************************************************************************
     * Button2 Operations
     ********************************************************************************************************/
    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {}
    private void jToggleButton2StateChanged(javax.swing.event.ChangeEvent evt) {}
    private void jToggleButton2ItemStateChanged(java.awt.event.ItemEvent evt) {

        switch (evt.getStateChange()) {
            case 1:
                jToggleButtonPanda.setText("Stop Panda");
                panda.start();
                break;
            case 2:
                jToggleButtonPanda.setText("Start Panda");
                panda.stop();
                break;
        }
    }

    /*********************************************************************************************************
     * Main function
     *
     * @param args the command line arguments
     ********************************************************************************************************/
    public static void main(String args[]) {

        if(args.length == 1 && (args[0] == "noGUI")) // launch application without a GUI
        {
            PandaDevice panda = new PandaDevice();
            AASHTTPServer server = null;

            System.out.println("Started application without GUI!\n Trying to host I4.0 Component:");
            panda.hostComponent(server);

            System.out.println("\n Trying to register Panda:");
            panda.register();

            System.out.println("\n Trying to start Panda communication:");
            panda.start();
        }
        else // launch application with a GUI
        {
            /* Set Nimbus look and feel (http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html) */
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(MainPandaDevice.class
                        .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(MainPandaDevice.class
                        .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(MainPandaDevice.class
                        .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(MainPandaDevice.class
                        .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new MainPandaDevice().setVisible(true);
                }
            });
        }
    }
}
