package net.liquidpineapple.pang.objects;

/**
 * Created by Erik on 13-9-2016.
 */
public class LifeSystem extends GameObject{

    private static final String textureH0 = "/sprites/hearts0.png";
    private static final String textureH1 = "/sprites/hearts1.png";
    private static final String textureH2 = "/sprites/hearts2.png";
    private static final String textureH3 = "/sprites/hearts3.png";
    private static int lives;
    private int lastLives;

    public LifeSystem(){
        super(textureH3,636,5);
        lives = 3;
    }

    public static void loseLife(){
        if(lives>0) {
            lives -= 1;
        }

    }

    public void updateLifes() {
        if(lastLives!=lives) {
            switch (lives) {
                case 3:
                    changeImage(textureH3);
                    break;
                case 2:
                    changeImage(textureH2);
                    break;
                case 1:
                    changeImage(textureH1);
                    break;
                case 0:
                    changeImage(textureH0);
                    System.exit(0);
                    break;
            }
            lastLives = lives;
        }
    }


    public static void gainLife() {
        if (lives < 4) {
            lives += 1;
        }
    }
}
