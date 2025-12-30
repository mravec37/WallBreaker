
/**
 * Obdĺžnik, s ktorým možno pohybovať a nakreslí sa na plátno.
 * 
 * @author  Michael Kolling, David J. Barnes, Tomáš Mravec
 * @version 1.0  (2019)
 */

public class Stena {
    private Obdlznik obdlznik;

    /**
     * Vytvor novú stenu preddefinovanej farby na preddefinovanej pozícii.
     */ 
    public Stena(int pA, int pB, int paX, int paY, String paFarba, boolean jeViditelny) {
        this.obdlznik = new Obdlznik(pA, pB, paX, paY, paFarba, jeViditelny);
    }

    /**
     * (Stena) Zobraz sa.
     */
   /* public void setJeViditelny(boolean viditelny) {
        this.obdlznik.setJeViditelny(viditelny);
    }*/
    /**
     * (Stena) Vráti viditelnosť.
     */
    public boolean getJeViditelny() {
        return this.obdlznik.getJeViditelny();
    }
    /*public setPlusX(int x) {
    /**
     * (Stena) Vráti farbu.
     */
    public String getFarba() {
        return this.obdlznik.getFarba();
    }
    /**
     * (Stena) Zobrazí sa na plátno.
     */
    public void zobraz() {
        this.obdlznik.zobraz();
    }
    /**
     * (Stena) Vráť x-ovu súradnicu.
     */
    public int getX() {
        return this.obdlznik.getX();
    }
    /**
     * (Stena) Vráť y-ovu súradnicu.
     */
    public int getY() {
        return this.obdlznik.getY();
    }
    /**
     * (Stena) Vráť stranu A.
     */
    public int getA() {
        return this.obdlznik.getA();
    }
    /**
     * (Stena) Vráť stranu B.
     */
    public int getB() {
        return this.obdlznik.getB();
    }
    /**
     * (Stena) Skry sa.
     */
    public void skry() {
        this.obdlznik.skry();
    }

    /**
     * (Stena) Zmeň farbu na hodnotu danú parametrom.
     * Nazov farby musí byť po anglicky.
     * Možné farby sú tieto:
     * červená - "red"
     * žltá    - "yellow"
     * modrá   - "blue"
     * zelená  - "green"
     * fialová - "magenta"
     * čierna  - "black"
     * biela   - "white"
     * hnedá   - "brown"
     */
    public void zmenFarbu(String farba) {
        this.obdlznik.zmenFarbu(farba);
    }

    /**
     * (Stena) Nakresli stenu so súčasnymi hodnotami atribútov.
     */
    private void nakresli() {
        this.obdlznik.nakresli();
    }

    /*
     * Erase the square on screen.
     */
    private void zmaz() {
        this.obdlznik.zmaz();
    }
}
