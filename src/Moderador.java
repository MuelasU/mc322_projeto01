package src;

import java.time.Duration;
import java.util.Calendar;

import src.agenda.Aula;
import src.agenda.Evento;
import src.agenda.Monitoria;
import src.repositorio.Diretorio;
import src.repositorio.Material;

/** 
 * É um tipo de {@link Usuario} que possui habilidades especiais, úteis para gerenciar o aplicativo e mantê-lo coeso.
 */
public class Moderador extends Usuario {
    public Moderador(String email, String senha, Perfil perfil) {
        super(email, senha, perfil);
    }
    
    public Aula criarAula(String nome, String descricao, Disciplina disciplina, Calendar data, Duration duracao, int capacidade, String salaDeVideo, Instrutor instrutor) {
        return new Aula();
    }

    public Monitoria criarMonitoria(String nome, String descricao, Disciplina disciplina, Calendar data, Duration duracao, int capacidade, String salaDeVideo, Instrutor instrutor) {
        return new Monitoria();
    }
    
    public Evento cancelarEvento(Evento evento) {return evento;}

    public Diretorio criarDiretorio(String nome, String descricao, Disciplina disciplina, Diretorio diretorioPai) {return new Diretorio();}

    public Diretorio aceitarDiretorio(Diretorio diretorio) {return diretorio;}

    public Diretorio removerDiretorio(Diretorio diretorio) {return diretorio;}
    
    public Material adicionarMaterial(String arquivo, String nome, String descricao, Disciplina disciplina, Diretorio diretorio) {
        return new Material();
    }

    public Material removerMaterial(Material material) {return material;}

    @Override
    public String toString() {
        return super.toString() + " Moderador";
    }
}
