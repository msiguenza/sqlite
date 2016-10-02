package Socios;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.border.LineBorder;

import conexiones.conexion;

import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;


public class CrearSocio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldDir;
	private JTextField textFieldTlf;
	private JTextField textFieldEdad;
	private conexion c=new conexion();

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearSocio frame = new CrearSocio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public CrearSocio() {
		setTitle("Crear Usuarios");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setModal(true);
		setUndecorated(true);
		setBounds(100, 100, 318, 389);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setBounds(24, 71, 114, 14);
		lblNombre.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblNombre);
		
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(148, 71, 140, 20);
		textFieldNombre.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setForeground(Color.BLACK);
		lblApellidos.setBounds(24, 102, 114, 14);
		lblApellidos.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblApellidos);
		
		textFieldApellidos = new JTextField();
		textFieldApellidos.setBounds(148, 102, 140, 20);
		textFieldApellidos.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldApellidos);
		textFieldApellidos.setColumns(10);
		
		JLabel lblContrasena = new JLabel("Edad :");
		lblContrasena.setForeground(Color.BLACK);
		lblContrasena.setBounds(24, 212, 114, 14);
		lblContrasena.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblContrasena);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setForeground(Color.BLACK);
		lblDireccion.setBounds(24, 133, 114, 14);
		lblDireccion.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblDireccion);
		
		textFieldDir = new JTextField();
		textFieldDir.setBounds(148, 133, 140, 20);
		textFieldDir.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldDir);
		textFieldDir.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono: ");
		lblTelefono.setForeground(Color.BLACK);
		lblTelefono.setBounds(24, 164, 114, 14);
		lblTelefono.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblTelefono);
		
		textFieldTlf = new JTextField();
		textFieldTlf.setBounds(148, 164, 140, 20);
		textFieldTlf.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldTlf);
		textFieldTlf.setColumns(10);
		
		JButton btnCrearUsuario = new JButton("A\u00F1adir");
		btnCrearUsuario.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCrearUsuario.setMnemonic('a');
		btnCrearUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCrearUsuario.setBounds(24, 322, 120, 31);
		btnCrearUsuario.setIcon(new ImageIcon("src/images/confirm.png"));
		contentPanel.add(btnCrearUsuario);
		
		
		btnCrearUsuario.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				btnCrearUsuarioActionPerformed(o);
			}
		});
		
		
		JButton Salir = new JButton("Cancelar");
		Salir.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Salir.setMnemonic('c');
		Salir.setBounds(168, 322, 120, 31);
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
		
		textFieldEdad = new JTextField();
		textFieldEdad.setColumns(10);
		textFieldEdad.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldEdad.setBounds(148, 212, 140, 20);
		contentPanel.add(textFieldEdad);
		
	}
	
	protected void btnCrearUsuarioActionPerformed (ActionEvent o){
		try{
			
			String sql="INSERT INTO socios (nombre,apellidos,edad,direccion,telefono) VALUES" + "(?,?,?,?,?)";//Consulta preparada para introducir los valores posteriormente
			
			
			String nombre, apellidos, edad, direccion, telefono;
			
			
			nombre=textFieldNombre.getText();
			apellidos=textFieldApellidos.getText();
			edad=textFieldEdad.getText();
			direccion=textFieldDir.getText();
			telefono=textFieldTlf.getText();
			
			
			PreparedStatement prest=(PreparedStatement) c.getConexion().prepareStatement(sql);
			
			prest.setString(1, nombre);
			prest.setString(2, apellidos);
			prest.setString(3, edad);
			prest.setString(4, direccion);
			prest.setString(5, telefono);
			prest.execute();
			JOptionPane.showMessageDialog(null, "Socio creado con éxito");
			dispose();
			}catch(SQLException exSql){
				JOptionPane.showMessageDialog(null, exSql.getMessage());
			}catch(Exception e){
                            e.printStackTrace();
			    JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
