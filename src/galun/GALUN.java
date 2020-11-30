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

        //se leen las string en la lista
        for (int i = 0; i < lineasArchivo.size(); i++) {

            System.out.println(lineasArchivo.get(i));

            for (int j = 0; j < lineasArchivo.get(i).length(); j++) {

                //busca palabra reservada while 
                String v1 = "";
                String w = "";
                String h = "";
                String i1 = "";
                String l = "";
                String e = "";
                String v2 = "";
                if ((j + 4) < lineasArchivo.get(i).length()) {
                    //v1 = String.valueOf(lineasArchivo.get(i).charAt(j));
                    w = String.valueOf(lineasArchivo.get(i).charAt(j));
                    h = String.valueOf(lineasArchivo.get(i).charAt(j + 1));
                    i1 = String.valueOf(lineasArchivo.get(i).charAt(j + 2));
                    l = String.valueOf(lineasArchivo.get(i).charAt(j + 3));
                    e = String.valueOf(lineasArchivo.get(i).charAt(j + 4));
                    //v2 = String.valueOf(lineasArchivo.get(i).charAt(j + 6));

                }

                if (w.equals("w") && h.equals("h") && i1.equals("i") && l.equals("l") && e.equals("e")) {
                    listaA.add("while");
                    listaB.add("Palabra reservada");

                }
                
                
                //Lee la palabra reservada for
                String f = "";
                String o = "";
                String r = "";
                if ((j + 3) < lineasArchivo.get(i).length()) {

                    f = String.valueOf(lineasArchivo.get(i).charAt(j));
                    o = String.valueOf(lineasArchivo.get(i).charAt(j + 1));
                    r = String.valueOf(lineasArchivo.get(i).charAt(j + 2));

                }
                if (f.equals("f") && h.equals("o") && i1.equals("r") ) {
                    listaA.add("for");
                    listaB.add("Palabra reservada");

                }
                
                //lee la palabra reservada if
                String i2 = "";
                String f2 = "";
               
                if ((j + 1) < lineasArchivo.get(i).length()) {

                    i2 = String.valueOf(lineasArchivo.get(i).charAt(j));
                    f2 = String.valueOf(lineasArchivo.get(i).charAt(j + 1));
                    

                }
                if (i2.equals("i") && f2.equals("f")  ) {
                    listaA.add("if");
                    listaB.add("Palabra reservada");

                }
                
                //lee la palabra reservada else
                
                String e1 = "";
                String l2 = "";
                String s2 = "";
                String e2 = "";
               
                if ((j + 3) < lineasArchivo.get(i).length()) {

                    e1 = String.valueOf(lineasArchivo.get(i).charAt(j));
                    l2 = String.valueOf(lineasArchivo.get(i).charAt(j+1));
                    s2 = String.valueOf(lineasArchivo.get(i).charAt(j+2));
                    e2 = String.valueOf(lineasArchivo.get(i).charAt(j+3));
                    

                }
                if (e1.equals("e") && l2.equals("l")&& s2.equals("s")&& e2.equals("e")  ) {
                    listaA.add("else");
                    listaB.add("Palabra reservada");

                }

                //busca cualquier operador aritmetico
                String opa = String.valueOf(lineasArchivo.get(i).charAt(j));
                if (opa.equals("+") || opa.equals("-") || opa.equals("*") || opa.equals("/")) {
                    listaA.add(opa);
                    listaB.add("Operador aritmetico");
                }

                //busca cualquier operador logico 
                String opl = String.valueOf(lineasArchivo.get(i).charAt(j));
                if (opl.equals("<") || opl.equals("=") || opl.equals(">")) {
                    listaA.add(opl);
                    listaB.add("Operador logico");
                }

            }
        }
        System.out.println("Lista A: ");
        for (int i = 0; i < listaA.size(); i++) {

            System.out.println(listaA.get(i));

        }
        System.out.println("Lista B: ");
        for (int i = 0; i < listaB.size(); i++) {

            System.out.println(listaB.get(i));

        }
    }    
}
