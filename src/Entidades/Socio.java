package Entidades;

public class Socio {
 
 public String idSocio;
 public String nombre;
 public String apellidos;
 public String edad;
 public String direccion;
 public String telefono;
 public String alquiler;
 
 

 public Socio(String nombre, String apellidos, String edad,
   String direccion, String telefono, String alquiler, String idSocio) {
  this.nombre = nombre;
  this.apellidos = apellidos;
  this.edad = edad;
  this.direccion = direccion;
  this.telefono = telefono;
  this.alquiler= alquiler;
  this.idSocio= idSocio;

  
}

 

 public Socio() {
  // TODO Auto-generated constructor stub
 }
 
 public String getIdSocio() {
  return idSocio;
 }

 public void setIdSocio(String idSocio) {
  this.idSocio = idSocio;
 }
 
 public String getAlquiler() {
  return alquiler;
 }

 public void setAlquiler(String alquiler) {
  this.alquiler = alquiler;
 }

 public String getNombre() {
  return nombre;
 }

 public void setNombre(String nombre) {
  this.nombre = nombre;
 }

 public String getApellidos() {
  return apellidos;
 }

 public void setApellidos(String apellidos) {
  this.apellidos = apellidos;
 }

 public String getEdad() {
  return edad;
 }

 public void setEdad(String edad) {
  this.edad = edad;
 }

 public String getDireccion() {
  return direccion;
 }

 public void setDireccion(String direccion) {
  this.direccion = direccion;
 }

 public String getTelefono() {
  return telefono;
 }

 public void setTelefono(String telefono) {
  this.telefono = telefono;
 }
 

}