package arreglo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import clase.Alumno;

public class ArregloAlumnos {
	
	//Atributo Privado
	private ArrayList <Alumno> alu;
	
	
	//Constructor
	public ArregloAlumnos() {
		//Se instancia y se crea el ArrayList
		alu = new ArrayList <Alumno>();
		//Cargar los alumnos del archivo txt al Jtable
		cargarAlumnos();
	}
	
	
	void cargarAlumnos() {
		//Leer de memoria y guardarlo en un archivo txt
		
				try {
					
					BufferedReader br;
					String linea;
					String [] s;
					
					//Para guardar los valores recuperados
					int codigo;
					String nombres, apePat, apeMat, correo;
					
					//Cargar el archivo en memoria modo lectura
					br = new BufferedReader(new FileReader("alumnos.txt"));
					
					while ((linea = br.readLine()) !=null) {
						//Dividemos la cadena y lo pasamos a un arreglo
						s = linea.split(";");
						
						//Recuperamos los valores del Arreglo
						codigo = Integer.parseInt(s [0].trim());
						nombres = s [1].trim();
						apePat = s [2].trim();
						apeMat = s [3].trim();
						correo = s [4].trim();
						
						//Adicionarlos al ArrayList
						adicionar(new Alumno(codigo,nombres,apePat,apeMat,correo));
					}
					
					br.close();			
				}
				catch (Exception e) {
					// TODO: handle exception
					System.out.println("Error: cargarAlumnos-->: "+ e.getMessage() );	
				}		
	}
	
	public void adicionar(Alumno x) {
		alu.add(x); //Incorporando en memoria
		grabarAlumnos (); //Llevar de memoria a archivo de texto
	}

	void grabarAlumnos() {
		//Leer el archivo de texto
				try {
					PrintWriter pw;
					String linea;
					Alumno x;
					
					//Creando el archivo
					pw = new PrintWriter(new FileWriter("alumnos.txt"));
					
					for (int i = 0; i < tamanio(); i++) {
						//Obtenermos la direccion de memoria
						x = obtener(i);
						//Pasamos los datos a una cadena
						linea = x.getCodigo() + ";"+
								x.getNombre() + ";"+
								x.getApePaterno() + ";"+
								x.getApeMaterno() + ";"+
								x.getNombre();
						
						//Incorporamos la linea a pw
						pw.println(linea);
					}
					//Cerramos el pw
					pw.close();
					
				}
				catch (Exception e) {
					// TODO: handle exception
					System.out.println("Error: grabarAlumnos-->: "+ e.getMessage() );
				}	
	}
	
	public int tamanio() {
		return alu.size();
				
	}
	
	public Alumno obtener(int i) {
		return alu.get(i);
	}
	
	public Alumno buscar(int codigo) {
		for (int i = 0; i < tamanio(); i++) {
			if(obtener(i).getCodigo() == codigo)
				return obtener(i);
		}
		return null;
	}
	
	public void  eliminar(Alumno x) {
		alu.remove(x); // Se elimina en memoria
		actualizarArchivo(); //Vuelve a pasar el contenido del ArrayList al archivo
	}
	
	public void actualizarArchivo() {
		
	}	
}
