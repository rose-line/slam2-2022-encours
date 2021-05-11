package fr.pgah.libgdx;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Joueur extends Protagoniste {

  private int invincibiliteRestante;

  public Joueur() {
    img = new Texture("toto.png");
    longueurEffective = img.getWidth();
    hauteurEffective = img.getHeight();
    longueurFenetre = Gdx.graphics.getWidth();
    hauteurFenetre = Gdx.graphics.getHeight();
    zoneDeHit = new Rectangle(coordX, coordY, longueurEffective, hauteurEffective);
    invincibiliteRestante = 0;
  }

  protected void deplacer() {
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

    majZoneDeHit();
  }

  protected void forcerAResterDansLeCadre() {
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

    majZoneDeHit();
  }

  public void dessiner(SpriteBatch batch) {
    if (invincibiliteRestante > 0) {
      // dessin avec halo
    } else {
      batch.draw(img, coordX, coordY);
    }
  }

  public boolean estEnCollisionAvecSprite(ArrayList<Protagoniste> protagonistes) {

    for (Protagoniste protagoniste : protagonistes) {
      if (protagoniste instanceof Ennemi) {
        Ennemi ennemi = (Ennemi) protagoniste;
        if (estEnCollisionAvec(ennemi)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean estEnCollisionAvec(Ennemi ennemi) {
    // 'overlaps' est une méthode fournie par libGDX
    if (zoneDeHit.overlaps(ennemi.zoneDeHit)) {
      return true;
    } else {
      return false;
    }
  }

  public void rendreInvincible() {
    invincibiliteRestante = 180;
  }

  public boolean estInvincible() {
    return invincibiliteRestante > 0;
  }

  public void decrementerInvincibilite() {
    invincibiliteRestante--;
  }
}
