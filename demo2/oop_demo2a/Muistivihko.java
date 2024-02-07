import java.util.ArrayList;

/**
 * Luokka allintaa muistivihkoa
 */
public class Muistivihko {
    private String nimi;
    private String omistaja;
    private ArrayList<String> muistiinpanot;

    /**
     * Muodostaa uuden muistivihkon
     *
     * @param nimi muistivihkon nimi
     * @param omistaja muistivihkon omistajan nimi
     */
    public Muistivihko(String nimi, String omistaja) {
        this.nimi = nimi;
        this.omistaja = omistaja;
        muistiinpanot = new ArrayList<>();
    }

    /**
     * Palauttaa muistivihkon nimen
     *
     * @return muistivihkon käyttäjälle näkyvän nimen
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Asettaa muistivihkon nimen
     *
     * @param nimi muistivihkon uusi nimi
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }


    /**
     * Palauttaa muistivihkon omistajan nimen
     *
     * @return muistivihkon käyttäjälle näkyvän omistajan nimen
     */
    public String getOmistaja() {
        return omistaja;
    }

    /**
     * Asettaa muistivihkon omistajalle uuden nimen
     *
     * @param omistaja muistivihkon omistajan uusi nimi
     */
    public void setOmistaja(String omistaja) {
        this.omistaja = omistaja;
    }

    /**
     * Lisää uuden muistiinpanon muistivihkoon
     *
     * @param viesti lisättävä muistiinpano
     */
    public void lisaaMuistiinpano(String viesti) {
        this.muistiinpanot.add(viesti);
    }

    /**
     * Palauttaa muistivihkon muistiinpanojen määrän
     *
     * @return muistiinpanojen määrän
     */
    public int muistiinpanoja() {
        return muistiinpanot.size();
    }

    /**
     * Tulostaa kaikki muistiinpanot muistivihkosta
     */
    public void tulostaMuistiinpanot() {
        for(String viesti : muistiinpanot) {
            System.out.println(viesti);
        }
    }
}
