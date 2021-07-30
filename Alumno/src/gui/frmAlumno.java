package gui;
 
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import arreglo.ArregloAlumnos;
import clase.Alumno;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class frmAlumno extends JFrame implements ActionListener, MouseListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel lblCodigo;
	private JLabel lblNombre;
	private JLabel lblApePat;
	private JLabel lblApeMat;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellidoPaterno;	
	private JTextField txtApellidoMaterno;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTable tblTabla;
	private DefaultTableModel modelo;
	private JLabel lblCorreo;
	private JTextField txtCorreo;
	
	//Declaracion Global
	ArregloAlumnos ae = new ArregloAlumnos();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmAlumno frame = new frmAlumno();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmAlumno() {
		setTitle("Ejemplo - Semana_12");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(10, 11, 40, 28);
		contentPane.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(54, 11, 40, 28);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(120, 11, 50, 28);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(170, 11, 60, 28);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		lblApePat = new JLabel("Apellido Paterno");
		lblApePat.setBounds(255, 11, 40, 28);
		contentPane.add(lblApePat);
		
		txtApellidoPaterno = new JTextField();
		txtApellidoPaterno.setBounds(295, 11, 40, 28);
		contentPane.add(txtApellidoPaterno);
		txtApellidoPaterno.setColumns(10);
		
		lblApeMat = new JLabel("Apellido Materno");
		lblApeMat.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApeMat.setBounds(358, 11, 40, 28);
		contentPane.add(lblApeMat);
		
		txtApellidoMaterno = new JTextField();
		txtApellidoMaterno.setBounds(402, 11, 40, 28);
		contentPane.add(txtApellidoMaterno);
		txtApellidoMaterno.setColumns(10);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(450, 50, 155, 23);
		contentPane.add(btnAdicionar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(450, 77, 155, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(450, 104, 155, 23);
		contentPane.add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 432, 274);
		contentPane.add(scrollPane);
		
		tblTabla = new JTable();
		tblTabla.addKeyListener(this);
		tblTabla.addMouseListener(this);
		scrollPane.setViewportView(tblTabla);
		tblTabla.setFillsViewportHeight(true);
		

		modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Nombres");
		modelo.addColumn("Apellido Paterno");
		modelo.addColumn("Apellido Materno");
		modelo.addColumn("Correo");
		tblTabla.setModel(modelo);
		
		lblCorreo = new JLabel("Correo");
		lblCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCorreo.setBounds(466, 11, 40, 28);
		contentPane.add(lblCorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(510, 11, 40, 28);
		contentPane.add(txtCorreo);
		
		listar();		
	}
 
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(arg0);
		}
	}
	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		/**
		 * Adiciona un nuevo alumno validando que el código no se repita
		 */	
		int codigo = leerCodigo();
		
		if(ae.buscar(codigo) == null) {
			//Recuperamos valores de la caja de texto
			String nombre = leerNombre();
			String apePat = leerApePat();
			String apeMat = leerApeMat();
			String correo = leerCorreo();
			
			//Crear el objeto alumno
			Alumno nuevo = new Alumno(codigo, nombre, apePat, apeMat, correo);
			
			//Adicionar al ArrayList y al achivo de texto
			ae.adicionar(nuevo);
			listar();
			limpieza();
			
			mensaje("Datos registrados con exito");
			
		}else {
			mensaje("El codigo de alumno ya existe");
		}
		
	 
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		int codigo = leerCodigo();
		if (ae.buscar(codigo) != null) {
		
		//Recuperamos valores de la caja de texto
		String nombre = leerNombre();
		String apePat = leerApePat();
		String apeMat = leerApeMat();
		String correo = leerCorreo();
		
		Alumno x = ae.buscar(codigo);
		x.setNombre(nombre);
		x.setApePaterno(apePat);
		x.setApeMaterno(apeMat);
		x.setCorreo(correo);
		
		ae.actualizarArchivo();		
		listar();
		limpieza();
		
		mensaje("Datos actualizados con exito");
		} else {
			mensaje("El codigo de alumno no existe");
		}
		 
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		/**
		 * Busca un código y si existe retira al alumno del arreglo
		 */	
		
		//Metodo para eliminar
		int codigo = leerCodigo();
		//Busca a el Alumno
		if (ae.buscar(codigo) == null) {
			//Si lo encuentra muesta un mensaje
			mensaje("El codigo no existe");
		} else {
			//Si lo encuentra
			Alumno x = ae.buscar(codigo);
			//Lo elimina
			ae.eliminar(x);
			listar();
			mensaje("El alumno fue eliminado con éxito");
		}		 
		limpieza();
	}
	//  Métodos tipo void (sin parámetros)
	void limpieza() {
		txtCodigo.setText("");
		txtNombre.setText("");
		txtApellidoPaterno.setText("");
		txtApellidoMaterno.setText("");
		txtCodigo.requestFocus();
	}	
   	void listar() {
		modelo.setRowCount(0);
		for (int i = 0; i < ae.tamanio(); i++) {
			Object []fila = {
					ae.obtener(i).getCodigo(),
					ae.obtener(i).getNombre(),
					ae.obtener(i).getApePaterno(),
					ae.obtener(i).getApeMaterno(),
					ae.obtener(i).getCorreo()
			};
			modelo.addRow(fila);
 		}
		 
	}
	//  Métodos tipo void (con parámetros)
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s);
	}
	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}
	//  Métodos que retornan valor (sin parámetros)
	int leerCodigo() {
		return Integer.parseInt(txtCodigo.getText().trim());
	}
	String leerNombre() {
		return txtNombre.getText().trim();
	}
	String leerApePat() {
		return txtApellidoPaterno.getText().trim();
	}
	String leerApeMat() {
		return txtApellidoMaterno.getText().trim();
	}
	String leerCorreo() {
		return txtCorreo.getText().trim();
	}
	
	void navegar(){

		int fila=tblTabla.getSelectedRow();
		txtCodigo.setText("" +tblTabla.getValueAt(fila, 0));
		txtNombre.setText("" +tblTabla.getValueAt(fila, 1));
		txtApellidoPaterno.setText("" +tblTabla.getValueAt(fila, 2));
		txtApellidoMaterno.setText("" +tblTabla.getValueAt(fila, 3));
		txtCorreo.setText("" +tblTabla.getValueAt(fila, 4));
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblTabla) {
			mouseClickedTblTabla(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedTblTabla(MouseEvent e) {
		navegar();
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == tblTabla) {
			keyReleasedTblTabla(e);
		}
	}
	public void keyTyped(KeyEvent e) {
	}
	protected void keyReleasedTblTabla(KeyEvent e) {
		navegar();
	}
}