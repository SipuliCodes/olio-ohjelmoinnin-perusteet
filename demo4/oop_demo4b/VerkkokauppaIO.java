import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Luokkaa käytetään verkkokaupan tietojen tallentamiseen
 * ja lataamiseen tietovarastosta.
 *
 * @author Erkki
 */
public class VerkkokauppaIO {

    public static void main(String[] args) {
        // Tähän voi kirjoittaa koodia, jolla testata
        // kirjoitus- ja lukumetodien toimintaa helposti
        Asiakas yksi = new Asiakas("1", "Pena", 155.0);
        Asiakas kaksi = new Asiakas("2", "Päivi", 1500.50);
        KantaAsiakas kolme = new KantaAsiakas("3", "Reijo", 25000, 50);
        ArrayList<Asiakas> asiakkaat = new ArrayList<>(List.of(yksi, kaksi, kolme));

        kirjoitaAsiakkaat(asiakkaat, "asiakkaat.txt");

        ArrayList<Asiakas> säilötytAsiakkaat = lueAsiakkaat("asiakkaat.txt");
        säilötytAsiakkaat.stream().forEach( asiakas -> {
            if (asiakas.getClass() == Asiakas.class) {
                System.out.println("asiakasnro: " + asiakas.getAsiakasNumero() +
                        " nimi: " + asiakas.getNimi() + " ostot: " + asiakas.getOstojaTehty());
            }
            else if(asiakas.getClass() == KantaAsiakas.class){
                System.out.println("asiakasnro: " + asiakas.getAsiakasNumero() +
                        " nimi: " + asiakas.getNimi() + " ostot: " + asiakas.getOstojaTehty()
                + " alennus: " + asiakas.getAlennusprosentti());
            }
            }
        );
    }

    private static final String EROTIN = ";";

    public static void kirjoitaTiedosto(String tiedostonNimi,
                                        String sisalto) {
        try (PrintWriter tiedosto = new PrintWriter(tiedostonNimi)) {
            tiedosto.write(sisalto);
        } catch (FileNotFoundException e) {
            System.out.println("Tapahtui virhe: " + e);
        }
    }

    /**
     * Lukee annetun nimisen tiedoston sisällön ja palauttaa sen listassa.
     * Jokainen listan alkio vastaa yhtä tiedoston riviä
     *
     * @param tiedostonNimi luettavan tiedoston nimi
     * @return tiedoston sisällön listana
     */
    public static ArrayList<String> lueTiedosto(String tiedostonNimi) {
        ArrayList<String> data = new ArrayList<>();
        try (Scanner lukija = new Scanner(new File(tiedostonNimi))) {
            while (lukija.hasNextLine()) {
                data.add(lukija.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Tapahtui virhe: " + e);
        }
        return data;
    }

    /**
     * Kirjoittaa asiakaslistan annetun nimiseen tiedostoon.
     *
     * @param asiakasLista  lista kirjoitettavista asiakkaista.
     * @param tiedostonNimi kirjoitettavan tiedoston nimi
     */
    public static void kirjoitaAsiakkaat(ArrayList<Asiakas> asiakasLista,
                                         String tiedostonNimi) {
        String data = "";
        for (Asiakas asiakas : asiakasLista) {
            data += asiakas.getData(VerkkokauppaIO.EROTIN);
            data += "\n";
        }
        // Poistetaan viimeinen "turha" rivinvaihto
        if (data.length() > 0) {
            data = data.substring(0, data.length() - 1);
        }
        kirjoitaTiedosto(tiedostonNimi, data);
    }

    /**
     * Palauttaa uuden asiakasolion annetun datarivin perusteella.
     * Rivillä tulee olla asiakasnumero, nimi ja tehdyt ostot tässä
     * järjestyksessä erotettuna merkillä
     * <code>VerkkokauppaIO.EROTIN</code>
     *
     * @param data datarivi, josta tiedot parsitaan
     * @return uuden Asiakas-olion dataan perustuen
     */
    public static Asiakas parsiAsiakas(String data) {
        String[] tiedot = data.split(VerkkokauppaIO.EROTIN);
        // Tässä vaiheessa tulee tietää tietojen järjestys
        if(tiedot.length == 3) {
            String asNro = tiedot[0];
            String nimi = tiedot[1];
            double ostot = Double.parseDouble(tiedot[2]);

            return new Asiakas(asNro, nimi, ostot);
        }

        String asNro = tiedot[0];
        String nimi = tiedot[1];
        double ostot = Double.parseDouble(tiedot[2]);
        int alennusprosentti = Integer.parseInt(tiedot[3]);

        return new KantaAsiakas(asNro, nimi, ostot, alennusprosentti);

    }

    /**
     * Metodi lukee asiakkaat annetun nimisestä tiedostosta ja
     * palauttaa sisällön mukaisen listan Asiakas-olioita.
     *
     * @param tiedostonNimi luettavan tiedoston nimi
     * @return tiedostosta luetut asiakasoliot listana
     */
    public static ArrayList<Asiakas> lueAsiakkaat(String tiedostonNimi) {
        ArrayList<Asiakas> asiakkaat = new ArrayList<>();
        ArrayList<String> data = lueTiedosto(tiedostonNimi);
        for (String adata : data) {
            Asiakas as = parsiAsiakas(adata);
            asiakkaat.add(as);
        }

        return asiakkaat;
    }

    /**
     * Kirjoittaa tuotelistan annetun nimiseen tiedostoon.
     *
     * @param tuotelista    lista tuotteista
     * @param tiedostonNimi kirjoitettavan tiedoston nimi
     */
    public static void kirjoitaTuotteet(ArrayList<Tuote> tuotelista, String tiedostonNimi) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(
                             new FileOutputStream(tiedostonNimi))) {
            oos.writeObject(tuotelista);
        } catch (IOException e) {
            System.out.println("Tapahtui virhe: " + e);
        }
    }

    /**
     * Lukee tuotelistan tiedostosta
     *
     * @param tiedostonNimi tiedoston nimi
     * @return listan tuotteita
     */
    public static ArrayList<Tuote> lueTuotteet(String tiedostonNimi) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tiedostonNimi))) {
            ArrayList<Tuote> tlista = (ArrayList<Tuote>) ois.readObject();
            return tlista;
        } catch (IOException e) {
            System.out.println("Tapahtui virhe: " + e);
        } catch (ClassNotFoundException e) {
            // Tämä virhe tulee, jos luettu tieto ei ole yhteensopiva
            // sen luokan kanssa, jonka tyyppiseksi se yritetään muuntaa
            System.out.println("Tapahtui virhe: " + e);
        }
        return null;
    }

    public static void kirjoitaMyyjat(ArrayList<Myyja> myyjaLista, String tiedostonNimi) {
        String data = "";
        for(Myyja myyja: myyjaLista) {
            data += myyja.getData(VerkkokauppaIO.EROTIN);
            data += "\n";
        }
        if (data.length() > 0) {
            data = data.substring(0, data.length() - 1);
        }
        kirjoitaTiedosto(tiedostonNimi, data);
    }

    public static Myyja parsiMyyja(String data) {
        String[] tiedot = data.split(VerkkokauppaIO.EROTIN);

        String tunniste = tiedot[0];
        String nimi = tiedot[1];
        double provisiot = Double.parseDouble(tiedot[2]);

        return new Myyja(tunniste, nimi, provisiot);
    }

    public static ArrayList<Myyja> lueMyyjat(String tiedostonNimi) {
        ArrayList<Myyja> myyjat = new ArrayList<>();
        ArrayList<String> data = lueTiedosto(tiedostonNimi);
        for (String mdata: data) {
            Myyja my = parsiMyyja(mdata);
            myyjat.add(my);
        }
        return myyjat;
    }

    public static void kirjoitaOstotapahtumat(ArrayList<Ostotapahtuma> ostotapahtumaLista, String tiedostonNimi) {
        try(ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(tiedostonNimi))) {
            oos.writeObject(ostotapahtumaLista);
        } catch (IOException e) {
            System.out.println("Tapahtui virhe: " + e);
        }
    }

    public static ArrayList<Ostotapahtuma> lueOstotapahtumat(String tiedostonNimi) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tiedostonNimi))){
            ArrayList<Ostotapahtuma> olista = (ArrayList<Ostotapahtuma>) ois.readObject();
            return olista;
        } catch (IOException e) {
            System.out.println("Tapahtui virhe: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Tapahtui virhe: " + e );
        }
        return null;
    }
}
