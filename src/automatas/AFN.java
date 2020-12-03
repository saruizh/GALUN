/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

/**
 *
 * @author nlninosi
 */
public class AFN{
    public EstadoAFN entrada ;
    public EstadoAFN salida ;

    public AFN(EstadoAFN entry, EstadoAFN exit) {
    	this.entrada = entry ;
    	this.salida  = exit;
    } 

    public boolean acepta(String str) {
    	return entrada.matches(str);
    }
//  Esta funcion crea un afn que acepta un caracter
    public static final AFN c(char c) {
    	EstadoAFN entrada = new EstadoAFN() ;
    	EstadoAFN salida = new EstadoAFN() ;
    	salida.aceptacion = true ;
    	entrada.anadirTransicion(c,salida) ;
    	return new AFN(entrada,salida) ;
    }
//  Esta funcion crea un afn que acepta una cadena vacia; 
    public static final AFN vacia() {
    	EstadoAFN entrada  = new EstadoAFN() ;
    	EstadoAFN c = new EstadoAFN() ;
    	entrada.anadirTransicionLambda(entrada) ;
    	entrada.aceptacion = true ;
    	return new AFN(entrada,entrada) ;
    }

//  Esta funcion dado un afn devuelve un afn con la estrella de kleene aplicada
    public static final AFN kleene(AFN afn) {
    	afn.salida.anadirTransicionLambda(afn.entrada) ;
        afn.entrada.anadirTransicionLambda(afn.salida) ;
        return afn ;
    }
//  Esta funcion devuelve la concatenacion de 2 afn  
    public static final AFN conjuncion(AFN primero, AFN segundo) {
    	primero.salida.aceptacion = false ;
    	segundo.salida.aceptacion = true ;
    	primero.salida.anadirTransicionLambda(segundo.entrada) ;
    	return new AFN(primero.entrada,segundo.salida) ;
    }
//  Esta funcion crea un afn que acepta cualquiera de los 2 afn dados 
    public static final AFN disyuncion(AFN opcion1, AFN opcion2) {
        opcion1.salida.aceptacion = false ;
        opcion2.salida.aceptacion = false ;
        EstadoAFN entrada = new EstadoAFN() ;
        EstadoAFN exit  = new EstadoAFN() ;
        exit.aceptacion = true ;
        entrada.anadirTransicionLambda(opcion1.entrada) ;
        entrada.anadirTransicionLambda(opcion2.entrada) ;
        opcion1.salida.anadirTransicionLambda(exit) ;
        opcion2.salida.anadirTransicionLambda(exit) ;
        return new AFN(entrada,exit) ;
    }

    /* Syntactic sugar. */
    public static final AFN re(Object o) {
        if (o instanceof AFN){
            return (AFN)o ;
        } else if (o instanceof Character){
            return c((Character)o) ;
        }else if (o instanceof String){
            return fromString((String)o) ;
        }else {
            throw new RuntimeException("bad regexp") ;
        }
    }
//  Se definen las funciones conjuncion y disyuncion de manera publica y estatica 
    public static final AFN disyuncion(String... expresion) {
        AFN exp = fromString(expresion[0]) ;
        for (int i = 1; i < expresion.length; i++) {
            exp = disyuncion(exp,re(expresion[i])) ;
        }
        return exp ;
    }

    public static final AFN conjuncion(Object... rexps) {
        AFN exp = vacia() ;
        for (int i = 0; i < rexps.length; i++) {
            exp = conjuncion(exp,re(rexps[i])) ;
        }
        return exp ;
    }
//  Esta funcion genera un afn dado un String
    public static final AFN fromString(String str) {
        if (str.length() == 0){
            return vacia() ;
        }else{
            return conjuncion(re(str.charAt(0)),fromString(str.substring(1))) ;
        }
    }
}
