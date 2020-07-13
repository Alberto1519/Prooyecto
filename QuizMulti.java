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

class QuizMulti extends JFrame implements ActionListener{

    private JLayeredPane panel;

    private ImageIcon iFondo;
    private JLabel jlFondo;

    private ImageIcon iTitulo;
    private JLabel jlTitulo;

    private ImageIcon iPregunta;
    private JLabel jlPregunta;

    private JTextField txtRespuesta;

    private ImageIcon iConectar;
    private JButton btnConectar;

    private String usuario;
    private String respuesta;
    private int puntos;

    private JLabel jlTiempo;
    private JLabel jlNotificacion;

    //Para las preguntas
    private int i = 0;
    private int canQ = 10;
    private int rango = 31;
    private int[] preguntaRandom = new int[canQ+1];
    private int[] respuestaRandom = new int[canQ+1];
    private int contadorP = 0;

    //Para conectar
    private JOptionPane errorConectar;
    private JOptionPane errorDesconectar;

    //Atributos para conectar al sevidor
    private Socket servidor;                   
    private String ip;
    private int puerto;
    private String mensaje;
    private PrintWriter out;

    private boolean conectado = false;
    private Monitor monitor;
    private Thread t;

    public QuizMulti(String usuario){

        this.usuario = usuario;

        this.setTitle("QUIZ MULTI");
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
        //crearPreguntas();
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
            iPregunta = new ImageIcon ("./imagenes/Preguntas/espera.png");
            jlPregunta = new JLabel();
            jlPregunta.setBounds(55,245,450,365); //(x, y, w, h)
            jlPregunta.setIcon(new ImageIcon(iPregunta.getImage().getScaledInstance(jlPregunta.getWidth(),jlPregunta.getHeight(),Image.SCALE_SMOOTH)));
            jlPregunta.setHorizontalAlignment(SwingConstants.CENTER);
        }catch(Exception e){
            System.out.println("Error al cargar imagen.");
        }

        //Area para responder
        txtRespuesta = new JTextField();
        txtRespuesta.setBounds(80,615,400,50);
        txtRespuesta.setBackground(Color.WHITE);
        txtRespuesta.setFont(new Font("Berlin Sans FB",0,30));
        txtRespuesta.setHorizontalAlignment(SwingConstants.CENTER); 

        //Tiempo
        jlTiempo = new JLabel("5");
        jlTiempo.setBounds(421,20,100,100);
        jlTiempo.setForeground(Color.WHITE);
        jlTiempo.setFont(new Font("Berlin Sans FB",0,80));
        jlTiempo.setHorizontalAlignment(SwingConstants.CENTER); 

        //Notificacion de coneccion
        jlNotificacion = new JLabel("Desconectado");
        jlNotificacion.setBounds(80,177,200,50);
        jlNotificacion.setFont(new Font("Berlin Sans FB",0,35));
        jlNotificacion.setForeground(Color.WHITE);
        jlNotificacion.setHorizontalAlignment(SwingConstants.CENTER);

        //Agregar al panel
        panel.add(jlTitulo,new Integer(2));
        panel.add(jlPregunta,new Integer(3));
        panel.add(txtRespuesta,new Integer(4));
        panel.add(jlTiempo,new Integer(5));
        panel.add(jlNotificacion,new Integer(6));
    }

    private void colocarBotones(){

        //Boton de ventana solitario
        try{
            iConectar = new ImageIcon("./imagenes/connection.png");
            btnConectar = new JButton();
            btnConectar.setBounds(325,152,210,100); //(x, y, w, h)
            btnConectar.setIcon(new ImageIcon(iConectar.getImage().getScaledInstance(btnConectar.getWidth(),btnConectar.getHeight(),Image.SCALE_SMOOTH)));
            btnConectar.setOpaque(false);
            btnConectar.setContentAreaFilled(false);
            btnConectar.setBorderPainted(false);
        }catch(Exception e){
            System.out.println("Error al cargar imgaen de boton.");
        }

        //Agregar boton al panel
        panel.add(btnConectar,new Integer(7)); 

        //Escuchar las acciones del boton de inicio
        btnConectar.addActionListener(this);
    }

    private void sleep(){
        try{ 
            Thread.sleep(5000);
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt(); 
        }
    }

    public void actionPerformed(ActionEvent event){

        if(event.getSource() == this.btnConectar)
        {
            if(!conectado){
                conectar();
            }

            else{
                desconectar();
            }
        }
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

            btnConectar.setEnabled(false);

            jlNotificacion.setText("Conectado");
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

            jlNotificacion.setText("Desconectado");
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