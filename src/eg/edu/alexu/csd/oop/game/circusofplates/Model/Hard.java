package eg.edu.alexu.csd.oop.game.circusofplates.Model;


import java.awt.*;
import java.util.ArrayList;

public class Hard implements Level {
    private BaseWorld owner;

    Hard(BaseWorld owner) {
        this.owner = owner;
        owner.getLog().getL().info("New hard level");
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
    }

    @Override
    public int getSpeed() {
        return 5;
    }

    @Override
    public int getControlSpeed() {
        return 15;
    }

}

