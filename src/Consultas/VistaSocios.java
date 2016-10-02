package Consultas;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import conexiones.conexion;
import java.sql.SQLException;

public class VistaSocios extends JDialog
{
        
	DefaultTableModel dtm;
	conexion c = new conexion();
	public JPanel contentPane;
	public JTable table;
	public JRadioButton rbtnApellidos, rbtnNombre;
	public JButton btnFiltrar, btnLimpiarTabla;

	ButtonGroup datos=new ButtonGroup();
	public JTextField textBusqueda;
        public JButton btnMostrar;

	/**
	 * Create the frame.
	 */
	public VistaSocios() 
	{
		setModal(true);
		
		setResizable(false);
		setTitle("Tabla Socios");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 483, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 138, 461, 121);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblSeleccioneElCampo = new JLabel("Seleccione el campo el que desea introducir:");
		lblSeleccioneElCampo.setBounds(32, 11, 539, 14);
		contentPane.add(lblSeleccioneElCampo);
		
		rbtnNombre = new JRadioButton("Nombre");
		rbtnNombre.setBounds(45, 45, 109, 23);
		contentPane.add(rbtnNombre);
		datos.add(rbtnNombre);
		
		rbtnApellidos = new JRadioButton("Apellidos");
		rbtnApellidos.setBounds(45, 87, 109, 23);
		contentPane.add(rbtnApellidos);
		datos.add(rbtnApellidos);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				btnCancelar();
			}
		});
		btnCancelar.setBounds(357, 328, 89, 23);
		contentPane.add(btnCancelar);
		
		btnLimpiarTabla = new JButton("Limpiar tabla");
		btnLimpiarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				limpiarTabla();
			}
		});
		btnLimpiarTabla.setBounds(30, 270, 109, 23);
		contentPane.add(btnLimpiarTabla);
		
		btnFiltrar = new JButton("Filtrar Busqueda");
		btnFiltrar.setBounds(270, 62, 132, 23);
		contentPane.add(btnFiltrar);
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				FiltrarBusqueda();
			}
		});
                
		textBusqueda = new JTextField();
		textBusqueda.setBounds(150, 63, 101, 20);
		contentPane.add(textBusqueda);
		textBusqueda.setColumns(10);
	}
	
	
	protected void limpiarTabla() 
	{
		DefaultTableModel tb=(DefaultTableModel) table.getModel();
		int filas=table.getRowCount();
		
		for(int i=1;i<=filas;i++)
		{
			tb.removeRow(0);
		}
	}

	protected void btnCancelar()
	{
		this.dispose();
	}
        
        
        public void FiltrarBusqueda(){
            
            if(rbtnNombre.isSelected())
			{
				textBusqueda.getText();
				
				try{
			Statement sql=c.getConexion().createStatement();
			ResultSet resulSql=sql.executeQuery("select * from socios where nombre like '%"+textBusqueda.getText()+"%'");
			DefaultTableModel m;
			
                        String titulos[] = {"Id Socios", "Nombre","Apellidos","Edad", "Dirección","Telefono","Alquiler"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			table.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String fila[]=new String[titulos.length];
			table.setModel(m);
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
				table.getColumnModel().getColumn(i).setCellRenderer(tcr);
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla");
		}
			} else if (rbtnApellidos.isSelected()){
                            
                            	textBusqueda.getText();
				
				try{
			Statement sql=c.getConexion().createStatement();
			ResultSet resulSql=sql.executeQuery("select * from socios where apellidos like '%"+textBusqueda.getText()+"%'");
			DefaultTableModel m;
			
                        String titulos[] = {"Id Socios", "Nombre","Apellidos","Edad", "Dirección","Telefono","Alquiler"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			table.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String fila[]=new String[titulos.length];
			table.setModel(m);
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
				table.getColumnModel().getColumn(i).setCellRenderer(tcr);
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla");
		}
                            
                            
                        }
            
            
        }
        
        
}