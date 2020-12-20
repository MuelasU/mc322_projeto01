package src.repositorio;

import java.util.ArrayList;
import java.util.Arrays;

import src.Estudante;
import src.Instrutor;

/**
 * É um local de armanezamento de {@link Material} que são úteis para o {@link Estudante} analisar melhor o conteúdo e para o {@link Instrutor} ter uma base de conteúdo para seu trabalho.
 * <p>
 * O conjunto de {@code Diretorio} e {@code Material} formam o <b>repositório</b> do aplicativo.
 */
public class Diretorio {
    private static int numeroDiretorios = 0;
    private static ArrayList<Diretorio> solicitacoes = new ArrayList<Diretorio>();
    private int id;
    private String nome;
    private String descricao;
    private String local;
    private boolean visivel;
    private ArrayList<Diretorio> subdiretorios;
    private ArrayList<Material> materiais;
    
    //#region Construtores
    /**
    * Usado apenas para determinar o diretório <em>root</em>.
    */
    public Diretorio() {
        id = numeroDiretorios;
        numeroDiretorios++;  
        nome = "root";
        descricao = "primeiro diretorio";
        local = this.nome + "/";
        visivel = true;
        subdiretorios = new ArrayList<Diretorio>();
        materiais = new ArrayList<Material>();
    }
    
    public Diretorio(String nome, String descricao, Diretorio diretorioPai) {
        id = numeroDiretorios;
        numeroDiretorios++;  
        this.nome = nome;
        this.descricao = descricao;
        this.local = diretorioPai.getLocal() + nome + "/";
        diretorioPai.getSubdiretorios().add(this);
        visivel = true;
        subdiretorios = new ArrayList<Diretorio>();
        materiais = new ArrayList<Material>();
    }
    
    //#region Getters and Setters
    public static int getNumeroDiretorios() {
        return numeroDiretorios;
    }
    
    public static void setNumeroDiretorios(int numeroDiretorios) {
        Diretorio.numeroDiretorios = numeroDiretorios;
    }
    
    public static ArrayList<Diretorio> getSolicitacoes() {
        return solicitacoes;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public boolean isVisivel() {
        return visivel;
    }
    
    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }
    
    /**
    * É no formato <em>root/dir/subdir/subsubdir/</em>
    * <p>
    * Cada nome entre as barras representam o atributo {@code nome} do {@link Diretorio}
    */
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
    
    public ArrayList<Diretorio> getSubdiretorios() {
        return subdiretorios;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public ArrayList<Material> getMateriais() {
        return materiais;
    }
    
    public void setMateriais(ArrayList<Material> materiais) {
        this.materiais = materiais;
    }
    //#endregion
    
    /**
    * Este método realiza a busca de um {@link Diretorio} a partir de uma {@code String} <b>local</b>, que possui um formato específico.
    * 
    * @param root : deve ser passado o único {@code Diretorio} que é pai de todos os outros
    * @param local : É no formato <em>root/dir/subdir/subsubdir/</em>
    * @return referência para  o {@code Diretorio} procurado. Caso os parâmetros estejam incorretos, acontece um erro.
    */
    public static Diretorio getDiretorioPorLocal(Diretorio root, String local) {
        //divide a String local nos delimitadores /
        ArrayList<String> nomeDosDiretorios = new ArrayList<String>(Arrays.asList(local.split("/")));
        //remove o primeiro nome
        nomeDosDiretorios.remove(nomeDosDiretorios.get(0));
        if(nomeDosDiretorios.isEmpty()) {   //se acabou a busca
            return root;    //retorna o diretorio
        }            
        else {  //se nao
            String nomeDoNovoRoot = nomeDosDiretorios.get(0);
            ArrayList<Diretorio> subDiretoriosDeRoot = root.getSubdiretorios();
            Diretorio novoRoot = null;
            for (Diretorio subdir : subDiretoriosDeRoot) { //busca o subdiretorio a partir do nome
                boolean achei = nomeDoNovoRoot.equals(subdir.getNome());
                novoRoot = achei ? subdir : null;
            }
            //determina a nova String local
            String novoLocal = String.join("/", nomeDosDiretorios) + "/";
            return getDiretorioPorLocal(novoRoot, novoLocal);   //faz a busca novamente
        }
    }

    @Override
    public String toString() {
    	String retorno = "Diretorio";
    	retorno += "\nNome: "+this.nome+" (id: "+this.id+")";
    	retorno += "\nDescricao: "+this.descricao;
    	retorno += "\nLocal: "+this.local;
    	retorno += "\nVisivel? "+this.isVisivel();
    	retorno += "\nSubdiretorios = {";
        for (int i=0; i<this.getSubdiretorios().size(); i++) {
            retorno = retorno + this.getSubdiretorios().get(i).getNome() + " ";
        }
        retorno += "}";
        retorno += "\nMateriais = {";
        for (int i=0; i<this.getMateriais().size(); i++) {
            retorno = retorno + this.getMateriais().get(i).getNome() + " ";
        }
        retorno += "}\n";
    	return retorno;
    }
}

