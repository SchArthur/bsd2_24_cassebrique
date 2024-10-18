package cassebrique.models;

import cassebrique.CasseBrique;

import java.awt.*;

public class Bonus extends Rond{

    protected int vitesseY = 3;
    protected boolean malus = false;
    protected boolean vitesse = false;
    protected boolean taille = false;

    private static final Color COLOR_VITESSE_BONUS = Color.BLUE;
    private static final Color COLOR_TAILLE_BONUS = Color.RED;
    private static final Color COLOR_VITESSE_MALUS = Color.DARK_GRAY;
    private static final Color COLOR_TAILLE_MALUS = Color.LIGHT_GRAY;

    public Bonus(int x, int y, int diametre) {
        super(x, y, diametre);

        if (Math.random() < 0.5){
            this.setMalus(true);
        }
        if (Math.random() < 0.5){
            this.setVitesse(true);
        } else {
            this.setTaille(true);
        }

        this.setCustomColor();

    }

    public void setCustomColor(){
        if (malus && vitesse){
            this.couleur = COLOR_VITESSE_MALUS;
        } else if (!malus && vitesse){
            this.couleur = COLOR_VITESSE_BONUS;
        } else if (malus && taille) {
            this.couleur = COLOR_TAILLE_MALUS;
        } else if (!malus && taille) {
            this.couleur = COLOR_TAILLE_BONUS;
        }
    }

    public boolean checkBarreCollision (Barre barre){
        if (this.getCenterY() > barre.getUpSide() && this.getCenterY() < barre.getDownSide()
            && this.getCenterX() > barre.getLeftSide() && this.getCenterX() < barre.getRightSide()){
            return true;
        } else {
            return false;
        }
    }


    public void deplacer() {

        y += vitesseY;

    }

    public boolean isMalus() {
        return malus;
    }

    public void setMalus(boolean malus) {
        this.malus = malus;
    }

    public boolean isVitesse() {
        return vitesse;
    }

    public void setVitesse(boolean vitesse) {
        this.vitesse = vitesse;
    }

    public boolean isTaille() {
        return taille;
    }

    public void setTaille(boolean taille) {
        this.taille = taille;
    }
}
