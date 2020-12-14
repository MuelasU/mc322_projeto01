package src.repositorio;

import java.util.ArrayList;

import src.Disciplina;
import src.Usuario;
import src.forum.Comentario;

public class Material {
    
    private static int numeroMateriais = 0;
    private int id;
    private String arquivo;
    private String nome;
    private String descricao;
    private Disciplina disciplina;
    private String local;
    private ArrayList<Comentario> comentarios;
    private final Usuario dono;
    
    public Material(String arquivo, String nome, String descricao, Disciplina disciplina, Diretorio diretorio, Usuario dono) {
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

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
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
    
    // /**
    // * Este m�todo move um material para um novo diretorio. 
    // * Faz a checagem se o usu�rio � o dono ou um moderador.
    // * Encontra o diret�rio antigo do material.
    // * Pega o local do novo diretorio
    // * Seta este local como o novo local do material
    // * Adiciona ao diretorio este material
    // * Remove do diretorio antigo o material
    // */
    // public void mover(Diretorio diretorio, Usuario user, Material material) {
    //     if (user == getDono() || user instanceof Moderador) {
    //         diretorio_antigo = getDiretorioPorLocal(getLocal());
    //         novo_local = diretorio.getLocal();
    //         setLocal(novo_local);
    //         diretorio.addMaterial(this.material);
    //         diretorio_antigo.removeMaterial(this.material);
    //     }
    // }
    
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
}
