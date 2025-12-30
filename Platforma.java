
/**
 * Platforma zložená z obdĺžnika s ktorou možno pohybovať a nakreslý sa na plátno.
 * 
 * @author  Michael Kolling, David J. Barnes, Tomáš Mravec
 * @version 1.0  (2019)
 */

public class Platforma {
    private Obdlznik platforma;

    /**
     * Vytvor nový obdĺžnik preddefinovanej farby na preddefinovanej pozícii.
     */
    public Platforma(int pA, int pB, int paX, int paY, String paFarba, boolean jeViditelny) {
        this.platforma = new Obdlznik(pA, pB, paX, paY, paFarba, jeViditelny);
    }

    /**
     * (Platforma) Zobraz sa.
     */
    public void setPlusX(int x) {
        this.platforma.setPlusX(x);
    }
    public void zobraz() {
        this.platforma.zobraz();
    }
    /**
     * (Platforma) Vráť x-ovu súradnicu.
     */
    public int getX() {
        return this.platforma.getX();
    }
    /**
     * (Platforma) Vráť y-ovu súradnicu.
     */
    public int getY() {
        return this.platforma.getY();
    }
    /**
     * (Platforma) Vráť dĺžku strany A.
     */
    public int getA() {
        return this.platforma.getA();
    }
    /**
     * (Platforma) Vráť dĺžku strany B.
     */
    public int getB() {
        return this.platforma.getB();
    }
    /**
     * (Platforma) Skry sa.
     */
    public void skry() {
        this.platforma.skry();
    }
    
    /**
     * (Platforma) Posuň sa vpravo o pevnú dĺžku.
     */
    public void posunVpravo() {
        this.platforma.posunVodorovne(13);
    }

    /**
     * (Platforma) Posuň sa vľavo o pevnú dĺžku.
     */
    public void posunVlavo() {
        this.platforma.posunVodorovne(-13);
    }

    /**
     * (Platforma) Posuň sa vodorovne o dĺžku danú parametrom.
     */
    public void posunVodorovne(int vzdialenost) {
        this.platforma.posunVodorovne(vzdialenost);
    }

    /**
     * (Platforma) Posuň sa pomaly vodorovne o dĺžku danú parametrom.
     */
    public void pomalyPosunVodorovne (int vzdialenost) {
        int delta;

        if (vzdialenost < 0) {
            delta = -1;
            vzdialenost = -vzdialenost;
        } else  {
            delta = 1;
        }

        for (int i = 0; i < vzdialenost; i++) {
            this.platforma.setPlusX(delta);
            this.nakresli();
        }
    }

    /*
     * Draw the square with current specifications on screen.
     */
    private void nakresli() {
        this.platforma.nakresli();
    }

    /*
     * Erase the square on screen.
     */
    private void zmaz() {
        this.platforma.zmaz();
    }
}
