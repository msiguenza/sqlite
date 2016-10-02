package Libros;

import Socios.SociosaddPrestamos;
import java.awt.BorderLayout;   
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.border.LineBorder;

import conexiones.conexion;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.awt.Color;

public class ModificarLibros extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldidLibro;
	private JTextField textFieldNEjemplares;
	private JTextField textFieldTitulo;
	private JTextField textFieldAno;
	private JTextField textFieldAnyoEdicion;
	private JTextField textFieldNPaginas;
        private String datosFila[];
	private conexion c=new conexion();
	private JTextField textFieldEditorial;
		
	public ModificarLibros(String fila[]) throws ParseException {
		setTitle("Modificación Artículo");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 313, 387);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		JLabel lblIdLibro = new JLabel("ID Libro:");
		lblIdLibro.setBounds(24, 40, 112, 14);
		lblIdLibro.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblIdLibro);
			
		textFieldidLibro = new JTextField();
		textFieldidLibro.setBounds(132, 40, 140, 20);
		textFieldidLibro.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldidLibro.setColumns(10);
		textFieldidLibro.setText(fila[0]);
		textFieldidLibro.setEditable(false);
		contentPanel.add(textFieldidLibro);
		
		JLabel lblnumejemplares = new JLabel("N\u00BAEjemplares:");
		lblnumejemplares.setBounds(24, 102, 112, 14);
		lblnumejemplares.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblnumejemplares);
		
		textFieldNEjemplares = new JTextField();
		textFieldNEjemplares.setBounds(132, 102, 140, 20);
		textFieldNEjemplares.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldNEjemplares.setColumns(10);
		textFieldNEjemplares.setText(fila[2]);
		contentPanel.add(textFieldNEjemplares);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(24, 71, 112, 14);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblTitulo);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(132, 71, 140, 20);
		textFieldTitulo.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setText(fila[1]);
		contentPanel.add(textFieldTitulo);
		
		JLabel lblEditorial = new JLabel("Editorial :");
		lblEditorial.setBounds(22, 145, 112, 14);
		lblEditorial.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblEditorial);
	
		
		JLabel lblNumPaginas = new JLabel("N\u00BA Paginas:");
		lblNumPaginas.setBounds(24, 197, 112, 14);
		lblNumPaginas.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblNumPaginas);
		
		textFieldNPaginas = new JTextField();
		textFieldNPaginas.setColumns(10);
		textFieldNPaginas.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldNPaginas.setBounds(132, 197, 140, 20);
		textFieldNPaginas.setText(fila[4]);
		contentPanel.add(textFieldNPaginas);

		JLabel labelanyoedicion = new JLabel("A\u00F1o Edici\u00F3n:");
		labelanyoedicion.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		labelanyoedicion.setBounds(24, 228, 112, 14);
		contentPanel.add(labelanyoedicion);
		
		textFieldAnyoEdicion = new JTextField();
		textFieldAnyoEdicion.setColumns(10);
		textFieldAnyoEdicion.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldAnyoEdicion.setBounds(132, 228, 140, 20);
		textFieldAnyoEdicion.setText(fila[5]);
		contentPanel.add(textFieldAnyoEdicion);
		
		JButton btnGuardarCambios = new JButton("Guardar");
		btnGuardarCambios.setMnemonic('g');
		btnGuardarCambios.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnGuardarCambios.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardarCambios.setBounds(24, 291, 112, 39);
		btnGuardarCambios.setIcon(new ImageIcon("src/images/save.png"));
		contentPanel.add(btnGuardarCambios);
			
		btnGuardarCambios.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				btnGurdarCambiosActionPerformed(o);
			}
		});
		
		
		JButton Salir = new JButton("Cancelar");
		Salir.setMnemonic('c');
		Salir.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Salir.setBounds(160, 291, 112, 39);
		Salir.setIcon(new ImageIcon("src/images/adiooos.png"));
		contentPanel.add(Salir);
		
		Salir.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				SalirActionPerformed(o);
			}
			private void SalirActionPerformed (ActionEvent o){
				dispose();
			}

		});
		
		JLabel lblImagenFondo = new JLabel("");
		lblImagenFondo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblImagenFondo.setBounds(0, 0, 314, 387);
		lblImagenFondo.setIcon(new ImageIcon("src/images/fondito4.jpg"));
		contentPanel.add(lblImagenFondo);
		
		textFieldEditorial = new JTextField();
		textFieldEditorial.setText((String) null);
		textFieldEditorial.setColumns(10);
		textFieldEditorial.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldEditorial.setBounds(131, 143, 140, 20);
                textFieldEditorial.setText(fila[3]);
		contentPanel.add(textFieldEditorial);
		
		datosFila=fila;
		
	}
	
	protected void btnGurdarCambiosActionPerformed (ActionEvent o){
            
                try{
			
			
			String sql="UPDATE libros SET idlibro=?, titulo=?, numejemplares=?, editorial=?, numpaginas=?, anyoedicion=? WHERE idlibro=? ";
			PreparedStatement prest=(PreparedStatement) c.getConexion().prepareStatement(sql);
			
			
			
                        String titulo,editorial,idlibro,numejemplares,numpaginas,anyoedicion;
                        
			idlibro=textFieldidLibro.getText();
			titulo=textFieldTitulo.getText();
			numejemplares=textFieldNEjemplares.getText();
			editorial=textFieldEditorial.getText();
			numpaginas=textFieldNPaginas.getText();
			anyoedicion=textFieldAnyoEdicion.getText();
			
			prest.setString(1, idlibro);
			prest.setString(2, titulo);
			prest.setString(3, numejemplares);
			prest.setString(4, editorial);
			prest.setString(5, numpaginas);
			prest.setString(6, anyoedicion);
			prest.setString(7, datosFila[0]);//Le asignamos el valor del array y no el de textfieldDni para poder cambiar el dni sin perder la referencia del dni al que le va a afectar
			
			prest.executeUpdate();
			JOptionPane.showMessageDialog(null, "Libro modificado con éxito");
			dispose();
			SociosaddPrestamos umdr=new SociosaddPrestamos();
		        umdr.setVisible(true);
			}catch(SQLException exSql){
				JOptionPane.showMessageDialog(null, exSql.getMessage());
			}catch(Exception e){
                            e.printStackTrace();
			    JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
                
                
	}
}