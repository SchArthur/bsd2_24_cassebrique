package cassebrique;

import cassebrique.models.Balle;
import cassebrique.models.Barre;
import cassebrique.models.Bonus;
import cassebrique.models.Brique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class CasseBrique extends Canvas implements KeyListener {

    public JFrame fenetre = new JFrame();
    public ArrayList<Balle> listeBalle = new ArrayList<>();
    public ArrayList<Brique> listeBrique = new ArrayList<>();
    public ArrayList<Bonus> listeBonus = new ArrayList<>();
    public Barre barre;

    public boolean pressRight = false;
    public boolean pressLeft = false;

    public static final int LARGEUR = 500;
    public static final int HAUTEUR = 700;

    public CasseBrique() throws InterruptedException {

        this.fenetre.setSize(LARGEUR, HAUTEUR);
        this.setSize(LARGEUR, HAUTEUR);
        this.setBounds(0,0,LARGEUR, HAUTEUR);

        this.fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panneau = new JPanel();
        panneau.add(this);
        this.fenetre.setContentPane(panneau);

        this.setIgnoreRepaint(true);
        this.setFocusable(false);
        this.fenetre.pack();
        this.fenetre.setResizable(false);
        this.fenetre.requestFocus();
        this.fenetre.addKeyListener(this);

        this.fenetre.setVisible(true);
        this.createBufferStrategy(2);

        lancerUnePartie();
    }

    public void lancerUnePartie() throws InterruptedException {

        listeBalle = new ArrayList<>();
        listeBalle.add(new Balle(100,400,5,6));

        barre = new Barre(
                CasseBrique.LARGEUR / 2 - Barre.largeurDefaut / 2,
                CasseBrique.HAUTEUR - 100);

        listeBrique = new ArrayList<>();
        for (int indexLigne = 0; indexLigne < 5; indexLigne ++) {
            for (int indexColonne = 0; indexColonne < 7; indexColonne ++) {
                Brique brique = new Brique(
                        indexColonne * (Brique.largeurDefaut + 2),
                        indexLigne * (Brique.hauteurDefaut + 2),
                        Color.CYAN);
                listeBrique.add(brique);
            }
        }

        //la balle peut avoir une couleur differente
        //ajouter un constructeur permettant de definir la couleur de la balle
        //si aucune couleur n'est donnée (utilisation du premier constructeur) : la couleur est aléatoire
        //    Math.random() = donne un nombre entre 0 et 1 (un double)
        //    new Color(R, G , B)  prend 3 float en parametre (pour rappel un double est trop grand pour un float)
        while(true) {

            Graphics2D dessin = (Graphics2D)this.getBufferStrategy().getDrawGraphics();

            dessin.setColor(Color.WHITE);
            dessin.fillRect(0, 0, LARGEUR, HAUTEUR);

            for(Balle balle : listeBalle) {
                balle.deplacer();
                balle.checkBarreCollision(barre);
                for (int i = listeBrique.size() - 1; i >= 0 ; i--) {
                    balle.checkBriqueCollision(listeBrique.get(i));
                    if (listeBrique.get(i).getResistance() <= 0){
                        if (Math.random() < 0.1){
                            listeBonus.add(new Bonus(listeBrique.get(i).getCenterX(), listeBrique.get(i).getDownSide(), 10));
                        }
                        listeBrique.remove(i);
                    }
                }
                balle.dessiner(dessin);
            }

            barre.dessiner(dessin);

            for (Brique brique : listeBrique) {
                brique.dessiner(dessin);
            }
            for (int i = listeBonus.size() - 1; i >= 0 ; i--) {
                if (listeBonus.get(i).checkBarreCollision(barre)){
                    barre.collectBonus(listeBonus.get(i));
                    listeBonus.remove(i);
                    break;
                }
                listeBonus.get(i).deplacer();
                listeBonus.get(i).dessiner(dessin);
            }

            // Inputs
            if (pressLeft){
                barre.deplacementGauche();
            }
            if (pressRight){
                barre.deplacementDroite();
            }

            dessin.dispose();
            this.getBufferStrategy().show();

            Thread.sleep(1000 / 60);
        }
    }

    //main : raccourci
    public static void main(String[] args) throws InterruptedException {
        new CasseBrique();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pressRight = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            pressLeft = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pressRight = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            pressLeft = false;
        }
    }
}
