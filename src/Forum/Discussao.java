package src.forum;

import java.util.ArrayList;
import java.util.Arrays;

import src.Disciplina;
import src.Usuario;
import src.repositorio.Material;

/**
 * É um tipo de {@link Mensagem} que visa estabelecer uma discussão sobre algum conteúdo.
 * <p>
 * O conjunto de discussões forma o <b>fórum</b> do aplicativo.
 */
public class Discussao extends Mensagem{
    private static int numeroDiscussoes = 0;
    private int id;
	private Disciplina disciplina;
	private boolean resolvida;
	private ArrayList<Comentario> comentarios;
	private Comentario melhorSolucao;
	private ArrayList<Material> referencia;
	
	public Discussao(String texto, Usuario dono, Disciplina disciplina, Material... referencia){
		super(texto, dono);
        id = numeroDiscussoes;
        numeroDiscussoes++;
		this.disciplina = disciplina;
		resolvida = false;
        comentarios = new ArrayList<Comentario>();
        this.referencia = new ArrayList<Material>(Arrays.asList(referencia));
	}
	
    //#region Getters e Setters
    public static int getNumeroDiscussoes() {
        return numeroDiscussoes;
    }

    public static void setNumeroDiscussoes(int numeroDiscussoes) {
        Discussao.numeroDiscussoes = numeroDiscussoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public boolean isResolvida() {
        return resolvida;
    }

    public void setResolvida(boolean resolvida) {
        this.resolvida = resolvida;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Comentario getMelhorSolucao() {
        return melhorSolucao;
    }

    public void setMelhorSolucao(Comentario melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }

    public ArrayList<Material> getReferencia() {
        return referencia;
    }

    public void setReferencia(ArrayList<Material> referencia) {
        this.referencia = referencia;
    }
    //#endregion
	
    /**
     * Este método adiciona um novo {@link Comentario} na {@link Discussao}.
     * 
     * @param texto
     * @param usuario
     * @return referência para o {@code Comentario} adicionado
     */
	public Comentario comentar(String texto, Usuario usuario) {
		Comentario comentario = new Comentario(texto, usuario);
        this.comentarios.add(comentario);
        return comentario;
	}
	
    /**
     * Este método resolve uma {@link Discussao}, determinando uma resposta como a {@code melhorSolucao}.
     * <p>
     * Apenas o {@code dono} da {@code Discussao} tem permissão para resolvê-la.
     * 
     * @param usuario
     * @return {@code true} se a {@code Discussao} foi resolvida. {@code false} caso contrário.
     */
	public boolean resolver(Usuario usuario, Comentario comentario) {
        boolean usuarioEDono = usuario == getDono();
        boolean comentarioEstaNaDiscussao = getComentarios().contains(comentario);
		if (usuarioEDono && comentarioEstaNaDiscussao) {
			setMelhorSolucao(comentario);
			setResolvida(true);
			return true;
        }
        return false;	
	}
	
	@Override
	public String toString() {
		String out = "";
		out += getTexto() + "\n";
		out += "Id da Discussao: " + getId() + "\n";
		out += "Respostas a discussao: ";
        for (int i=0; i<this.getComentarios().size(); i++) {
            out += "\n"+this.getComentarios().get(i).getTexto()+"; Gostei: "+this.getComentarios().get(i).getUpvotes()+"; Nao gostei: "+this.getComentarios().get(i).getDownvotes();
        }
        out += "\n";
		if (resolvida) {
			out += "Melhor Solucao: " + getMelhorSolucao().getTexto();
		}
		return out;
	}
}
