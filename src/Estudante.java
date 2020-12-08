package src;

import src.Agenda.Evento;
import src.Agenda.Monitoria;
import src.Forum.Discussao;
import src.Repositorio.*;

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

    public boolean inscreverEvento(Evento evento) {return true;}

    public boolean desinscreverEvento(Evento evento) {return true;}

    public Monitoria solicitarMonitoria() {return new Monitoria();}

    public Discussao iniciarDiscussao(String texto, Disciplina disciplina, Material... linkMaterial) {return new Discussao();}
}
