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
        //File input=new File("input"); //Proyecto en blanco;
        //System.out.print(input.getAbsolutePath());
        //System.out.print(input.exists());
        ArrayList <AFN> afns = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        // entrada de una cadena
        System.out.println("Ingrese la ruta : ");
        String ruta1 = sc.nextLine();
        System.out.println("Ingrese la ruta del codigo : ");
        String ruta2 = sc.nextLine();
        afns=automatas(leerArchivo(ruta1));
        leerCodigo(ruta2);
    }
    public static List<String> leerArchivo(String ruta) {
        List<String> lineasArchivo = new ArrayList<>();

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
        }
        return lineasArchivo;
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
        }
        palabras=ReadLine(lineasArchivo);
        return palabras;
    }
    
    public static ArrayList<AFN> automatas( List<String> lineasArchivo){
        return null;
    }
    public static void tokens(){
        
    }
    public static List<String> ReadLine(List<String> lineasArchivo){
        List<String> palabras= new ArrayList<>();
        int length =  lineasArchivo.size();
        for(int i =0;i<length;i++){
            String string = lineasArchivo.get(i);
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
}
