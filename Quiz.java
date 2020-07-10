import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.event.*;
public class Quiz extends JFrame implements ActionListener{
    
    //Elementos ventana
      JPanel panel;
      JLabel jlpreguntas;
      JTextField txtRespuesta;
      JButton siguiente;
      String respuestaCorrecta;
      String respuestaIngresada;
      int cantidadPreguntas=14;
      int preguntaRandom[] = new int [cantidadPreguntas+1];//lista de numeros random 
 	    int contadorPregunta=0;


      public Quiz(){
      //elementos de la ventana
      this.setTitle("Back To The 80s");
      panel = new JPanel();
      panel.setLayout(null);
      siguiente = new JButton("siguiente");
      jlpreguntas = new JLabel("Preguntas");
      txtRespuesta = new JTextField();
      
      panel.add(jlpreguntas);    
      panel.add(txtRespuesta);
      panel.add(siguiente);  
      
      jlpreguntas.setBounds(0, 0, 800, 400);
      txtRespuesta.setBounds(100, 100, 200, 50);
      siguiente.setBounds(900,0,200,50);
     
      this.setBounds(0,0,java.awt.Toolkit.getDefaultToolkit().getScreenSize().width,java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
      this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
      this.add(panel);
      this.setVisible(true);
      siguiente.addActionListener(this);
      
      //Seleccion de la pregunta
      ArrayList<String>contenido = new ArrayList<String>();
      contenido = Archivo.leerTodo("preguntas.txt");
      ArrayList<String>respuestas = new ArrayList<String>();
      respuestas = Archivo.leerTodo("respuestas.txt");
      int renglon=0;//contador de renglones
      //esta parte es la que hace el arryslit de numeros random sin repetir
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
        
        for(String p:contenido){
          
          renglon=renglon+1;

        if(renglon==preguntaRandom[2]){

          jlpreguntas.setText(p); 
          
        }
      }
    }
    public void actionPerformed(ActionEvent event)
    {
	    if(event.getSource() == this.siguiente){//aqui pensaba acomdar el cambio de la etiqueta donde se muestra a pregunta en la ventana
	    	contadorPregunta = contadorPregunta+1;
	    	System.out.println(preguntaRandom[contadorPregunta]);//aqui segun yo era para que cada vez que se presiona vaya avanzando el arraylist
	    }                                                        // de los numeros random
    }
      public static void main(String[] args){
       Quiz qz = new Quiz();
     }
   }