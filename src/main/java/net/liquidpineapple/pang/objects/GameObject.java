package net.liquidpineapple.pang.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date 2016/09/07.
 */
@Data
@EqualsAndHashCode
public abstract class GameObject {

    protected static final String defaultTexture = "/sprites/no-texture.png";

    protected int xPos;
    protected int yPos;
    protected Image image;

    public GameObject(String textureLocation, int startX, int startY) {
        ImageIcon imageIcon;
        if (textureLocation != null) {
            imageIcon = new ImageIcon(textureLocation);
        } else {
            imageIcon = new ImageIcon(defaultTexture);
        }
        image = imageIcon.getImage();

        xPos = startX;
        yPos = startY;
    }
}
