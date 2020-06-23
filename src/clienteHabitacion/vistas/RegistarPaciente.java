/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteHabitacion.vistas;

//import clienteHabitacion.ClienteMedico;
//import clienteHabitacion.sop_rmi.HabitacionInt;
import clienteHabitacion.ClienteDeObjetos;
import clienteHabitacion.sop_corba.Paciente;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.omg.CORBA.BooleanHolder;
import org.omg.CORBA.StringHolder;
import servidorAlertas.dto.PacienteDTO;
import servidorAlertas.dto.PacienteDTOHolder;
import servidorAlertas.sop_corba.GestionPacientes;
import servidorAlertas.sop_corba.GestionPacientesHelper;
//import servidorAlertas.dto.UsuarioDTO;
//import servidorAlertas.sop_rmi.GestionPacienteInt;

/**
 *
 * @author usuario
 */
public class RegistarPaciente extends javax.swing.JFrame {

    public static ClienteDeObjetos co;
    String etiqueta;

    /**
     * Creates new form prueba
     */
    public RegistarPaciente(ClienteDeObjetos co) {
        this.co = co;
        //Sthis.cm = cm;
        // this.objHabitacion = objHabitacion;
        //this.guiMenuMedico = guiMenuMedico;
        initComponents();
        setLocationRelativeTo(null);
        rbtn_cc.setSelected(true);
        etiqueta = btn_crear.getText();
    }

    public void cambiarEtiqueta() {
        btn_crear.setText("Actualizar");
    }

    public void infoActualizar(PacienteDTO pacienteDTO) {
        txf_id.setText("" + pacienteDTO.id);
        txf_id.setEditable(false);
        txf_nombres.setText(pacienteDTO.nombres);
        txf_apellidos.setText(pacienteDTO.apellidos);
        txf_direccion.setText(pacienteDTO.direccion);

        String tipo = pacienteDTO.tipo_id;
        if (tipo.equals("CC")) {
            rbtn_cc.setSelected(true);
        } else if (tipo.equals("TI")) {
            rbtn_ti.setSelected(true);
        } else {
            rbtn_pp.setSelected(true);
        }
    }

    public boolean verificar(String nombres, String apellidos, String direccion) {
        //String id = txf_id.getText();//Validar tipo de dato
        if (nombres.equals("") || apellidos.equals("") || direccion.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public RegistarPaciente() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btng_tiposDeId = new javax.swing.ButtonGroup();
        btn_crear = new javax.swing.JButton();
        lb_id = new javax.swing.JLabel();
        lb_tipoId = new javax.swing.JLabel();
        lb_nombres = new javax.swing.JLabel();
        lb_apellidos = new javax.swing.JLabel();
        lb_direccion = new javax.swing.JLabel();
        txf_id = new javax.swing.JTextField();
        txf_nombres = new javax.swing.JTextField();
        txf_apellidos = new javax.swing.JTextField();
        txf_direccion = new javax.swing.JTextField();
        bnt_volver = new javax.swing.JButton();
        rbtn_cc = new javax.swing.JRadioButton();
        rbtn_ti = new javax.swing.JRadioButton();
        rbtn_pp = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_crear.setText("Crear");
        btn_crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_crearActionPerformed(evt);
            }
        });

        lb_id.setText("ID");

        lb_tipoId.setText("Tipo ID");

        lb_nombres.setText("Nombres");

        lb_apellidos.setText("Apellidos");

        lb_direccion.setText("Dirección");

        bnt_volver.setText("Volver al menú");
        bnt_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_volverActionPerformed(evt);
            }
        });

        btng_tiposDeId.add(rbtn_cc);
        rbtn_cc.setText("CC");

        btng_tiposDeId.add(rbtn_ti);
        rbtn_ti.setText("TI");

        btng_tiposDeId.add(rbtn_pp);
        rbtn_pp.setText("PP");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_crear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bnt_volver))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_tipoId)
                            .addComponent(lb_id)
                            .addComponent(lb_direccion)
                            .addComponent(lb_nombres)
                            .addComponent(lb_apellidos))
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txf_id, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txf_apellidos)
                            .addComponent(txf_nombres)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbtn_cc)
                                .addGap(18, 18, 18)
                                .addComponent(rbtn_ti)
                                .addGap(18, 18, 18)
                                .addComponent(rbtn_pp))
                            .addComponent(txf_direccion))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txf_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_id))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_tipoId)
                    .addComponent(rbtn_cc)
                    .addComponent(rbtn_ti)
                    .addComponent(rbtn_pp))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txf_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_nombres))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txf_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_apellidos))
                        .addGap(21, 21, 21)
                        .addComponent(txf_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_direccion, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnt_volver)
                    .addComponent(btn_crear))
                .addGap(111, 111, 111))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public boolean validarID() {
        if (txf_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo ID se encuentra vacio");
            return false;
        } else if (!esNumerico(txf_id.getText())) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor númerico en el campo ID");
            txf_id.setText("");
            return false;
        } else if (Integer.parseInt(txf_id.getText()) < 0) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor númerico positivo en el campo ID");
            txf_id.setText("");
            return false;
        } else {
            return true;
        }
    }

    public boolean esNumerico(String cadena) {

        try {

            if (cadena.matches("\\d*")) {
                return true;
            }
        } catch (NumberFormatException e) {

        }
        return false;
    }

    private void btn_crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_crearActionPerformed

        PacienteDTO paciente = null;
        String tipoId = " ";
        if (rbtn_cc.isSelected()) {
            tipoId = "CC";
        }
        if (rbtn_ti.isSelected()) {
            tipoId = "TI";
        }
        if (rbtn_pp.isSelected()) {
            tipoId = "PP";
        }
        String nombres = txf_nombres.getText();
        String apellidos = txf_apellidos.getText();
        String direccion = txf_direccion.getText();
        if (!verificar(nombres, apellidos, direccion)) {
            JOptionPane.showMessageDialog(null, "Por favor diligenciar todos los campos");

        } else {//si estan llenos los campo
            if (btn_crear.getText().equals("Crear")) {
                int id;
                if (validarID()) {
                    id = Integer.parseInt(txf_id.getText());
                    paciente = new PacienteDTO(nombres, apellidos, tipoId, id, direccion);
                    boolean rta = co.obtenerObjGestionPaciente().registrarPaciente(//Se crea/registra paciente
                            paciente,
                            co.obtenerServant(),
                            co.obtenerStringHolder()
                    );
                    if (rta) {
                        JOptionPane.showMessageDialog(null, "Paciente creado con exito");
                        MenuMedico vista = new MenuMedico(co);
                        vista.pasarUsuario(paciente);
                        vista.darVisibilidad();
                        vista.cargarInfoUusuario();
                        vista.setVisible(true);
                        this.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al crear el paciente");
                    }
                }
            } else {//se va a actualizar
                int id = Integer.parseInt(txf_id.getText());
                paciente = new PacienteDTO(nombres, apellidos, tipoId, id, direccion);
                boolean res = co.obtenerObjGestionPaciente().actualizarPaciente(paciente);
                if (res) {
                    JOptionPane.showMessageDialog(null, "Paciente acualizado con éxito");
                    MenuMedico vista = new MenuMedico(co);
                    vista.pasarUsuario(paciente);
                    vista.darVisibilidad();
                    vista.cargarInfoUusuario();
                    vista.setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error en la actualización");
                }
            }
        }

    }//GEN-LAST:event_btn_crearActionPerformed

    private void bnt_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_volverActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        MenuMedico menu;
        menu = new MenuMedico(co);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_bnt_volverActionPerformed

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
            java.util.logging.Logger.getLogger(RegistarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistarPaciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnt_volver;
    private javax.swing.JButton btn_crear;
    private javax.swing.ButtonGroup btng_tiposDeId;
    private javax.swing.JLabel lb_apellidos;
    private javax.swing.JLabel lb_direccion;
    private javax.swing.JLabel lb_id;
    private javax.swing.JLabel lb_nombres;
    private javax.swing.JLabel lb_tipoId;
    private javax.swing.JRadioButton rbtn_cc;
    private javax.swing.JRadioButton rbtn_pp;
    private javax.swing.JRadioButton rbtn_ti;
    private javax.swing.JTextField txf_apellidos;
    private javax.swing.JTextField txf_direccion;
    private javax.swing.JTextField txf_id;
    private javax.swing.JTextField txf_nombres;
    // End of variables declaration//GEN-END:variables
}
