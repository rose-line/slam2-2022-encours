package fr.pgah.libgdx;

import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Intro extends ApplicationAdapter {

  final int NB_SPRITES = 5;
  SpriteBatch batch;
  int longueurFenetre;
  int hauteurFenetre;
  ArrayList<Sprite> sprites;
  // Joueur joueur;
  boolean gameOver;
  Texture gameOverTexture;

  @Override
  public void create() {
    batch = new SpriteBatch();
    longueurFenetre = Gdx.graphics.getWidth();
    hauteurFenetre = Gdx.graphics.getHeight();

    gameOver = false;
    gameOverTexture = new Texture("game_over.png");

    initialisationSprites();
    // initialiserJoueur();
  }

  private void initialisationSprites() {
    // sprites = new Sprite[NB_SPRITES];
    sprites = new ArrayList<Sprite>();
    for (int i = 0; i < NB_SPRITES; i++) {
      // sprites[i] = new Sprite("chien.png");
      sprites.add(new Sprite("chien.png"));
    }
  }

  // private void initialiserJoueur() {
  // joueur = new Joueur();
  // }

  @Override
  public void render() {
    // gameOver est mis à TRUE dès qu'un "hit" est repéré
    if (!gameOver) {
      reinitialiserArrierePlan();
      majEtatProtagonistes();
      majEtatJeu();
      dessiner();
    }
  }


  private void reinitialiserArrierePlan() {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  private void majEtatProtagonistes() {
    // Sprites
    for (Sprite sprite : sprites) {
      sprite.majEtat();
    }

    // Joueur
    // joueur.majEtat();
  }

  private void majEtatJeu() {
    // On vérifie si le jeu continue ou pas
    // if (joueur.estEnCollisionAvec(sprites)) {
    // gameOver = true;
    // }
    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
      gererTir();
    }

    if (sprites.isEmpty()) {
      gameOver = true;
    }
  }

  private void gererTir() {
    int tirX = Gdx.input.getX();
    int tirY = Gdx.input.getY();

    ArrayList<Sprite> spritesTouches = new ArrayList<>();

    for (Sprite sprite : sprites) {
      if (sprite.seTrouveSur(tirX, tirY)) {
        // sprites.remove(sprite);
        // retenir les sprites
        spritesTouches.add(sprite);
        // break; // sortir immédiatement de la boucle
      }
    }

    // effacer le sprite hit potentiel
    // if (spriteTouche != null) {
    // sprites.remove(spriteTouche);
    // }

    // effacer tous les sprites touchés
    // for (Sprite spriteTouche : spritesTouches) {
    // sprites.remove(spriteTouche);
    // }

    sprites.removeAll(spritesTouches);
  }

  private void dessiner() {
    batch.begin();
    if (gameOver) {
      // cet affichage GAME OVER ne se fera qu'une fois
      // à la fin de la dernière frame au moment du "hit"
      // puisqu'ensuite le render ne fera plus rien
      batch.draw(gameOverTexture, 100, 100);
    } else {
      // Affichage "normal", jeu en cours
      for (Sprite sprite : sprites) {
        sprite.dessiner(batch);
      }
      // joueur.dessiner(batch);
    }
    batch.end();
  }
}
