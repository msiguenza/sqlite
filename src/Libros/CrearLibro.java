package Libros;

import java.awt.BorderLayout;   
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;


public class CrearLibro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldTitulo;
	private conexion c=new conexion();
	private JTextField textFieldnumejem;
	private JTextField textFieldEditorial;
	private JTextField textFieldnpaginas;
	private JTextField textFieldanyoedicion;


	
	public CrearLibro(String c) {
		setTitle("Creación Artículos");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setModal(true);
		setUndecorated(true);
		setBounds(100, 100, 313, 303);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(149, 25, 140, 20);
		textFieldTitulo.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(24, 26, 78, 14);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblTitulo);
		
		
		JLabel lblnumejem = new JLabel("N\u00BA Ejemplares");
		lblnumejem.setBounds(24, 68, 99, 14);
		lblnumejem.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblnumejem);
		
		
		
		JButton btnCrearArticulo = new JButton("A\u00F1adir");
		btnCrearArticulo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCrearArticulo.setMnemonic('a');
		btnCrearArticulo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCrearArticulo.setBounds(24, 227, 120, 31);
		btnCrearArticulo.setIcon(new ImageIcon("src/images/confirm.png"));
		contentPanel.add(btnCrearArticulo);
		
		
		btnCrearArticulo.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				btnCrearArticuloActionPerformed(o);
			}
		});
		
		
		JButton Salir = new JButton("Cancelar");
		Salir.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Salir.setMnemonic('c');
		Salir.setBounds(169, 227, 120, 31);
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
		
		JLabel lblCamposObligatorios = new JLabel("Campos obligatorios con el s\u00EDmbolo *");
		lblCamposObligatorios.setForeground(Color.RED);
		lblCamposObligatorios.setBounds(24, 269, 216, 14);
		contentPanel.add(lblCamposObligatorios);
		
		textFieldnumejem = new JTextField();
		textFieldnumejem.setColumns(10);
		textFieldnumejem.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldnumejem.setBounds(149, 67, 140, 20);
		contentPanel.add(textFieldnumejem);
		
		JLabel labeleditorial = new JLabel("Editorial");
		labeleditorial.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		labeleditorial.setBounds(24, 111, 99, 14);
		contentPanel.add(labeleditorial);
		
		textFieldEditorial = new JTextField();
		textFieldEditorial.setColumns(10);
		textFieldEditorial.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldEditorial.setBounds(149, 110, 140, 20);
		contentPanel.add(textFieldEditorial);
		
		JLabel labelnumpag = new JLabel("N\u00BA Paginas");
		labelnumpag.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		labelnumpag.setBounds(24, 151, 99, 14);
		contentPanel.add(labelnumpag);
		
		textFieldnpaginas = new JTextField();
		textFieldnpaginas.setColumns(10);
		textFieldnpaginas.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldnpaginas.setBounds(149, 150, 140, 20);
		contentPanel.add(textFieldnpaginas);
		
		JLabel labelanioedc = new JLabel("A\u00F1o Edicion");
		labelanioedc.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		labelanioedc.setBounds(24, 197, 99, 14);
		contentPanel.add(labelanioedc);
		
		textFieldanyoedicion = new JTextField();
		textFieldanyoedicion.setColumns(10);
		textFieldanyoedicion.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldanyoedicion.setBounds(149, 196, 140, 20);
		contentPanel.add(textFieldanyoedicion);
		
		
	}

   
	
	protected void btnCrearArticuloActionPerformed (ActionEvent o){
		try{
			
			String sql="INSERT INTO libros (titulo,numejemplares,editorial,numpaginas,anyoedicion) VALUES" + "(?,?,?,?,?)";//Consulta preparada para introducir los valores posteriormente
			
			int numejemplares,numpaginas,anyoedicion;
			String titulo,editorial;
			
			
			titulo=textFieldTitulo.getText();
			numejemplares=Integer.parseInt(textFieldnumejem.getText());
			editorial=textFieldEditorial.getText();	
			numpaginas=Integer.parseInt(textFieldnpaginas.getText());
			anyoedicion=Integer.parseInt(textFieldanyoedicion.getText());
			
			
			
			PreparedStatement prest=(PreparedStatement) c.getConexion().prepareStatement(sql);
			
			prest.setString(1, titulo);
			prest.setInt(2, numejemplares);
			prest.setString(3, editorial);
			prest.setInt(4, numpaginas);
			prest.setInt(5, anyoedicion);
			
			prest.execute();
			
			JOptionPane.showMessageDialog(null, "Libro creado");
			dispose();
		}catch(SQLException exSql){
			JOptionPane.showMessageDialog(null, "Posible error: Error de conexión con la base de datos, asegúrese de tener activado el host\n\n Error Mysq: "+exSql.getMessage());
		}catch(Exception e){
		    JOptionPane.showMessageDialog(null, "Campos de páginas vacíos o con letras, introduzca números");
		}
		
	}
}