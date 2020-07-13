import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.event.*;

class VentanaPuntaje extends JFrame implements ActionListener{

	private JLayeredPane panel;

	private JLabel jlFondo;
	private ImageIcon iFondo;
	
	//Titulos utilizados
	private JLabel jlTitulo_1;
	private ImageIcon iTitulo_1;

	//Botones
	private ImageIcon iRestart;
	private JButton btnReiniciar;
	private ImageIcon iMenu;
	private JButton btnMenu;

	private Sonido sn = new Sonido();

	//Area del puntaje
	private JTextArea txaPuntajesTotales;
	private ArrayList <String> puntajes;
	private JScrollPane scbar;

	private Archivo arch = new Archivo();

	private int puntos;
	private String usuario;
	private int mood;

	private String puntosSt;

	public VentanaPuntaje(String usuario,int puntos,int mood)
	{
		this.puntos = puntos;
		puntosSt = String.valueOf(puntos);

		this.usuario = usuario;

		this.mood = mood;

		puntajes = new ArrayList<String>();

		this.setTitle("SCORES");
		this.setSize(500,500);
		this.setLocationRelativeTo(null);

		componentes();

		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setResizable(false); //Evitar que se puede hacer mas pequena la ventana
		this.setVisible(true);

		//Escuchar los botones
		btnReiniciar.addActionListener(this);
		btnMenu.addActionListener(this);
	}

	private void componentes(){

		colocarFondo();
		colocarEtiquetas();
		colocarBotones();
		colocarAreas();
		
	}

	private void colocarFondo(){

		panel = new JLayeredPane();
		panel.setLayout(null);
		this.getContentPane().add(panel);

		//Imagen de fondo
		try{
			iFondo = new ImageIcon ("./imagenes/wallpaperPuntaje.png");
			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,500,500); //(x, y, w, h)
			jlFondo.setIcon(new ImageIcon(iFondo.getImage().getScaledInstance(jlFondo.getWidth(),jlFondo.getHeight(),Image.SCALE_SMOOTH)));
			jlFondo.setHorizontalAlignment(SwingConstants.CENTER);
		}catch(Exception e){
			System.out.println("Error al cargar imagen de fondo.");
		}

		panel.add(jlFondo,new Integer(1));
	}

 	private void colocarEtiquetas(){

 		//Titulo de la ventana
 		try{
			iTitulo_1 = new ImageIcon ("./imagenes/titulo_2.png");
			jlTitulo_1 = new JLabel();
			jlTitulo_1.setBounds(60, 10, 380, 80); //(x, y, w, h)
			jlTitulo_1.setIcon(new ImageIcon(iTitulo_1.getImage().getScaledInstance(jlTitulo_1.getWidth(),jlTitulo_1.getHeight(),Image.SCALE_SMOOTH)));
			jlTitulo_1.setHorizontalAlignment(SwingConstants.CENTER);
		}catch(Exception e){
			System.out.println("Error al cargar imagen.");
		} 

		//Agregar al panel
		panel.add(jlTitulo_1,new Integer(2));

 	}

 	private void colocarBotones(){

		//Boton para reiniciar el juego
		try{
			iRestart = new ImageIcon("./imagenes/restart.png");
			btnReiniciar = new JButton();
			btnReiniciar.setBounds(130, 420, 100, 40); //(x, y, w, h)
			btnReiniciar.setOpaque(true);
			btnReiniciar.setBackground(Color.BLUE);
			btnReiniciar.setIcon(new ImageIcon(iRestart.getImage().getScaledInstance(btnReiniciar.getWidth(),btnReiniciar.getHeight(),Image.SCALE_SMOOTH)));
		}catch(Exception e){
			System.out.println("Error al cargar imgaen de boton.");
		}

		//Boton para ir al menu
		try{
			iMenu = new ImageIcon("./imagenes/menu.png");
			btnMenu = new JButton();
			btnMenu.setBounds(270, 420, 100, 40); //(x, y, w, h)
			btnMenu.setOpaque(true);
			btnMenu.setBackground(Color.WHITE);
			btnMenu.setIcon(new ImageIcon(iMenu.getImage().getScaledInstance(btnMenu.getWidth(),btnMenu.getHeight(),Image.SCALE_SMOOTH)));
		}catch(Exception e){
			System.out.println("Error al cargar imgaen de boton.");
		}
		
		//Agregar botones al panel
		panel.add(btnReiniciar,new Integer(4));
		panel.add(btnMenu,new Integer(5));
 	}

 	private void colocarAreas(){

 		//Mostras los puntajes obtenidos
 		txaPuntajesTotales = new JTextArea(); //Los puntajes
		txaPuntajesTotales.setBounds(50,105,400,300);
		
		try{
			arch.guardarPuntos(usuario,puntosSt);
		}catch(Exception e){
			System.out.println("Error al cargar jugador o puntos.");
		}
		leerTodo();

		txaPuntajesTotales.setFont(new Font("Berlin Sans FB",0,18));
		txaPuntajesTotales.setForeground(Color.WHITE);
		txaPuntajesTotales.setBackground(Color.BLACK);
		txaPuntajesTotales.setEditable(false);

		//Scrollbar
		scbar = new JScrollPane();
		scbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scbar.setBounds(50,105,400,300);
		scbar.getViewport().add(txaPuntajesTotales);

		//Agregar al panel
		panel.add(scbar,new Integer(3));
 	}


	private void leerTodo(){

		puntajes = arch.leerTodo("puntajes.txt");
			
		String pts = "";

		for (int i=0; i<puntajes.size(); i++) 
		{
			pts = pts + puntajes.get(i) + "\n";
		}

		txaPuntajesTotales.setText(pts);
		txaPuntajesTotales.setEditable(false);	
	}

	public void actionPerformed(ActionEvent event){

		if(event.getSource() == this.btnReiniciar){
			
			sn.iniciar();
			sn.reproducir(68,1,200);
			sn.finalizar();

			dispose();
			if(mood == 1){
				QuizSolo qZ = new QuizSolo(usuario);
			}else{
				QuizMulti qM = new QuizMulti(usuario);
			}
		}

		if(event.getSource() == this.btnMenu){

			sn.iniciar();
			sn.reproducir(68,1,200);
			sn.finalizar();

			dispose();
			VentanaPrincipal vP = new VentanaPrincipal(usuario);
		}
	}

}