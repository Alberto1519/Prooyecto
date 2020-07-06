import java.io.*;
import javax.imageio.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.util.*;
import java.net.*;
import java.lang.Thread;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

class Trivia80s extends JFrame implements ActionListener{

	//Atributos de la Ventna
	private JLayeredPane panel;
	private ImageIcon iFondo;
	private JLabel jlFondo;
	private ImageIcon iTitulo;
	private JLabel jlTitulo;
	private JTextField txtUsuario;

	private JOptionPane errorConectar;
	private JOptionPane errorDesconectar;

	private ImageIcon iAvanzar;
	private JButton btnAvanzar;
	private Musica ms;

	//Atributos para conectar al sevidor
	private Socket servidor;                   
    private String ip;
    private int puerto;
    private String usuario;
    private String mensaje;
    private PrintWriter out;

    private boolean conectado = false;
    private Monitor monitor;
    private Thread t;

	public Trivia80s(){

		ms = new Musica("./musica/intro.wav");
		ms.playMusic();

		this.setTitle("START...");
		this.setSize(500,500); //Tamano de la ventana (x, y)
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
			iFondo = new ImageIcon ("./imagenes/wallpaperInicio.gif");
			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,500,500); //(x, y, w, h)
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
			iTitulo = new ImageIcon ("./imagenes/triviaLogo.png");
			jlTitulo = new JLabel();
			jlTitulo.setBounds(20,10,460,200); //(x, y, w, h)
			jlTitulo.setIcon(new ImageIcon(iTitulo.getImage().getScaledInstance(jlTitulo.getWidth(),jlTitulo.getHeight(),Image.SCALE_SMOOTH)));
			jlTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		}catch(Exception e){
			System.out.println("Error al cargar imagen.");
		}

		//Nombre del usuario
		txtUsuario = new JTextField("Usuario123");
		txtUsuario.setBounds(100,240,300,40);
		txtUsuario.setOpaque(true);
		txtUsuario.setEditable(true);
		txtUsuario.setFont(new Font("Pixeled Regular",0,15));
		txtUsuario.setBackground(Color.white);
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Agregar al panel
		panel.add(jlTitulo,new Integer(2));
		panel.add(txtUsuario,new Integer(3));
	}

	private void colocarBotones(){

		//Boton de ventana solitario
		try{
			iAvanzar = new ImageIcon("./imagenes/signup.png");
			btnAvanzar = new JButton();
			btnAvanzar.setBounds(125,315,250,100); //(x, y, w, h)
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

	private void sleep(){
		try{ 
			Thread.sleep(5000);
		}catch(InterruptedException ex){
			Thread.currentThread().interrupt(); 
		}
	}

	//Escuchar las opciones
	public void actionPerformed(ActionEvent event){

		if(event.getSource() == this.btnAvanzar)
		{
			usuario = txtUsuario.getText();
			jlTitulo.setVisible(false);
			txtUsuario.setVisible(false);
			btnAvanzar.setVisible(false);
			conectar();	
			sleep();
			this.setVisible(false);
			VentanaPrincipal vP = new VentanaPrincipal(usuario);
		}
	}

	public static void main(String[] args) {
		Trivia80s tV = new Trivia80s();
	}

	public void conectar(){

		System.out.println("Conectando...");
		try
		{
			servidor = new Socket("12345", 5001); 
            monitor = new Monitor(servidor);
            t = new Thread(monitor);
            t.start();
            out = new PrintWriter(servidor.getOutputStream(),true);
			System.out.println("Conectado");
			conectado = true;

		}
		catch(Exception err){
			errorConectar.showMessageDialog(null,"Existio un error al conectar","ERROR",JOptionPane.ERROR_MESSAGE);
            err.printStackTrace();
            System.out.println("Error al conectar");
        }
    }

    public void desconectar(){
    	try
		{
			out.println("DSCNCTR");
			t.interrupt();
			servidor.close();
			System.out.println("Desconectado");
			conectado = false;
		}
		catch(Exception err){
			errorDesconectar.showMessageDialog(null,"Existio un error al desconectar","ERROR",JOptionPane.ERROR_MESSAGE);
            err.printStackTrace();
            System.out.println("Error al desconectar");
        }
    	
    }
}
    class Monitor implements Runnable{

    Socket servidor;

    public Monitor(Socket servidor){
        this.servidor = servidor;
    }

    @Override
    public void run(){
        try{  
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}