import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;

class VentanaPrincipal extends JFrame implements ActionListener{

	private JLayeredPane panel;

	private Musica ms;

	private ImageIcon iFondo;
	private JLabel jlFondo;

	private ImageIcon iTitulo;
	private JLabel jlTitulo;

	private ImageIcon iElegir;
	private JLabel jlElegir;

	private ImageIcon iSolitario;
	private JButton btnSolitario;

	private ImageIcon iMulti;
	private JButton btnMulti;

	private String usuario;

	public VentanaPrincipal(String usuario){

		this.usuario = usuario;

		ms = new Musica("./musica/BlueMonday.wav");
		ms.playMusic();

		this.setTitle("BACK...");
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
			iTitulo = new ImageIcon ("./imagenes/back.png");
			jlTitulo = new JLabel();
			jlTitulo.setBounds(352,7,281,153); //(x, y, w, h)
			jlTitulo.setIcon(new ImageIcon(iTitulo.getImage().getScaledInstance(jlTitulo.getWidth(),jlTitulo.getHeight(),Image.SCALE_SMOOTH)));
			jlTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		}catch(Exception e){
			System.out.println("Error al cargar imagen.");
		}

		//Titulo de seleccion
		try{
			iElegir = new ImageIcon ("./imagenes/choose.png");
			jlElegir = new JLabel();
			jlElegir.setBounds(396,165,193,80); //(x, y, w, h)
			jlElegir.setIcon(new ImageIcon(iElegir.getImage().getScaledInstance(jlElegir.getWidth(),jlElegir.getHeight(),Image.SCALE_SMOOTH)));
			jlElegir.setHorizontalAlignment(SwingConstants.CENTER);
		}catch(Exception e){
			System.out.println("Error al cargar imagen.");
		}  

		//Agregar al panel
		panel.add(jlTitulo,new Integer(2));
		panel.add(jlElegir,new Integer(3));
	}

	private void colocarBotones(){

		//Boton de ventana solitario
		try{
			iSolitario = new ImageIcon("./imagenes/single.png");
			btnSolitario = new JButton();
			btnSolitario.setBounds(140,255,254,180); //(x, y, w, h)
			btnSolitario.setIcon(new ImageIcon(iSolitario.getImage().getScaledInstance(btnSolitario.getWidth(),btnSolitario.getHeight(),Image.SCALE_SMOOTH)));
			btnSolitario.setOpaque(false);
			btnSolitario.setContentAreaFilled(false);
			btnSolitario.setBorderPainted(false);
		}catch(Exception e){
			System.out.println("Error al cargar imgaen de boton.");
		}

		//Boton de ventana multijugador
		try{
			iMulti = new ImageIcon("./imagenes/multiplayer.png");
			btnMulti = new JButton();
			btnMulti.setBounds(530,265,354,220); //(x, y, w, h)
			btnMulti.setIcon(new ImageIcon(iMulti.getImage().getScaledInstance(btnMulti.getWidth(),btnMulti.getHeight(),Image.SCALE_SMOOTH)));
			btnMulti.setOpaque(false);
			btnMulti.setContentAreaFilled(false);
			btnMulti.setBorderPainted(false);
		}catch(Exception e){
			System.out.println("Error al cargar imgaen de boton.");
		}

		//Agregar boton al panel
		panel.add(btnSolitario,new Integer(4)); 
		panel.add(btnMulti,new Integer(5)); 

		//Escuchar las acciones del boton de inicio
		btnSolitario.addActionListener(this);
		btnMulti.addActionListener(this);
	}


	//Escuchar las opciones
	public void actionPerformed(ActionEvent event){

		if(event.getSource() == this.btnSolitario)
		{
			QuizSolo qS = new QuizSolo(usuario);
			dispose();
			ms.StopPlaying();
		}

		if(event.getSource() == this.btnMulti)
		{
			QuizMulti qM = new QuizMulti(usuario);
			dispose();
			ms.StopPlaying();
		}
	}
}	