/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.iut2.genconf.util;

import java.io.Serializable;
/**
 *
 * @author hippo
 */
public enum Type implements Serializable{
    
    KEYNOTE {
        public Type getType(){
            return KEYNOTE;
        }
        
        @Override
        public String toString() {
            return "keynote";
        }        
    },
    ATELIER {
        public Type getType(){
            return ATELIER;
        }
        
        @Override
        public String toString() {
            return "atelier";
        }        
    },
    ARTICLE {
        public Type getType(){
            return ARTICLE;
        }
        
        @Override
        public String toString() {
            return "article";
        }        
    };
    
     public static Type setType(String type) {
        if (type==Type.KEYNOTE.toString()) {
            return Type.KEYNOTE;
        }
        else if (type==Type.ATELIER.toString()){
            return Type.ATELIER;
        }
        else{
            return Type.ARTICLE;
        }
    }
    
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
}
