import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;

class VentanaPrincipal extends JFrame implements ActionListener{

	JLayeredPane panel;

	Musica ms;

	ImageIcon iFondo;
	JLabel jlFondo;

	ImageIcon iTitulo;
	JLabel jlTitulo;
	
	ImageIcon iPreguntas;
	JButton btnPreguntas;

	ImageIcon iCarreras;
	JButton btnCarreras;


	public VentanaPrincipal(){

		ms = new Musica("./musica/BlueMonday.wav");
		ms.playMusic();

		this.setTitle("VIDEOGAMES ...");
		this.setSize(985,550); //Tamano de la ventana (x, y)
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
	}

	private void colocarFondo(){

		panel = new JLayeredPane();
		panel.setLayout(null);
		this.getContentPane().add(panel);

		//Imagen de fondo

		try{
			iFondo = new ImageIcon ("./imagenes/wallpaper.gif");
			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,985,550); //(x, y, w, h)
			jlFondo.setIcon(iFondo);
			iFondo.setImageObserver(jlFondo);
		}catch(Exception e){
			System.out.println("Error al cargar imagen de fondo.");
		}

		panel.add(jlFondo,new Integer(1));
	}

	private void colocarEtiquetas(){

		//Titulo principal
		try{
			iTitulo = new ImageIcon ("./imagenes/tituloP.png");
			jlTitulo = new JLabel();
			jlTitulo.setBounds(200,5,585,125); //(x, y, w, h)
			jlTitulo.setIcon(new ImageIcon(iTitulo.getImage().getScaledInstance(jlTitulo.getWidth(),jlTitulo.getHeight(),Image.SCALE_SMOOTH)));
			jlTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		}catch(Exception e){
			System.out.println("Error al cargar imagen.");
		} 
		
		//Agregar al panel
		panel.add(jlTitulo,new Integer(2));
	}

	private void colocarBotones(){
		
		//Boton de ventana Preguntas
		try{
			iPreguntas = new ImageIcon("./imagenes/questions.png");
			btnPreguntas = new JButton();
			btnPreguntas.setBounds(47,200,400,300); //(x, y, w, h)
			btnPreguntas.setIcon(new ImageIcon(iPreguntas.getImage().getScaledInstance(btnPreguntas.getWidth(),btnPreguntas.getHeight(),Image.SCALE_SMOOTH)));
			btnPreguntas.setOpaque(true);
			btnPreguntas.setBorder(null);
		}catch(Exception e){
			System.out.println("Error al cargar imgaen de boton.");
		}

		//Agregar boton al panel
		panel.add(btnPreguntas,new Integer(3)); 

		//Escuchar las acciones del boton de inicio
		btnPreguntas.addActionListener(this);
	}


	//Escuchar las opciones
	public void actionPerformed(ActionEvent event){
		
		if(event.getSource() == this.btnPreguntas)
		{
			
			this.setVisible(false);
			ms.StopPlaying();
		}

		if(event.getSource() == this.btnCarreras)
		{
			
			this.setVisible(false);
			ms.StopPlaying();
		}
	}
}
	