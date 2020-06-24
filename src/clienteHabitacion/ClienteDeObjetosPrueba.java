/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clienteHabitacion;

import static clienteHabitacion.ClienteDeObjetos.ref;
import clienteHabitacion.sop_corba.Paciente;
import clienteHabitacion.sop_corba.PacienteHelper;
import clienteHabitacion.sop_corba.PacienteImpl;
import clienteHabitacion.utilidades.UtilidadesConsola;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.StringHolder;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import servidorAlertas.dto.IndicadoresDTO;
import servidorAlertas.dto.PacienteDTO;
import servidorAlertas.sop_corba.GestionPacientesHelper;

/**
 *
 * @author JhonMZ
 */
public class ClienteDeObjetosPrueba {
    public static void main(String args[]) {
        try {
            String[] vec = new String[4];
            vec[0] = "-ORBInitialPort";
            System.out.println("Ingrese la dirección IP donde escucha el n_s");
            vec[1] = UtilidadesConsola.leerCadena();
            vec[2] = "-ORBInitialPort";
            System.out.println("Ingrese el puerto donde escucha el n_s");
            vec[3] = UtilidadesConsola.leerCadena();

            // se crea e inicia el ORB
            ORB orb = ORB.init(vec, null);

            // se obtiene la referencia al name service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // *** Resuelve la referencia del objeto en el N_S ***
            String name = "objAlertas";
            ref = GestionPacientesHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Obtenido el manejador sobre el servidor de objetos: " + ref);
            
            PacienteImpl clienteCallbackImpl = new PacienteImpl();
            // obtiene la referencia del rootpoa & activa el POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            org.omg.CORBA.Object refCliente = rootpoa.servant_to_reference(clienteCallbackImpl);
            // obtiene la referencia del objeto callback
            Paciente objcllbck = PacienteHelper.narrow(refCliente);
            StringHolder resultado = new StringHolder("");
            ref.establecerMaxPacientes(3);
            ref.registrarPaciente(new PacienteDTO("Pepito Arturo", "N", "CC", 1058, "Cra 9"), objcllbck, resultado);
            //ref.actualizarPaciente(new PacienteDTO("Pepito Arturo", "N", "CC", 1234, "Cra 9"));
            /*PacienteDTO objPaciente =  ref.buscarPaciente(1058);
            System.out.println("Identificacion:"+objPaciente.tipo_id+" "+objPaciente.id);
            System.out.println("Nombre:"+objPaciente.nombres);
            System.out.println("Apellido:"+objPaciente.apellidos);
            System.out.println("Direccion:"+objPaciente.direccion);*/
            ref.enviarIndicadores(1058,new IndicadoresDTO(100, 100, 40,1058));
        } catch (AdapterInactive ex) {
            Logger.getLogger(ClienteDeObjetosPrueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidName ex) {
            Logger.getLogger(ClienteDeObjetosPrueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServantNotActive ex) {
            Logger.getLogger(ClienteDeObjetosPrueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WrongPolicy ex) {
            Logger.getLogger(ClienteDeObjetosPrueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFound ex) {
            Logger.getLogger(ClienteDeObjetosPrueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotProceed ex) {
            Logger.getLogger(ClienteDeObjetosPrueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
            Logger.getLogger(ClienteDeObjetosPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
