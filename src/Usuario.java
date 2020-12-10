package src;

import java.util.Calendar;

import src.agenda.Agenda;

/**
 * Implementa o elemento fundamental deste aplicativo.
 * <p>
 * Esta classe representa o usuario que utiliza o aplicativo e se subdivide nas subclasses {@link Estudante}, {@link Instrutor} e {@link Moderador}, cada uma contendo suas especificações.
 */
public class Usuario {
    private static int numeroUsuarios = 0;
    private int id;
    private String email;
    private String senha;
    private Perfil perfil;
    private final Agenda agenda;
    private Calendar dataDeAtivacao;

    public Usuario(String email, String senha, Perfil perfil) {
        id = numeroUsuarios;
        numeroUsuarios++;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        agenda = new Agenda();
        dataDeAtivacao = Calendar.getInstance();
    }

    //#region Getters e Setters
    public static int getNumeroUsuarios() {
        return numeroUsuarios;
    }

    public static void setNumeroUsuarios(int numeroUsuarios) {
        Usuario.numeroUsuarios = numeroUsuarios;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public Calendar getDataDeAtivacao() {
        return dataDeAtivacao;
    }

    public void setDataDeAtivacao(Calendar dataDeAtivacao) {
        this.dataDeAtivacao = dataDeAtivacao;
    }
    //#endregion    

    @Override
    public String toString() {
        return "Usuario";
    }
}