package eg.edu.alexu.csd.oop.game.circusofplates.Model;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PlugIn {

    private static PlugIn instance;

     List<Class<? extends GameObject>> getSupportedShapes() {
        return supportedShapes;
    }

    private List<Class<? extends GameObject>> supportedShapes;

    static {
        instance = new PlugIn();
    }

    private PlugIn() {
        CustomLog.getInstance().getL().info("New plugin loader");
        supportedShapes = new ArrayList<>();
        installPluginShape();

    }

    public static PlugIn getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    private void installPluginShape() {
        try {
            File[] arr = new File("plugins").listFiles();
            for (File f : arr) {
                String jarPath = f.getPath();
                JarFile jarFile = new JarFile(jarPath);
                Enumeration<JarEntry> e = jarFile.entries();
                URL[] urls = {new URL("jar:file:" + jarPath + "!/")};
                URLClassLoader cl = URLClassLoader.newInstance(urls);
                while (e.hasMoreElements()) {
                    JarEntry je = e.nextElement();
                    if (je.isDirectory() || !je.getName().endsWith(".class")) {
                        continue;
                    }
                    String className = je.getName().substring(0, je.getName().length() - 6);
                    className = className.replace('/', '.');
                    Class c = cl.loadClass(className);
                    if (GameObject.class.isAssignableFrom(c) && c.getName().compareTo("eg.edu.alexu.csd.oop.game.GameObject") != 0) {
                        supportedShapes.add((Class<? extends GameObject>) c);
                        ImgPool.getInstance().addToPool(getNewShape(supportedShapes.size() - 1, 0, 0, true, true).getSpriteImages()[0]);
                    }

                }
            }
        } catch (Exception e) {
            CustomLog.getInstance().getL().severe(CustomLog.getStackTrace(e));
            System.out.println("Error Loading plugin");
        }


    }


     GameObject getNewShape(int index, int x, int y, boolean visible, boolean horiOnly) {
        try {
            Constructor<?> ctor = supportedShapes.get(index).getConstructors()[0];
            Object object = ctor.newInstance(x, y, visible, horiOnly);
            return (GameObject) object;

        } catch (Exception e) {
            CustomLog.getInstance().getL().severe(CustomLog.getStackTrace(e));
            System.out.println("Error loading plugin");
        }
        return null;
    }


}
