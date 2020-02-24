package eg.edu.alexu.csd.oop.game.circusofplates.Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.image.BufferedImage;

public class Shape implements GameObject {

    private BufferedImage[] imgs;
    private int x;
    private int y;
    private boolean visible;
    private boolean horizontalOnly;

     Shape(int x, int y, boolean visible, boolean horizontalOnly, BufferedImage img) {
        CustomLog.getInstance().getL().info("New shape created");
        this.x = x;
        this.y = y;
        this.visible = visible;
        this.horizontalOnly = horizontalOnly;
        imgs = new BufferedImage[1];
            imgs[0] = img;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        if (!horizontalOnly) {
            this.y = y;
        }
    }

    @Override
    public int getWidth() {
        return imgs[0].getWidth();
    }

    @Override
    public int getHeight() {
        return imgs[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return imgs;
    }
}
