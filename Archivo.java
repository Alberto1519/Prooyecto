import java.io.*;
import java.util.*;

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

    public static void guardarTodo(String informacion){
            try{
                    FileOutputStream fos = new FileOutputStream ("archivo_creado_1.txt");
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
                    out.write(informacion);
                    out.close(); 
                }
        catch (Exception e) //Agregar Excepciones
        { 
                    e.printStackTrace();
                    System.exit(1);
        }   
    }

    public static void guardarTodo(String informacion, String nombre){
            try{
                    FileOutputStream fos = new FileOutputStream (nombre);
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
                    out.write(informacion);
                    out.close(); 
                }
              catch (Exception e) //Agregar Excepciones
                {
                    
                }
        }
    }