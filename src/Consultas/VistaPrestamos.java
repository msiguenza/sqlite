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

public class VistaPrestamos extends JDialog
{
        
	DefaultTableModel dtm;
	conexion c = new conexion();
	public JPanel contentPane;
	public JTable table;
	public JRadioButton rbtnLibrosPorSocio, rbtnlibrosPrestAct;
	public JButton btnFiltrar, btnLimpiarTabla;

        public JRadioButton rdbtnLibrosExcedeFecha;
        public JRadioButton rdbtnSociosExcedeFecha;
        
	ButtonGroup datos=new ButtonGroup();
	public JTextField textBusqueda;
        public JButton btnMostrar;
        private JLabel lblCuandoSeaNecesario;
       

	/**
	 * Create the frame.
	 */
	public VistaPrestamos() 
	{
		setModal(true);
		
		setResizable(false);
		setTitle("Tabla Prestamos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 565, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 230, 461, 121);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblSeleccioneElCampo = new JLabel("Seleccione el campo el que desea introducir");
		lblSeleccioneElCampo.setBounds(32, 11, 346, 14);
		contentPane.add(lblSeleccioneElCampo);
		
		rbtnlibrosPrestAct = new JRadioButton("Libros Prestados Actualmente");
		rbtnlibrosPrestAct.setBounds(45, 45, 206, 23);
		contentPane.add(rbtnlibrosPrestAct);
		datos.add(rbtnlibrosPrestAct);
		
		rbtnLibrosPorSocio = new JRadioButton("N\u00BA Libros por Socio");
		rbtnLibrosPorSocio.setBounds(45, 87, 164, 23);
		contentPane.add(rbtnLibrosPorSocio);
		datos.add(rbtnLibrosPorSocio);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				btnCancelar();
			}
		});
		btnCancelar.setBounds(420, 418, 89, 23);
		contentPane.add(btnCancelar);
		
		btnLimpiarTabla = new JButton("Limpiar tabla");
		btnLimpiarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				limpiarTabla();
			}
		});
		btnLimpiarTabla.setBounds(48, 362, 109, 23);
		contentPane.add(btnLimpiarTabla);
		
		btnFiltrar = new JButton("Filtrar Busqueda");
		btnFiltrar.setBounds(377, 87, 132, 23);
		contentPane.add(btnFiltrar);
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				FiltrarBusqueda();
			}
		});
                
		textBusqueda = new JTextField();
		textBusqueda.setBounds(377, 46, 132, 30);
		contentPane.add(textBusqueda);
		textBusqueda.setColumns(10);
		
		rdbtnLibrosExcedeFecha = new JRadioButton("Libros Excede Fecha");
		rdbtnLibrosExcedeFecha.setBounds(48, 130, 164, 23);
		contentPane.add(rdbtnLibrosExcedeFecha);
                datos.add(rdbtnLibrosExcedeFecha);
		
		rdbtnSociosExcedeFecha = new JRadioButton("Socios Excede Fecha");
		rdbtnSociosExcedeFecha.setBounds(48, 173, 164, 23);
		contentPane.add(rdbtnSociosExcedeFecha);
                datos.add(rdbtnSociosExcedeFecha);

		
		JLabel lblIntroduzca = new JLabel("Introduzca  ID");
		lblIntroduzca.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduzca.setBounds(377, 11, 132, 14);
		contentPane.add(lblIntroduzca);
		
		lblCuandoSeaNecesario = new JLabel("Cuando Sea necesario");
		lblCuandoSeaNecesario.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuandoSeaNecesario.setBounds(365, 32, 158, 14);
		contentPane.add(lblCuandoSeaNecesario);
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
            
            if(rbtnlibrosPrestAct.isSelected())
			{
				
				try{
			Statement sql=c.getConexion().createStatement();
			ResultSet resulSql=sql.executeQuery("select * from libros where disponible=1");
			DefaultTableModel m;
			
            String titulos[] = {"Id Libro", "Titulo","Nº Ejemplares","Editorial", "Nº Paginas","Año Edición","Disponible"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			table.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String fila[]=new String[titulos.length];
			table.setModel(m);
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
				table.getColumnModel().getColumn(i).setCellRenderer(tcr);
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla");
		}
			} else if (rbtnLibrosPorSocio.isSelected()){
                            
                            	textBusqueda.getText();
				
				try{
			Statement sql=c.getConexion().createStatement();
			ResultSet resulSql=sql.executeQuery("select count(*) from prestamos where idsocios="+textBusqueda.getText()+"");
			DefaultTableModel m;
			
                        String titulos[] = {"Nº Libros por Socio"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			table.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String fila[]=new String[titulos.length];
			table.setModel(m);
			while(resulSql.next()){
					fila[0]=resulSql.getString("count(*)");
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
                                 
                } else if (rdbtnLibrosExcedeFecha.isSelected()){
                        			
			try{
			Statement sql=c.getConexion().createStatement();
			ResultSet resulSql=sql.executeQuery("select idlibro from prestamos where fechafin>2013/11/3");
			DefaultTableModel m;
			
                        String titulos[] = {"Libros que han superado la Fecha de Fin de Prestamo"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			table.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String fila[]=new String[titulos.length];
			table.setModel(m);
			while(resulSql.next()){
					fila[0]=resulSql.getString("idlibro");
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
                    
                    
                            
                        } else if (rdbtnSociosExcedeFecha.isSelected()){
                            
                            	try{
			Statement sql=c.getConexion().createStatement();
			ResultSet resulSql=sql.executeQuery("select idsocios from prestamos where fechafin>2013/11/3");
			DefaultTableModel m;
			
                        String titulos[] = {"Libros que han superado la Fecha de Fin de Prestamo"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			table.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String fila[]=new String[titulos.length];
			table.setModel(m);
			while(resulSql.next()){
					fila[0]=resulSql.getString("idsocios");
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
        
        