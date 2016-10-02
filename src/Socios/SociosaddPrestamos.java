package Socios;
import Libros.CrearLibro;
import Libros.ModificarLibros;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import conexiones.conexion;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class SociosaddPrestamos extends JFrame {

	private JPanel contentPane;
	private JTable tablaSocios;
	private JTable tablaLibros;
	Calendar c1 = Calendar.getInstance();//para Reservas
	private conexion c=new conexion();
	
	public SociosaddPrestamos() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 947, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		///---------------------///
		
		
		JButton btnAtrs = new JButton("Atr\u00E1s");
		btnAtrs.setMnemonic('x');
		btnAtrs.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnAtrs.setIcon(new ImageIcon("src/images/return.png"));
		btnAtrs.setBounds(796, 506, 112, 37);
		contentPane.add(btnAtrs);
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
 //----------------- Zona Socio ------------------
		
		JLabel lblSocios = new JLabel("Socios ");
		lblSocios.setHorizontalAlignment(SwingConstants.CENTER);
		lblSocios.setFont(new Font("Rockwell Condensed", Font.BOLD | Font.ITALIC, 16));
		lblSocios.setBounds(42, 11, 666, 23);
		contentPane.add(lblSocios);
		
		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setBackground(new Color(255, 250, 205));
		scrollPane3.setBounds(42, 45, 666, 170);
		contentPane.add(scrollPane3);
		

		tablaSocios = new JTable(){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false; //Deshabilitación de Celdas
		}};
		tablaSocios.setBackground(new Color(255, 250, 205));
		tablaSocios.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane3.setViewportView(tablaSocios);
		prepararTablaSocio();
		
		JButton btnCrearSocio = new JButton("Crear");
		btnCrearSocio.setMnemonic('c');
		btnCrearSocio.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCrearSocio.setIcon(new ImageIcon("src/images/confirm.png"));
	
		btnCrearSocio.setBounds(736, 58, 117, 29);
		contentPane.add(btnCrearSocio);
		btnCrearSocio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCrearUsuarioPerformed(arg0);
			}
			private void btnCrearUsuarioPerformed(ActionEvent arg0) {
				CrearSocio cu=new CrearSocio();
				cu.setVisible(true);
			}
		});
		
		JButton btnModificarSocio = new JButton("Modificar");
		btnModificarSocio.setMnemonic('m');
		btnModificarSocio.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnModificarSocio.setIcon(new ImageIcon("src/images/modify.png"));
		btnModificarSocio.setBounds(736, 98, 117, 37);
		contentPane.add(btnModificarSocio);
		btnModificarSocio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnModificarSocioPerformed(arg0);
			}
		});
		
		JButton btnRefrescarSocio = new JButton("Refrescar");
		btnRefrescarSocio.setMnemonic('r');
		btnRefrescarSocio.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnRefrescarSocio.setIcon(new ImageIcon("src/images/refresh.png"));
		btnRefrescarSocio.setBounds(736, 186, 117, 29);
		contentPane.add(btnRefrescarSocio);
		setLocationRelativeTo(null);
		
		// Actualizar los datos cuando se hayan modificado o creado usuarios
		btnRefrescarSocio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRefrescarPerformed(arg0);
			}
			private void btnRefrescarPerformed(ActionEvent arg0) {
				prepararTablaSocio();
			}
		});
		
		
		JButton btnEliminarSoc = new JButton("Eliminar");
		btnEliminarSoc.setMnemonic('e');
		btnEliminarSoc.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnEliminarSoc.setIcon(new ImageIcon("src/images/adiooos.png"));
		btnEliminarSoc.setBounds(736, 146, 117, 29);
		contentPane.add(btnEliminarSoc);
		btnEliminarSoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnEliminarSocioPerformed(arg0);
			}
		});
		
 //--------------- Zona Libros -----------------------------------------------------------------------
		
		JLabel lblLibros = new JLabel("Libros");
		lblLibros.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibros.setFont(new Font("Rockwell Condensed", Font.BOLD | Font.ITALIC, 16));
		lblLibros.setBounds(42, 270, 666, 23);
		contentPane.add(lblLibros);
		
		JButton btnCrearLibros = new JButton("Crear");
		btnCrearLibros.setMnemonic('a');
		btnCrearLibros.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCrearLibros.setIcon(new ImageIcon("src/images/confirm.png"));
		btnCrearLibros.setBounds(736, 321, 117, 29);
		contentPane.add(btnCrearLibros);
		btnCrearLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 CrearLibro cl = new CrearLibro("");
				 cl.setVisible(true);
			}
		});
		
		JButton btnModificarLibros = new JButton("Modificar");
		btnModificarLibros.setMnemonic('o');
		btnModificarLibros.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnModificarLibros.setIcon(new ImageIcon("src/images/modify.png"));
		btnModificarLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            try {
                                btnModificarLibrosPerformed(e);
                            } catch (ParseException ex) {
                                Logger.getLogger(SociosaddPrestamos.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		});
		btnModificarLibros.setBounds(736, 361, 117, 29);
		contentPane.add(btnModificarLibros);
		
		JButton btnEliminarLibros = new JButton("Eliminar");
		btnEliminarLibros.setMnemonic('m');
		btnEliminarLibros.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnEliminarLibros.setIcon(new ImageIcon("src/images/adiooos.png"));
		btnEliminarLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnEliminarLibrosPerformed(arg0);
			}
		});
		btnEliminarLibros.setBounds(736, 401, 117, 23);
		contentPane.add(btnEliminarLibros);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBackground(new Color(255, 250, 205));
		scrollPane2.setBounds(48, 307, 660, 170);
		contentPane.add(scrollPane2);
		
		
		tablaLibros = new JTable(){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false; //Deshabilitación de Celdas
		}};
		tablaLibros.setBackground(new Color(255, 250, 205));
		scrollPane2.setViewportView(tablaLibros);
		tablaLibros.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		prepararTablaLibros();
		
		JButton btnRefrescarLibros = new JButton("Refrescar");
		btnRefrescarLibros.setMnemonic('s');
		btnRefrescarLibros.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnRefrescarLibros.setIcon(new ImageIcon("src/images/refresh.png"));
		btnRefrescarLibros.setBounds(736, 435, 117, 37);
		contentPane.add(btnRefrescarLibros);
		btnRefrescarLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                            try {
                                prepararTablaLibros();
                            } catch (SQLException ex) {
                                Logger.getLogger(SociosaddPrestamos.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		});
		
		
 //--------------- Zona Reservas -----------------------------------------------------------------------
		
	     // --------- Botón reserva ------  	
	        JButton btnRealizarPrestamos = new JButton("Realizar Prestamo");
	        btnRealizarPrestamos.setMnemonic('v');
	        btnRealizarPrestamos.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
	        btnRealizarPrestamos.setIcon(new ImageIcon("src/images/Notas.png"));
			btnRealizarPrestamos.setBackground(new Color(0, 255, 102));
			btnRealizarPrestamos.setBounds(708, 236, 145, 29);
			contentPane.add(btnRealizarPrestamos);
	  
			
			btnRealizarPrestamos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
                                    try {
                                        btnRealizarReservaPerformed(arg0);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(SociosaddPrestamos.class.getName()).log(Level.SEVERE, null, ex);
                                    }
				}
				private void btnRealizarReservaPerformed(ActionEvent arg0) throws SQLException {
					int filSelSoc, filSelLib;
					
					filSelSoc=tablaSocios.getSelectedRow();
					filSelLib=tablaLibros.getSelectedRow();
		
				    String idsocios;
				  
				    String idlibro;	
				  
					if(filSelSoc==-1 || filSelLib==-1){
						JOptionPane.showMessageDialog(null, "Debe seleccionar un Socio y un Libro.");
					}else{
                                                int disponible=Integer.parseInt((String)tablaLibros.getValueAt(filSelLib, 6));
						idsocios=(String) tablaSocios.getValueAt(filSelSoc, 0);
                                                int alquiler=Integer.parseInt(String.valueOf(tablaSocios.getValueAt(filSelSoc, 6)));
                                                idlibro=(String) tablaLibros.getValueAt(filSelLib, 0);
                                            
                                                if (alquiler==1 || disponible==1){
                                              
                                                    if(alquiler==1){
                                                	JOptionPane.showMessageDialog(null, "Este Socio dispone de una reserva");
							}
                                                    
                                                    else  if(disponible==1){
							JOptionPane.showMessageDialog(null, "Este Libro no está disponible");
							
                                                    }
                                                   
                                                } else{  
									idlibro=((String)tablaLibros.getValueAt(filSelLib, 0));
									
									int dia = c1.get(Calendar.DATE);
									int mes = c1.get(Calendar.MONTH)+1;
									int ano = c1.get(Calendar.YEAR);
									int mesDev =mes+2;
									
									String fechainicio=ano+"/"+mes+"/"+dia;
									String fechaFin=ano+"/"+mesDev+"/"+dia;;
									JOptionPane.showMessageDialog(null,"Fecha de Reserva: "+fechainicio+" \n\n Fecha devolución: "+fechaFin);
									
									try{
										Statement stm=c.getConexion().createStatement();
										stm.executeUpdate("INSERT INTO prestamos (idlibro,idsocios,fechainicio,fechafin) VALUES" + "('"+idlibro+"','"+idsocios+"','"+fechainicio+"','"+fechainicio+"')");//Consulta preparada para introducir los valores posteriormente
										stm.executeUpdate("UPDATE socios SET alquiler=1 WHERE idsocios='"+idsocios+"'");
										stm.executeUpdate("UPDATE libros SET disponible=1 WHERE idlibro="+idlibro+"");
										JOptionPane.showMessageDialog(null, "Reserva realizada con éxito");
										}catch(SQLException exSql){
											exSql.printStackTrace();
											JOptionPane.showMessageDialog(null, "Error de conexión");
										}catch(Exception e){
											e.printStackTrace();
											System.out.print("2");
											JOptionPane.showMessageDialog(null, "Introduce los parámetros correctos");
									}
									prepararTablaLibros();
									prepararTablaSocio();
						
                                        
                                             }
                                                 }
                                }
			});
			
			JLabel label = new JLabel("");
			label.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			label.setIcon(new ImageIcon("src/images/fondogrande.jpg"));
			label.setBounds(0, 0, 947, 568);
			contentPane.add(label);
			
	}
	//-------- Fin Constructor ---------
	
	//-------- Zona Acciones Socios ------------
	
	private void btnModificarSocioPerformed(ActionEvent arg0) {
		int fsel;
		String datosFila[]= new String[tablaSocios.getColumnCount()]; 
		fsel=tablaSocios.getSelectedRow();
		if(fsel==-1){
			JOptionPane.showMessageDialog(null, "Selecciona una fila");
		}else{
			
			//Recojo los datos de las columnas que necesito de la fila seleccionada, los paso
			//a un array y lo paso al constructor del Jdialog ModificarSocio para iniciarlizarlos para la modificación
			// en sus textfields Correspondientes
			datosFila[0]=(String)tablaSocios.getValueAt(fsel, 0);//IdSOcio
			datosFila[1]=(String)tablaSocios.getValueAt(fsel, 1);//Nombre
			datosFila[2]=(String)tablaSocios.getValueAt(fsel, 2);//Apellidos
			datosFila[3]=(String)tablaSocios.getValueAt(fsel, 3);//Edad
			datosFila[5]=(String)tablaSocios.getValueAt(fsel, 4);//Dirección
			datosFila[6]=(String)tablaSocios.getValueAt(fsel, 5);//Teléfono*/

		
                	ModificarSocio mu=new ModificarSocio(datosFila);
			mu.setVisible(true);
			dispose();
		}
	}
	
	protected void btnEliminarSocioPerformed(ActionEvent arg0) {
		try{
				
				String sql="DELETE FROM socios WHERE idsocios=?";
				PreparedStatement prest=(PreparedStatement) c.getConexion().prepareStatement(sql);
				
				int filsel;
				String idsocios;
				int r;
				filsel=tablaSocios.getSelectedRow();
				
				if(filsel==-1){
					JOptionPane.showMessageDialog(null, "Selecciona un Socio");
				}else{
					idsocios=(String) tablaSocios.getValueAt(filsel, 0);
					r=JOptionPane.showConfirmDialog(null, "¿Desea eliminar el Socio seleccionado con ID Socio: "+idsocios+"?","Eliminar",JOptionPane.YES_NO_OPTION);
					if(r==JOptionPane.YES_OPTION){
						prest.setString(1,idsocios);
						prest.executeUpdate();
						prepararTablaSocio();
					}
				}
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null,"Error de conexión, asegúrese de tener el host activado.","Error",JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, e.getCause());
			}catch(Exception ex){
				 JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
			}
			
		
	}
	

	public void prepararTablaSocio() {
		try{
			Statement sql=c.getConexion().createStatement();
			ResultSet resulSql=sql.executeQuery("SELECT * FROM socios");
			DefaultTableModel m;
			
                            String titulos[] = {"Id Socios", "Nombre","Apellidos","Edad", "Dirección","Telefono","Alquiler"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			tablaSocios.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String fila[]=new String[titulos.length];
			tablaSocios.setModel(m);
			while(resulSql.next()){
					fila[0]=resulSql.getString("idsocios");
					fila[1]=resulSql.getString("nombre");
					fila[2]=resulSql.getString("apellidos");
					fila[3]=resulSql.getString("edad");
					fila[4]=resulSql.getString("direccion");
					fila[5]=resulSql.getString("telefono");
                                        fila[6]=resulSql.getString("alquiler");
					m.addRow(fila);
			}
			
			int i=0;
			while(i<titulos.length){
				tablaSocios.getColumnModel().getColumn(i).setCellRenderer(tcr);
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla");
		}
	}
	
	//-------- Zona acciones Libros ----------
		protected void btnModificarLibrosPerformed(ActionEvent e) throws ParseException {
			int filsel;
				String datosFila[]= new String[tablaLibros.getColumnCount()]; 
				filsel=tablaLibros.getSelectedRow();
						
				
					
					if(filsel==-1){
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún Libro");
					}else{
						//tablaArticulos.getModel();
						datosFila[0]=(String)tablaLibros.getValueAt(filsel, 0);//idlibro
						datosFila[1]=(String)tablaLibros.getValueAt(filsel, 1);//titulo
						datosFila[2]=(String)tablaLibros.getValueAt(filsel, 2);//numejemplares
						datosFila[3]=(String)tablaLibros.getValueAt(filsel, 3);//editorial
						datosFila[4]=(String)tablaLibros.getValueAt(filsel, 4);//numpaginas
						datosFila[5]=(String)tablaLibros.getValueAt(filsel, 5);//anyoedicion
						ModificarLibros ma=new ModificarLibros(datosFila);
						ma.setVisible(true);
					}
					
				
			}
		
		protected void btnEliminarLibrosPerformed(ActionEvent arg0) {
			try{
				
				String sql="DELETE FROM libros WHERE idlibro=?";
				PreparedStatement prest=(PreparedStatement) c.getConexion().prepareStatement(sql);
				
				int filsel;
				String cod_articulo;
				int r;
				filsel=tablaLibros.getSelectedRow();
				
				if(filsel==-1){
					JOptionPane.showMessageDialog(null, "Selecciona un Libro");
				}else{
					cod_articulo=(String) tablaLibros.getValueAt(filsel, 0);
					r=JOptionPane.showConfirmDialog(null, "¿Desea eliminar el Libro seleccionado con Cod_Articulo: "+cod_articulo+"?","Eliminar",JOptionPane.YES_NO_OPTION);
					if(r==JOptionPane.YES_OPTION){
						prest.setString(1,cod_articulo);
						prest.executeUpdate();
						prepararTablaLibros();
					}
				}
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null,"Error de conexión, asegúrese de tener el host activado.","Error",JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, e.getCause());
			}catch(Exception ex){
				 JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		
                        }
			
                }
		

		
			
		public void prepararTablaLibros() throws SQLException {
			try{
				
				
				Statement sql=c.getConexion().createStatement();
				ResultSet resulSql=sql.executeQuery("SELECT * FROM libros");
				//Class.forName("com.mysql.jdbc.Driver");
				
				DefaultTableModel m;
				
				String titulos[] = {"Id Libro","Título","Numero Ejemplares","Editorial","Num Paginas","Año Edición","Disponible"};
				m = new DefaultTableModel(null,titulos);
				
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
				tablaLibros.setRowSorter(sorter);
		     	
				DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
				tcr.setHorizontalAlignment(SwingConstants.CENTER);
			
				String fila[]=new String[titulos.length];
				tablaLibros.setModel(m);
				while(resulSql.next()){
						fila[0]=resulSql.getString("idlibro");
						fila[1]=resulSql.getString("titulo");
						fila[2]=resulSql.getString("numejemplares");
						fila[3]=resulSql.getString("editorial");
						fila[4]=resulSql.getString("numpaginas");
						fila[5]=resulSql.getString("anyoedicion");
                                               	fila[6]=resulSql.getString("disponible");

						m.addRow(fila);
				}
				
				int i=0;
				while(i<titulos.length){
					tablaLibros.getColumnModel().getColumn(i).setCellRenderer(tcr);
					i++;
				}
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla","Error", JOptionPane.ERROR_MESSAGE);
			}
                }
                public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SociosaddPrestamos frame = new SociosaddPrestamos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
                
}