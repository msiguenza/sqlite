package MenuPrincipal;

import Consultas.VistaLibros;
import Consultas.VistaPrestamos;
import Consultas.VistaSocios;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Socios.SociosaddPrestamos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MAIN extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MAIN frame = new MAIN();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MAIN() {
		setResizable(false);
		setTitle("Acceso a Datos Biblioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Secci\u00F3n de Socios");
		button.setMnemonic('u');
		button.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		button.setBounds(27, 82, 327, 48);
		contentPane.add(button);
                button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SociosaddPrestamos sap;
                            try {
                                sap = new SociosaddPrestamos();
                                sap.setVisible(true);
                            } catch (SQLException ex) {
                                Logger.getLogger(MAIN.class.getName()).log(Level.SEVERE, null, ex);
                            }
                               
				
			}
		});
		
		JButton button_1 = new JButton("Consultas Administrador");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
                            	VistaPrestamos VS;
                                VS = new VistaPrestamos();
                                VS.setVisible(true);   
			}
		});
		button_1.setMnemonic('n');
		button_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		button_1.setBounds(27, 199, 327, 48);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Consultas Socios");
		button_2.setMnemonic('r');
		button_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		button_2.setBounds(27, 323, 327, 48);
		contentPane.add(button_2);
                button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VistaSocios VS = new VistaSocios();
				VS.setVisible(true);
			}
		});
		
		JButton button_3 = new JButton("Consultas Libros");
		button_3.setMnemonic('i');
		button_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		button_3.setBounds(27, 441, 327, 48);
		contentPane.add(button_3);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VistaLibros VL = new VistaLibros();
				VL.setVisible(true);
			}
		});
                
                
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 670, 21);
		contentPane.add(menuBar);
		
		JMenu menu = new JMenu("Men\u00FA");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("Salir aplicaci\u00F3n");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ItemSalir();
				
			}
		});
		menuItem.setMnemonic('S');
		menu.add(menuItem);
	}

	
	
	public void ItemSalir(){
		
		JOptionPane.showMessageDialog(null, "Va a salir de la aplicación, gracias por utilizar nuestro servicio","¡Hasta Pronto!",JOptionPane.WARNING_MESSAGE);

		System.exit(0); //CIERRA LA APLICACIÓN
		
	}
	
	
	
}