package fr.pgah.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Protagoniste {

  int vitesse = 10;
  int longueurFenetre;
  int hauteurFenetre;
  Texture img;
  int coordX;
  int coordY;
  int longueurEffective;
  int hauteurEffective;
  Rectangle zoneDeHit; // pour la détection des collisions

  protected void majZoneDeHit() {
    zoneDeHit.setPosition(coordX, coordY);
  }

  public void majEtat() {
    deplacer();
    forcerAResterDansLeCadre();
  }

  protected abstract void deplacer();

  protected abstract void forcerAResterDansLeCadre();

  public abstract void dessiner(SpriteBatch batch);

}
