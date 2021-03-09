package fr.pgah.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Joueur {

  final int vitesse = 10;
  int longueurFenetre;
  int hauteurFenetre;
  Texture img;
  int coordX;
  int coordY;
  int longueurEffective;
  int hauteurEffective;
  Rectangle zone;

  public Joueur() {
    img = new Texture("toto.png");
    longueurEffective = img.getWidth();
    hauteurEffective = img.getHeight();
    longueurFenetre = Gdx.graphics.getWidth();
    hauteurFenetre = Gdx.graphics.getHeight();
    zone = new Rectangle(coordX, coordY, longueurEffective, hauteurEffective);
  }

  public void majEtat() {
    deplacer();
    forcerAResterDansLeCadre();
  }

  private void deplacer() {
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      coordX -= vitesse;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      coordX += vitesse;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      coordY += vitesse;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      coordY -= vitesse;
    }
    zone.setPosition(coordX, coordY);
  }

  private void forcerAResterDansLeCadre() {
    // Gestion bordure droite
    if (coordX + longueurEffective > longueurFenetre) {
      coordX = longueurFenetre - longueurEffective;
    }

    // Gestion bordure gauche
    if (coordX < 0) {
      coordX = 0;
    }

    // Gestion bordures haute
    if (coordY + hauteurEffective > hauteurFenetre) {
      coordY = hauteurFenetre - hauteurEffective;
    }

    // Gestion bordure basse
    if (coordY < 0) {
      coordY = 0;
    }

    zone.setPosition(coordX, coordY);
  }

  public void dessiner(SpriteBatch batch) {
    batch.draw(img, coordX, coordY);
  }

  public boolean estEnCollisionAvec(Sprite[] sprites) {
    // pour chaque sprite dans sprites
    // si le sprite touche le joueur
    // alors renvoyer vrai
    // sinon faux
    for (Sprite sprite : sprites) {
      if (estEnCollisionAvec(sprite)) {
        return true;
      }
    }
    return false;
  }

  private boolean estEnCollisionAvec(Sprite sprite) {
    if (zone.overlaps(sprite.zone)) {
      return true;
    } else {
      return false;
    }
  }
}
