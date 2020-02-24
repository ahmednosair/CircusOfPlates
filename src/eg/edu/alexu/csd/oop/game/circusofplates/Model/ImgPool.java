package eg.edu.alexu.csd.oop.game.circusofplates.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImgPool {

    private static ImgPool instance;
    private java.util.List<BufferedImage> plates;
    private List<BufferedImage> players;

    static {
        instance = new ImgPool();
    }

    private ImgPool() {
        File folder = new File("resources\\Imgs\\objectImgs");
        if (folder.isDirectory()) {
            File[] imgs = folder.listFiles();
            if (imgs != null) {
                plates = new ArrayList<>();
                for (int i = 0; i < imgs.length; i++) {
                    try {
                        plates.add(ImgUtility.getInstance().resize(ImageIO.read(imgs[i]), 60, 64));
                    } catch (IOException e) {
                        CustomLog.getInstance().getL().severe(CustomLog.getStackTrace(e));
                        System.out.println("Error loading image");
                    }
                }
            }
        }
        folder = new File("resources\\Imgs\\characImgs");
        if (folder.isDirectory()) {
            File[] imgs = folder.listFiles();
            if (imgs != null) {
                players = new ArrayList<>();
                for (int i = 0; i < imgs.length; i++) {
                    try {
                        players.add(ImgUtility.getInstance().resize(ImageIO.read(imgs[i]), 200, 200));
                    } catch (IOException e) {
                        System.out.println("Error loading image");
                        CustomLog.getInstance().getL().severe(CustomLog.getStackTrace(e));

                    }
                }
            }
        }
        CustomLog.getInstance().getL().info("New image pool");

    }

    public static ImgPool getInstance() {

        return instance;
    }

    public BufferedImage getRandObjImg() {
        return plates.get((int) (Math.random() * plates.size()));
    }

    public BufferedImage getRandCharacImg() {
        return players.get((int) (Math.random() * players.size()));
    }
    public void addToPool(BufferedImage img){
        plates.add(img);
    }

}
