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
import automatas.*;
import static automatas.AFN.*;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;

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
        /*
        System.out.println("Ingrese la ruta : ");

        String ruta = sc.nextLine();

        leerArchivo(ruta);
        */
        
        
        
        
        /*       EJEMPLO       */
        //Ejemplos de expresiones de entrada
        /*
         // c&[a]*| d&[a]* //retorna Rtoken1
        AFN e1 = disyuncionAFN(conjuncion("d",kleene(fromString("a"))),conjuncion("c",kleene(fromString("a")))) ;
        e1.setToken("Rtoken1");
        // [[aa]|[bb]]*&a  //retorna Rtoken2
        AFN e2 = conjuncion(kleene(disyuncionString("aa","bb")),"a") ;
        e2.setToken("Rtoken2");
        */
        
        //Ejemplo De leida y salida
        /*
        List<String> codigoEjemplo = new ArrayList<>();
        codigoEjemplo.add("aabba");
        codigoEjemplo.add("aaaaaaaaa");
        codigoEjemplo.add("aaaaaaaa");
        codigoEjemplo.add("caaaaaa");
        codigoEjemplo.add("daaaaaa");
        codigoEjemplo.add("adaaaa");
        codigoEjemplo.add("aaaabba");
        codigoEjemplo.add("X");
        ArrayList<AFN> afnEjemplo = new ArrayList<>();
        afnEjemplo.add(e1);
        afnEjemplo.add(e2);
        List<String> output = result(codigoEjemplo,afnEjemplo);
        for(int i =0; i<output.size();i++){
            System.out.println(output.get(i));
        }
        */
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
                ArrayList<String> temp = new ArrayList<>();
                    if(String.valueOf(lineasArchivo.get(i).charAt(j)).equals("[")){
                       while(true){
                            
                             if(j==lineasArchivo.get(i).length()){
                                break;
                            }
                             
                             else if(String.valueOf(lineasArchivo.get(i).charAt(j)).equals("&")){
                                //guardo a en la lista
                                a=a+String.valueOf(lineasArchivo.get(i).charAt(j));
                               temp.add("and");
                               temp.add(a);
                               a="";
                            }
                           
                            else if(String.valueOf(lineasArchivo.get(i).charAt(j)).equals("|")){
                                //guardo a en la lista
                                a=a+String.valueOf(lineasArchivo.get(i).charAt(j));
                               temp.add("or");
                               temp.add(a);
                               a="";
                              
                            }
                            else if(String.valueOf(lineasArchivo.get(i).charAt(j)).equals("]")){
                                //guardo a en la lista
                              
                               if(String.valueOf(lineasArchivo.get(i).charAt(j+1)).equals("*")){
                                //guardo a en la lista
                                a=a+String.valueOf(lineasArchivo.get(i).charAt(j+1));
                               temp.add("kleene");
                               temp.add(a);
                            }
                               tokens.add(temp);
                              temp= new ArrayList<>();
                              a="";
                              break;
                            } else if(String.valueOf(lineasArchivo.get(i).charAt(j)).equals("[")){
                               
                            } 
                            else{
                                a=a+String.valueOf(lineasArchivo.get(i).charAt(j));//concatena palabra
                               temp.add("identificador");
                               temp.add(a);
                               /*if(!String.valueOf(lineasArchivo.get(i).charAt(j+1)).equals("]")){
                               tokens.add(temp);
                               }*/
                              a="";
                            }
                         j++;
                    }
                    }
                    else if(String.valueOf(lineasArchivo.get(i).charAt(j)).equals("&")){
                                //guardo a en la lista
                               temp = new ArrayList<>();
                                a=a+String.valueOf(lineasArchivo.get(i).charAt(j));
                               temp.add("and");
                               temp.add(a);
                               tokens.add(temp);
                               a="";
                               
                            }
                           
                            else if(String.valueOf(lineasArchivo.get(i).charAt(j)).equals("|")){
                                //guardo a en la lista
                                temp = new ArrayList<>();
                                a=a+String.valueOf(lineasArchivo.get(i).charAt(j));
                               temp.add("or");
                               temp.add(a);
                               tokens.add(temp);
                               a="";
                              
                            }
                           
                        else if(String.valueOf(lineasArchivo.get(i).charAt(j)).equals("]")){
                                //guardo a en la lista
                              
                          
                              break;
                            } 
                  
                
                
                
                
                /*if (j==8) {
                    //v1 = String.valueOf(lineasArchivo.get(i).charAt(j));
                    //w = String.valueOf(lineasArchivo.get(i).charAt(j));
                    System.out.print(lineasArchivo.get(i).charAt(j));
                }*/
               
            }
            
             
         System.out.println(tokens.toString());
            System.out.println("archivo leído");
           
        }
    }    
    public static List<String> leerCodigo(String ruta) {
        List<String> lineasArchivo = new ArrayList<>();
        List<String> palabras = new ArrayList<>();

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        //Se lee las lineas del archivo y se extraen los datos
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
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
            
            
            System.out.println("archivo leído");
            
        }
        palabras=ReadLine(lineasArchivo);
        return palabras;
    }
    public static List<String> ReadLine(List<String> lineasArchivo){
        List<String> palabras= new ArrayList<>();
        int length =  lineasArchivo.size();
        for(int i =0;i<length;i++){
            String string = lineasArchivo.get(i);
            // se asumio el comentario como si fuera // pero eso lo dicta el usuario
            if(string.contains("//")){
                lineasArchivo.remove(i);
                String sep = "//";
                String[] parts = string.split(sep);
                lineasArchivo.add(i,parts[0]);
            }
        }
        for(int i =0;i<length;i++){
            String string = lineasArchivo.get(i);
            String sep = "\\s+";
            String[] parts = string.trim().split(sep);
            for(int j =0;j<parts.length;j++){
                String a= parts[j];
                palabras.add(a);
            }
        }
        Set<String> set = new LinkedHashSet<>();  
        set.addAll(palabras); 
        palabras.clear(); 
        palabras.addAll(set);
        return palabras;
    }
    public static List<String> result(List<String> palabras,ArrayList<AFN> afns){
        List<String> listaA = new ArrayList<>();
        int nafns=afns.size();
        int npalabras=palabras.size();
        boolean si=false;
        for(int i =0;i<npalabras;i++){
            for(int j =0;j<nafns;j++){
                AFN actual = afns.get(j);
                if(actual.acepta(palabras.get(i))){
                    String str =String.join(" = ",palabras.get(i),actual.token);
                    listaA.add(str);
                    si=true;
                    break;
                }else{
                    si=false;
                }
                
            }
            if(!si){
                String str =String.join(" = ",palabras.get(i),"ERROR: No definido");
                listaA.add(str);
                si=false;
            }
            
        }
        return listaA;
    }
}
