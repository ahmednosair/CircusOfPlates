package eg.edu.alexu.csd.oop.game.circusofplates.Model;

public class LevelFactory {
    private static LevelFactory instance;
    private static BaseWorld owner;
    static {
        instance=new LevelFactory();
    }
    private LevelFactory() {
    }

    public static LevelFactory getInstance(BaseWorld ownerWorld) {
        CustomLog.getInstance().getL().info("New Level factory");
        owner = ownerWorld;
        return instance;
    }

     Level getLevel(String type) {
        CustomLog.getInstance().getL().info("New returned Level by factory");
        switch (type){
            case "Easy":return new Easy(owner);
            case "Medium":return new Medium(owner);
            case "Hard":return new Hard(owner);
            case "Nightmare":return new Nightmare(owner);
        }
        return null;
    }
}
