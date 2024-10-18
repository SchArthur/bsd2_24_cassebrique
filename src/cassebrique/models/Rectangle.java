package cassebrique.models;

import java.awt.*;

public class Rectangle extends Sprite {

    protected int largeur;
    protected int hauteur;

    public Rectangle(int x, int y, int largeur, int hauteur, Color couleur) {
        super(x, y, couleur);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x,y,largeur,hauteur);
    }

    public int getRightSide(){
        return x + largeur;
    }
    public int getLeftSide(){
        return x;
    }
    public int getUpSide(){
        return y;
    }
    public int getDownSide(){
        return y + hauteur;
    }

}
