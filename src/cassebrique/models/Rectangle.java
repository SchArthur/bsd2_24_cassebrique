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
}