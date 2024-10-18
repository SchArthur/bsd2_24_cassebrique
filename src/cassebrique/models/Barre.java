package cassebrique.models;

import cassebrique.CasseBrique;

import java.awt.*;

public class Barre extends Rectangle {

    protected int vitesse;
    public static int hauteurDefaut = 30;
    public static int largeurDefaut = 200;

    public Barre(int x, int y) {
        super(x, y, largeurDefaut, hauteurDefaut , Color.BLUE);
        this.vitesse = 5;
    }

    public void deplacementDroite() {
        this.x += this.vitesse;
        if (this.x > CasseBrique.LARGEUR - this.largeur) {
            this.x = CasseBrique.LARGEUR - this.largeur;
        }
    }

    public void deplacementGauche() {
        this.x -= this.vitesse;
        if (this.x < 0) {
            this.x = 0;
        }
    }

    public void collectBonus(Bonus bonus) {
        if (bonus.isMalus()){
            if (bonus.isTaille()){
                this.largeur -= 14;
                this.x += 7;
                System.out.println("Taille -14");
                System.out.println(this.largeur);
            }
            if (bonus.isVitesse()){
                this.setVitesse(this.getVitesse() - 3);
                System.out.println("Vitesse -3");
                System.out.println(this.vitesse);
            }
        } else {
            if (bonus.isTaille()){
                this.largeur += 14;
                this.x -= 7;
                System.out.println("Taille +14");
                System.out.println(this.largeur);
            }
            if (bonus.isVitesse()){
                this.setVitesse(this.getVitesse() + 3);
                System.out.println("Vitesse +4");
                System.out.println(this.vitesse);
            }
        }
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
        if (this.vitesse <= 0){
            this.vitesse = 1;
        }
    }
}
