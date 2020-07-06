import java.io.*;
import java.util.*;
class Quiz{
  public static void main(String[] args) {
      ArrayList<String>contenido = new ArrayList<String>();
      contenido = Archivo.leerTodo("Preguntas.txt");
      int renglon=0;
      int pregunta = (int)(Math.random()*4+1);
      for(String r:contenido)
      {
        renglon=renglon+1;
        if(renglon==pregunta)
        {
          System.out.println(r);
        }
      }
  }
}