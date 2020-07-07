import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;

class QuizSolo extends JFrame implements ActionListener{

	private JLayeredPane panel;

	private ImageIcon iFondo;
	private JLabel jlFondo;

	private ImageIcon iTitulo;
	private JLabel jlTitulo;

	private ImageIcon iAvanzar;
	private JButton btnAvanzar;

	private String usuario;

	public QuizSolo(String usuario){

		this.usuario = usuario;

		this.setTitle("QUIZ SOLO");
		this.setSize(563,1000); //Tamano de la ventana (x, y)
		this.setLocationRelativeTo(null); //Ventana en el centro

		componentes();//Agregar todos los componentes a la ventana

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false); //Evitar que se puede hacer mas pequena la ventana
		this.setVisible(true);
	}

	private void componentes(){

		colocarFondo();
		colocarEtiquetas();
		//colocarBotones();
	}

	private void colocarFondo(){

		panel = new JLayeredPane();
		//panel.setLayout(null);
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
		 

		//Agregar al panel
		panel.add(jlTitulo,new Integer(2));
	}

	private void colocarBotones(){

		//Boton de ventana solitario
		try{
			iAvanzar = new ImageIcon("./imagenes/.png");
			btnAvanzar = new JButton();
			btnAvanzar.setBounds(140,255,254,180); //(x, y, w, h)
			btnAvanzar.setIcon(new ImageIcon(iAvanzar.getImage().getScaledInstance(btnAvanzar.getWidth(),btnAvanzar.getHeight(),Image.SCALE_SMOOTH)));
			btnAvanzar.setOpaque(false);
			btnAvanzar.setContentAreaFilled(false);
			btnAvanzar.setBorderPainted(false);
		}catch(Exception e){
			System.out.println("Error al cargar imgaen de boton.");
		}

		//Agregar boton al panel
		panel.add(btnAvanzar,new Integer(4)); 

		//Escuchar las acciones del boton de inicio
		btnAvanzar.addActionListener(this);
	}


	//Escuchar las opciones
	public void actionPerformed(ActionEvent event){

		if(event.getSource() == this.btnAvanzar)
		{
			
		}
	}
}