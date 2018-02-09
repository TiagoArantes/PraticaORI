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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author a11311BSI240
	Disciplina: Organização e Recuperação da Informação
	Algoritmo capaz de realizar a leitura de uma coleção de arquivo e dado uma consulta do usuário,
	retorna uma lista de documentos ordenados de acordo com a similaridade entre o documento e a consulta.
	Contém métodos para realizar ranqueamento e a ponderação do termo e documento nos módelos apresentados na disciplina
	(TF-IDF, Probabilistico)
 */
public class PraticaORI4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here

        //BufferedReader arq = new BufferedReader(new FileReader("C:/Users/a11311BSI240/Desktop/corpus.txt"));
        //BufferedWriter esc = new BufferedWriter (new FileWriter("C:/Users/a11311BSI240/Desktop/pratica1ORI"));
        //List<Termo> termo = new ArrayList<Termo>();
        //BufferedReader arq = new BufferedReader(new FileReader("C:/Users/Tiago/Desktop/hino.txt"));
        //BufferedWriter esc = new BufferedWriter(new FileWriter("C:/Users/Tiago/Desktop/pratica2.txt"));
        int cont = 0;
        int cont2 = 0;
        //float quantDoc = 0;
        //List<String> list = new ArrayList<String>();
        Set<String> srep = new HashSet<String>();//criar lista sem repetição
        List<Vocabulario> doc = new ArrayList<>();//lista de vocabulario q será usada para calcular ni
        List<Doc> ter = new ArrayList<>();
        List<Doc> ter2 = new ArrayList<>();
        //Doc docum = new Doc();

        //criando variaveis para fazer leitura do indice
        BufferedReader indices = new BufferedReader(new FileReader("C:/Users/Tiago/Desktop/cranfield/documentos selecionados.txt"));

        Indice indice = new Indice();
        while (indices.ready()) {
            String linha = indices.readLine();
            //contLinhas ++;

            String[] vetor = linha.split("[ .;,:!?]");
            for (int i = 0; i < vetor.length; i++) {
                indice.id.add(vetor[i]);

            }

        }
        indices.close();
        for (String ind : indice.id) {
            System.out.println(ind);
        }

        //File arquivos[];
        //File diretorio = new File("C:/Users/tiagoarantes/Desktop/praticaOri2/docs");C:\Users\Tiago\Desktop\Pratica3
        //File diretorio = new File("C:/Users/Tiago/Desktop/Pratica3");
        //File diretorio = new File("C:/Users/Tiago/Desktop/praticaOri");
        //arquivos = diretorio.listFiles();
        //quantDoc = arquivos.length;
        //for (int j = 0; j < arquivos.length; j++) {
        //BufferedReader arq = new BufferedReader(new FileReader(arquivos[j]));
        BufferedReader arq = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/Tiago/Desktop/cranfield/cran.all.1400"), "ISO-8859-1"));
        //System.out.println("doc " + j);
        //Vocabulario d = new Vocabulario();
        //Doc docum = new Doc();
        boolean ind = false;
        boolean cind = false;
        boolean cdoc = false;
        //Termo t = new Termo();
        Vocabulario d = new Vocabulario();
        Doc docum = new Doc();
        while (arq.ready()) {
            String[] vetor = null;
            String linha = arq.readLine();
            //vetor = linha.split("[ ;,:!?]");

            String ndoc = null;
            if (cdoc && cind && ind) {
                System.out.println("Entrou=====================");
                if (!linha.contains(".I")) {
                    System.out.println("Vai preencher");
                    vetor = linha.split("[ .;,:!?]");
                    //Vocabulario d = new Vocabulario();
                    //Doc docum = new Doc();
                    for (int i = 0; i < vetor.length; i++) {

                        //if (vetor[i].length() > 3) {
                        String p = vetor[i];
                        Termo t = new Termo();
                        //list.add(p.toLowerCase());
                        srep.add(p.toLowerCase());
                        //System.out.println(p);
                        d.documento.add(p.toLowerCase());
                        t.setPalavra(p.toLowerCase());
                        docum.t.add(t);
                        //docum.setNroDoc(j+1);
                        //doc.add(d);
                        // }

                    }
                    

                } else {
                    doc.add(d);
                    //System.out.println("array " + docum.t.toString());
                    ter.add(docum);
                    cdoc = false;
                    cind = false;
                    ind = false;
                }
            }

            if (linha.contains(".I")) {
                vetor = linha.split(" ");
                ndoc = vetor[1];
                ind = true;
                //cind = false;
                //cdoc = false;
            }
            if (!cind) {
                if (indice.id.contains(ndoc)) {
                    cind = true;
                }
            }

            if (cind) {
                if (linha.contains(".W")) {
                    cdoc = true;
                    d = new Vocabulario();
                    docum = new Doc();
                }

            }

//            for (int i = 0; i < vetor.length; i++) {
//
//                //if (vetor[i].length() > 3) {
//                String p = vetor[i];
//                Termo t = new Termo();
//                //list.add(p.toLowerCase());
//                srep.add(p.toLowerCase());
//                //System.out.println(p);
//                d.documento.add(p.toLowerCase());
//                t.setPalavra(p.toLowerCase());
//                docum.t.add(t);
//                //docum.setNroDoc(j+1);
//                //doc.add(d);
//                // }
//
//            }
//
        }
        //doc.add(d);
        //System.out.println("array " + docum.t.toString());
        //ter.add(docum);
        arq.close();
        //}
        //System.out.println("TAMANHO DE TER " + ter.size());
        //criando os objetos na lista ter2 q será populada
        int contador = 1;
//        for (Doc d : ter) {
//
//            Doc de = new Doc();
//            de.setNroDoc(contador);
//            System.out.println("add mais um " + contador);
//            ter2.add(de);
//            contador++;
//        }
        for (String palavra : srep) {
            for (int i = 0; i < ter.size(); i++) {
                int cont3 = 0;
                boolean contem = false;
                //System.out.println("Doc ");
                for (int j = 0; j < ter.get(i).t.size(); j++) {
                    // System.out.println(ter.get(i).t.get(j).palavra);
                    //for(int k=0; k<ter.get(i).t.size();k++){
                    if (ter.get(i).t.get(j).palavra.equalsIgnoreCase(palavra)) {
                        cont3++;
                        contem = true;
                    }
                    //}

                    ter.get(i).t.get(j).setFreqRel(freqRelativa(doc, ter.get(i).t.get(j).palavra));
                    ter.get(i).t.get(j).setQuantidade(cont3);
                    //cont3 = 0;
                }
                Termo t = new Termo();
                if (contem) {

                    t.setFreqRel(freqRelativa(doc, palavra));
                    t.setPalavra(palavra);
                    t.setQuantidade(cont3);
                    ter2.get(i).t.add(t);
                } else {
                    t.setPalavra(palavra);
                    t.setQuantidade(0);
                    ter2.get(i).t.add(t);
                }
                contem = false;
                // c++;
            }
        }
        for (int i = 0; i < ter2.size(); i++) {
            int cont3 = 0;
             System.out.println("Doc ");
            for (int j = 0; j < ter2.get(i).t.size(); j++) {
                System.out.println(ter2.get(i).t.get(j).palavra + " fr " + ter2.get(i).t.get(j).freqRel + " cont " + ter2.get(i).t.get(j).quantidade);
            }
        }

        //Collections.sort(termo);
        //calculaTF(ter2);
        //calculaIDF(ter2, (float) doc.size());
        //calculaTFIDF(ter2);
        //calculaCerto(ter2, (float)doc.size());
        //System.out.println("TAMANHO DE DOC " + doc.size()+  " TER "+ter.size()+ " SREP "+ srep.size());
        //calculaNorma(ter2);
        //geraArquivo(ter2);
        //preencheVetor(ter2);
        //imprimeVetor(ter2);
//        for (int i = 0; i < termo.size() - 1; i++) {
//            //System.out.println(termo.get(i).palavra + " " + termo.get(i).quantidade);
//            System.out.println(termo.get(i).palavra);
//            esc.write(termo.get(i).palavra + " " + termo.get(i).quantidade + "\r\n");
//            
//        }
        //arq.close();
        // esc.close();
        boolean ajusteFormula = verificaFormula(ter2, (float) ter2.size());
        System.out.println(ajusteFormula);
        String teste = "Y";

        do {
            String consulta;

            Scanner ler = new Scanner(System.in);
            System.out.println("Digite a consulta:");
            consulta = ler.nextLine();

            String[] vetorConsulta = null;

            vetorConsulta = consulta.split("[ .;,:!?]");
            Vocabulario cons = new Vocabulario();
            Doc docc = new Doc();
            List<String> listConsulta = new ArrayList<String>();
            Set<String> srepc = new HashSet<String>();
            List<Vocabulario> docCons = new ArrayList<>();
            List<Doc> ldoc = new ArrayList<>();
            List<Doc> ldoc2 = new ArrayList<>();

            for (int i = 0; i < vetorConsulta.length; i++) {

                //if (vetor[i].length() > 3) {
                String p = vetorConsulta[i];
                Termo term = new Termo();
                listConsulta.add(p.toLowerCase());
                srepc.add(p.toLowerCase());
                //System.out.println(p);
                cons.documento.add(p.toLowerCase());
                term.setPalavra(p.toLowerCase());
                docc.t.add(term);
                //doc.add(d);
                // }

            }
            docCons.add(cons);
            //System.out.println("array " + docum.t.toString());
            ldoc.add(docc);
            for (Doc dd : ldoc) {
                Doc de = new Doc();
                //System.out.println("add mais um");
                ldoc2.add(de);
            }
//       ldoc2 = ldoc;
//        for (int i = 0; i < ldoc2.size(); i++) {
//            int cont3 = 0;
//             System.out.println("Doc teste");
//            for (int j = 0; j < ldoc2.get(i).t.size(); j++) {
//                System.out.println(ldoc2.get(i).t.get(j).palavra + " fr " + ldoc2.get(i).t.get(j).freqRel + " cont " + ldoc2.get(i).t.get(j).quantidade);
//            }
//        }
            for (String palavra : srepc) {
                for (int i = 0; i < ldoc.size(); i++) {
                    int cont3 = 0;
                    boolean contem = false;
                    //System.out.println("Doc ");
                    for (int j = 0; j < ldoc.get(i).t.size(); j++) {
                        // System.out.println(ter.get(i).t.get(j).palavra);
                        //for(int k=0; k<ter.get(i).t.size();k++){
                        if (ldoc.get(i).t.get(j).palavra.equalsIgnoreCase(palavra)) {
                            cont3++;
                            contem = true;
                        }
                        //}

                        //ldoc.get(i).t.get(j).setFreqRel(freqRelativaConsulta(ldoc2, ldoc.get(i).t.get(j).palavra));
                        //ldoc.get(i).t.get(j).setQuantidade(cont3);
                        //cont3 = 0;
                    }
                    Termo t = new Termo();
                    if (contem) {

                        t.setFreqRel(freqRelativa(doc, palavra));
                        t.setPalavra(palavra);
                        t.setQuantidade(cont3);
                        ldoc2.get(i).t.add(t);
                    } else {
                        t.setPalavra(palavra);
                        t.setQuantidade(0);
                        t.setFreqRel(0);
                        t.setPeso(0);
                        t.setPesoIDF(0);
                        t.setPesoTF(0);
                        ldoc2.get(i).t.add(t);
                    }

                    contem = false;
                    // c++;
                }
            }
            for (int i = 0; i < ldoc2.size(); i++) {
                int cont3 = 0;
                System.out.println("Doc final");
                for (int j = 0; j < ldoc2.get(i).t.size(); j++) {
                    System.out.println(ldoc2.get(i).t.get(j).palavra + " fr " + ldoc2.get(i).t.get(j).freqRel + " cont " + ldoc2.get(i).t.get(j).quantidade);
                }
            }

            //System.out.println("nro de documentos"+ (float)ldoc2.size());
            //calculaTF(ldoc2);
            //calculaIDF(ldoc2, ter2.size());
            //calculaTFIDF(ldoc2);
//        //calculaCerto(ter2, (float)doc.size());
//        //System.out.println("TAMANHO DE DOC " + doc.size()+  " TER "+ter.size()+ " SREP "+ srep.size());
            //calculaNorma(ldoc2);
            //geraArquivo(ldoc2);
            //preencheVetorConsulta(ter2.get(0), ldoc2);
            //imprimeVetor(ldoc2);
            calculaSimilaridadeProb(ldoc2, ter2, ajusteFormula);

            imprimeOrdSim(ter2);
            System.out.println("Deseja realizar mais uma consulta: Y or N");
            teste = ler.nextLine();
        } while (teste.equalsIgnoreCase("Y"));

    }

    public static boolean verificaFormula(List<Doc> doc, float nroDoc) {

        for (Doc d : doc) {
            //System.out.println("Doc ");
            for (Termo t : d.t) {
                //System.out.println(t.palavra + " " + "ni = " + t.freqRel + " N = " + nroDoc );
                if (t.freqRel > nroDoc / 2) {
                    return true;
                }
            }
        }

        return false;

    }

    public static void calculaNorma(List<Doc> doc) {
        float norma = 0;
        int nroDoc = 0;
        for (Doc d : doc) {
            //System.out.println("doc ===== ");
            nroDoc++;
            for (Termo te : d.t) {
                norma += (te.peso * te.peso);
            }
            d.setNorma((float) Math.sqrt((float) norma));
            //System.out.println("Doc " + nroDoc + " norma: " + Math.sqrt((double) norma));
            //System.out.println("Doc " + nroDoc + " norma: " + d.norma);
            norma = 0;
        }
    }

    public static void calculaTFIDF(List<Doc> doc) {
        int contDoc = 1;
        for (Doc d : doc) {
            System.out.println("Doc " + contDoc);
            contDoc++;
            for (Termo t : d.t) {
                t.setPeso(t.pesoTF * t.pesoIDF);
                System.out.println(t.palavra + " q " + t.quantidade + " fr " + t.freqRel + " pesoTF " + t.pesoTF + " pesoidf " + t.pesoIDF + " tfidf " + t.peso);
            }
        }
    }

    public static void geraArquivo(List<Doc> doc) throws IOException {
        BufferedWriter esc = new BufferedWriter(new FileWriter("C:/Users/Tiago/Desktop/pratica4.txt"));
        int contDoc = 1;
        System.out.println("Gerando Arquivo:");
        for (Doc d : doc) {
            //System.out.println("Doc " + contDoc);
            System.out.println("Doc ----- " + contDoc);
            esc.write("Doc " + contDoc + "\r\n");
            contDoc++;
            for (Termo t : d.t) {
                //t.setPeso(t.pesoTF * t.pesoIDF);
                System.out.println(t.palavra + " " + t.peso);
                esc.write(t.palavra + " " + t.peso + "\r\n");
            }
        }
        esc.close();
    }

    public static void calculaTF(List<Doc> doc) {
        for (Doc d : doc) {
            for (Termo t : d.t) {
                if (t.quantidade > 0) {
                    t.setPesoTF((float) ((1 + (Math.log(t.quantidade)) / (Math.log(2)))));
                }
            }
        }
    }

    public static void calculaIDF(List<Doc> doc, float nroDoc) {
        for (Doc d : doc) {
            for (Termo t : d.t) {
                if (t.freqRel > 0) {
                    t.setPesoIDF((float) (Math.log(nroDoc / t.freqRel) / Math.log(2)));
                }
            }
        }
    }

    public static void calculaIDFConsulta(List<Doc> doc, float nroDoc) {
        for (Doc d : doc) {
            for (Termo t : d.t) {
                if (t.freqRel > 0) {
                    t.setPesoIDF((float) (Math.log(nroDoc / t.freqRel) / Math.log(2)));
                }
            }
        }
    }

    public static float freqRelativa(List<Vocabulario> doc, String pa) {
        float fr = 0;
        for (Vocabulario p : doc) {
            if (p.documento.contains(pa)) {
                fr++;
            }
        }
//        System.out.println("Fr "+fr);
        return fr;
    }

    public static void preencheVetor(List<Doc> doc) {
        int cont = 0;
        //int contDoc = 0;
        for (Doc d : doc) {
            d.vetor = new float[d.t.size()];
            for (Termo t : d.t) {
                d.vetor[cont] = t.peso;
                cont++;
            }
            cont = 0;
        }
    }

    public static void imprimeVetor(List<Doc> doc) {
        //int cont = 0;
        for (Doc d : doc) {
            //System.out.println("Doc");
            for (int i = 0; i < d.vetor.length; i++) {
                //System.out.println("vetor " + d.vetor[i]);
            }
        }
    }

    public static void preencheVetorConsulta(Doc doc, List<Doc> consulta) {
        int cont = 0;
        for (Doc d : consulta) {
            d.vetor = new float[doc.vetor.length];
            for (int i = 0; i < doc.vetor.length; i++) {
                //System.out.println("vetor "+d.vetor[i]);
                d.vetor[i] = 0;
            }
        }
        for (Doc d : consulta) {
            //d.vetor = new float[doc.vetor.length];
            // System.out.println("Doc");
            for (Termo t : d.t) {
                for (Termo tc : doc.t) {
                    //System.out.println("palavra cons " + t.palavra + " palavra cole " + tc.palavra);
                    if (t.palavra.equalsIgnoreCase(tc.palavra)) {
                        //System.out.println("Entrou " + " peso " + t.peso);
                        d.vetor[cont] = t.peso;
                    }
//                    else{
//                        d.vetor[cont] = 0;
//                    }
                    cont++;
                    //System.out.println("cont " + cont);
                }
                //System.out.println("vetor "+d.vetor[cont]);
                //cont++;
                cont = 0;
            }
            //cont = 0;
        }

    }

    public static void calculaSimilaridadeProb(List<Doc> consulta, List<Doc> colecao, boolean ajusteForm) {
        int contDoc = 0;
        float sim = 0;
        for (int i = 0; i < consulta.size(); i++) {//caso tenha mais de uma consulta
            for (Doc col : colecao) {
                System.out.println("Doc = " + contDoc);
                for (Termo t : col.t) {
                    System.out.println("Termo colecao = " + t.palavra + " freq relativa = " + t.freqRel + " quantidade = " + t.quantidade);
                    if (t.quantidade > 0) {//verifica se termo esta no documento
                        for (Termo tcons : consulta.get(i).t) {
                            System.out.println("Termo consulta = " + tcons.palavra);
                            if (t.palavra.equalsIgnoreCase(tcons.palavra)) {
                                if (ajusteForm) {//formula ajustada caso tenha algum ni > N/2
                                    System.out.println("Sim antes do calculo = " + sim);
                                    sim = sim + ((float) (Math.log((colecao.size() + 0.5) / (t.freqRel + 0.5)) / Math.log(2)));
                                    System.out.println("Sim parcial = " + sim);
                                } else {
                                    System.out.println("Entrou errado ");
                                    sim = sim + ((float) (Math.log((colecao.size() - t.freqRel + 0.5) / (t.freqRel + 0.5)) / Math.log(2)));
                                }
                            }
                        }
                    }
                }
                contDoc++;
                //System.out.println("Sim doc " + contDoc + " = " + sim);
                //System.out.println("norma doc " + contDoc + " = " + norma);
                col.similaridade = sim;
                System.out.println("Similarida resultado doc " + col.nroDoc + " = " + col.similaridade);
                sim = 0;
            }
        }
    }

    public static void calculaSimilaridade(List<Doc> consulta, List<Doc> colecao) {
        int contDoc = 0;
        float sim = 0;
        float norma = 0;
        for (int j = 0; j < consulta.size(); j++) {
            for (Doc col : colecao) {
                //System.out.println("Doc " + contDoc);
                for (int i = 0; i < colecao.get(0).vetor.length; i++) {
                    //System.out.println("lenght " + colecao.get(0).vetor.length);
                    //System.out.println("valor de i " + "mult " + col.vetor[i] * consulta.get(j).vetor[i]);
                    sim += col.vetor[i] * consulta.get(j).vetor[i];
                }
                contDoc++;
                norma = col.norma * consulta.get(j).norma;
                //System.out.println("Sim doc " + contDoc + " = " + sim);
                //System.out.println("norma doc " + contDoc + " = " + norma);
                col.similaridade = sim / norma;
                System.out.println("Similarida resultado doc " + contDoc + " = " + sim / norma);
                sim = 0;
            }
        }
    }

    public static void imprimeOrdSim(List<Doc> ldoc) {
        Collections.sort(ldoc);
        //int contDoc = 1;
        System.out.println("Docs ordenado");
        for (Doc doc : ldoc) {
            System.out.println("doc " + doc.nroDoc + " " + doc.similaridade);
            //contDoc++;
        }
    }
}
