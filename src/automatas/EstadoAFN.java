/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import java.util.ArrayList;

/**
 *
 * @author nlninosi
 */
public class EstadoAFN {
    //
    public static final int MAX_CHAR = 255 ;
    public boolean aceptacion               = false ;
    //
    private ArrayList<EstadoAFN> transicion[] = new ArrayList[MAX_CHAR] ;
    private ArrayList<EstadoAFN> lambda  = new ArrayList() ;
    

    //
    public void anadirTransicion(char c, EstadoAFN next) {
    	transicion[(int)c].add(next) ;
    }
    public void anadirTransicionLambda(EstadoAFN next) {
        lambda.add(next) ;
    }
    
    public EstadoAFN () {
        for (int i = 0; i < transicion.length; i++) transicion[i] = new ArrayList();
        
    }

    public boolean matches(String s) {
        return matches(s,new ArrayList()) ;
    }

    private boolean matches(String s, ArrayList sVisitados) {
/*
*/
/*
 * Cuando emparejamos trabajamos caracter por caracter 
 *
 * Si no hay caracteres en la cadena,comprbaremos sieste este un estado de aceptacion
 * o si podemos llegar a un estado de aceptacion por medi de transicoines lambda 
 *
 * Si aun tenemos caracteres, intentamos consumir caracter a caracter
 * y la cadena restante
 *
 * Si falla, preguntamos a los nodos vecinos si pueden comsumir la cadena 
 *
 * Si eso falla, el emparejamiento falla.
 *
 * 
 */
   /* Hemos encontrado un camino de regreso a nosotras mismas a través de nodos vacíos;
     * stop or we'll go into an infinite loop. */
    //
        if (sVisitados.contains(this)){
            return false ;
        }
/* En caso de que se haga una transicion vacia, tenemos que añadir el estado a los estados visitados
  
        . */
        sVisitados.add(this) ;
        if (s.length() == 0) {
/* El string esta vacio asi que podemos emparejar el estado del string solo si es un estado final
* o podemos alcanzar un estado final sin consumir ninguna entrada
      */
            if (aceptacion) return true ;
/* Dado que este estado no es final, preguntaremos si algún estado vecino que podamos alcanzar 
            en los nodos vacíos puede coincidir con la cadena vacía. */
            for (EstadoAFN next : lambda) {
                if (next.matches("",sVisitados))
                return true ;
            }
        return false ;
        } else {
    /* En este caso el string no esta vacio, asi que podemos sacar le primer caracter y mirar 
            si los vecinos de ese caracter pueden emparejarse con el resto de la cadena 
            */
        int c = (int)s.charAt(0) ;

        for (EstadoAFN next : transicion[c]) {
            if (next.matches(s.substring(1)))
            return true ;
        }
    /* Aqui no podemos hacer que se empareje la cadena consumiendo un carácter, por lo que preguntaremos a
        nuestros vecinos de transición vacía si pueden hacer emparejar toda la cadena.. */
        for (EstadoAFN next : lambda) {
            if (next.matches(s,sVisitados))
            return true ;   
        }
        return false ;
        }
    }
}
