package src.model;

import java.time.Duration;
import java.util.Calendar;

import src.model.agenda.Agenda;
import src.model.agenda.Evento;
import src.model.agenda.Monitoria;
import src.model.forum.Discussao;
import src.model.repositorio.Material;

/**
 * É um tipo de {@link Usuario} que busca no aplicativo ajuda para seus estudos. É o principal público alvo do aplicativo.
 * <p>
 * O {@code Estudante} encontra no aplicativo oportunidades de assistir a aulas de diversas disciplinas, participar de monitorias e fóruns de discussão, e encontrar conteúdos úteis para seu aprendizado.
 * 
 * @see src.model.agenda.Aula
 * @see src.model.agenda.Monitoria
 * @see src.model.forum.Discussao
 * @see src.model.repositorio.Material
 */
public class Estudante extends Usuario {
    public Estudante(String email, String senha, Perfil perfil) {
        super(email, senha, perfil);
    }

    /**
     * Este método garante a participação do {@link Estudante} no {@link Evento}.
     * <p>
     * Uma referência para o {@code Evento} é anotada na {@link Agenda} individual do {@code Estudante}. Além disso, uma referência para o {@code Estudante} é guardade no atributo {@link Evento#participantes} do {@code Evento} em questão.
     * 
     * @param evento
     * @return {@code true} se a inscrição ocorreu. {@code false} caso contrário
     */
    public boolean inscreverEvento(Evento evento) {
        boolean aindaTemVaga = evento.getParticipantes().size() < evento.getCapacidade();
        boolean eventoEstaNoFuturo = evento.getData().after(Calendar.getInstance());
        if (aindaTemVaga && eventoEstaNoFuturo) {
            this.getAgenda().getEventos().add(evento);
            evento.getParticipantes().add(this);
            return true;
        }
        return false;
    }

    /**
     * Este método exclui a participação do {@link Estudante} no {@link Evento}.
     * <p>
     * A referência para o {@code Evento} é retirada da {@link Agenda} individual do {@code Estudante}. Além disso, a referência para o {@code Estudante} é removida do atributo {@link Evento#participantes} do {@code Evento} em questão.
     * 
     * @param evento que o {@code Estudante} deseja se desinscrever
     * @return referência para o <b>evento</b>
     */
    public Evento desinscreverEvento(Evento evento) {
        this.getAgenda().getEventos().remove(evento);
        evento.getParticipantes().remove(this);
        return evento;
    }

    /**
     * Este método permite ao {@code Estudante} pedir para que uma {@link Monitoria} ocorra em um horário solicitado.
     * <p>
     * O pedido fica aguardando confirmação de algum {@link Instrutor} que esteja disposto. Uma referência para a {@code Monitoria} solicitada fica guardada no atributo estático {@link Monitoria#solicitacoes}.
     * 
     * @param data
     * @param nome
     * @param descricao
     * @param disciplina
     * @return referência para a {@code Monitoria} solicitada. {@code null} caso <b>data</b> esteja no passado
     */
    public Monitoria solicitarMonitoria(Calendar data, String nome, String descricao, Disciplina disciplina) {
        boolean monitoriaEstaNoFuturo = data.after(Calendar.getInstance());
        if (monitoriaEstaNoFuturo) {
            Monitoria solicitacao = new Monitoria(nome, descricao, disciplina, data);
            this.inscreverEvento(solicitacao);
            return solicitacao;
        }
        return null;
    }

    /**
     * Este método permite ao {@link Estudante} iniciar uma <em>thread</em> de {@link Discussao} referente a algum conteúdo que desejar. É útil para que ele possa tirar dúvidas de questões ou esclarecer algum tópico estudado.
     * 
     * @param texto
     * @param disciplina
     * @param referencia (opcional): material no qual a discussão se baseia
     * @return referência para a {@code Discussao} iniciada
     */
    public Discussao iniciarDiscussao(String texto, Disciplina disciplina, Material... referencia) {
        return new Discussao(texto, this, disciplina, referencia);
    }

    @Override
    public Evento criarEvento(String nome, String descricao, Disciplina disciplina, Calendar data, Duration duracao, int capacidade, String salaDeVideo, Instrutor instrutor, boolean ehAula) {
        return null;
    }

    @Override
    public boolean confirmarEvento(Evento evento) {
        return false;
    }
    
    @Override
    public boolean cancelarEvento(Evento evento) {
        return false;
    }

    @Override
    public String toString() {
        return "Estudante\n"+super.toString();
    }
}
