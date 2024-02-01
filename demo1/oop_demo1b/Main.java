class Main {
    public static void main(String[] args) {

    }
}

class Esiintyja{
    private String esiintymisala;
    private String esiintymisNimi;
    private String nimi;
    private double tuntipalkka;
    private double tienattuPalkkio;
}

class Asiakas{
    private String sahkoposti;
    private String etunimi;
    private String sukunimi;
    private String puhelinnumero;
    private String salasana;
    private Tilaisuus tilaisuus;
}

class Tilaisuus{
    private String nimi;
    private String tilaisuusTyyppi;
    private Esiintyja esiintyja;
}