package fr.pgah.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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
      sprites[i].pivoter();
    }
  }

  private void reinitialiserArrierePlan() {
    // Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  private void deplacer() {
    for (int i = 0; i < sprites.length; i++) {
      sprites[i].deplacer();
    }
  }

  private void dessiner() {
    batch.begin();
    for (int i = 0; i < sprites.length; i++) {
      sprites[i].dessiner(batch);
    }
    batch.end();
  }

  private void forcerAResterDansCadre() {
    for (int i = 0; i < sprites.length; i++) {
      sprites[i].forcerAResterDansLeCadre();
    }
  }
}
