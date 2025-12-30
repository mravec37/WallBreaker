import java.awt.geom.Ellipse2D;

/**
 * Lopta, s ktorou možno pohybovať a nakreslí sa na plátno.
 * 
 * @author  Michael Kolling, David J. Barnes, Tomáš Mravec
 * @version 1.0  (2019)
 */

public class Lopta {
    private int priemer;
    private int lavyHornyX;
    private int lavyHornyY;
    private int smerX;
    private int smerY;
    private String farba;
    private boolean jeViditelny;
    
    /**
     * Vytvor novú Loptu preddefinovanej farby na preddefinovanej pozícii . 
     */
    public Lopta(int x, int y, int smerX, int smerY, String farba) {
        this.priemer = 20;
        this.lavyHornyX = x;
        this.lavyHornyY = y;
        this.farba = farba;
        this.jeViditelny = false;
        this.smerX = smerX;
        this.smerY = smerY;
    }

    /**
     * (Lopta) Zobraz sa.
     */
    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }
    /**
     * (Lopta) Vráť farbu lopty.
     */
    public String getFarba() {
        return this.farba;
    }
    /**
     * (Lopta) Skontroluje sa či sa lopta dotýka okraja
     */
    private void kontrolaKontaktu() {
        
        if (this.lavyHornyY  <= 0) {
            this.smerY *= -1;
        }
        if (this.lavyHornyY >= 730) {
            this.smerY *= -1;
        }
        if (this.lavyHornyX <= 0) {
            this.smerX *= -1;
        }
        if (this.lavyHornyX >= 615) {
            this.smerX *= -1;
        }
    }
    /**
     * Vráti priemer
     */
    public int getPriemer() {
        return this.priemer;
    }
    /**
     * (Lopta) Vráť hodnotu y-ovej súradnice.
     */
    public int getY() {
        return this.lavyHornyY;
    }
    /**
     * (Lopta) Vráť hodnotu x-ovej súradnice.
     */
    public int getX() {
        return this.lavyHornyX;
    }
    /**
     * (Lopta) Invertuje smer pohybu lopty na x-ovej súradnici.
     */
    public void zmenSmerX() {
        this.smerX *= -1;
        this.pohyb();
    }
    /**
     * (Lopta) Zmení rýchlosť a smer lopty na x-ovej súradnici.
     */
    public void nastavSmerX(int x) {
        this.smerX = x;
    }
    /**
     * (Lopta) Vráť hodnotu smeru a rýchlosti lopty na x-ovej súradnici.
     */
    public int getSmerX() {
        return this.smerX;
    }
    /**
     * (Lopta) Invertuje smer lopty na y-ovej súradnici.
     */
    public void zmenSmerY() {
        this.smerY *= -1;
        this.pohyb();
    }
    /**
     * (Lopta) Vykoná krok pohybu.
     */
    public void pohyb() {
        this.kontrolaKontaktu();
        this.lavyHornyX += this.smerX;
        this.lavyHornyY += this.smerY;
        this.nakresli();
    }
    /**
     * (Lopta) Skry sa.
     */
    public void skry() {
        this.zmaz();
        this.jeViditelny = false;
    }

    /**
     * (Lopta) Zmeň farbu na hodnotu danú parametrom.
     * Nazov farby musí byť po anglicky.
     * Možné farby sú tieto:
     * červená - "red"
     * žltá    - "yellow"
     * modrá   - "blue"
     * zelená  - "green"
     * fialová - "magenta"
     * čierna  - "black"
     */
    
    /**
     * (Lopta) Zmení farbu lopty.
     */
    public void zmenFarbu(String farba) {
        this.farba = farba;
        this.nakresli();
    }

    /**
     * (Lopta) Vykreslý sa lopta so súčasnými hodnotami atribútov.
     */
    private void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.draw(this, this.farba, new Ellipse2D.Double(this.lavyHornyX, this.lavyHornyY, 
                                                          this.priemer, this.priemer));
            canvas.wait(10);
        }
    }

    /*
     * Erase the circle on screen.
     */
    private void zmaz() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.erase(this);
        }
    }
}
