package fr.pgah.libgdx;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Protagonistes {

  private SpriteBatch batch;
  private ArrayList<Protagoniste> protagonistes;
  private Joueur joueur;

  public Protagonistes(int nombreEnnemis, SpriteBatch batch) {
    this.batch = batch;
    protagonistes = new ArrayList<>();
    joueur = initialiserJoueur();
    initialiserEnnemis(nombreEnnemis);
  }

  public boolean joueurEstTouche() {
    return joueur.estEnCollisionAvecSprite(protagonistes);
  }

  private void initialiserEnnemis(int nombreEnnemis) {
    for (int i = 0; i < nombreEnnemis; i++) {
      protagonistes.add(new Ennemi("chien.png"));
    }
  }

  private Joueur initialiserJoueur() {
    Joueur joueur = new Joueur();
    protagonistes.add(joueur);
    return joueur;
  }

  public void majEtat() {
    for (Protagoniste p : protagonistes) {
      p.majEtat();
    }
  }

  public void dessiner() {
    for (Protagoniste p : protagonistes) {
      p.dessiner(batch);
    }
  }

  public void rendreJoueurInvincible() {
    joueur.rendreInvincible();
  }

  public boolean joueurEstInvincible() {
    return joueur.estInvincible();
  }

  public void decrementerInvincibiliteJoueur() {
    joueur.decrementerInvincibilite();
  }
}
