/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praticaori4;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class Doc implements Comparable<Doc>{
    
    List<Termo> t = new ArrayList<>();
    int nroDoc;
    float norma;
    float similaridade;
    float vetor[];
    
    public Doc(){
        
    }

    public int getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(int nroDoc) {
        this.nroDoc = nroDoc;
    }

    public float getSimilaridade() {
        return similaridade;
    }

    public void setSimilaridade(float similaridade) {
        this.similaridade = similaridade;
    }

    public void setNorma(float norma) {
        this.norma = norma;
    }

    public float getNorma() {
        return norma;
    }

    public int compareTo(Doc doc) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(this.similaridade > doc.similaridade){
            return -1;
        }
        if(this.similaridade < doc.similaridade){
            return 1;
        }
        return 0;
    }
    
}
