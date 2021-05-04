package fr.pgah.libgdx;

import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Intro extends ApplicationAdapter {

  final int NB_ENNEMIS = 5;
  SpriteBatch batch;
  int longueurFenetre;
  int hauteurFenetre;
  // ArrayList<Protagoniste> protagonistes;
  Protagonistes protagonistes;
  boolean gameOver;
  Texture gameOverTexture;

  @Override
  public void create() {
    batch = new SpriteBatch();
    longueurFenetre = Gdx.graphics.getWidth();
    hauteurFenetre = Gdx.graphics.getHeight();

    gameOver = false;
    gameOverTexture = new Texture("game_over.png");

    // protagonistes = new ArrayList<Protagoniste>();
    protagonistes = new Protagonistes(NB_ENNEMIS, batch);
  }

  @Override
  public void render() {
    // gameOver est mis à TRUE dès qu'un "hit" est repéré
    if (!gameOver) {
      reinitialiserArrierePlan();
      protagonistes.majEtat();
      majEtatJeu();
      dessiner();
    }
  }

  private void reinitialiserArrierePlan() {
    Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  private void majEtatJeu() {
    // On vérifie si le jeu continue ou pas
    if (protagonistes.joueurEstTouche()) {
      gameOver = true;
    }
  }

  private void dessiner() {
    batch.begin();
    if (gameOver) {
      batch.draw(gameOverTexture, 100, 100);
    } else {
      protagonistes.dessiner();
    }
    batch.end();
  }
}
