package eg.edu.alexu.csd.oop.game.circusofplates.Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BaseWorld implements World {
    private Level currentLevel;
    private List<GameObject> constants;
    private List<GameObject> movables;
    private List<GameObject> controllable;
    private List<List<List<Integer>>> hold;
    private CustomLog log;
    List<List<List<Integer>>> getHold() {
        return hold;
    }

    List<Integer> getIsHold() {
        return isHold;
    }

    List<Point> getCounters() {
        return counters;
    }

    private final int width;
    private final int height;
    private int score;
    private List<Integer> isHold;
    private List<Point> counters;

    public BaseWorld(int height, int width, String currentLevel) {
        log= CustomLog.getInstance();
        this.height = height;
        this.width = width;
        constants = new ArrayList<>();
        movables = new ArrayList<>();
        controllable = new ArrayList<>();
        isHold = new ArrayList<>();
        hold = new ArrayList<>();
        counters = new ArrayList<>();
        score = 0;
        PlugIn plug = PlugIn.getInstance();
        for (int i = 0; i < PlugIn.getInstance().getSupportedShapes().size(); i++) {
            for (int j = 0; j < 5; j++) {
                movables.add(plug.getNewShape(i, (int) (Math.random() * width), (int) (Math.random() * height / 2), true, false));
                isHold.add(null);
            }
        }
        this.currentLevel = LevelFactory.getInstance(this).getLevel(currentLevel);
        log.getL().info("New world created");
    }


    @Override
    public List<GameObject> getConstantObjects() {
        log.getL().info("Getting constant objects");
        return constants;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        log.getL().info("Getting movable objects");
        return movables;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        log.getL().info("Getting controllable objects");
        return controllable;
    }

    @Override
    public int getWidth() {
        log.getL().info("Getting the width");
        return width;
    }

    @Override
    public int getHeight() {
        log.getL().info("Getting the height");
        return height;
    }

    @Override
    public boolean refresh() {

        if (controllable.size() > 1) {
            if (controllable.get(0).getX() > width - 450) {
                controllable.get(0).setX(width - 450);
            }
            if (controllable.get(1).getX() < 250) {
                controllable.get(1).setX(250);
            }
        }
        for (int i = 0; i < movables.size(); i++) {
            int index = getHoldIndex(movables.get(i));
            Integer is = isHold.get(i);
            if (index >= 0 || is != null) {
                if (is == null) {
                    isHold.set(i, index);
                    if (movables.get(i).getX() < controllable.get(index).getX() + 100) {
                        movables.get(i).setY(controllable.get(index).getY() - counters.get(index).x);
                        counters.get(index).x = (counters.get(index).x + 64);
                        hold.get(index).get(0).add(i);
                    } else {
                        movables.get(i).setY(controllable.get(index).getY() - counters.get(index).y);
                        counters.get(index).y = (counters.get(index).y + 64);
                        hold.get(index).get(1).add(i);
                    }
                } else {
                    movables.get(i).setX(movables.get(i).getX() < controllable.get(is).getX() + 100 ? controllable.get(is).getX() - 10 : controllable.get(is).getX() + 160);
                }

            } else {
                movables.get(i).setY((movables.get(i).getY() + 1));
            }
            if (movables.get(i).getY() == height) {
                movables.get(i).setY(-1 * (int) (Math.random() * height));
                movables.get(i).setX((int) (Math.random() * width));
            }
        }
        handleSimilar();
        for (List<List<Integer>> lists : hold) {
            for (int hand = 0; hand < 2; hand++) {
                for (Integer integer : lists.get(hand)) {
                    if (movables.get(integer).getY() <= 10) {
                        for (GameObject movable : movables) {
                            movable.setY(0);
                        }
                        return false;
                    }
                }
            }
        }
        return true;

    }


    @Override
    public String getStatus() {
        log.getL().info("Status checked");

        return "Score = " + score;
    }

    @Override
    public int getSpeed() {
        log.getL().info("Speed checked");

        return currentLevel.getSpeed();
    }

     CustomLog getLog() {
        return log;
    }

    @Override
    public int getControlSpeed() {
        log.getL().info("Control speed checked");

        return currentLevel.getControlSpeed();

    }


    private void handleSimilar() {
        List<Integer> arr;
        for (int j = 0; j < controllable.size(); j++) {
            for (int hand = 0; hand < 2; hand++) {
                arr = hold.get(j).get(hand);
                for (int i = 0; i < arr.size() - 2; i++) {
                    BufferedImage fImg = getMovableObjects().get(arr.get(i)).getSpriteImages()[0];
                    BufferedImage sImg = getMovableObjects().get(arr.get(i + 1)).getSpriteImages()[0];
                    BufferedImage thImg = getMovableObjects().get(arr.get(i + 2)).getSpriteImages()[0];
                    if (fImg.getRGB(fImg.getWidth() / 2, fImg.getHeight() / 2 + 10) == (sImg.getRGB(sImg.getWidth() / 2, sImg.getHeight() / 2 + 10)) && sImg.getRGB(sImg.getWidth() / 2, sImg.getHeight() / 2 + 10) == (thImg.getRGB(thImg.getWidth() / 2, thImg.getHeight() / 2 + 10))) {
                        getMovableObjects().get(arr.get(i)).setX((int) (Math.random() * getWidth()));
                        getMovableObjects().get(arr.get(i)).setY(-1 * (int) (Math.random() * getHeight()));
                        getMovableObjects().get(arr.get(i)).getSpriteImages()[0] = ImgPool.getInstance().getRandObjImg();
                        getMovableObjects().get(arr.get(i + 1)).setX((int) (Math.random() * getWidth()));
                        getMovableObjects().get(arr.get(i + 1)).setY(-1 * (int) (Math.random() * getHeight()));
                        getMovableObjects().get(arr.get(i + 1)).getSpriteImages()[0] = ImgPool.getInstance().getRandObjImg();
                        getMovableObjects().get(arr.get(i + 2)).setX((int) (Math.random() * getWidth()));
                        getMovableObjects().get(arr.get(i + 2)).setY(-1 * (int) (Math.random() * getHeight()));
                        getMovableObjects().get(arr.get(i + 2)).getSpriteImages()[0] = ImgPool.getInstance().getRandObjImg();
                        isHold.set(arr.get(i), null);
                        isHold.set(arr.get(i + 1), null);
                        isHold.set(arr.get(i + 2), null);
                        if (hand == 0) {
                            counters.get(j).x = (counters.get(j).x - 192);
                        } else {
                            counters.get(j).y = (counters.get(j).y - 192);
                        }
                        score++;
                        arr.remove(i);
                        arr.remove(i);
                        arr.remove(i);
                        i--;
                    }
                }
            }
        }
    }

    private int getHoldIndex(GameObject obj) {
        for (int i = 0; i < controllable.size(); i++) {
            if ((obj.getX() < controllable.get(i).getX() + 100 ? obj.getY() + counters.get(i).x == controllable.get(i).getY() : obj.getY() + counters.get(i).y == controllable.get(i).getY()) && ((obj.getX() + 30 >= controllable.get(i).getX() - 10 && obj.getX() + 30 <= controllable.get(i).getX() + 40) || (obj.getX() + 30 >= controllable.get(i).getX() + 160 && obj.getX() + 30 <= controllable.get(i).getX() + 210))) {
                return i;
            }
        }
        return -1;
    }

}
