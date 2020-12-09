/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galun;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.File;

/**
 *
 * @author sarui
 */
public class GALUN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //File input=new File("input"); Proyecto en blanco
        //System.out.print(input.getAbsolutePath());
        //System.out.print(input.exists());
        Scanner sc = new Scanner(System.in);

        // entrada de una cadena
        System.out.println("Ingrese la ruta : ");

        String ruta = sc.nextLine();

        leerArchivo(ruta); 
    }
    public static void leerArchivo(String ruta) {

        List<String> lineasArchivo = new ArrayList<>();
        List<String> listaA = new ArrayList<>();
        List<String> listaB = new ArrayList<>();

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        //Se lee las lineas del archivo y se extraen los datos
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            //archivo = new File("C:\\Reglas.txt"); 
            archivo = new File(ruta);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;

            while ((linea = br.readLine()) != null) {

                lineasArchivo.add(linea); // añade archivo a la lista 
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
       
        for (int i = 0; i < lineasArchivo.size(); i++) {  
             ArrayList<ArrayList<String>> tokens = new ArrayList<>();
            String a="";
            for (int j = 0; j < lineasArchivo.get(i).length(); j++){
                
                    if(String.valueOf(lineasArchivo.get(i).charAt(j)).equals("[")){
                       while(true){
                             if(j==lineasArchivo.get(i).length()){
                                break;
                            }
                             
                            else if(String.valueOf(lineasArchivo.get(i).charAt(j)).equals("]")){
                                //guardo a en la lista
                               ArrayList<String> temp = new ArrayList<>();
                               temp.add("identificador");
                               temp.add(a);
                               tokens.add(temp);
                               a="";
                               break;
                            }
                            else{
                                a=a+String.valueOf(lineasArchivo.get(i).charAt(j));//concatena palabra
                               
                            }
                         j++;
                    }
                    }
                    else if(String.valueOf(lineasArchivo.get(i).charAt(j)).equals("&")){
                                //guardo a en la lista
                               ArrayList<String> temp = new ArrayList<>();
                                a=a+String.valueOf(lineasArchivo.get(i).charAt(j));
                               temp.add("and");
                               temp.add(a);
                               tokens.add(temp);
                               a="";
                               
                            }
                            else if(String.valueOf(lineasArchivo.get(i).charAt(j)).equals("*")){
                                //guardo a en la lista
                               ArrayList<String> temp = new ArrayList<>();
                                a=a+String.valueOf(lineasArchivo.get(i).charAt(j));
                               temp.add("kleene");
                               temp.add(a);
                               tokens.add(temp);
                               a="";
                             
                            }
                            else if(String.valueOf(lineasArchivo.get(i).charAt(j)).equals("|")){
                                //guardo a en la lista
                               ArrayList<String> temp = new ArrayList<>();
                                a=a+String.valueOf(lineasArchivo.get(i).charAt(j));
                               temp.add("or");
                               temp.add(a);
                               tokens.add(temp);
                               a="";
                              
                            }
                           
                        
                  
                
                
                
                
                /*if (j==8) {
                    //v1 = String.valueOf(lineasArchivo.get(i).charAt(j));
                    //w = String.valueOf(lineasArchivo.get(i).charAt(j));
                    System.out.print(lineasArchivo.get(i).charAt(j));
                }*/
                System.out.println(tokens.toString());
            }
            
            
            System.out.println("archivo leído");
            
        }
        
    }    
}
