package clienteHabitacion.sop_corba;


/**
* cliente/sop_corba/PacienteHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from gesPacientes.idl
* domingo 21 de junio de 2020 06:38:06 PM COT
*/

abstract public class PacienteHelper
{
  private static String  _id = "IDL:cliente/sop_corba/Paciente:1.0";

  public static void insert (org.omg.CORBA.Any a, clienteHabitacion.sop_corba.Paciente that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static clienteHabitacion.sop_corba.Paciente extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (clienteHabitacion.sop_corba.PacienteHelper.id (), "Paciente");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static clienteHabitacion.sop_corba.Paciente read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_PacienteStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, clienteHabitacion.sop_corba.Paciente value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static clienteHabitacion.sop_corba.Paciente narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof clienteHabitacion.sop_corba.Paciente)
      return (clienteHabitacion.sop_corba.Paciente)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
            clienteHabitacion.sop_corba._PacienteStub stub = new clienteHabitacion.sop_corba._PacienteStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static clienteHabitacion.sop_corba.Paciente unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof clienteHabitacion.sop_corba.Paciente)
      return (clienteHabitacion.sop_corba.Paciente)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
            clienteHabitacion.sop_corba._PacienteStub stub = new clienteHabitacion.sop_corba._PacienteStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}