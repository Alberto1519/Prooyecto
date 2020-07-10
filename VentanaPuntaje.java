import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.event.*;

class VentanaPuntaje extends JFrame implements ActionListener{

	JPanel panel;
	
	//Titulos utilizados
	JLabel jlTitulo_1;
	ImageIcon iTitulo_1;

	//Botones
	ImageIcon iRestart;
	JButton btnReiniciar;
	ImageIcon iMenu;
	JButton btnMenu;

	//Area del puntaje
	JTextArea txaPuntajesTotales;
	ArrayList <String> puntajes;
	JScrollPane scbar;

	Archivo arch = new Archivo();

	int puntos;
	String usuario;

	String puntosSt;

	public VentanaPuntaje(String usuario,int puntos)
	{
		this.puntos = puntos;
		puntosSt = String.valueOf(puntos);

		this.usuario = usuario;

		puntajes = new ArrayList<String>();

		this.setTitle("SCORES");
		this.setBounds(150,100,480, 545);

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

		panel = new JPanel();
		panel.setLayout(null);
		this.getContentPane().add(panel);
	}

 	private void colocarEtiquetas(){

 		//Titulo de la ventana
 		try{
			iTitulo_1 = new ImageIcon ("./imagenes/titulo_2.png");
			jlTitulo_1 = new JLabel();
			jlTitulo_1.setBounds(50, 10, 380, 80); //(x, y, w, h)
			jlTitulo_1.setIcon(new ImageIcon(iTitulo_1.getImage().getScaledInstance(jlTitulo_1.getWidth(),jlTitulo_1.getHeight(),Image.SCALE_SMOOTH)));
			jlTitulo_1.setHorizontalAlignment(SwingConstants.CENTER);
		}catch(Exception e){
			System.out.println("Error al cargar imagen.");
		} 

		//Agregar al panel
		panel.add(jlTitulo_1);

 	}

 	private void colocarBotones(){

		//Boton para reiniciar el juego
		try{
			iRestart = new ImageIcon("./imagenes/restart.png");
			btnReiniciar = new JButton();
			btnReiniciar.setBounds(120, 450, 100, 40); //(x, y, w, h)
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
			btnMenu.setBounds(260, 450, 100, 40); //(x, y, w, h)
			btnMenu.setOpaque(true);
			btnMenu.setBackground(Color.WHITE);
			btnMenu.setIcon(new ImageIcon(iMenu.getImage().getScaledInstance(btnMenu.getWidth(),btnMenu.getHeight(),Image.SCALE_SMOOTH)));
		}catch(Exception e){
			System.out.println("Error al cargar imgaen de boton.");
		}
		
		//Agregar botones al panel
		panel.add(btnReiniciar);
		panel.add(btnMenu);
 	}

 	private void colocarAreas(){

 		//Mostras los puntajes obtenidos
 		txaPuntajesTotales = new JTextArea(); //Los puntajes
		txaPuntajesTotales.setBounds(50,90,380,300);
		
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
		scbar.setBounds(50,90,380,300);
		scbar.getViewport().add(txaPuntajesTotales);

		//Agregar al panel
		panel.add(scbar);
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
			dispose();
			QuizSolo qZ = new QuizSolo(usuario);
		}

		if(event.getSource() == this.btnMenu){
			dispose();
			VentanaPrincipal vP = new VentanaPrincipal(usuario);
		}
	}

}