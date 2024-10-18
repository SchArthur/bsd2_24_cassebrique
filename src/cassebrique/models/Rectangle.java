package cassebrique.models;

import java.awt.*;

public class Rectangle extends Sprite {

    protected int largeur;
    protected int hauteur;
    protected Color couleur;

    public Rectangle(int x, int y, int largeur, int hauteur, Color couleur) {
        super(x, y);
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.couleur = couleur;
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

    public int getCenterX(){
        return this.x + this.largeur / 2;
    }

    public int getCenterY(){
        return this.y + this.hauteur / 2;
    }

}
