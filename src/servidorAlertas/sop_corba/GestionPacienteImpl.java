package servidorAlertas.sop_corba;

import clienteHabitacion.sop_corba.Paciente;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import org.omg.CORBA.StringHolder;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import servidorAlertas.dao.AlertaDAO;
import servidorAlertas.dao.PacienteDAO;
import servidorAlertas.dto.AlertaDTO;
import servidorAlertas.dto.HistorialAlertasDTO;
import servidorAlertas.dto.IndicadoresDTO;
import servidorAlertas.dto.PacienteDTO;
import servidorAlertas.vista.VistaLogAlertas;
import servidorNotificaciones.sop_corba.Notificaciones;
import servidorNotificaciones.sop_corba.NotificacionesHelper;

/**
 *
 * @author JhonMZ
 */
public class GestionPacienteImpl implements GestionPacientesOperations {

    private VistaLogAlertas guiAlertas;
    private Notificaciones objRefRemotaNotificaciones;
    private Hashtable<Integer, Pair<PacienteDTO,Paciente>> objRegistros;
    private int MAX_PACIENTES = -1;
    private PacienteDAO objPacienteDAO;
    private AlertaDAO objAlertaDAO;
    
    public GestionPacienteImpl() {
        objRegistros = new Hashtable<>();
        objPacienteDAO = new PacienteDAO();
        objAlertaDAO = new AlertaDAO();
        this.guiAlertas = new VistaLogAlertas();
        this.guiAlertas.setVisible(true);
    }
    
    /**
     * Registra un paciente en la base de datos, si el paciente ya esta registrado 
     * actualiza sus datos
     * @param objPaciente Objeto que contiene los datos del paciente
     * @param refCliente Servant del paciente 
     * @param resultado parametro de salida donde se retorna un mensaje del registro
     * @return Retorna true si el registro o actualizacion es exitoso
     */
    @Override
    public boolean registrarPaciente(PacienteDTO objPaciente, Paciente refCliente, StringHolder resultado) {
        log("Ejecutando registrarPaciente...");
        boolean res = false;
        resultado.value = "";
        if(objRegistros.size() < MAX_PACIENTES){
            if(!pacienteActivo(objPaciente)){
                if(!pacienteRegistrado(objPaciente)){
                    res = objPacienteDAO.registrarPaciente(objPaciente);
                    log("Paciente nuevo:");
                    log("Nombre:"+objPaciente.nombres+" "+objPaciente.apellidos);
                    log("Identificacion: "+objPaciente.tipo_id+" "+objPaciente.id);
                    log("Direccion:"+objPaciente.direccion);
                    resultado.value = "registrado";
                }else{
                    resultado.value = "actualizado";
                    log("El paciente con id "+objPaciente.id+" ya esta registrado se procedera actualizar sus datos");//TODO actualizar
                    res = actualizarPaciente(objPaciente);
                }
                if(res==true){
                    objRegistros.put(objPaciente.id, new Pair<PacienteDTO,Paciente>(objPaciente,refCliente));
                }else{
                    resultado.value = "Error";
                    log("Error al registrar paciente");
                }
            }else{
                resultado.value = "Paciente_activo";
                log("El paciente con id "+objPaciente.id+" ya esta siendo monitorizado");
            }
        }else{
            resultado.value = "Limite_superado";
            log("No se pueden almacenar mas pacientes.");
        }
        return res;
    }
    
    private boolean pacienteActivo(PacienteDTO objPaciente){
        //System.out.println("Buscando paciente...");
        return objRegistros.get(objPaciente.id) != null;
    }
    
    private boolean pacienteRegistrado(PacienteDTO objPacienteDTO){
        return objPacienteDAO.consultarPacienteId(objPacienteDTO.id) != null;
    }
    
    
    @Override
    public boolean actualizarPaciente(PacienteDTO objPaciente) {
        log("Ejecutando actualizarPaciente...");
        boolean res = false;
        if(pacienteRegistrado(objPaciente)){
            res = objPacienteDAO.actualizarPaciente(objPaciente);
            log("Actualizando datos paciente con id"+objPaciente.id+":");
            log("Nombre:"+objPaciente.nombres+" "+objPaciente.apellidos);
            log("Identificacion: "+objPaciente.tipo_id+" "+objPaciente.id);
            log("Direccion:"+objPaciente.direccion);
        }else{
            log("El paciente con id "+objPaciente.id+" no esta registrado se procedera a registrar sus datos");
            res = objPacienteDAO.registrarPaciente(objPaciente);
            log("Paciente nuevo:");
            log("Nombre:"+objPaciente.nombres+" "+objPaciente.apellidos);
            log("Identificacion: "+objPaciente.tipo_id+" "+objPaciente.id);
            log("Direccion:"+objPaciente.direccion);
        }
        return res;
    }

    @Override
    public PacienteDTO buscarPaciente(int idPaciente) {
        log("Ejecutando buscarPaciente...");
        PacienteDTO objPaciente = objPacienteDAO.consultarPacienteId(idPaciente);
        if(objPaciente!=null){
            log("Paciente encontrado:");
            log("Nombre:"+objPaciente.nombres+" "+objPaciente.apellidos);
            log("Identificacion: "+objPaciente.tipo_id+" "+objPaciente.id);
            log("Direccion:"+objPaciente.direccion);
        }else{
            objPaciente = new PacienteDTO();
            log("No se encontro el paciente con id "+idPaciente);
        }
        return objPaciente;
    }

    
    /**
     * Evalua los indicadores del paciente dandoles una puntuacion, si los indicadores estan
     * fuera de lo normal se retorna false 
     * @param idPaciente identificacion del paciente
     * @param objIndicadores objeto con la informacion de los indicadores
     * @return Retorna true si los indicadores estan en un valor normal o false de lo contrario
     */
    @Override
    public boolean enviarIndicadores(int idPaciente, IndicadoresDTO objIndicadores) {
        boolean aux=false;
        log("\nEjecutando enviarIndicadores...");
        
        int puntuacion = obtenerPuntuacion(objIndicadores);
        log("Indicadores del paciente con id "+idPaciente);
        log("FC:"+objIndicadores.frecuenciaCardiaca);
        log("FR:"+objIndicadores.frecuenciaRespiratoria);
        log("ToC:"+objIndicadores.temperatura);
        log("Puntuacion:"+puntuacion);
        
        if(puntuacion>1){
            log("Indicadores fuera de lo normal. Almacenando alerta y enviando alerta...");
            
            AlertaDTO objAlerta = new AlertaDTO(objIndicadores, LocalDate.now().toString(), LocalTime.now().toString(),puntuacion);
            objAlertaDAO.registrarAlerta(idPaciente, objAlerta);
            HistorialAlertasDTO objHistorial = obtenerHistorial(idPaciente);
            objRefRemotaNotificaciones.enviarAlerta(objHistorial);
            Pair<PacienteDTO,Paciente> objCliente = objRegistros.get(idPaciente);
            objCliente.getValue().alertarPaciente("Paciente con indicadores fuera de lo normal");
            aux=true;
        }
        return aux;
    }
    
    private HistorialAlertasDTO obtenerHistorial(int idPaciente) {
        HistorialAlertasDTO objHistorial = new HistorialAlertasDTO();
        objHistorial.objPaciente = buscarPaciente(idPaciente);
        objHistorial.alertas  = objAlertaDAO.obtenerUltimas6Alertas(idPaciente);
        
        for (int i = 0; i < 6; i++) {
            if(objHistorial.alertas[i]==null)
                objHistorial.alertas[i] = new AlertaDTO(new IndicadoresDTO(), "", "", -1);
        }

        return objHistorial;
    }
    
    private int obtenerPuntuacion(IndicadoresDTO objIndicadores){
        int puntuacion = 0;
        
        if(objIndicadores != null){
            if(objIndicadores.frecuenciaCardiaca < 60 
                    || objIndicadores.frecuenciaCardiaca > 80)puntuacion++;

            if(objIndicadores.frecuenciaRespiratoria < 70 
                    || objIndicadores.frecuenciaRespiratoria > 90)puntuacion++;

            if(objIndicadores.temperatura < 36.2 
                    || objIndicadores.temperatura > 38.2)puntuacion++;
        }
        return puntuacion;
       
    }   

    @Override
    public boolean establecerMaxPacientes(int num) {
        log("\nEjecutando establecerMaxPacientes...");
        if(num>=1 && num<=5){
            this.MAX_PACIENTES = num;
            return true;
        }else{
        return false;
        }
    }

    @Override
    public int getMaxPacientes() {
       return MAX_PACIENTES;
    }

    @Override
    public int getNumRegistros() {
        return objRegistros.size();
    }

    public void consultarReferenciaRemota(NamingContextExt nce, String servicio) {
        try {
            this.objRefRemotaNotificaciones = NotificacionesHelper.narrow(nce.resolve_str(servicio));
            System.out.println("Obteniendo el manejador sobre el servidor de objetos:" + this.objRefRemotaNotificaciones);
            guiAlertas.setVisible(true);
        } catch (NotFound ex) {
            Logger.getLogger(GestionPacienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotProceed ex) {
            Logger.getLogger(GestionPacienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidName ex) {
            Logger.getLogger(GestionPacienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void log(String log){
        System.out.println(log);
        guiAlertas.agregarLog(log);
    }
}
