package src.model.repositorio;

import java.io.File;
import java.util.ArrayList;

import src.model.Comentavel;
import src.model.Disciplina;
import src.model.Moderador;
import src.model.Usuario;
import src.model.forum.Comentario;

/**
 * Esta classe representa algum conteúdo que deve ser compartilhado no aplicativo.
 */
public class Material implements Comentavel{
    private static int numeroMateriais = 0;
    private int id;
    // private String arquivo;
    private File arquivo;
    private String nome;
    private String descricao;
    private Disciplina disciplina;
    private String local;
    private ArrayList<Comentario> comentarios;
    private final Usuario dono;
    
    public Material(/*String arquivo, */File arquivo, String nome, String descricao, Disciplina disciplina, Diretorio diretorio, Usuario dono) {
        id = numeroMateriais;
        numeroMateriais++;
        this.arquivo = arquivo;
        this.nome = nome;
        this.descricao = descricao;
        this.disciplina = disciplina;
        this.local = diretorio.getLocal();
        diretorio.getMateriais().add(this);
        this.dono = dono;
        comentarios = new ArrayList<Comentario>();
    }

    //#region Getters and Setters
    public static int getNumeroMateriais() {
        return numeroMateriais;
    }

    public static void setNumeroMateriais(int numeroMateriais) {
        Material.numeroMateriais = numeroMateriais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public /*String*/File getArquivo() {
        return arquivo;
    }

    public void setArquivo(/*String*/File arquivo) {
        this.arquivo = arquivo;
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

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Usuario getDono() {
        return dono;
    }
    //#endregion
    
    /**
     * Este método move um {@link Material} para um novo {@link Diretorio}.
     * 
     * @param destino
     * @param root
     * @param solicitante
     * @return {@code true} se moveu. {@code false} caso contrário, quando o <b>solicitante</b> não tem permissão.
     */
    public boolean mover(Diretorio destino, Diretorio root, Usuario solicitante) {
        boolean solicitanteehModerador = solicitante instanceof Moderador;
        boolean solicitanteEhDono = solicitante == this.getDono();
        if (solicitanteEhDono || solicitanteehModerador) {
            //remove o material do diretorio atual
            Diretorio diretorioPai = Diretorio.getDiretorioPorLocal(root, this.getLocal());
            diretorioPai.getMateriais().remove(this);
            //adiciona o material no diretorio destino
            destino.getMateriais().add(this);
            this.local = destino.getLocal();
            return true;
        }
        return false;
    }
    
    @Override
	public Comentario comentar(String texto, Usuario usuario) {
		Comentario comentario = new Comentario(texto, usuario);
        this.comentarios.add(comentario);
        return comentario;
    }
    
    @Override
	public String toString() {
		String retorno = "Material";
		retorno += "\nNome: "+this.nome+" (id: "+this.id+")";
    	retorno += "\nDescricao: "+this.descricao;
    	retorno += "\nDisciplina: "+this.disciplina;
    	retorno += "\nLocal: "+this.local;
    	retorno += "\nArquivo: "+this.arquivo;
    	retorno += "\nDono: "+this.dono.getPerfil().getNome();
    	retorno += "\nComentarios = {";
        for (int i=0; i<this.getComentarios().size(); i++) {
            retorno = retorno + this.getComentarios().get(i).getTexto() + " ";
        }
        retorno += "}\n";
        return retorno;
	}
}
