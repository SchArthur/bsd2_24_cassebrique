package cassebrique.models;

import java.awt.*;

public class Brique extends Rectangle {

    protected int resistance;
    public static int hauteurDefaut = 40;
    public static int largeurDefaut = 70;

    public Brique(int x, int y, Color couleur, int resistance) {
        super(x, y, largeurDefaut, hauteurDefaut , couleur);
        this.resistance = resistance;
    }

    public Brique(int x, int y, Color couleur) {
        super(x, y, largeurDefaut, hauteurDefaut , couleur);
        this.resistance = nombreAleatoire(1,6);
    }

    public void takeDamage(int damage) {
        setResistance(getResistance() - damage);
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public void dessiner(Graphics2D dessin) {
        super.dessiner(dessin);
        dessin.setColor(Color.BLACK);
        dessin.drawString(String.valueOf(resistance), x + largeurDefaut / 2 - 10, y + hauteurDefaut / 2 + 5);
    }

    protected int nombreAleatoire(int min, int max) {
        return (int)(Math.random() * (max - min) + min);
    }
}
