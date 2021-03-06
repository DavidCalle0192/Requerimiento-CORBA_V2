/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteHabitacion.vistas;

import clienteHabitacion.ClienteDeObjetos;
//import cliente.sop_corba.ClsHabitacion;
//import cliente.sop_corba.HabitacionInt;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import servidorAlertas.dto.IndicadoresDTO;
import servidorAlertas.dto.PacienteDTO;
import servidorAlertas.dto.PacienteDTOHolder;
import servidorAlertas.sop_corba.GestionPacientes;
//import servidorAlertas.dto.IndicadorDTO;
//import servidorAlertas.dto.UsuarioDTO;
//import servidorAlertas.sop_rmi.GestionPacienteInt;

/**
 *
 * @author usuario
 */
public class MenuMedico extends javax.swing.JFrame {

    //public static GestionPacientes ref;
    public static ClienteDeObjetos co;
    //public static ClsHabitacion objHabitacion;
    public static int bandera = 0;
    public static String texto;
    public static int aux = 0;//controla la existencia de pacientes registrados 
    public static String enviarIndicadores;
    public static String alerta;
    public static int contador;
    int rol;
    int identificacion;
    
    PacienteDTO objusuario;

    /**
     * Creates new form MenuMedico
     */
    public MenuMedico(ClienteDeObjetos co) {
        this.co = co;
        //definirRol();
        initComponents();
        setLocationRelativeTo(null);
        btn_iniciarSeguimiento.setEnabled(false);
        btn_paraSeguimiento.setEnabled(false);
        btn_paraSeguimiento.setVisible(false);
        btn_actualizar.setEnabled(false);

    }

    public void habilitarActualizar(){
        btn_actualizar.setEnabled(true);
    }
    public void pasarUsuario(PacienteDTO objpacientedto) {
        objusuario = objpacientedto;
    }

    public void definirRol() {
        if (co.obtenerObjGestionPaciente().getNumRegistros() == 0) {
            //lb_menuMedico.setText("Menú administrador");
        }
    }

    public MenuMedico() {
        initComponents();
    }

    public void cargarInfoUusuario() {
        identificacion=objusuario.id;
        lb_nombre_apellido.setText("Nombre: " + objusuario.nombres + " " + objusuario.apellidos);
        lb_tipo_id.setText("Tipo ID: " + objusuario.tipo_id);
        lb_direccion.setText("Dirección: " + objusuario.direccion);
    }

    public void darVisibilidad() {
        btn_registrarPaciente.setEnabled(false);
        btn_registrarPaciente.setVisible(false);
        btn_iniciarSeguimiento.setEnabled(true);

    }

    public void hilo() {
        enviarIndicadores = "";
        alerta ="";
        contador = 0;
        texto = "";
        bandera = 0;
        txtArea_seguimiento.setText("");
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
        //GestionPacientes ref;
            @Override
            public void run() {

                if (bandera == 1) {
                    timer.cancel();
                } else {
                    
                    IndicadoresDTO indicador = co.iniciarSeguimiento(objusuario.id);
                    boolean respuesta = co.obtenerObjGestionPaciente().enviarIndicadores(objusuario.id, indicador);
                    if(respuesta){
                        Date objDate = new Date();
                        String strDateFormat = "hh: mm: ss a dd-MMM-aaaa"; // El formato de fecha está especificado  
                        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // La cadena de formato de fecha se pasa como un argumento al objeto
                        enviarIndicadores = "Paciente con indicadores fuera de lo normal.";
                        contador++;
                        alerta=alerta+"Se ha presentado "+contador+" Alertas: "+objSDF.format(objDate)+"\n";
                    }else{
                        enviarIndicadores ="Paciente con indicadores estables.";
                    }
                    //ref.enviarIndicadores(objusuario.id, indicador);
                    texto = texto
                            + "ID paciente: " + objusuario.id
                            + "\nFrecuencia cardiaca: " + indicador.getFrecuenciaCardiaca()
                            + "\nFrecuencia respiratoria: " + indicador.getFrecuenciaRespiratoria()
                            + "\nTemperatura: " + indicador.getTemperatura()
                            //+ "\nRespuesta: " + enviarIndicadores
                            + "\n\n";
                }
                //JOptionPane.showMessageDialog(null, " "+co.obtenerServant());
                txtArea_seguimiento.setText(texto);
                txtAreaAlerta.setText(enviarIndicadores+"\n"+alerta+"\n\n");

            }

        };
        timer.schedule(task, 0, 8000);//se cambio la variacion del tiempo para pruebas

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
        btn_registrarPaciente = new javax.swing.JButton();
        btn_iniciarSeguimiento = new javax.swing.JButton();
        lb_menuMedico = new javax.swing.JLabel();
        btn_paraSeguimiento = new javax.swing.JButton();
        lb_nombre_apellido = new javax.swing.JLabel();
        lb_tipo_id = new javax.swing.JLabel();
        lb_direccion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea_seguimiento = new javax.swing.JTextArea();
        btn_limpiar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        btn_buscar = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaAlerta = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        txf_buscarOactualizar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_registrarPaciente.setText("Registrar paciente");
        btn_registrarPaciente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_registrarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarPacienteActionPerformed(evt);
            }
        });

        btn_iniciarSeguimiento.setText("Iniciar seguimiento");
        btn_iniciarSeguimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_iniciarSeguimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_iniciarSeguimientoActionPerformed(evt);
            }
        });

        lb_menuMedico.setText("Menú Médico");

        btn_paraSeguimiento.setText("Parar Seguimiento");
        btn_paraSeguimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_paraSeguimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_paraSeguimientoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_menuMedico)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btn_paraSeguimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_iniciarSeguimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                        .addComponent(btn_registrarPaciente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_nombre_apellido, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(lb_tipo_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_direccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lb_menuMedico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_registrarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_iniciarSeguimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lb_nombre_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_tipo_id, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_paraSeguimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtArea_seguimiento.setColumns(20);
        txtArea_seguimiento.setRows(5);
        txtArea_seguimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(txtArea_seguimiento);

        btn_limpiar.setText("Limpiar");
        btn_limpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        btn_salir.setText("Salir");
        btn_salir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        btn_buscar.setText("Buscar");
        btn_buscar.setToolTipText("Buscar paciente en la base de datos");
        btn_buscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        btn_actualizar.setText("Actualizar");
        btn_actualizar.setToolTipText("Solo se puede actualizar el registro actual");
        btn_actualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizarActionPerformed(evt);
            }
        });

        txtAreaAlerta.setColumns(20);
        txtAreaAlerta.setRows(5);
        txtAreaAlerta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(txtAreaAlerta);

        jLabel2.setText("Alertas");

        txf_buscarOactualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btn_buscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_actualizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                .addComponent(btn_salir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_limpiar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                .addComponent(txf_buscarOactualizar, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel1))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(5, 5, 5)
                        .addComponent(txf_buscarOactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        // TODO add your handling code here:
        //this.dispose();
        System.exit(0);
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_registrarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarPacienteActionPerformed

        aux = 1;
        
        if (co.obtenerObjGestionPaciente().getNumRegistros() == co.obtenerObjGestionPaciente().getMaxPacientes()) {
            JOptionPane.showMessageDialog(null, "Se ha llegado al maximo de registros");
        } else {

            RegistarPaciente rp = new RegistarPaciente(co);
            rp.setVisible(true);
            this.setVisible(false);
        }

    }//GEN-LAST:event_btn_registrarPacienteActionPerformed

    private void btn_paraSeguimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_paraSeguimientoActionPerformed
        // TODO add your handling code here:
        bandera = 1;
        btn_paraSeguimiento.setEnabled(false);
        btn_paraSeguimiento.setVisible(false);
    }//GEN-LAST:event_btn_paraSeguimientoActionPerformed

    private void btn_iniciarSeguimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_iniciarSeguimientoActionPerformed
        // TODO add your handling code here:

        if (aux == 0) {
            JOptionPane.showMessageDialog(null, "No existen pacientes registrados.");
        } else {
            btn_paraSeguimiento.setEnabled(true);
            btn_paraSeguimiento.setVisible(true);
            hilo();
            
        }

    }//GEN-LAST:event_btn_iniciarSeguimientoActionPerformed
    /**/
    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        // TODO add your handling code here:
        bandera = 1;
        txtArea_seguimiento.setText("");

    }//GEN-LAST:event_btn_limpiarActionPerformed

    public boolean esNumerico(String cadena) {

        try {

            if (cadena.matches("\\d*")) {
                return true;
            }
        } catch (NumberFormatException e) {

        }
        return false;
    }

    public boolean validar() {
        if (txf_buscarOactualizar.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo ID se encuentra vacio");
            return false;
        } else if (!esNumerico(txf_buscarOactualizar.getText())) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor númerico");
            txf_buscarOactualizar.setText("");
            return false;
        } else {
            return true;
        }
    }
    
    
    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        // TODO add your handling code here:
        if (validar()) {
            int id = Integer.parseInt(txf_buscarOactualizar.getText());
            txtArea_seguimiento.setText("");
            objusuario = co.obtenerObjGestionPaciente().buscarPaciente(id);

            String mostrar="";

            if (objusuario.nombres.equals("")) {
             JOptionPane.showMessageDialog(null, "El ID: "+txf_buscarOactualizar.getText()+" No se encuetra registrado.");
             txf_buscarOactualizar.setText("");
            } else {
                
                mostrar
                        = "ID: " + objusuario.id
                        + "\nNombres: " + objusuario.nombres
                        + "\nApellidos: " + objusuario.apellidos
                        + "\nDirección: " + objusuario.direccion;
            }
            txtArea_seguimiento.setText(mostrar);
        }
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void btn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarActionPerformed
        // TODO add your handling code here:
       // if (validar()) {
            //int id = Integer.parseInt(txf_buscarOactualizar.getText());
            int id = identificacion;
            RegistarPaciente vista = new RegistarPaciente(co);
            objusuario = co.obtenerObjGestionPaciente().buscarPaciente(id);
            if (objusuario.nombres.equals("")) {
                JOptionPane.showMessageDialog(null, "El ID: "+txf_buscarOactualizar.getText()+" No se encuetra registrado.");
                txf_buscarOactualizar.setText("");
            } else {
                vista.infoActualizar(objusuario);
                
                vista.cambiarEtiqueta();
                vista.setVisible(true);
                this.setVisible(false);
            }

        //}
    }//GEN-LAST:event_btn_actualizarActionPerformed

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
            java.util.logging.Logger.getLogger(MenuMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuMedico().setVisible(true);

            }
        });
    }

    public void mostraMensajeAlerta(String mensaje) {
        System.err.println(mensaje);
        //lblMensaje.setText(mensaje);
        JOptionPane.showMessageDialog(null, mensaje);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_iniciarSeguimiento;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_paraSeguimiento;
    private javax.swing.JButton btn_registrarPaciente;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_direccion;
    private javax.swing.JLabel lb_menuMedico;
    private javax.swing.JLabel lb_nombre_apellido;
    private javax.swing.JLabel lb_tipo_id;
    private javax.swing.JTextField txf_buscarOactualizar;
    private javax.swing.JTextArea txtAreaAlerta;
    private javax.swing.JTextArea txtArea_seguimiento;
    // End of variables declaration//GEN-END:variables
}
