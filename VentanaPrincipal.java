import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.util.*;
import java.net.*;
import java.lang.Thread;

class VentanaPrincipal extends JFrame implements ActionListener{

	//Atributos de la Ventna
	private JLayeredPane panel;
	private ImageIcon iFondo;
	private JLabel jlFondo;
	private ImageIcon iTitulo;
	private JLabel jlTitulo;
	private ImageIcon iUsuario;
	private JLabel jlUsuario;
    private JTextField txtUsuario;
	private ImageIcon iIP;
	private JLabel jlIP;
	private JTextField txtIP;
	private ImageIcon iPuerto;
	private JLabel jlPuerto;
	private JTextField txtPuerto;
	private ImageIcon iConectar;
	private JButton btnConectar;
	private Musica ms;

	//Atributos para conectar al sevidor
	private Socket servidor;                   
    private PrintWriter out;
    private String ip;
    private int puerto;
    private String usuario;
    private String mensaje;

    private boolean conectado = false;
    private Thread t;

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
		colocarTxt();
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

		//Titulo usuario
		try{
			iUsuario = new ImageIcon ("./imagenes/user.png");
			jlUsuario = new JLabel();
			jlUsuario.setBounds(50,175,150,80); //(x, y, w, h)
			jlUsuario.setIcon(new ImageIcon(iUsuario.getImage().getScaledInstance(jlUsuario.getWidth(),jlUsuario.getHeight(),Image.SCALE_SMOOTH)));
			jlUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		}catch(Exception e){
			System.out.println("Error al cargar imagen.");
		}

		//Titulo IP
		try{
			iIP = new ImageIcon ("./imagenes/ip.png");
			jlIP = new JLabel();
			jlIP.setBounds(50,300,80,80); //(x, y, w, h)
			jlIP.setIcon(new ImageIcon(iIP.getImage().getScaledInstance(jlIP.getWidth(),jlIP.getHeight(),Image.SCALE_SMOOTH)));
			jlIP.setHorizontalAlignment(SwingConstants.CENTER);
		}catch(Exception e){
			System.out.println("Error al cargar imagen.");
		} 
		
		//Titulo Puerto
		try{
			iPuerto = new ImageIcon ("./imagenes/port.png");
			jlPuerto = new JLabel();
			jlPuerto.setBounds(50,425,180,80); //(x, y, w, h)
			jlPuerto.setIcon(new ImageIcon(iPuerto.getImage().getScaledInstance(jlPuerto.getWidth(),jlPuerto.getHeight(),Image.SCALE_SMOOTH)));
			jlPuerto.setHorizontalAlignment(SwingConstants.CENTER);
		}catch(Exception e){
			System.out.println("Error al cargar imagen.");
		} 
		
		//Agregar al panel
		panel.add(jlTitulo,new Integer(2));
		panel.add(jlUsuario,new Integer(3));
		panel.add(jlIP,new Integer(4));
		panel.add(jlPuerto,new Integer(5));
	}

	private void colocarTxt(){
		
		//Datos del usuarios
		txtUsuario = new JTextField();
		txtUsuario.setBounds(250,175,400,80);
		txtUsuario.setFont(new Font("Arial",Font.BOLD,80));

		txtIP = new JTextField();
		txtIP.setBounds(250,300,400,80);
		txtIP.setFont(new Font("Arial",Font.BOLD,80));

		txtPuerto = new JTextField();
		txtPuerto.setBounds(250,425,250,80);
		txtPuerto.setFont(new Font("Arial",Font.BOLD,80));

		//Agregar al panel
		panel.add(txtUsuario,new Integer(6));
		panel.add(txtIP,new Integer(7));
		panel.add(txtPuerto,new Integer(8));
	}

	private void colocarBotones(){
		
		//Boton de ventana Preguntas
		try{
			iConectar = new ImageIcon("./imagenes/connect.png");
			btnConectar = new JButton();
			btnConectar.setBounds(720,425,220,80); //(x, y, w, h)
			btnConectar.setIcon(new ImageIcon(iConectar.getImage().getScaledInstance(btnConectar.getWidth(),btnConectar.getHeight(),Image.SCALE_SMOOTH)));
			btnConectar.setBackground(Color.BLACK);
		}catch(Exception e){
			System.out.println("Error al cargar imgaen de boton.");
		}

		//Agregar boton al panel
		panel.add(btnConectar,new Integer(9)); 

		//Escuchar las acciones del boton de inicio
		btnConectar.addActionListener(this);
	}

	public void conectar(){

		System.out.println("Conectando...");

		try
		{
			ip = txtIP.getText();
			puerto = Integer.parseInt(txtPuerto.getText());

			servidor = new Socket(ip, puerto); 
            t.start();
            out = new PrintWriter(servidor.getOutputStream(),true);
            usuario = txtUsuario.getText();

            txtUsuario.setEnabled(false);
            txtIP.setEnabled(false);
            txtPuerto.setEnabled(false);
			System.out.println("Estas conectado");
			conectado = true;

		}
		catch(Exception err){
            err.printStackTrace();
            System.out.println("Eroor al conectar");
        }
    }

    public void desconectar(){
    	try
		{
			out.println("DSCNCTR");
			t.interrupt();
			servidor.close();
			txtUsuario.setEnabled(true);
            txtIP.setEnabled(true);
            txtPuerto.setEnabled(true);

			System.out.println("Desconectado");
			conectado = false;
		}
		catch(Exception err){
            err.printStackTrace();
            System.out.println("Error al deconectar");
        }
    	
    }

	//Escuchar las opciones
	public void actionPerformed(ActionEvent event){
		
		if(event.getSource() == this.btnConectar)
		{
			conectar();
			this.setVisible(false);
			//Calis c = new Calis();
			ms.StopPlaying();
		}
	}

	public static void main(String[] args) {
		VentanaPrincipal vP = new VentanaPrincipal();
	}
}
	