package servidorAlertas.dto;


/**
* servidorAlertas/dto/AlertaDTOHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from gesPacientes.idl
* domingo 21 de junio de 2020 06:38:06 PM COT
*/

abstract public class AlertaDTOHelper
{
  private static String  _id = "IDL:servidorAlertas/dto/AlertaDTO:1.0";

  public static void insert (org.omg.CORBA.Any a, servidorAlertas.dto.AlertaDTO that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static servidorAlertas.dto.AlertaDTO extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [4];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = servidorAlertas.dto.IndicadoresDTOHelper.type ();
          _members0[0] = new org.omg.CORBA.StructMember (
            "indicadores",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "fecha",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "hora",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[3] = new org.omg.CORBA.StructMember (
            "puntuacion",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (servidorAlertas.dto.AlertaDTOHelper.id (), "AlertaDTO", _members0);
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

  public static servidorAlertas.dto.AlertaDTO read (org.omg.CORBA.portable.InputStream istream)
  {
    servidorAlertas.dto.AlertaDTO value = new servidorAlertas.dto.AlertaDTO ();
    value.indicadores = servidorAlertas.dto.IndicadoresDTOHelper.read (istream);
    value.fecha = istream.read_string ();
    value.hora = istream.read_string ();
    value.puntuacion = istream.read_long ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, servidorAlertas.dto.AlertaDTO value)
  {
    servidorAlertas.dto.IndicadoresDTOHelper.write (ostream, value.indicadores);
    ostream.write_string (value.fecha);
    ostream.write_string (value.hora);
    ostream.write_long (value.puntuacion);
  }

}
