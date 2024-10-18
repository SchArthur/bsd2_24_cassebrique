package cassebrique.models;

import cassebrique.CasseBrique;

import java.awt.*;

public class Balle extends Sprite {

    protected int vitesseX;
    protected int vitesseY;
    protected int diametre = 20;


    private boolean rectangleVerticalOverlap(Rectangle rectangle){
        if (this.getDownSide() >= rectangle.getUpSide() && this.getUpSide() <= rectangle.getDownSide()){
            return true;
        } else {
            return false;
        }
    }
    private boolean rectangleHorizontalOverlap(Rectangle rectangle){
        if (this.getRightSide() >= rectangle.getLeftSide() && this.getLeftSide() <= rectangle.getRightSide()){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkRectangleCollision(Rectangle rectangle){
        if (rectangleVerticalOverlap(rectangle) && rectangleHorizontalOverlap(rectangle)){
            return true;
        }
        else {
            return false;
        }
    }

    public void checkBarreCollision(Barre barre){
        if (checkRectangleCollision(barre)){
            this.y = barre.getUpSide() - this.diametre;
            this.vitesseY = -this.vitesseY;
        }
    }

    public void checkBriqueCollision(Brique brique){
        if (checkRectangleCollision(brique)){
            boolean rightBrickSideCollision = this.getLeftSide() > brique.getRightSide() - 5;
            boolean leftBrickSideCollision = this.getRightSide() < brique.getLeftSide() + 5;
            boolean upBrickSideCollision = this.getDownSide() < brique.getUpSide() + 5;
            boolean downBrickSideCollision = this.getUpSide() > brique.getDownSide() - 5;

            boolean downRightBrickSideCollision = downBrickSideCollision && rightBrickSideCollision;
            boolean downLeftBrickSideCollision = downBrickSideCollision && leftBrickSideCollision;
            boolean upRightBrickSideCollision = upBrickSideCollision && rightBrickSideCollision;
            boolean upLeftBrickSideCollision = upBrickSideCollision && leftBrickSideCollision;

            if (downRightBrickSideCollision){
                this.vitesseY = Math.abs(this.vitesseY);
                this.vitesseX = Math.abs(this.vitesseX);
            } else if (downLeftBrickSideCollision) {
                this.vitesseY = Math.abs(this.vitesseY);
                this.vitesseX = - Math.abs(this.vitesseX);
            } else if (upRightBrickSideCollision) {
                this.vitesseY = - Math.abs(this.vitesseY);
                this.vitesseX = Math.abs(this.vitesseX);
            } else if (upLeftBrickSideCollision) {
                this.vitesseY = - Math.abs(this.vitesseY);
                this.vitesseX = - Math.abs(this.vitesseX);
            } else if (downBrickSideCollision) {
                this.vitesseY = Math.abs(this.vitesseY);
            } else if (upBrickSideCollision) {
                this.vitesseY = -Math.abs(this.vitesseY);
            } else if (leftBrickSideCollision) {
                this.vitesseX = -Math.abs(this.vitesseX);
            } else if (rightBrickSideCollision) {
                this.vitesseX = Math.abs(this.vitesseX);
            }

            brique.takeDamage(1);
        }
    }

    public Balle() {
        super();
        this.x = this.nombreAleatoire(diametre,CasseBrique.LARGEUR - diametre);
        this.y = this.nombreAleatoire(400,500);
        this.vitesseX = 3;
        this.vitesseY = -3;
        this.couleur = new Color(ratioAleatoire(), ratioAleatoire(), ratioAleatoire(0.4f,0.7f));
    }

    public Balle(int x, int y, int vitesseX, int vitesseY) {
        this.x = x;
        this.y = y;
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
        this.couleur = new Color(ratioAleatoire(), ratioAleatoire(), ratioAleatoire(0.4f,0.7f));
    }

    public Balle(int x, int y, int vitesseX, int vitesseY, Color couleur) {
        this.x = x;
        this.y = y;
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
        this.couleur = couleur;
    }

    protected float ratioAleatoire(float min, float max) {
        return (float)Math.random() * (max - min) + min;
    }

    protected float ratioAleatoire() {
        //return (float)Math.random() * 0.6f + 0.2f;
        return ratioAleatoire(0.2f, 0.8f);
    }

    protected int nombreAleatoire(int min, int max) {
        return (int)(Math.random() * (max - min) + min);
    }

    public void deplacer() {

        x += vitesseX;
        y += vitesseY;

        if(x >= CasseBrique.LARGEUR - diametre || x <= 0) {
            vitesseX = -vitesseX;
        }

        if(y >= CasseBrique.HAUTEUR - diametre || y <= 0) {
            vitesseY = -vitesseY;
        }
    }

    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillOval(x,y,diametre,diametre);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVitesseX() {
        return vitesseX;
    }

    public void setVitesseX(int vitesseX) {
        this.vitesseX = vitesseX;
    }

    public int getVitesseY() {
        return vitesseY;
    }

    public void setVitesseY(int vitesseY) {
        this.vitesseY = vitesseY;
    }

    public int getDiametre() {
        return diametre;
    }

    public void setDiametre(int diametre) {
        this.diametre = diametre;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public int getRightSide(){
        return x + diametre;
    }
    public int getLeftSide(){
        return x;
    }
    public int getUpSide(){
        return y;
    }
    public int getDownSide(){
        return y + diametre;
    }

    public void setDownSide(int y){
        this.setY(y - this.diametre);
    }
    public void setRightSide(int x){
        this.setX(x - this.diametre);
    }
    public void setLeftSide(int x){
        this.setX(x);
    }
    public void setUpSide(int y){
        this.setY(y);
    }
}
