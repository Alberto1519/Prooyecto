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

	private ImageIcon iPregunta;
	private JLabel jlPregunta;

	private JTextField txtRespuesta;

	private ImageIcon iAvanzar;
	private JButton btnAvanzar;

	private String usuario;
	private String respuesta;
	private int puntos;

	private int i = 0;
	private int canQ = 10;
	private int rango = 31;
    private int[] preguntaRandom = new int[canQ+1];
    private int[] respuestaRandom = new int[canQ+1];

 	private int contadorP = 0;

	public QuizSolo(String usuario){

		this.usuario = usuario;

		this.setTitle("QUIZ SOLO");
		this.setSize(563,750); //Tamano de la ventana (x, y)
		this.setLocationRelativeTo(null); //Ventana en el centro

		componentes();//Agregar todos los componentes a la ventana

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false); //Evitar que se puede hacer mas pequena la ventana
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
			jlFondo.setBounds(0,0,563,750); //(x, y, w, h)
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
			jlTitulo.setBounds(201,20,160,120); //(x, y, w, h)
			jlTitulo.setIcon(new ImageIcon(iTitulo.getImage().getScaledInstance(jlTitulo.getWidth(),jlTitulo.getHeight(),Image.SCALE_SMOOTH)));
			jlTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		}catch(Exception e){
			System.out.println("Error al cargar imagen.");
		}

		//Pregunta
		try{
			iPregunta = new ImageIcon ("./imagenes/Preguntas/instrucciones.png");
			jlPregunta = new JLabel();
			jlPregunta.setBounds(55,165,450,365); //(x, y, w, h)
			jlPregunta.setIcon(new ImageIcon(iPregunta.getImage().getScaledInstance(jlPregunta.getWidth(),jlPregunta.getHeight(),Image.SCALE_SMOOTH)));
			jlPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		}catch(Exception e){
			System.out.println("Error al cargar imagen.");
		}

		//Area para responder
		txtRespuesta = new JTextField();
		txtRespuesta.setBounds(80,550,400,50);
		txtRespuesta.setBackground(Color.WHITE);
		txtRespuesta.setFont(new Font("Berlin Sans FB",0,30));
		txtRespuesta.setHorizontalAlignment(SwingConstants.CENTER); 

		//Agregar al panel
		panel.add(jlTitulo,new Integer(2));
		panel.add(jlPregunta,new Integer(3));
		panel.add(txtRespuesta,new Integer(4));
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

       preguntaRandom[i] = (int)(Math.random()*rango+1);
       for(i = 0; i < canQ; i++){

          preguntaRandom[i] = (int)(Math.random()*rango+1);
          
          for(int j=0; j<i; j++)
          {
            if(preguntaRandom[i]==preguntaRandom[j])
            {
              i--;
            }
          }
       	}
       	
       	respuestaRandom = preguntaRandom; 
    }

    private void compararRespuestas(String respuesta){

    	ArrayList<String>contenido = new ArrayList<String>();
    	contenido = Archivo.leerTodo("preguntas.txt");
    	ArrayList<String>respuestas = new ArrayList<String>();
      	respuestas = Archivo.leerTodo("respuestas.txt");
      	
    	int renglon=0;
    	int renglonR = 0;

    	for(String p:contenido){

    		renglon=renglon+1;

    		if(renglon==preguntaRandom[contadorP]){

    			try{
						iPregunta = new ImageIcon ("./imagenes/Preguntas/"+p);
						jlPregunta.setIcon(new ImageIcon(iPregunta.getImage().getScaledInstance(jlPregunta.getWidth(),jlPregunta.getHeight(),Image.SCALE_SMOOTH)));
						jlPregunta.setHorizontalAlignment(SwingConstants.CENTER);
					}catch(Exception e){
						System.out.println("Error al cargar imagen.");
				}	
    		}
    	}

    	if(contadorP>0){
    		for(String q:respuestas){

    		renglonR = renglonR + 1;

    		if(renglonR==respuestaRandom[contadorP-1]){
    			
				if(respuesta.equalsIgnoreCase(q)){

	    			puntos = puntos + 1;
	    		}
    		}
    	}
    	}
    }

	//Escuchar las opciones
	public void actionPerformed(ActionEvent event){

		if(event.getSource() == this.btnAvanzar){

			String respuesta = txtRespuesta.getText().toUpperCase();

				if(contadorP >= 10){
	    		
	    			VentanaPuntaje vP = new VentanaPuntaje(usuario,puntos);
	    			dispose();
	    		}

	    	compararRespuestas(respuesta);
	
        	contadorP = contadorP+1;
	    	txtRespuesta.setText("");
		}
	}
}