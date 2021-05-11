package fr.pgah.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Jeu extends ApplicationAdapter {

  private final int NB_ENNEMIS = 5;
  private SpriteBatch batch;
  private Protagonistes protagonistes;
  private int invincibiliteRestante;
  private int barreDeVie;
  private boolean gameOver;
  private Texture gameOverTexture;

  @Override
  public void create() {
    batch = new SpriteBatch();

    invincibiliteRestante = 0;
    barreDeVie = 3;
    gameOver = false;
    gameOverTexture = new Texture("game_over.png");

    protagonistes = new Protagonistes(NB_ENNEMIS, batch);
  }

  @Override
  public void render() {
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
    if (invincibiliteRestante > 0) {
      invincibiliteRestante--;
      System.out.println("invincibilite restante : " + invincibiliteRestante);
      if (invincibiliteRestante == 0) {
        System.out.println("plus invincible");
      }
    } else {
      if (protagonistes.joueurEstTouche()) {
        System.out.println("Touche ! Invincibilite temporaire");
        invincibiliteRestante = 180;
        barreDeVie--;
        System.out.println("barre de vie : " + barreDeVie);
        if (barreDeVie == 0) {
          gameOver = true;
        }
      }
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
