

import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * Hra je trieda, ktorá umožňuje fungovanie hry Wall Breaker.
 * V tejto triede sa vytvára funkčnosť jednotlivých objektov.
 * 
 * @author  Tomáš Mravec
 * @version 1.0  (2019)
 */

public class Hra {
    
    private Platforma platforma;
    private Lopta lopta;
    private int pocetUrovni;
    private int pocetStien;
    private List<Stena> steny;
    private Stav stav;
    private int skore;
    
    /**
     * Vytvor inštanciu Hry, inicializuj prvotné hodnoty atribútov.
     *
     */
    public Hra() {
        this.platforma = new Platforma (110, 10, 270, 680, "blue", false);
        this.steny = new ArrayList();
        this.pocetUrovni = 1;
        this.pocetStien = 5 * this.pocetUrovni;
        this.stav = Stav.POCIATOCNY;
        this.skore = 0;
        Platno.dajPlatno().addKeyListener(new KontrolaKlaves());    
    }
    /**
     *  Kontroluje stláčanie šípiek na klávesnici.
     */
    private class KontrolaKlaves implements KeyListener {
        public void keyPressed(KeyEvent event) {
            
            if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                Hra.this.posunVlavo();
            } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                Hra.this.posunVpravo();
            } else if (event.getKeyCode() == KeyEvent.VK_SPACE || event.getKeyCode() == KeyEvent.VK_ENTER) {
                if (Hra.this.stav == Stav.POCIATOCNY) {    
                    Hra.this.engine();
                    Hra.this.stav = Stav.NEUKONCENY;
                } else if (!(Hra.this.stav == Stav.NEUKONCENY)) {
                    Hra.this.novaHra();
                }
            }
        }
        public void keyReleased(KeyEvent e) {
  
        }
        public void keyTyped(KeyEvent e) {
  
        }
       
    }
    /**
     *  (Hra)Lopta sa nastaví na defaultné súradnice s defaultnými vlastnosťami.
     *  
     */
    private void nastavLoptu() {
        this.lopta = new Lopta (40, 200, 2, 2, "red");
    }
    /**
     * Obsahuje metódu, ktorá sa vykonáva každý určitý interval, zabezpečuje hlavnú funkčnosť hry.
     */
    private void engine() {  
        this.novaHra();
        Runnable runnable1 = new Runnable() {
            public void run() {
               
                if (Hra.this.stav == Stav.NEUKONCENY) {
                    Hra.this.zistiStav();
                    Hra.this.lopta.pohyb();
                    Hra.this.dotykSPlatformou();
                    if (Hra.this.lopta.getY() <= 60 * Hra.this.pocetUrovni) {
                        Hra.this.dotykSoStenou();
                    }
                    Hra.this.dotykSHranicou();
                } 
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(runnable1, 0, 10, TimeUnit.MILLISECONDS);
        
    }
    /**
     * Všetky objekty zmiznú z plátna.
     */
    private void koniec() {
        for (int i = 0; i < this.steny.size(); i++) {
            this.steny.get(i).skry();
        }
        this.lopta.skry();
        this.platforma.skry();
        this.pocetStien = 0;
    }
    /**
     * Kontroluje, či hráč zničil všetky steny.
     */
    private void zistiStav() {
        if (this.pocetStien == 0) {
            this.stav = Stav.VYHRA;
            JOptionPane.showMessageDialog(null, "Vyhral si!" + "\n" +  "Počet odrazov: " + this.skore);
            this.koniec();
            
        }
    }        
    
    
    /**
     * Kontroluje, či sa lopta dotkla jednej zo stien.
     *
     */
    private void dotykSoStenou() {
       
        int index = 300;
        boolean zmenX = true;
        boolean zmenY = false;
        for (int i = 0; i < this.pocetStien; i++) {
            //dolna strana   
            if (this.lopta.getY() == this.steny.get(i).getY() + this.steny.get(i).getB() && this.lopta.getX() + this.lopta.getPriemer() >= this.steny.get(i).getX() && this.lopta.getX() <= this.steny.get(i).getX() + this.steny.get(i).getA() && this.lopta.getFarba().equals(this.steny.get(i).getFarba())) {
                this.steny.get(i).skry();
                this.lopta.zmenSmerY();
                index = i;
                this.lopta.zmenFarbu(this.steny.get(i).getFarba());
                this.pocetStien--;
                 //horna strana
            } else if (this.lopta.getY() + this.lopta.getPriemer() == this.steny.get(i).getY() && this.lopta.getX() + this.lopta.getPriemer() >= this.steny.get(i).getX() && this.lopta.getX() <= this.steny.get(i).getX() + this.steny.get(i).getA() && this.lopta.getFarba().equals(this.steny.get(i).getFarba())) {
                this.steny.get(i).skry();
                this.lopta.zmenSmerY();
                index = i;
                this.lopta.zmenFarbu(this.steny.get(i).getFarba());
                this.pocetStien--;
                 //lava strana
            } else if (this.lopta.getY() < this.steny.get(i).getY() + this.steny.get(i).getB() && this.lopta.getY() + this.lopta.getPriemer() > this.steny.get(i).getY() && this.lopta.getX() + this.lopta.getPriemer() == this.steny.get(i).getX() && this.lopta.getFarba().equals(this.steny.get(i).getFarba())) {
                this.steny.get(i).skry();
                this.lopta.zmenSmerX();
                this.lopta.zmenFarbu(this.steny.get(i).getFarba());
                index = i;
                this.pocetStien--;
                //prava strana 
            } else if (this.lopta.getY() < this.steny.get(i).getY() + this.steny.get(i).getB() && this.lopta.getY() + this.lopta.getPriemer() > this.steny.get(i).getY() && this.lopta.getX() == this.steny.get(i).getX() + this.steny.get(i).getA() && this.lopta.getFarba().equals(this.steny.get(i).getFarba())) {
                this.steny.get(i).skry();
                this.lopta.zmenSmerX();
                this.lopta.zmenFarbu(this.steny.get(i).getFarba());
                index = i;
                this.pocetStien--;
                //lava strana
            } else if (this.lopta.getY() < this.steny.get(i).getY() + this.steny.get(i).getB() && this.lopta.getY() + this.lopta.getPriemer() > this.steny.get(i).getY() && this.lopta.getX() + this.lopta.getPriemer() == this.steny.get(i).getX()) {
                this.lopta.zmenFarbu(this.steny.get(i).getFarba());
                this.lopta.zmenSmerX();
                //prava strana
            } else if (this.lopta.getY() < this.steny.get(i).getY() + this.steny.get(i).getB() && this.lopta.getY() + this.lopta.getPriemer() > this.steny.get(i).getY() && this.lopta.getX() == this.steny.get(i).getX() + this.steny.get(i).getA()) {
                this.lopta.zmenFarbu(this.steny.get(i).getFarba());
                this.lopta.zmenSmerX();
                // dolna strana
            } else if (this.lopta.getY() == this.steny.get(i).getY() + this.steny.get(i).getB() && this.lopta.getX() + this.lopta.getPriemer() >= this.steny.get(i).getX() && this.lopta.getX() <= this.steny.get(i).getX() + this.steny.get(i).getA()) {
                this.lopta.zmenFarbu(this.steny.get(i).getFarba());
                this.lopta.zmenSmerY();
                //horna strana
            } else if (this.lopta.getY() + this.lopta.getPriemer() == this.steny.get(i).getY() && this.lopta.getX() + this.lopta.getPriemer() >= this.steny.get(i).getX() && this.lopta.getX() <= this.steny.get(i).getX() + this.steny.get(i).getA()) {
                this.lopta.zmenFarbu(this.steny.get(i).getFarba());
                this.lopta.zmenSmerY();
            }  
        }
        //odstránenie "zničenej" steny z Listu 
        if (index < this.steny.size()) {
            this.steny.remove(index);
            
        }
        
        
    } 
    /**
     * Naplní List a plátno stenami na daných pozíciach s určitými farbami.
     */
    private void vytvorenieStien() {
        this.steny = new ArrayList();
        String farba = "";
        int posunX = 0;
        int posunY = 0;
        this.pocetStien = 5 * this.pocetUrovni;
        for (int i = 0; i < this.pocetStien; i++) {
            if (i % 2 == 0) {
                farba = "yellow";  
            } 
            if (i % 2 == 1) {
                farba = "red";
            }
            if (i % 5 == 0 && i != 0) {
                posunX = 0;
                posunY += 60;
            }
            this.steny.add(new Stena(126, 60, 0 + posunX, 0 + posunY, farba, true));
            posunX += 126;
            
        }
        for (int i = 0; i < this.pocetStien; i++) {
            this.steny.get(i).zobraz();
        }
       
    } 
    
    /**
     * Pripravý objekty na začatie hry.
     */
    private void novaHra() {
        String a = JOptionPane.showInputDialog( null, "Zadaj počet riadkov stien(1 - 3)");
        if (Integer.parseInt(a) < 1 || Integer.parseInt(a) > 3) {
            this.pocetUrovni = 1;
        } else {
            this.pocetUrovni = Integer.parseInt(a);
        } 
        this.skore = 0;
        this.nastavLoptu();
        this.lopta.zobraz();
        this.vytvorenieStien();
        this.stav = Stav.NEUKONCENY;
        this.platforma.zobraz();
       
    }
    
    /**
     * Ak užívateľ stlačil ľavú šípku, platforma sa posunie doľava.
     */
    private void posunVlavo() {
        if (!(this.platforma.getX() <= 0)) {
            this.platforma.posunVlavo();
        }
    }
    /**
     * Ak užívateľ stlačil pravú šípku, platforma sa posunie doprava.
     */ 
    private void posunVpravo() {
        if (!(this.platforma.getX() >= 517)) {
            this.platforma.posunVpravo();
        }
    }
    /**
     * Zabezpečuje kontrolu dotyku lopty s platformou, ak je dotyk, zabezpečí odraz loptičky.
     */
    private void dotykSPlatformou() {
       
        if ((this.platforma.getY() <= this.lopta.getY() + this.lopta.getPriemer() + 2 && this.platforma.getY() >= this.lopta.getY() + this.lopta.getPriemer() && ((this.platforma.getX() <= this.lopta.getX() + this.lopta.getPriemer()) && this.lopta.getX() <= this.platforma.getX() + this.platforma.getA()))) {
            this.lopta.zmenSmerY();
            this.skore++;
        } 
        if (this.platforma.getY() <= this.lopta.getY() + this.lopta.getPriemer() - 1 && this.lopta.getY() <= this.platforma.getY() + this.platforma.getB() + 1  && this.platforma.getX() <= this.lopta.getX() + this.lopta.getPriemer() && this.platforma.getX() + 2 >= this.lopta.getX() + this.lopta.getPriemer()) {
            this.lopta.zmenSmerX();
            this.skore++;
            if (this.lopta.getSmerX() > 0) { 
                this.lopta.nastavSmerX(-3);
            }
        }
        if (this.platforma.getY() <= this.lopta.getY() + this.lopta.getPriemer() - 1 && this.lopta.getY() <= this.platforma.getY() + this.platforma.getB() + 1 && this.lopta.getX() >= this.platforma.getX() + this.platforma.getA() - 1 && this.lopta.getX() <= this.platforma.getX() + this.platforma.getA() + 1) {
            this.lopta.zmenSmerX();
            this.skore++;
            if (this.lopta.getSmerX() < 0) {
                this.lopta.nastavSmerX(3);
            }
        }
        
    }
    /**
     * Kontroluje, či sa loptička dotkla spodnej hranice, ak áno, hráč prehral.
     */
    private void dotykSHranicou() {
        if (this.lopta.getY() >= 720) {
            this.stav = Stav.PREHRA;
            JOptionPane.showMessageDialog(null, "Prehral si!" + "\n" + "Počet odrazov: " + this.skore + "\n" +  "Zostalo ti zničiť ešte: " + this.pocetStien + " stien");
            this.koniec();
            
        }
    }
}
