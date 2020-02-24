package eg.edu.alexu.csd.oop.game.circusofplates.Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Nightmare implements Level {
    private BaseWorld owner;

    Nightmare(BaseWorld owner) {
        owner.getLog().getL().info("New nightmare level");
        this.owner = owner;
        for (int i = 0; i < 30; i++) {
            owner.getMovableObjects().add(new Shape((int) (Math.random() * owner.getWidth()), (int) (Math.random() * owner.getHeight() / 2), true, false, ImgPool.getInstance().getRandObjImg()));
            owner.getIsHold().add(null);
        }
        owner.getControlableObjects().add(new Shape(owner.getWidth() / 3 - 100, owner.getHeight() - 200, true, true, ImgPool.getInstance().getRandCharacImg()));
        owner.getCounters().add(new Point(15, 15));
        owner.getControlableObjects().add(new Shape(owner.getWidth() / 3 + 150, owner.getHeight() - 200, true, true, ImgPool.getInstance().getRandCharacImg()));
        owner.getCounters().add(new Point(15, 15));
        owner.getHold().add(new ArrayList<>());
        owner.getHold().add(new ArrayList<>());
        owner.getHold().get(0).add(new ArrayList<>());
        owner.getHold().get(0).add(new ArrayList<>());
        owner.getHold().get(1).add(new ArrayList<>());
        owner.getHold().get(1).add(new ArrayList<>());
        Timer timer = new Timer();
        timer.schedule(new ChangeColor(), 1000, 3000);
    }

    @Override
    public int getSpeed() {
        return 5;
    }

    @Override
    public int getControlSpeed() {
        return 20;
    }


    private class ChangeColor extends TimerTask {
        @Override
        public void run() {
            if (!owner.refresh()) {
                this.cancel();
            }
            owner.getLog().getL().info("Color changed in nightmare");
            for (int i = 0; i < owner.getMovableObjects().size(); i++) {
                if (owner.getIsHold().get(i) == null) {
                    owner.getMovableObjects().get(i).getSpriteImages()[0] = ImgPool.getInstance().getRandObjImg();
                }
            }
        }
    }
}

