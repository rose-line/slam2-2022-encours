package fr.pgah.libgdx;

import java.util.Random;
import com.badlogic.gdx.graphics.Texture;

public class Sprite {

  Texture img;
  int coordX;
  int coordY;
  boolean versLaDroite;
  boolean versLeHaut;
  double facteurTaille;
  int vitesse;
  float rotation;
  int vitesseRotation;
  Random generateurAleatoire;

  public void initialiser() {
    generateurAleatoire = new Random();
    img = new Texture("sio.jpg");
    facteurTaille = 0.15;
    vitesse = 1 + generateurAleatoire.nextInt(10);
    rotation = 0;
    vitesseRotation = 5 + generateurAleatoire.nextInt(21);
    versLaDroite = generateurAleatoire.nextBoolean();
    versLeHaut = generateurAleatoire.nextBoolean();
    coordX = generateurAleatoire.nextInt(longueurFenetre - calculerLongueurImg(i));
    coordY = generateurAleatoire.nextInt(hauteurFenetre - calculerHauteurImg(i));
  }

}
