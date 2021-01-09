package src.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Caracteriza um {@link Usuario} registrado no aplicativo.
 */
public class Perfil {
    private String nome;
    private String descricao;
    private String foto;
    private Estado estado;
    private String cidade;
    private String telefone;
    private Escolaridade escolaridade;
    private Calendar dataDeNascimento;

    public Perfil(String nome, Estado estado, String cidade, Escolaridade escolaridade, Calendar dataDeNascimento) {
        this.nome = nome;
        this.estado = estado;
        this.cidade = cidade;
        this.escolaridade = escolaridade;
        this.dataDeNascimento = dataDeNascimento;
    }

    //#region Getters e Setters
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public Calendar getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Calendar dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
    //#endregion

    @Override
    public String toString() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/mm/yyyy");
        String retorno = "Perfil";
        retorno += "\nNome: "+this.getNome();
        retorno += "\nLocal: "+this.getCidade()+"-"+this.getEstado().getSigla();
        retorno += "\nEscolaridade: "+this.getEscolaridade().getDescricao();
        retorno += "\nData de Nascimento: " + fmt.format(this.getDataDeNascimento().getTime()) + "\n";
        return retorno;
    }
}
