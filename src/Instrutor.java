package src;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;

import src.agenda.Aula;
import src.agenda.Evento;
import src.agenda.Monitoria;
import src.repositorio.*;

/**
 * É um tipo de {@link Usuario} que possui habilidades necessárias para ministrar aulas, realizar monitorias e disponibilizar conteúdos. Este usuário é responsável por fazer o trabalho docente no contexto educacional do aplicativo.
 */
public class Instrutor extends Usuario {
    private int numeroAulas;
    private int numeroMonitorias;
    private ArrayList<Disciplina> areasDeInteresse;
    private final Avaliacao avaliacao;

    public Instrutor(String email, String senha, Perfil perfil, ArrayList<Disciplina> areasDeInteresse) {
        super(email, senha, perfil);
        this.areasDeInteresse = areasDeInteresse;
        avaliacao = new Avaliacao();
        numeroAulas = 0; 
        numeroMonitorias = 0; 
    }

    //#region Getters e Setters
    public int getNumeroAulas() {
        return numeroAulas;
    }

    public void setNumeroAulas(int numeroAulas) {
        this.numeroAulas = numeroAulas;
    }

    public int getNumeroMonitorias() {
        return numeroMonitorias;
    }

    public void setNumeroMonitorias(int numeroMonitorias) {
        this.numeroMonitorias = numeroMonitorias;
    }

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
    
    public Aula criarAula(String nome, String descricao, Disciplina disciplina, Calendar data, Duration duracao, int capacidade, String salaDeVideo) {
        return new Aula();
    }

    public Monitoria criarMonitoria(String nome, String descricao, Disciplina disciplina, Calendar data, Duration duracao, int capacidade, String salaDeVideo) {
        return new Monitoria();
    }
    
    public Evento cancelarEvento(Evento evento) {return evento;}

    public Monitoria aceitarMonitoria(Monitoria solicitacao) {return solicitacao;}

    public Material adicionarMaterial(String arquivo, String nome, String descricao, Disciplina disciplina, Diretorio diretorio) {
        return new Material();
    }

    public Diretorio solicitarDiretorio(String nome, String descricao, Disciplina disciplina, Diretorio diretorioPai) {return new Diretorio();}

    @Override
    public String toString() {
        return super.toString() + " Instrutor";
    }
}
