package src.model;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import src.model.agenda.Aula;
import src.model.agenda.Evento;
import src.model.agenda.Monitoria;
import src.model.repositorio.Diretorio;
import src.model.repositorio.Material;
import src.model.forum.Discussao;

/** 
 * É um tipo de {@link Usuario} que possui habilidades especiais, úteis para gerenciar o aplicativo e mantê-lo coeso.
 */
public class Moderador extends Usuario {
    public Moderador(String email, String senha, Perfil perfil) {
        super(email, senha, perfil);
    }
    
    @Override
    public Evento criarEvento(String nome, String descricao, Disciplina disciplina, Calendar data, Duration duracao, int capacidade, String salaDeVideo, Instrutor instrutor, boolean ehAula) {
        boolean dataEstaNoFuturo = data.after(Calendar.getInstance());
        if (dataEstaNoFuturo) {
            return ehAula ? new Aula(nome, descricao, disciplina, data, duracao, capacidade, salaDeVideo, instrutor)
                                    : new Monitoria(nome, descricao, disciplina, data, duracao, capacidade, salaDeVideo, instrutor);
        }
        return null;
    }
    
    /**
     * Este método permite ao {@link Moderador} cancelar um {@link Evento}.
     * <p>
     * Este método apenas altera o atributo {@code confirmado} do {@code Evento}.
     * 
     * @param evento
     * @return referência para o <b>evento</b>
     */
    public Evento cancelarEvento(Evento evento) {
        evento.setConfirmado(false);
        return evento;
    }

    /**
     * @param nome
     * @param descricao
     * @param disciplina
     * @param diretorioPai
     * @return referência para o {@link Diretorio} instanciado.
     */
    public Diretorio criarDiretorio(String nome, String descricao, Diretorio diretorioPai) {
        return new Diretorio(nome, descricao, diretorioPai);
    }

    /**
     * Este método permite ao {@link Moderador} aceitar o pedido de novo {@link Diretorio} feito por um {@link Instrutor}.
     * <p>
     * Este método torna o {@code Diretorio} visível e o remove da lista de {@code solicitacoes}.
     * 
     * @param solicitacao
     * @return referência para o <b>solicitacao</b>. {@code null} caso este não esteja em {@code Diretorio.solicitacoes}
     */
    public Diretorio aceitarDiretorio(Diretorio solicitacao) {
        boolean diretorioFoiSolicitado = Diretorio.getSolicitacoes().contains(solicitacao);
        if (diretorioFoiSolicitado) {
            solicitacao.setVisivel(true);
            Diretorio.getSolicitacoes().remove(solicitacao);
            return solicitacao;
        }
        return null;
    }

    /**
     * Este método permite ao {@link Moderador} remover um {@link Diretorio}, seus subdiretórios e todo {@link Material} nele contido do <b>Repositório</b> do aplicativo.
     * <p>
     * Algum {@code Material} pode não ser peridio se este estiver anexo em um {@link Evento} ou uma {@link Discussao}.
     * 
     * @param diretorio
     * @return referência para o <b>diretorio</b> removido
     */
    public Diretorio removerDiretorio(Diretorio diretorio, Diretorio root) {
        ArrayList<String> nomesDiretorios = new ArrayList<String>(Arrays.asList(diretorio.getLocal().split("/")));
        nomesDiretorios.remove(diretorio.getNome());
        String localDiretorioPai = String.join("/", nomesDiretorios) + "/";
        Diretorio diretorioPai = Diretorio.getDiretorioPorLocal(root, localDiretorioPai);
        diretorioPai.getSubdiretorios().remove(diretorio);
        diretorio.setLocal("");
        return diretorio;
    }
    
    /**
     * Este método permite ao {@link Moderador} adicionar algum {@link Material} em determinado {@link Diretorio} do aplicativo.
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
     * Este método permite ao {@link Moderador} remover um {@link Material} do <b>repositório</b> do aplicativo.
     * 
     * @param material
     * @return referência para o <b>material</b> removido
     */
    public Material removerMaterial(Material material, Diretorio root) {
        Diretorio diretorioPai = Diretorio.getDiretorioPorLocal(root, material.getLocal());
        diretorioPai.getMateriais().remove(material);
        material.setLocal("");
        return material;
    }

    @Override
    public String toString() {
        return " Moderador\n"+super.toString();
    }
}
