import java.awt.Rectangle;

/**
 * Obdĺžnik, s ktorým možno pohybovať a nakreslí sa na plátno.
 * 
 * @author  Michael Kolling, David J. Barnes, Tomáš Mravec
 * @version 1.0  (2019)
 */

public class Obdlznik {
    private int stranaA;
    private int stranaB;
    private int lavyHornyX;
    private int lavyHornyY;
    private String farba;
    private boolean jeViditelny;

    /**
     * Vytvor nový obdĺžnik preddefinovanej farby na preddefinovanej pozícii.
     */
    public Obdlznik(int pA, int pB, int paX, int paY, String paFarba, boolean jeViditelny) {
        this.stranaA = pA;
        this.stranaB = pB;
        this.lavyHornyX = paX;
        this.lavyHornyY = paY;
        this.farba = paFarba;
        this.jeViditelny = jeViditelny;
    }

    /**
     * (Obdĺžnik) Zobraz sa.
     */
    public boolean getJeViditelny() {
        return this.jeViditelny;
    }
 
    /**
     * (Obdĺžnik) Zobraz sa na plátne.
     */
    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }
    public void setPlusX(int x) {
        this.lavyHornyX += x;
    }
    /**
     * (Obdĺžnik) Vráť x-ovu súradnicu.
     */
    public int getX() {
        return this.lavyHornyX;
    }
    /**
     * (Obdĺžnik) Vráť y-ovu súradnicu.
     */
    public int getY() {
        return this.lavyHornyY;
    }
    /**
     * (Obdĺžnik) Vráť dĺžku strany A.
     */
    public int getA() {
        return this.stranaA;
    }
    /**
     * (Obdĺžnik) Vráť dĺžku strany B.
     */
    public int getB() {
        return this.stranaB;
    }
    /**
     * (Obdĺžnik) Vráť viditelnost.
     */
    public boolean getViditelnost() {
        return this.jeViditelny;
    }
    /**
     * (Obdĺžnik) Vráť farbu.
     */
    public String getFarba() {
        return this.farba;
    }
    
    /**
     * (Obdĺžnik) Skry sa.
     */
    public void skry() {
        this.zmaz();
        this.jeViditelny = false;
    }
    
    /**
     * (Obdĺžnik) Posuň sa vpravo o pevnú dĺžku.
     */
    public void posunVpravo() {
        this.posunVodorovne(13);
    }

    /**
     * (Obdĺžnik) Posuň sa vľavo o pevnú dĺžku.
     */
    public void posunVlavo() {
        this.posunVodorovne(-13);
    }

    /**
     * (Obdĺžnik) Posuň sa vodorovne o dĺžku danú parametrom.
     */
    public void posunVodorovne(int vzdialenost) {
        this.zmaz();
        this.lavyHornyX += vzdialenost;
        this.nakresli();
    }

    /**
     * (Obdĺžnik) Posuň sa zvisle o dĺžku danú parametrom.
     */
    public void posunZvisle(int vzdialenost) {
        this.zmaz();
        this.lavyHornyY += vzdialenost;
        this.nakresli();
    }

    /**
     * (Obdĺžnik) Posuň sa pomaly vodorovne o dĺžku danú parametrom.
     */
    public void pomalyPosunVodorovne(int vzdialenost) {
        int delta;

        if (vzdialenost < 0) {
            delta = -1;
            vzdialenost = -vzdialenost;
        } else  {
            delta = 1;
        }

        for (int i = 0; i < vzdialenost; i++) {
            this.lavyHornyX += delta;
            this.nakresli();
        }
    }

    /**
     * (Obdĺžnik) Posuň sa pomaly vodorovne o dĺžku danú parametrom.
     */
    public void pomalyPosunZvisle(int vzdialenost) {
        int delta;

        if (vzdialenost < 0) {
            delta = -1;
            vzdialenost = -vzdialenost;
        } else {
            delta = 1;
        }

        for (int i = 0; i < vzdialenost; i++) {
            this.lavyHornyY += delta;
            this.nakresli();
        }
    }

    /**
     * (Obdĺžnik) Zmeň farbu na hodnotu danú parametrom.
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
        this.farba = farba;
        this.nakresli();
    }

    /*
     * Draw the square with current specifications on screen.
     */
    public void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.draw(this, this.farba,
                        new Rectangle(this.lavyHornyX, this.lavyHornyY, this.stranaA, this.stranaB));
            canvas.wait(10);
        }
    }

    /*
     * Erase the square on screen.
     */
    public void zmaz() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.erase(this);
        }
    }
}
