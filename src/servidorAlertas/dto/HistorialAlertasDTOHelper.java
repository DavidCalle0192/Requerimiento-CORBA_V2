package servidorAlertas.dto;


/**
* servidorAlertas/dto/HistorialAlertasDTOHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from gesPacientes.idl
* domingo 21 de junio de 2020 06:38:06 PM COT
*/

abstract public class HistorialAlertasDTOHelper
{
  private static String  _id = "IDL:servidorAlertas/dto/HistorialAlertasDTO:1.0";

  public static void insert (org.omg.CORBA.Any a, servidorAlertas.dto.HistorialAlertasDTO that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static servidorAlertas.dto.HistorialAlertasDTO extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = servidorAlertas.dto.AlertaDTOHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_array_tc (6, _tcOf_members0 );
          _members0[0] = new org.omg.CORBA.StructMember (
            "alertas",
            _tcOf_members0,
            null);
          _tcOf_members0 = servidorAlertas.dto.PacienteDTOHelper.type ();
          _members0[1] = new org.omg.CORBA.StructMember (
            "objPaciente",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (servidorAlertas.dto.HistorialAlertasDTOHelper.id (), "HistorialAlertasDTO", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static servidorAlertas.dto.HistorialAlertasDTO read (org.omg.CORBA.portable.InputStream istream)
  {
    servidorAlertas.dto.HistorialAlertasDTO value = new servidorAlertas.dto.HistorialAlertasDTO ();
    value.alertas = new servidorAlertas.dto.AlertaDTO[6];
    for (int _o0 = 0;_o0 < (6); ++_o0)
    {
      value.alertas[_o0] = servidorAlertas.dto.AlertaDTOHelper.read (istream);
    }
    value.objPaciente = servidorAlertas.dto.PacienteDTOHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, servidorAlertas.dto.HistorialAlertasDTO value)
  {
    if (value.alertas.length != (6))
      throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    for (int _i0 = 0;_i0 < (6); ++_i0)
    {
      servidorAlertas.dto.AlertaDTOHelper.write (ostream, value.alertas[_i0]);
    }
    servidorAlertas.dto.PacienteDTOHelper.write (ostream, value.objPaciente);
  }

}
