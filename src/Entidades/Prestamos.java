package Entidades;

public class Prestamos {

 public String idPrestamo;
 public String idLibro;
 public String idSocios;
 public String fechaInicio;
 public String fechaFin;
 


 public Prestamos(String idPrestamo, String idLibro, String idSocios,
   String fechaInicio, String fechaFin) {
  this.idPrestamo = idPrestamo;
  this.idLibro = idLibro;
  this.idSocios = idSocios;
  this.fechaInicio = fechaInicio;
  this.fechaFin = fechaFin;
  
}



 public Prestamos() {
  // TODO Auto-generated constructor stub
 }



 public String getIdPrestamo() {
  return idPrestamo;
 }



 public void setIdPrestamo(String idPrestamo) {
  this.idPrestamo = idPrestamo;
 }



 public String getIdLibro() {
  return idLibro;
 }



 public void setIdLibro(String idLibro) {
  this.idLibro = idLibro;
 }



 public String getIdSocios() {
  return idSocios;
 }



 public void setIdSocios(String idSocios) {
  this.idSocios = idSocios;
 }



 public String getFechaInicio() {
  return fechaInicio;
 }



 public void setFechaInicio(String fechaInicio) {
  this.fechaInicio = fechaInicio;
 }



 public String getFechaFin() {
  return fechaFin;
 }



 public void setFechaFin(String fechaFin) {
  this.fechaFin = fechaFin;
 }

}