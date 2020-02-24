package eg.edu.alexu.csd.oop.game.circusofplates.Model;



import java.awt.*;
import java.util.ArrayList;

public class Easy implements Level {
    private BaseWorld owner;
    Easy(BaseWorld owner) {
        this.owner = owner;
        owner.getLog().getL().info("New easy level");
        for (int i = 0; i < 15; i++) {
            owner.getMovableObjects().add(new Shape((int) (Math.random() * owner.getWidth()), (int) (Math.random() * owner.getHeight() / 2), true, false, ImgPool.getInstance().getRandObjImg()));
            owner.getIsHold().add(null);
        }
        owner.getControlableObjects().add(new Shape(owner.getWidth() / 2 - 100, owner.getHeight() - 200, true, true, ImgPool.getInstance().getRandCharacImg()));
        owner.getCounters().add(new Point(15,15));
        owner.getHold().add(new ArrayList<>());
        owner.getHold().get(0).add(new ArrayList<>());
        owner.getHold().get(0).add(new ArrayList<>());
    }


    @Override
    public int getSpeed() {
        return 15;
    }

    @Override
    public int getControlSpeed() {
        return 20;
    }

}
