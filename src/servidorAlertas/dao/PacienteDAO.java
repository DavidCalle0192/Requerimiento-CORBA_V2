/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorAlertas.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorAlertas.dto.PacienteDTO;

/**
 *
 * @author JhonMZ
 */
public class PacienteDAO {
    
    private Conexion objConex;

    public PacienteDAO() {
        this.objConex = new Conexion();
    }
    
    public boolean registrarPaciente(PacienteDTO objPaciente){
        try {
            objConex.conectar();
            PreparedStatement sentencia = null;
            String insert = "INSERT INTO `paciente` (`idPaciente`, `tipo_id`, `nombre`, `apellido`, `direccion`) VALUES (?, ?, ?, ?, ?)";
            System.out.print(objConex);
            sentencia = objConex.getConnection().prepareStatement(insert);
            sentencia.setInt(1, objPaciente.id);
            sentencia.setString(2, objPaciente.tipo_id);
            sentencia.setString(3, objPaciente.nombres);
            sentencia.setString(4, objPaciente.apellidos);
            sentencia.setString(5, objPaciente.direccion);;
            
            boolean res = sentencia.executeUpdate()>0;
            sentencia.close();
            objConex.desconectar();
            
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean actualizarPaciente(PacienteDTO objPaciente){
        try {
            objConex.conectar();
            PreparedStatement sentencia = null;
            String update = "UPDATE paciente SET paciente.tipo_id=?,"
                                                +"paciente.nombre=?,"
                                                +"paciente.apellido=?,"
                                                +"paciente.direccion=?"
                                                +"WHERE paciente.idPaciente = ?;";
            System.out.print(objConex);
            sentencia = objConex.getConnection().prepareStatement(update);
            sentencia.setString(1, objPaciente.tipo_id);
            sentencia.setString(2, objPaciente.nombres);
            sentencia.setString(3, objPaciente.apellidos);
            sentencia.setString(4, objPaciente.direccion);;
            sentencia.setInt(5, objPaciente.id);
            
            boolean res = sentencia.executeUpdate()>0;
            sentencia.close();
            objConex.desconectar();
            
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public PacienteDTO consultarPacienteId(int idPaciente){
        //PacienteDTO objPaciente = null;
        PacienteDTO objPaciente = new PacienteDTO();
        
        try {
            objConex.conectar();
            
            PreparedStatement sentencia = null;
            String consulta = "SELECT * FROM `paciente` WHERE idPaciente = ?";
            sentencia = objConex.getConnection().prepareStatement(consulta);
            sentencia.setInt(1, idPaciente);
            ResultSet res = sentencia.executeQuery();
            while(res.next()){
                //objPaciente = new PacienteDTO();
                objPaciente.id = idPaciente;
                objPaciente.tipo_id = res.getString("tipo_id");
                objPaciente.nombres = res.getString("nombre");
                objPaciente.apellidos = res.getString("apellido");
                objPaciente.direccion = res.getString("direccion");
            }
            sentencia.close();
            objConex.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objPaciente;
    }
}
