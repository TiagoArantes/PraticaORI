/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praticaori4;

/**
 *
 * @author a11311BSI240
 */
public class Termo implements Comparable<Termo>{
//public class Termo {
    
    String palavra;
    float quantidade;
    float freqRel;
    float peso;
    float pesoTF;
    float pesoIDF;
    
    public Termo(String palavra, float quantidade, float peso, float freq){
        this.palavra = palavra;
        this.quantidade = quantidade;
        this.peso = peso;
        this.freqRel = freq;
    }

    public Termo() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public float getPesoIDF() {
        return pesoIDF;
    }

    public float getPesoTF() {
        return pesoTF;
    }

    public void setPesoIDF(float pesoIDF) {
        this.pesoIDF = pesoIDF;
    }

    public void setPesoTF(float pesoTF) {
        this.pesoTF = pesoTF;
    }

    public float getFreqRel() {
        return freqRel;
    }

    public void setFreqRel(float freqRel) {
        this.freqRel = freqRel;
    }
    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public String getPalavra() {
        return palavra;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getPeso() {
        return peso;
    }
    
    @Override
    public int compareTo(Termo t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        if(this.peso > t.getPeso()){
            return -1;
        }
        if(this.peso < t.getPeso()){
            return 1;
        }
        return 0;
        
    }
    
    
    
}
