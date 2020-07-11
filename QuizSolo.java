import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;
import java.util.*;

class QuizSolo extends JFrame implements ActionListener{

	private JLayeredPane panel;

	private ImageIcon iFondo;
	private JLabel jlFondo;

	private ImageIcon iTitulo;
	private JLabel jlTitulo;

	private JLabel jlPregunta;

	private JTextField txtRespuesta;

	private ImageIcon iAvanzar;
	private JButton btnAvanzar;

	private String usuario;
	private String respuesta;

	private int cantidadPreguntas=14;
    private int preguntaRandom[] = new int [cantidadPreguntas+1]; // Lista de numeros random 
 	private int contadorPregunta=0;
 	private int renglon=0; //Contador de renglones
 	private ArrayList<String>contenido = new ArrayList<String>();

	public QuizSolo(String usuario){

		this.usuario = usuario;

		this.setTitle("QUIZ SOLO");
		this.setSize(563,1000); //Tamano de la ventana (x, y)
		this.setLocationRelativeTo(null); //Ventana en el centro

		componentes();//Agregar todos los componentes a la ventana

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true); //Evitar que se puede hacer mas pequena la ventana
		this.setVisible(true);
	}

	private void componentes(){

		colocarFondo();
		colocarEtiquetas();
		colocarBotones();
		crearPreguntas();
	}

	private void colocarFondo(){

		panel = new JLayeredPane();
		panel.setLayout(null);
		this.getContentPane().add(panel);

		//Imagen de fondo
		try{
			iFondo = new ImageIcon ("./imagenes/wallpaperSolo.png");
			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,563,1000); //(x, y, w, h)
			jlFondo.setIcon(new ImageIcon(iFondo.getImage().getScaledInstance(jlFondo.getWidth(),jlFondo.getHeight(),Image.SCALE_SMOOTH)));
			jlFondo.setHorizontalAlignment(SwingConstants.CENTER);
		}catch(Exception e){
			System.out.println("Error al cargar imagen de fondo.");
		}

		panel.add(jlFondo,new Integer(1));
	}

	private void colocarEtiquetas(){

		//Titulo principal
		try{
			iTitulo = new ImageIcon ("./imagenes/TituloSolo.png");
			jlTitulo = new JLabel();
			jlTitulo.setBounds(181,20,200,160); //(x, y, w, h)
			jlTitulo.setIcon(new ImageIcon(iTitulo.getImage().getScaledInstance(jlTitulo.getWidth(),jlTitulo.getHeight(),Image.SCALE_SMOOTH)));
			jlTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		}catch(Exception e){
			System.out.println("Error al cargar imagen.");
		}

		//Pregunta
		jlPregunta = new JLabel("Presione READY para iniciar");
		jlPregunta.setBounds(55,200,450,30);
		jlPregunta.setOpaque(false);
		jlPregunta.setForeground(Color.WHITE);
		jlPregunta.setFont(new Font("Berlin Sans FB",0,20));
		jlPregunta.setHorizontalAlignment(SwingConstants.CENTER); 

		//Imagen de la pregunta
		txtRespuesta = new JTextField();
		txtRespuesta.setBounds(80,500,400,50);
		txtRespuesta.setBackground(Color.WHITE);
		txtRespuesta.setFont(new Font("Berlin Sans FB",0,30));
		txtRespuesta.setHorizontalAlignment(SwingConstants.CENTER); 

		//Agregar al panel
		panel.add(jlTitulo,new Integer(2));
		panel.add(jlPregunta,new Integer(3));
		panel.add(txtRespuesta,new Integer(4));
		//Falta JLbael de la imagen de la pregunta
	}

	private void colocarBotones(){

		//Boton de ventana solitario
		try{
			iAvanzar = new ImageIcon("./imagenes/ready.png");
			btnAvanzar = new JButton();
			btnAvanzar.setBounds(157,600,250,100); //(x, y, w, h)
			btnAvanzar.setIcon(new ImageIcon(iAvanzar.getImage().getScaledInstance(btnAvanzar.getWidth(),btnAvanzar.getHeight(),Image.SCALE_SMOOTH)));
			btnAvanzar.setOpaque(false);
			btnAvanzar.setContentAreaFilled(false);
			btnAvanzar.setBorderPainted(false);
		}catch(Exception e){
			System.out.println("Error al cargar imgaen de boton.");
		}

		//Agregar boton al panel
		panel.add(btnAvanzar,new Integer(6)); 

		//Escuchar las acciones del boton de inicio
		btnAvanzar.addActionListener(this);
	}

	private void crearPreguntas(){

		//Seleccion de la pregunta
     	contenido = Archivo.leerTodo("preguntas.txt");
      	ArrayList<String>respuestas = new ArrayList<String>();
      	respuestas = Archivo.leerTodo("respuestas.txt");
      
      	int i=0;
     	int k=0;
            
       for(i=0; i<cantidadPreguntas; i++) 
        {
          preguntaRandom[i] = (int)(Math.random()*cantidadPreguntas);
          for(int j=0; j<i; j++)
          {
            if(preguntaRandom[i]==preguntaRandom[j])
            {
              i--;
            }
          }
        }
      }

	//Escuchar las opciones
	public void actionPerformed(ActionEvent event){
		ArrayList<String>contenido = new ArrayList<String>();
        contenido = Archivo.leerTodo("preguntas.txt");
	    ArrayList<String>respuestas = new ArrayList<String>();
	    respuestas = Archivo.leerTodo("respuestas.txt");
    	int renglon=0;
	    if(event.getSource() == this.btnAvanzar){
	    	contadorPregunta = contadorPregunta+1;
	    	//System.out.println(preguntaRandom[contadorPregunta]);
	   for(String q:respuestas){
        renglon=renglon+1;
        if(renglon==preguntaRandom[preguntaRandom[contadorPregunta]]){
        	System.out.println(q);
       			}
       			for(String p:contenido){
        		if(renglon==preguntaRandom[preguntaRandom[contadorPregunta]]){
          jlPregunta.setText(p); 
      }
  }
    		}
		}
	}
}
