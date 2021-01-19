package fr.pgah.libgdx;

import java.util.Random;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Intro extends ApplicationAdapter {

  final int NB_SPRITES = 20;
  SpriteBatch batch;
  int longueurFenetre;
  int hauteurFenetre;

  Sprite[] sprites;

  @Override
  public void create() {
    batch = new SpriteBatch();
    longueurFenetre = Gdx.graphics.getWidth();
    hauteurFenetre = Gdx.graphics.getHeight();


    initialisationSprites();
  }

  private void initialisationSprites() {
    sprites = new Sprite[NB_SPRITES];
    for (int i = 0; i < sprites.length; i++) {
      sprites[i] = new Sprite();
      sprites[i].initialiser();
    }
  }


  private int calculerLongueurImg(int index) {
    return (int) (sprites[index].img.getWidth() * sprites[index].facteurTaille);
  }

  private int calculerHauteurImg(int index) {
    return (int) (sprites[index].img.getHeight() * sprites[index].facteurTaille);
  }

  @Override
  public void render() {
    reinitialiserArrierePlan();
    deplacer();
    pivoter();
    forcerAResterDansCadre();
    dessiner();
  }

  private void pivoter() {
    for (int i = 0; i < NB_SPRITES; i++) {
      sprites[i].rotation = sprites[i].rotation + sprites[i].vitesseRotation;
    }
  }

  private void reinitialiserArrierePlan() {
    // Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  private void deplacer() {
    for (int i = 0; i < sprites.length; i++) {
      if (sprites[i].versLaDroite) {
        sprites[i].coordX = sprites[i].coordX + sprites[i].vitesse;
      } else {
        sprites[i].coordX = sprites[i].coordX - sprites[i].vitesse;
      }
      if (sprites[i].versLeHaut) {
        sprites[i].coordY = sprites[i].coordY + sprites[i].vitesse;
      } else {
        sprites[i].coordY = sprites[i].coordY - sprites[i].vitesse;
      }
    }
  }

  private void dessiner() {
    batch.begin();
    for (int i = 0; i < sprites.length; i++) {
      int longueur = calculerLongueurImg(i);
      int hauteur = calculerHauteurImg(i);
      batch.draw(sprites[i].img, sprites[i].coordX, sprites[i].coordY,
          longueur / 2, hauteur / 2, longueur, hauteur,
          1, 1, sprites[i].rotation,
          0, 0, sprites[i].img.getWidth(), sprites[i].img.getHeight(),
          false, false);
    }
    batch.end();
  }

  private void forcerAResterDansCadre() {
    for (int i = 0; i < sprites.length; i++) {
      int longueur = calculerLongueurImg(i);
      int hauteur = calculerHauteurImg(i);
      // Gestion bordure droite
      if (sprites[i].coordX + longueur > longueurFenetre) {
        sprites[i].coordX = longueurFenetre - longueur;
        sprites[i].versLaDroite = false;
      }

      // Gestion bordure gauche
      if (sprites[i].coordX < 0) {
        sprites[i].coordX = 0;
        sprites[i].versLaDroite = true;
      }

      // Gestion bordures haute
      if (sprites[i].coordY + hauteur > hauteurFenetre) {
        sprites[i].coordY = hauteurFenetre - hauteur;
        sprites[i].versLeHaut = false;
      }

      // Gestion bordure basse
      if (sprites[i].coordY < 0) {
        sprites[i].coordY = 0;
        sprites[i].versLeHaut = true;
      }
    }
  }
}
