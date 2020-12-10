package src;

import java.util.Calendar;

import src.agenda.Agenda;
import src.agenda.Evento;
import src.agenda.Monitoria;
import src.forum.Discussao;
import src.repositorio.Material;

/**
 * É um tipo de {@link Usuario} que busca no aplicativo ajuda para seus estudos. É o principal público alvo do aplicativo.
 * <p>
 * O {@code Estudante} encontra no aplicativo oportunidades de assistir a aulas de diversas disciplinas, participar de monitorias e fóruns de discussão, e encontrar conteúdos úteis para seu aprendizado.
 * 
 * @see src.agenda.Aula
 * @see src.agenda.Monitoria
 * @see src.forum.Discussao
 * @see src.repositorio.Material
 */
public class Estudante extends Usuario {
    private int numeroAulas;
    private int numeroMonitorias;

    public Estudante(String email, String senha, Perfil perfil) {
        super(email, senha, perfil);
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
    //#endregion

    /**
     * Este método garante a participação do {@code Estudante} no {@link Evento}.
     * <p>
     * Uma referência para o {@code Evento} é anotada na {@link Agenda} individual do {@code Estudante}. Além disso, uma referência para o {@code Estudante} é guardade no atributo {@link Evento#participantes} do {@code Evento} em questão.
     * 
     * @param evento que o {@code Estudante} deseja participar
     * @return {@code true} se a inscrição ocorreu. {@code false} caso contrário
     */
    public boolean inscreverEvento(Evento evento) {return true;}

    /**
     * Este método exclui a participação do {@code Estudante} no {@link Evento}.
     * <p>
     * A referência para o {@code Evento} é retirada da {@link Agenda} individual do {@code Estudante}. Além disso, a referência para o {@code Estudante} é removida do atributo {@link Evento#participantes} do {@code Evento} em questão.
     * 
     * @param evento que o {@code Estudante} deseja se desinscrever
     * @return {@code true}
     */
    public boolean desinscreverEvento(Evento evento) {return true;}

    /**
     * Este método permite ao {@code Estudante} pedir para que uma {@link Monitoria} ocorra em um horário solicitado.
     * <p>
     * O pedido fica aguardando confirmação de algum {@link Instrutor} que esteja disposto. Uma referência para a {@code Monitoria} solicitada fica guardada no atributo estático {@link Monitoria#solicitacoes}.
     * 
     * @param data
     * @param nome
     * @param descricao
     * @param disciplina
     * @return referência para a {@code Monitoria} solicitada
     */
    public Monitoria solicitarMonitoria(Calendar data, String nome, String descricao, Disciplina disciplina) {return new Monitoria();}

    /**
     * Este método permite ao {@code Estudante} iniciar uma <em>thread</em> de {@link Discussao} referente a algum conteúdo que desejar. É útil para que ele possa tirar dúvidas de questões ou esclarecer algum tópico estudado.
     * 
     * @param texto
     * @param disciplina
     * @param linkMaterial (opcional): material no qual a discussão se baseia
     * @return referência para a {@code Discussao} iniciada
     */
    public Discussao iniciarDiscussao(String texto, Disciplina disciplina, Material... linkMaterial) {return new Discussao();}

    @Override
    public String toString() {
        return super.toString() + " Estudante";
    }
}
