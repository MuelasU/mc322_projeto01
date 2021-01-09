package src.model;

public enum Estado {
    RIO_GRANDE_DO_SUL("RS", "Rio Grande do Sul"),
    SANTA_CATARINA("SC", "Santa Catarina"),
    PARANA("PR", "Parana"),
    MATO_GROSSO_DO_SUL("MS", "Mato Grosso do Sul"),
    SAO_PAULO("SP","Sao Paulo"),
    RIO_DE_JANEIRO("RJ","Rio de Janeiro"),
    MATO_GROSSO("MT","Mato Grosso"),
    GOIAS("GO","Goias"),
    MINAS_GERAIS("MG","Minas Gerais"),
    ESPIRITO_SANTO("ES","Espirito Santo"),
    RONDONIA("RO","Rondonia"),
    TOCANTINS("TO","Tocantins"),
    BAHIA("BA","Bahia"),
    SERGIPE("SE","Sergipe"),
    ALAGOAS("AL","Alagoas"),
    ACRE("AC","Acre"),
    AMAZONAS("AM","Amazonas"),
    PARA("PA","Para"),
    RORAIMA("RR","Roraima"),
    MARANHAO("MA","Maranhao"),
    PIAUI("PI","Piaui"),
    PERNAMBUCO("PE","Pernambuco"),
    PARAIBA("PB","Paraiba"),
    CEARA("CE","Ceará"),
    RIO_GRANDE_DO_NORTE("RN","Rio Grande do Norte"),
    DISTRITO_FEDERAL("DF","Distrito Federal"),
    AMAPA("AP","Amapa");

    private String sigla;
    private String nome;

    Estado(String sigla, String nome) {
        this.sigla = sigla;
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }
}
