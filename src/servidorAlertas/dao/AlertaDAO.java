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
import servidorAlertas.dto.AlertaDTO;
import servidorAlertas.dto.PacienteDTO;

/**
 *
 * @author JhonMZ
 */
public class AlertaDAO {
    private Conexion objConex;

    public AlertaDAO() {
        this.objConex = new Conexion();
    }
    
    public boolean registrarAlerta(int idPaciente, AlertaDTO objAlerta){
        try {
            objConex.conectar();
            PreparedStatement sentencia = null;
            String insert = "INSERT INTO `alerta` (`idPaciente`, `frecuenciaCardiaca`, `frecuenciaRespiratoria`, `temperatura`, `puntuacion`, `fecha`) "
                    +"VALUES (?, ?, ?, ?, ?, ?);";
            System.out.print(objConex);
            sentencia = objConex.getConnection().prepareStatement(insert);
            sentencia.setInt(1, idPaciente);
            sentencia.setInt(2, objAlerta.indicadores.frecuenciaCardiaca);
            sentencia.setInt(3, objAlerta.indicadores.frecuenciaRespiratoria);
            sentencia.setFloat(4, objAlerta.indicadores.temperatura);
            sentencia.setInt(5, objAlerta.puntuacion);
            sentencia.setString(6,objAlerta.fecha+" "+objAlerta.hora);
            boolean res = sentencia.executeUpdate()>0;
            sentencia.close();
            objConex.desconectar();
            
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public AlertaDTO[] obtenerUltimas6Alertas(int idPaciente){
        AlertaDTO objAlertas[] = new AlertaDTO[6];
        try {
            objConex.conectar();
            PreparedStatement sentencia = null;
            String consulta = "SELECT * FROM alerta WHERE idPaciente = ? ORDER BY fecha DESC LIMIT 6";
            sentencia = objConex.getConnection().prepareStatement(consulta);
            sentencia.setInt(1, idPaciente);
            ResultSet res = sentencia.executeQuery();
            int cont = 0;
            while(res.next()){
                AlertaDTO objAlerta = new AlertaDTO();
                objAlerta.indicadores.frecuenciaCardiaca = res.getInt("frecuenciaCardiaca");
                objAlerta.indicadores.frecuenciaRespiratoria = res.getInt("frecuenciaRespiratoria");
                objAlerta.indicadores.temperatura = res.getFloat("temperatura");
                objAlerta.puntuacion = res.getInt("puntuacion");
                String fecha[] = res.getString("fecha").split(" ");
                objAlerta.fecha = fecha[0];
                objAlerta.hora = fecha[1];
                objAlertas[cont] = objAlerta;
                cont++;
            }
            sentencia.close();
            objConex.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objAlertas;
    }
}
