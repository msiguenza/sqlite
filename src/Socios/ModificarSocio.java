package Socios;
import java.awt.BorderLayout;
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
import java.util.logging.Level;
import java.util.logging.Logger;


public class ModificarSocio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldidsocios;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldDir;
	private JTextField textFieldTlf;
	private JTextField textFieldEdad;
	private String datosFila[];
	private conexion c=new conexion();
	
	public ModificarSocio(String fila[]) {
		setTitle("Modificar Usuario");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 539, 389);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		JLabel lblidosocios = new JLabel("ID Socios: *");
		lblidosocios.setBounds(24, 40, 78, 14);
		lblidosocios.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblidosocios);
			
		textFieldidsocios = new JTextField();
		textFieldidsocios.setBounds(112, 39, 140, 20);
		textFieldidsocios.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldidsocios.setColumns(10);
		textFieldidsocios.setText(fila[0]);
		contentPanel.add(textFieldidsocios);
		
		JLabel lblNombre = new JLabel("Nombre: *");
		lblNombre.setBounds(24, 71, 78, 14);
		lblNombre.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(112, 70, 140, 20);
		textFieldNombre.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldNombre.setColumns(10);
		textFieldNombre.setText(fila[1]);
		contentPanel.add(textFieldNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(24, 102, 78, 14);
		lblApellidos.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblApellidos);
		
		textFieldApellidos = new JTextField();
		textFieldApellidos.setBounds(112, 101, 140, 20);
		textFieldApellidos.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setText(fila[2]);
		contentPanel.add(textFieldApellidos);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setBounds(24, 133, 78, 14);
		lblDireccion.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblDireccion);
		
		textFieldDir = new JTextField();
		textFieldDir.setBounds(112, 132, 140, 20);
		textFieldDir.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldDir.setColumns(10);
		textFieldDir.setText(fila[5]);
		contentPanel.add(textFieldDir);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono: *");
		lblTelefono.setBounds(24, 164, 78, 14);
		lblTelefono.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblTelefono);
		
		textFieldTlf = new JTextField();
		textFieldTlf.setBounds(112, 163, 140, 20);
		textFieldTlf.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldTlf.setColumns(10);
		textFieldTlf.setText(fila[6]);
		contentPanel.add(textFieldTlf);
	
		JLabel labelEdad = new JLabel("Edad:*");
		labelEdad.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		labelEdad.setBounds(24, 228, 78, 14);
		contentPanel.add(labelEdad);
		
		textFieldEdad = new JTextField();
		textFieldEdad.setColumns(10);
		textFieldEdad.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldEdad.setBounds(112, 227, 140, 20);
		textFieldEdad.setText(fila[3]);
		contentPanel.add(textFieldEdad);
		
		
		//-------- Botones ---------
		JButton btnGuardarCambios = new JButton("Guardar");
		btnGuardarCambios.setMnemonic('g');
		btnGuardarCambios.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnGuardarCambios.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardarCambios.setBounds(24, 299, 112, 31);
		btnGuardarCambios.setIcon(new ImageIcon("src/images/save.png"));
		contentPanel.add(btnGuardarCambios);
		
		btnGuardarCambios.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				btnGurdarCambiosActionPerformed(o);
			}
		});
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setMnemonic('c');
		btnCancelar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCancelar.setBounds(160, 299, 112, 31);
		btnCancelar.setIcon(new ImageIcon("src/images/adiooos.png"));
		contentPanel.add(btnCancelar);
		
		JLabel lblImagenFondo = new JLabel("");
		lblImagenFondo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblImagenFondo.setBounds(0, 0, 539, 389);
		lblImagenFondo.setIcon(new ImageIcon("src/images/fondito5.jpg"));
		contentPanel.add(lblImagenFondo);
		
		btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
                            try {
                                btnCancelarPerformed(o);
                            } catch (SQLException ex) {
                                Logger.getLogger(ModificarSocio.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
			private void btnCancelarPerformed(ActionEvent o) throws SQLException {
				dispose();
				SociosaddPrestamos u= new SociosaddPrestamos();
				u.setVisible(true);
			}
		});
		
		datosFila=fila;//Inicializamos el atributo de la clase datos Fila con el array de string fila que pasamos por parametros en el constructor de la clase 
		// para poder cambiar el dni también al hacer el Update de la consulta al guardar los cambios . Si no se hace este cambio podríamos cambiar cualquier
		// valor de los atributos menos el dni 
		
	}
	
	protected void btnGurdarCambiosActionPerformed (ActionEvent o){
		try{
			
			
			String sql="UPDATE socios SET idsocios=?, nombre=?, apellidos=?, edad=?,direccion=?, telefono=? WHERE idsocios=? ";
			PreparedStatement prest=(PreparedStatement) c.getConexion().prepareStatement(sql);
			
			String idsocios, nombre, apellidos, edad, direccion, telefono;
			
			idsocios=textFieldidsocios.getText();
			nombre=textFieldNombre.getText();
			apellidos=textFieldApellidos.getText();
			edad=textFieldEdad.getText();
			direccion=textFieldDir.getText();
			telefono=textFieldTlf.getText();
			
			prest.setString(1, idsocios);
			prest.setString(2, nombre);
			prest.setString(3, apellidos);
			prest.setString(4, edad);
			prest.setString(5, direccion);
			prest.setString(6, telefono);
			prest.setString(7, datosFila[0]);//Le asignamos el valor del array y no el de textfieldDni para poder cambiar el dni sin perder la referencia del dni al que le va a afectar
			
			prest.executeUpdate();
			JOptionPane.showMessageDialog(null, "Socio modificado con éxito");
			dispose();
			SociosaddPrestamos umdr=new SociosaddPrestamos();
			umdr.setVisible(true);
			}catch(SQLException exSql){
				JOptionPane.showMessageDialog(null, exSql.getMessage());
			}catch(Exception e){
			    JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
}

