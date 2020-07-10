import java.io.*;
import java.util.*;
import java.util.Date;

class Archivo{
    
    public static ArrayList<String> leerTodo(String nombreArchivo){
            String strLine = new String();
            ArrayList<String> arraylist = new ArrayList<String>();
        try(FileInputStream fis = new FileInputStream(nombreArchivo);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader( new InputStreamReader(in));)
            {
                strLine = br.readLine();
                while (strLine != null)
                {
                    arraylist.add(strLine);
                    strLine = br.readLine();   
                }
            }
        catch (Exception e ) //Agregar Excepciones
            {
                arraylist.add("Error: Archivo no existente");
                return arraylist;
            }
            //arraylist.remove(arraylist.size()-1); // remover el ultimo elemento, que es null
            return arraylist;
    }

    public static void guardarPuntos(String jugador, String puntos){
        
        File pt = new File("./puntajes.txt");
        Date fecha = new Date();
        String datos = jugador + "   " + puntos + " pts   " + fecha.toString();

            try{
                FileWriter fw = new FileWriter(pt,true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(datos);
                pw.close();
                fw.close();
            }catch(Exception e){
                System.out.println("No se pudo guardar el nuevo dato.");
        }
    }   
}
