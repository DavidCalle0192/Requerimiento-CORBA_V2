/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cliente.sop_corba;

/**
 *
 * @author JhonMZ
 */
public class PacienteImpl extends PacientePOA{

    public PacienteImpl() {
    }
    
    @Override
    public void alertarPaciente(String mensaje) {
        System.out.println(mensaje);
    }
    
}
