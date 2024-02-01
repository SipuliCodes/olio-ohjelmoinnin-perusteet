class Main {
    public static void main(String[] args) {
        System.out.println("hello");
    }
}

class Esiintyja{
    private String esiintymisala;
    private String esiintymisNimi;
    private String nimi;
    private double tuntipalkka;
    private double tienattuPalkkio;

    public Esiintyja(String esiintymisala, String esiintymisNimi, String nimi, double tuntipalkka) {
        this.esiintymisala = esiintymisala;
        this.esiintymisNimi = esiintymisNimi;
        this.nimi = nimi;
        this.tuntipalkka = tuntipalkka;
        tienattuPalkkio = 0;
    }

    public String getEsiintymisala() {
        return esiintymisala;
    }

    public void setEsiintymisala(String esiintymisala) {
        this.esiintymisala = esiintymisala;
    }

    public String getEsiintymisNimi() {
        return esiintymisNimi;
    }

    public void setEsiintymisNimi(String esiintymisNimi) {
        this.esiintymisNimi = esiintymisNimi;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public double getTuntipalkka() {
        return tuntipalkka;
    }

    public void setTuntipalkka(double tuntipalkka) {
        if(tuntipalkka >= 0.0) {
            this.tuntipalkka = tuntipalkka;
        }
    }

    public void addTienattuPalkkio(Tilaisuus tilaisuus) {
        tienattuPalkkio += tilaisuus.getKestoTunteina() * tuntipalkka;
    }
}

class Asiakas{
    private String sahkoposti;
    private String etunimi;
    private String sukunimi;
    private String puhelinnumero;
    private String salasana;
    private Tilaisuus tilaisuus;

    public Asiakas(String sahkoposti, String etunimi, String sukunimi, String puhelinnumero, String salasana) {
        this.sahkoposti = sahkoposti;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.puhelinnumero = puhelinnumero;
        this.salasana = salasana;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public String getPuhelinnumero() {
        return puhelinnumero;
    }

    public void setPuhelinnumero(String puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public Tilaisuus getTilaisuus() {
        return tilaisuus;
    }

    public void setTilaisuus(Tilaisuus tilaisuus) {
        this.tilaisuus = tilaisuus;
    }
}

class Tilaisuus{
    private String nimi;
    private String tilaisuusTyyppi;
    private Esiintyja esiintyja;
    private double kestoTunteina;

    public Tilaisuus(String nimi, String tilaisuusTyyppi, Esiintyja esiintyja, double kesto) {
        this.nimi = nimi;
        this.tilaisuusTyyppi = tilaisuusTyyppi;
        this.esiintyja = esiintyja;
        this.kesto = kesto;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getTilaisuusTyyppi() {
        return tilaisuusTyyppi;
    }

    public void setTilaisuusTyyppi(String tilaisuusTyyppi) {
        this.tilaisuusTyyppi = tilaisuusTyyppi;
    }

    public Esiintyja getEsiintyja() {
        return esiintyja;
    }

    public void setEsiintyja(Esiintyja esiintyja) {
        this.esiintyja = esiintyja;
    }

    public double getKestoTunteina() {
        return kestoTunteina;
    }
}