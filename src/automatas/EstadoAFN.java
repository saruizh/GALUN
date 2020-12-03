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
 * When matching, we work character by character.
 *
 * If we're out of characters in the string, we'll check to
 * see if this state if final, or if we can get to a final
 * state from here through empty edges.
 *
 * If we're not out of characters, we'll try to consume a
 * character and then match what's left of the string.
 *
 * If that fails, we'll ask if empty-edge neighbors can match
 * the entire string.
 *
 * If that fails, the match fails.
 *
 * Note: Because we could have a circular loop of empty
 * transitions, we'll have to keep track of the states we
 * visited through empty transitions so we don't end up
 * looping forever.
 */
   /* We've found a path back to ourself through empty edges;
     * stop or we'll go into an infinite loop. */
    //
        if (sVisitados.contains(this)){
            return false ;
        }
/* In case we make an empty transition, we need to add this
 * state to the visited list. */
        sVisitados.add(this) ;
        if (s.length() == 0) {
/* The string is empty, so we match this string only if
* this state is a final state, or we can reach a final
* state without consuming any input. */
            if (aceptacion) return true ;
/* Since this state is not final, we'll ask if any
* neighboring states that we can reach on empty edges can
* match the empty string. */
            for (EstadoAFN next : lambda) {
                if (next.matches("",sVisitados))
                return true ;
            }
        return false ;
        } else {
    /* In this case, the string is not empty, so we'll pull
     * the first character off and check to see if our
     * neighbors for that character can match the remainder of
     * the string. */
        int c = (int)s.charAt(0) ;

        for (EstadoAFN next : transicion[c]) {
            if (next.matches(s.substring(1)))
            return true ;
        }
    /* It looks like we weren't able to match the string by
     * consuming a character, so we'll ask our
     * empty-transition neighbors if they can match the entire
     * string. */
        for (EstadoAFN next : lambda) {
            if (next.matches(s,sVisitados))
            return true ;
        }
        return false ;
        }
    }
}
