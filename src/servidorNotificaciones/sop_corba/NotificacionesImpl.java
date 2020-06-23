/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorNotificaciones.sop_corba;

import servidorAlertas.dto.AlertaDTO;
import servidorAlertas.dto.HistorialAlertasDTO;
import servidorAlertas.dto.PacienteDTO;
import servidorNotificaciones.vista.VistaNotificaciones;

/**
 *
 * @author JhonMZ
 */
public class NotificacionesImpl implements NotificacionesOperations{
    
    VistaNotificaciones guiNotificaciones;
    
    public NotificacionesImpl() {
        this.guiNotificaciones = new VistaNotificaciones();
        //this.guiNotificaciones.setVisible(true);
    }

    
    @Override
    public void enviarAlerta(HistorialAlertasDTO objHistorial) {
        System.out.println("Ejecutando enviarAlerta...");
        guiNotificaciones.editarInfo(objHistorial);
        guiNotificaciones.setVisible(true);
        PacienteDTO objPaciente = objHistorial.objPaciente;
        AlertaDTO objAlerta = objHistorial.alertas[0];
        System.out.println("Alerta recibida:");
        System.out.println("N° Id:"+objPaciente.id);
        System.out.println("Nombre y apellidos:"+objPaciente.nombres+" "+objPaciente.apellidos);
        System.out.println("Direccion:"+objPaciente.direccion);
        System.out.println("Fecha de alerta:"+objAlerta.fecha);
        System.out.println("Hora de alerta:"+objAlerta.hora);
        
        System.out.println("FC:"+objAlerta.indicadores.frecuenciaCardiaca);
        System.out.println("FR:"+objAlerta.indicadores.frecuenciaRespiratoria);
        System.out.println("ToC:"+objAlerta.indicadores.temperatura);
        System.out.println("Puntuacion:"+objAlerta.puntuacion);

        System.out.println("***Historial***");
        for (int i = 1; i < objHistorial.alertas.length; i++) {
            AlertaDTO objAlertaTemp = objHistorial.alertas[i];
            if(objAlertaTemp!=null)
                System.out.println("Fecha:"+objAlertaTemp.fecha+" Hota:"+objAlertaTemp.hora
                                +" FC:"+objAlertaTemp.indicadores.frecuenciaCardiaca
                                +" FR:"+objAlertaTemp.indicadores.frecuenciaRespiratoria
                                +" ToC:"+objAlertaTemp.indicadores.temperatura
                                +" Puntuacion:"+objAlertaTemp.puntuacion);
        }
    }
    
}
