package fr.pgah.libgdx;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Protagonistes {

  private SpriteBatch batch;
  private ArrayList<Protagoniste> protagonistes;

  public Protagonistes(int nombreEnnemis, SpriteBatch batch) {
    this.batch = batch;
    protagonistes = new ArrayList<>();
    initialiserJoueur();
    initialiserEnnemis(nombreEnnemis);
  }

  public boolean joueurEstTouche() {
    for (Protagoniste protagoniste : protagonistes) {
      if (protagoniste instanceof Joueur) {
        Joueur joueur = (Joueur) protagoniste;
        return joueur.estEnCollisionAvecSprite(protagonistes);
      }
    }
    return false;
  }

  private void initialiserEnnemis(int nombreEnnemis) {
    for (int i = 0; i < nombreEnnemis; i++) {
      protagonistes.add(new Ennemi("chien.png"));
    }
  }

  private void initialiserJoueur() {
    protagonistes.add(new Joueur());
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
}
