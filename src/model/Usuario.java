package src.model;

import java.time.Duration;
import java.util.Calendar;

import src.model.agenda.Agenda;
import src.model.agenda.Evento;

/**
 * Implementa o elemento fundamental deste aplicativo.
 * <p>
 * Esta classe representa o usuario que utiliza o aplicativo e se subdivide nas subclasses {@link Estudante}, {@link Instrutor} e {@link Moderador}, cada uma contendo suas especificações.
 */
public abstract class Usuario {
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

    /**
     * Este método permite ao {@link Usuario} agendar um {@link Evento}.
     *  
     * @param nome
     * @param descricao
     * @param disciplina
     * @param data
     * @param duracao
     * @param capacidade
     * @param salaDeVideo
     * @param instrutor
     * @param ehAula
     * @return referência para o {@code Evento} instanciada. {@code null} caso a {@code Evento} seja impossível de ser realizada.
     */
    public abstract Evento criarEvento(String nome, String descricao, Disciplina disciplina, Calendar data, Duration duracao, int capacidade, String salaDeVideo, Instrutor instrutor, boolean ehAula);

    @Override
    public String toString() {
        String retorno = "Usuario";
        retorno += "\nId: "+this.getId();
        retorno += "\nEmail: "+this.getEmail();
        retorno += "\nSenha: "+this.getSenha();
        retorno += "\nPerfil: "+this.getPerfil();
        retorno += this.getAgenda();
        retorno += "\nData de ativacao: "+this.getDataDeAtivacao().get(Calendar.DATE)+"/"+this.getDataDeAtivacao().get(Calendar.MONTH)+"/"+this.getDataDeAtivacao().get(Calendar.YEAR)+"\n";
        return retorno;
    }
}