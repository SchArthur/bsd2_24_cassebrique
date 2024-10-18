package cassebrique.models;

import java.awt.*;

public class Rond extends Sprite{
    protected int diametre = 20;
    protected Color couleur = Color.blue;

    public Rond(){
        super();
    }

    public Rond(int x, int y, Color couleur) {
        super(x, y);
        this.couleur = couleur;
    }

    public Rond(int x, int y, int diametre) {
        super(x, y);
        this.diametre = diametre;
    }

    public Rond(int x, int y, Color couleur, int diametre) {
        super(x, y);
        this.diametre = diametre;
        this.couleur = couleur;
    }

    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillOval(x,y,diametre,diametre);
    }

    public int getCenterX(){
        return this.x + diametre/2;
    }

    public int getCenterY(){
        return this.y + diametre/2;
    }
}
