package org.srfg;

import org.eclipse.basyx.vab.directory.proxy.VABDirectoryProxy;
import org.eclipse.basyx.vab.manager.VABConnectionManager;
import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;
import org.eclipse.basyx.vab.protocol.http.connector.HTTPConnectorProvider;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/********************************************************************************************************
 * This class contains the main function implementation and
 * serves as the basyx belt client starting point
 *
 * @author mathias.schmoigl
 ********************************************************************************************************/
public class MainOtherDevice extends javax.swing.JFrame{

    // needed for model thread
    private Thread runner;
    private IModelProvider model;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox;
    private javax.swing.JCheckBox jCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToggleButton jToggleButton;
    // End of variables declaration//GEN-END:variables


    /*********************************************************************************************************
     * Creates new form CustomApp
     ********************************************************************************************************/
    public MainOtherDevice() {
        initComponents();
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

        jToggleButton = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox = new javax.swing.JComboBox<>();
        jCheckBox = new javax.swing.JCheckBox();

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToggleButton.setText("Verbinde zu Device");
        jToggleButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButton1ItemStateChanged(evt);
            }
        });

        jLabel1.setText("Distanz");
        jLabel2.setText("Lampe ein/ausschalten");

        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Geringe Distanz", "Mittlere Distanz", "Weite Distanz", "Sehr weite Distanz" }));
        jComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addComponent(jLabel1)
                                                .addGap(39, 39, 39)
                                                .addComponent(jComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addComponent(jLabel2)
                                                .addGap(39, 39, 39)
                                                .addComponent(jCheckBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(jToggleButton)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jToggleButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    /*********************************************************************************************************
     * jComboBox1ActionPerformed
     ********************************************************************************************************/
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here
    }

    /*********************************************************************************************************
     * jToggleButton1ItemStateChanged
     ********************************************************************************************************/
    private void jToggleButton1ItemStateChanged(java.awt.event.ItemEvent evt) {
        switch (evt.getStateChange()) {
            case 1:
                jToggleButton.setText("Trenne von Device");

                // start basyx communication
                model = connectToDevice();
                runner = doIt(model);
                runner.start();
                break;
            case 2:
                jToggleButton.setText("Verbinde mit Device");
                if (runner != null && runner.isAlive()) {
                    model = null;
                    runner.interrupt();
                }
                break;
        }
        // TODO add your handling code here
    }

    /*********************************************************************************************************
     * jCheckBox1ActionPerformed
     ********************************************************************************************************/
    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {

        AbstractButton abstractButton = (AbstractButton) evt.getSource();
        model.invokeOperation("operations/setLight", abstractButton.getModel().isSelected());
    }


    /*********************************************************************************************************
     * jComboBox1ItemStateChanged
     ********************************************************************************************************/
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
        switch(evt.getItem().toString()) {
            case "Geringe Distanz":
                if ( model != null ) {
                    model.invokeOperation("operations/setSpeed", 0.1);
                }
                break;
            case "Mittlere Distanz":
                if ( model != null ) {
                    model.invokeOperation("operations/setSpeed", 1.0);
                }
                break;
            case "Weite Distanz":
                if ( model != null ) {
                    model.invokeOperation("operations/setSpeed", 1.5);
                }
                break;
            case "Sehr weite Distanz":
                if ( model != null ) {
                    model.invokeOperation("operations/setSpeed", 5.5);
                }
                break;
        }
    }

    /*********************************************************************************************************
     * connectToDevice
     ********************************************************************************************************/
    private IModelProvider connectToDevice() {

        // At the connected site, no direct access to the model is possible. Every access is done through the network infrastructure
        // The Virtual Automation Bus hides network details to the connected site. Only the endpoint of the directory has to be known:
        VABDirectoryProxy directoryProxy = new VABDirectoryProxy("http://localhost:5000/iasset/directory/");

        // The connection manager is responsible for resolving every connection attempt. It needs:
        // - The directory at which all models are registered
        // - A provider for different types of network protocols (in this example, only HTTP-REST)
        VABConnectionManager connectionManager = new VABConnectionManager(directoryProxy, new HTTPConnectorProvider());

        // It is now one line of code to retrieve a model provider for any registered model in the network
        IModelProvider connectedBelt = connectionManager.connectToVABElement("belt01");
        return connectedBelt;
    }

    /*********************************************************************************************************
     * doIt
     ********************************************************************************************************/
    private Thread doIt(final IModelProvider model) {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(500);

                        // Retrieve the current temperature from the model provider
                        jTextArea1.setText( "Belt State: " + model.getModelPropertyValue("/properties/beltstate") + "\n" +
                                            "Belt Distance: " + model.getModelPropertyValue("/properties/beltdist") + "\n" +
                                            "Belt Moving: " + model.getModelPropertyValue("/properties/beltmoving") );
                    }
                } catch (InterruptedException ex) {
                    // stop the thread now
                    Logger.getLogger(MainOtherDevice.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        return new Thread(runnable);
    }

    /*********************************************************************************************************
     * main program entrance
     ********************************************************************************************************/
    public static void main(String[] args)
    {
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
            java.util.logging.Logger.getLogger(MainOtherDevice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainOtherDevice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainOtherDevice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainOtherDevice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainOtherDevice().setVisible(true);
            }
        });
    }
}