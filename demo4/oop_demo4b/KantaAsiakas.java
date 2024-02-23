import java.io.Serial;

public class KantaAsiakas extends Asiakas{
    @Serial
    private static final long serialVersionUID = -4128309929504389010L;
    private int alennusprosentti;

    public KantaAsiakas(String asiakasNumero, String nimi,
                        double ostojaTehty, int alennusprosentti) {
        super(asiakasNumero, nimi, ostojaTehty);
        this.alennusprosentti = alennusprosentti;
    }

    @Override
    public int getAlennusprosentti() {
        return super.getAlennusprosentti() + alennusprosentti;
    }

    public void setAlennusprosentti(int alennusprosentti) {
        this.alennusprosentti = alennusprosentti;
    }

    @Override
    public String toString() {
        return super.toString() + ", alennusprosentti: " + alennusprosentti;
    }

    public String getData(String erotinmerkki){
        String data = super.getData(erotinmerkki);
        data += erotinmerkki + alennusprosentti;

        return data;
    }
}
