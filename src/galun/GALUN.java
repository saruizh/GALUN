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
import java.io.FileWriter;
import java.io.PrintWriter;
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
        System.out.println("Ingrese la ruta del primer input: ");

        String ruta1 = sc.nextLine();

        System.out.println("Ingrese la ruta del segundo input: ");

        String ruta2 = sc.nextLine();

        ArrayList<AFN> afns = leerArchivo(ruta1);
        List<String> codigo = leerCodigo(ruta2);
        List<String> output = result(codigo, afns);
        for (int i = 0; i < output.size(); i++) {
            System.out.println(output.get(i));
        }

        /*       EJEMPLO       */
        //Ejemplos de expresiones de entrada
        /*
         // c&[a]*| d&[a]* //retorna Rtoken1
        AFN e1 = disyuncionAFN(conjuncion("d",kleene(fromString("a"))),conjuncion("c",kleene(fromString("a")))) ;
        e1.setToken("Rtoken1");
        // [[aa]|[bb]]*&a  //retorna Rtoken2
        AFN e2 = conjuncion(kleene(disyuncionString("aa","bb")),"a") ;
        e2.setToken("Rtoken2");
        
        //Ejemplo De leida y salida
        
        List<String> codigoEjemplo = new ArrayList<>();
        codigoEjemplo.add("aabba");
        codigoEjemplo.add("aaaaaaaaa");
        codigoEjemplo.add("aaaaabbbbb");
        codigoEjemplo.add("a");
        codigoEjemplo.add("daaaaaa//aaaaaa");
        codigoEjemplo.add("adaaaa");
        codigoEjemplo.add("aaaabba");
        codigoEjemplo.add("a");
        codigoEjemplo.add("X");
        ArrayList<AFN> afnEjemplo = leerArchivo(ruta);
        afnEjemplo.add(e1);
        afnEjemplo.add(e2);
        
        List<String> output = result(codigoEjemplo,afnEjemplo);
        for(int i =0; i<output.size();i++){
            System.out.println(output.get(i));
        }
         */
    }

    public static ArrayList<AFN> leerArchivo(String ruta) {
        ArrayList<AFN> afns = new ArrayList<>();
        List<String> lineasArchivo = new ArrayList<>();

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
                if (linea.contains("[a-z]")) {
                    String az = "([a]|[b]|[c]|[d]|[e]|[f]|[g]|[h]|[i]|[j]|[k]|[l]|[m]|[n]|[o]|[p]|[q]|[r]|[s]|[t]|[u]|[v]|[x]|[y]|[w]|[z])";
                    lineasArchivo.add(az);
                   

                } else if (linea.contains("[a-z]*")) {
                    String az2 = "([a]|[b]|[c]|[d]|[e]|[f]|[g]|[h]|[i]|[j]|[k]|[l]|[m]|[n]|[o]|[p]|[q]|[r]|[s]|[t]|[u]|[v]|[x]|[y]|[w]|[z])*";
                    lineasArchivo.add(az2);
                    
                }else if (linea.contains("[0-9]*")) {
                    String num2 = "([0]|[1]|[2]|[3]|[4]|[5]|[6]|[7]|[8]|[9])*";
                    lineasArchivo.add(num2);
                    
                } else if (linea.contains("[0-9]")) {
                    String num = "([0]|[1]|[2]|[3]|[4]|[5]|[6]|[7]|[8]|[9])";
                    lineasArchivo.add(num);
                } else {
                    System.out.println(linea);
                   
                    lineasArchivo.add(linea); // añade archivo a la lista 
                }

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
            AFN newAFN = syntax(lineasArchivo.get(i));
            newAFN.setToken("a1");
            afns.add(newAFN);
        }
        return afns;
    }

    public static AFN syntax(String in) {
        String derecha = "";
        String izquierda = "";
        String a = "";
        for (int j = 0; j < in.length(); j++) {
            int parcount = 0;
            if (in.charAt(j) == '(') {
                parcount++;
                j++;
                while (true) {
                    if (j == in.length()) {
                        break;
                    } else if (String.valueOf(in.charAt(j)).equals("(")) {
                        a = a + String.valueOf(in.charAt(j));
                        parcount++;
                    } else if (String.valueOf(in.charAt(j)).equals(")")) {
                        if (parcount == 1) {
                            izquierda = a;
                            a = "";
                            break;
                        } else {
                            a = a + String.valueOf(in.charAt(j));
                            parcount--;
                        }
                    } else {
                        a = a + String.valueOf(in.charAt(j));//concatena palabra
                    }
                    j++;
                }

                if (j == in.length() - 1) {
                    return syntax(izquierda);
                } else {
                    j++;
                    switch (in.charAt(j)) {
                        case '*':
                            if (j == in.length() - 1) {
                                return kleene(syntax(izquierda));
                            } else {
                                j++;
                                if (in.charAt(j) == '&') {
                                    derecha = in.substring(j + 1);
                                    return conjuncion(kleene(syntax(izquierda)), syntax(derecha));
                                } else if (in.charAt(j) == '|') {
                                    derecha = in.substring(j + 1);
                                    return disyuncionAFN(kleene(syntax(izquierda)), syntax(derecha));
                                }
                            }
                            break;
                        case '&':
                            derecha = in.substring(j + 1);
                            return conjuncion(syntax(izquierda), syntax(derecha));
                        case '|':
                            derecha = in.substring(j + 1);
                            return disyuncionAFN(syntax(izquierda), syntax(derecha));
                        default:
                            break;
                    }
                }
            }
            if (in.charAt(j) == '[') {
                j++;
                while (true) {
                    if (j == in.length()) {
                        break;
                    } else if (String.valueOf(in.charAt(j)).equals("]")) {
                        izquierda = a;
                        a = "";
                        break;
                    } else {
                        a = a + String.valueOf(in.charAt(j));//concatena palabra
                    }
                    j++;
                }
                if (j == in.length() - 1) {
                    System.out.println(j);
                    return fromString(izquierda);
                } else {
                    j++;
                    switch (in.charAt(j)) {
                        case '*':
                            if (j == in.length() - 1) {
                                return kleene(fromString(izquierda));
                            } else {
                                j++;
                                if (in.charAt(j) == '&') {
                                    derecha = in.substring(j + 1);
                                    return conjuncion(kleene(fromString(izquierda)), syntax(derecha));
                                } else if (in.charAt(j) == '|') {
                                    derecha = in.substring(j + 1);
                                    return disyuncionAFN(kleene(fromString(izquierda)), syntax(derecha));
                                }
                            }
                            break;
                        case '&':
                            derecha = in.substring(j + 1);
                            return conjuncion(fromString(izquierda), syntax(derecha));
                        case '|':
                            derecha = in.substring(j + 1);
                            return disyuncionAFN(fromString(izquierda), syntax(derecha));
                        default:
                            break;
                    }
                }
            }
        }
        return null;
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
        palabras = ReadLine(lineasArchivo);
        return palabras;
    }

    public static List<String> ReadLine(List<String> lineasArchivo) {
        List<String> palabras = new ArrayList<>();
        int length = lineasArchivo.size();
        for (int i = 0; i < length; i++) {
            String string = lineasArchivo.get(i);
            String sep = "\\s+";
            String[] parts = string.trim().split(sep);
            for (int j = 0; j < parts.length; j++) {
                String a = parts[j];
                palabras.add(a);
            }
        }
        Set<String> set = new LinkedHashSet<>();
        set.addAll(palabras);
        palabras.clear();
        palabras.addAll(set);
        return palabras;
    }

    public static List<String> result(List<String> palabras, ArrayList<AFN> afns) {
        List<String> listaA = new ArrayList<>();
        int nafns = afns.size();
        int npalabras = palabras.size();
        boolean si = false;
        for (int i = 0; i < npalabras; i++) {
            for (int j = 0; j < nafns; j++) {
                AFN actual = afns.get(j);
                if (actual.acepta(palabras.get(i))) {
                    String str = String.join(" = ", palabras.get(i), actual.token);
                    listaA.add(str);
                    si = true;
                    break;
                } else {
                    si = false;
                }

            }
            if (!si) {
                String str = String.join(" = ", palabras.get(i), "ERROR: No definido");
                listaA.add(str);
                si = false;
            }

        }
        return listaA;
    }

    public static void escribirArchivo(List<String> lista, String ruta) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(ruta + "output.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < lista.size(); i++) {
                pw.println(lista.get(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
