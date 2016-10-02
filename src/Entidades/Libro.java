package Entidades;

public class Libro {
 
 public String idLibro;
 public String titulo;
 public String nEjemplares;
 public String editorial;
 public String nPaginas;
 public String anioEdicion;
 public String disponible;
 


 public Libro(String titulo, String nEjemplares, String editorial,
   String nPaginas, String anioEdicion, String disponible, String idLibro) {
  this.titulo = titulo;
  this.nEjemplares = nEjemplares;
  this.editorial = editorial;
  this.nPaginas = nPaginas;
  this.anioEdicion = anioEdicion;
  this.disponible= disponible;
  this.idLibro=idLibro;
  
}



 public Libro() {
  // TODO Auto-generated constructor stub
 }

 public String getIdLibro() {
  return idLibro;
 }

 public void setIdLibro(String idLibro) {
  this.idLibro = idLibro;
 }
 
 public String getDisponible() {
  return disponible;
 }

 public void setDisponible(String disponible) {
  this.disponible = disponible;
 }

 public String getTitulo() {
  return titulo;
 }

 public void setTitulo(String titulo) {
  this.titulo = titulo;
 }

 public String getnEjemplares() {
  return nEjemplares;
 }

 public void setnEjemplares(String nEjemplares) {
  this.nEjemplares = nEjemplares;
 }

 public String getEditorial() {
  return editorial;
 }

 public void setEditorial(String editorial) {
  this.editorial = editorial;
 }

 public String getnPaginas() {
  return nPaginas;
 }

 public void setnPaginas(String nPaginas) {
  this.nPaginas = nPaginas;
 }

 public String getAnioEdicion() {
  return anioEdicion;
 }

 public void setAnioEdicion(String anioEdicion) {
  this.anioEdicion = anioEdicion;
 }
}