package eg.edu.alexu.csd.oop.game.circusofplates.Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImgUtility {
    private static ImgUtility instance;

    static {
        instance = new ImgUtility();
    }

    private ImgUtility() {
        CustomLog.getInstance().getL().info("New image utility");

    }

    public static ImgUtility getInstance() {
        return instance;
    }

     BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }
}
