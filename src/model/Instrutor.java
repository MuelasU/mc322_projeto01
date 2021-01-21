package src.model;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;

import src.model.agenda.Aula;
import src.model.agenda.Evento;
import src.model.agenda.Monitoria;
import src.model.repositorio.*;

/**
 * É um tipo de {@link Usuario} que possui habilidades necessárias para ministrar aulas, realizar monitorias e disponibilizar conteúdos. Este usuário é responsável por fazer o trabalho docente no contexto educacional do aplicativo.
 */
public class Instrutor extends Usuario {
    private ArrayList<Disciplina> areasDeInteresse;
    private final Avaliacao avaliacao;

    public Instrutor(String email, String senha, Perfil perfil, ArrayList<Disciplina> areasDeInteresse) {
        super(email, senha, perfil);
        this.areasDeInteresse = areasDeInteresse;
        avaliacao = new Avaliacao();
    }

    //#region Getters e Setters
    public ArrayList<Disciplina> getAreasDeInteresse() {
        return areasDeInteresse;
    }

    public void setAreasDeInteresse(ArrayList<Disciplina> areasDeInteresse) {
        this.areasDeInteresse = areasDeInteresse;
    }
    
    public Avaliacao getAvaliacao() {
        return avaliacao;
    }
    //#endregion

    @Override
    public Evento criarEvento(String nome, String descricao, Disciplina disciplina, Calendar data, Duration duracao, int capacidade, String salaDeVideo, Instrutor instrutor, boolean ehAula) {
        boolean dataEstaNoFuturo = data.after(Calendar.getInstance());
        if (dataEstaNoFuturo) {
            return ehAula ? new Aula(nome, descricao, disciplina, data, duracao, capacidade, salaDeVideo, this)
                             : new Monitoria(nome, descricao, disciplina, data, duracao, capacidade, salaDeVideo, this);
        }
        return null;
    }
    
    /**
     * Este método permite ao {@link Instrutor} cancelar um {@link Evento}.
     * <p>
     * Este método apenas altera o atributo {@code confirmado} do {@code Evento}.
     * 
     * @param evento
     * @return {@code true} caso o {@code Instrutor} seja o responsável pelo {@code Evento}. {@code false} caso contrario 
     */
    public boolean cancelarEvento(Evento evento) {
        boolean souInstrutorResponsavel = evento.getInstrutor() == this;
        if (souInstrutorResponsavel) {
            evento.setConfirmado(false);
        }
        return souInstrutorResponsavel;
    }

    /**
     * Este método permite ao {@link Instrutor} aceitar o pedido de {@link Monitoria} feito por um {@link Estudante}.
     * 
     * @param solicitacao
     * @return referência para a <b>solicitacao</b>. {@code null} caso este não esteja em {@code Monitoria.solicitacoes}
     */
    public Monitoria aceitarMonitoria(Monitoria solicitacao, Duration duracao, int capacidade, String salaDeVideo) {
        boolean monitoriaFoiSolicitada = Monitoria.getSolicitacoes().contains(solicitacao);
        if (monitoriaFoiSolicitada) {
            solicitacao.setInstrutor(this);
            solicitacao.setCapacidade(capacidade);
            solicitacao.setSalaDeVideo(salaDeVideo);
            solicitacao.setConfirmado(true);           
            solicitacao.setDuracao(duracao); 
            Monitoria.getSolicitacoes().remove(solicitacao);
            return solicitacao;
        }
        return null;
    }

    /**
     * Este método permite ao {@link Instrutor} adicionar algum {@link Material} no <b>repositório</b> do aplicativo.
     * 
     * @param arquivo
     * @param nome
     * @param descricao
     * @param disciplina
     * @param diretorio
     * @return referência para o {@code Material} instanciado
     */
    public Material adicionarMaterial(File arquivo, String nome, String descricao, Disciplina disciplina, Diretorio diretorio) {
        return new Material(arquivo, nome, descricao, disciplina, diretorio, this);
    }

    /**
     * Este método permite ao {@link Instrutor} solicitar a criação de um {@link Diretorio} no <b>repositório</b> do aplicativo.
     * <p>
     * O pedido fica aguardando aceitação de algum {@link Moderador}. Uma referência para o {@code Diretorio} solicitado fica guardado no atributo estático {@code Diretorio.solicitacoes}.
     * 
     * @param nome
     * @param descricao
     * @param disciplina
     * @param diretorioPai
     * @return referência para o {@code Diretorio} solicitado
     */
    public Diretorio solicitarDiretorio(String nome, String descricao, Diretorio diretorioPai) {
        Diretorio solicitacao = new Diretorio(nome, descricao, diretorioPai);
        solicitacao.setVisivel(false);
        Diretorio.getSolicitacoes().add(solicitacao);
        return solicitacao;
    }

    @Override
    public String toString() {
		String retorno = "Instrutor\n";
		retorno += super.toString();
		retorno += "Areas de interesse:"+ this.getAreasDeInteresse()+"\n";
		return retorno;
    }
}
