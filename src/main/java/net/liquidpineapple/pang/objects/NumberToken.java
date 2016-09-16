package net.liquidpineapple.pang.objects;

/**
 * Created by Erik on 12-9-2016.
 */
public class NumberToken extends GameObject {

    private int currentnumber;
    private static final String textureLocation0 = "/sprites/Numbers/Hud_0.png";
    private static final String textureLocation1 = "/sprites/Numbers/Hud_1.png";
    private static final String textureLocation2 = "/sprites/Numbers/Hud_2.png";
    private static final String textureLocation3 = "/sprites/Numbers/Hud_3.png";
    private static final String textureLocation4 = "/sprites/Numbers/Hud_4.png";
    private static final String textureLocation5 = "/sprites/Numbers/Hud_5.png";
    private static final String textureLocation6 = "/sprites/Numbers/Hud_6.png";
    private static final String textureLocation7 = "/sprites/Numbers/Hud_7.png";
    private static final String textureLocation8 = "/sprites/Numbers/Hud_8.png";
    private static final String textureLocation9 = "/sprites/Numbers/Hud_9.png";


    NumberToken(int X, int Y){
        super(textureLocation0, X, Y);
    }

    public void SetScoreToken(int Number){
        if(Number != currentnumber) {
            switch (Number) {
                case 0:
                    changeImage(textureLocation0);
                    break;
                case 1:
                    changeImage(textureLocation1);
                    break;
                case 2:
                    changeImage(textureLocation2);
                    break;
                case 3:
                    changeImage(textureLocation3);
                    break;
                case 4:
                    changeImage(textureLocation4);
                    break;
                case 5:
                    changeImage(textureLocation5);
                    break;
                case 6:
                    changeImage(textureLocation6);
                    break;
                case 7:
                    changeImage(textureLocation7);
                    break;
                case 8:
                    changeImage(textureLocation8);
                    break;
                case 9:
                    changeImage(textureLocation9);
                    break;
            }
        }
        currentnumber = Number;
    }

    public int getcurrentnumber(){
        return currentnumber;
    }

}
