/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorAlertas.dao;

import java.sql.*;

/**
 *
 * @author JhonMZ
 */
public class Conexion {
    
   private Connection con;
    
   private final String BD_NAME = "bdpacientes";
   private final String LOGIN = "root";
   private final String PASSWORD = "";
   private final String URL = "jdbc:mysql://localhost/"+BD_NAME;

    public Conexion() {
        
    }
    
   
    /**Permite hacer la conexion con la base de datos    
     */
   public boolean conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //crea una instancia de la controlador de la base de datos
            con = DriverManager.getConnection(URL,LOGIN,PASSWORD);
            // gnera una conexión con la base de datos
            return true;
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    /**Cierra la conexion con la base de datos
     */
   public void desconectar(){
       try{
            con.close();
        }
        catch(Exception e){
            System.out.println("Error " + e.getMessage());
        }
   }
     /**Retorna un objeto que almacena la referencia a la conexion con la base de datos
     *
     */
   public Connection getConnection(){
      return con;
   }
}
