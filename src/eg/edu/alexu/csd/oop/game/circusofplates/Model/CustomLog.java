package eg.edu.alexu.csd.oop.game.circusofplates.Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.*;
import java.util.logging.Level;

@SuppressWarnings("ALL")
public
class CustomLog {
    private static CustomLog instance;
    private static Logger l;
    private FileHandler fh;
    public Logger getL() {
        return l;
    }

    static {
        instance=new  CustomLog();
    }
    public static CustomLog getInstance() {
        return instance;
    }

    private CustomLog() {
        try {
            l = Logger.getLogger("MainLog");
            FileHandler h = new LevelBasedFileHandler("info.log", Level.INFO);
            h.setFormatter(new SimpleFormatter());
            l.addHandler(h);
            h=new LevelBasedFileHandler("warn.log", Level.WARNING);
            h.setFormatter(new SimpleFormatter());
            l.addHandler(h);
            h=new LevelBasedFileHandler("severe.log", Level.SEVERE);
            h.setFormatter(new SimpleFormatter());
            l.addHandler(h);
            l.setUseParentHandlers(false);

        } catch (SecurityException | IOException e) {
            System.out.println("Failed to handle log file.");
        }

    }

    public static String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public class LevelBasedFileHandler extends FileHandler {
        public LevelBasedFileHandler(final java.util.logging.Level level) throws IOException, SecurityException {
            super();
            super.setLevel(level);
        }

        public LevelBasedFileHandler(final String s, final java.util.logging.Level level) throws IOException, SecurityException {
            super(s);
            super.setLevel(level);
        }

        public LevelBasedFileHandler(final String s, final boolean b, final java.util.logging.Level level) throws IOException, SecurityException {
            super(s, b);
            super.setLevel(level);
        }

        public LevelBasedFileHandler(final String s, final int i, final int i1, final java.util.logging.Level level) throws IOException, SecurityException {
            super(s, i, i1);
            super.setLevel(level);
        }

        public LevelBasedFileHandler(final String s, final int i, final int i1, final boolean b, final java.util.logging.Level level) throws IOException, SecurityException {
            super(s, i, i1, b);
            super.setLevel(level);
        }

        @Override
        public void setLevel(Level newLevel) {
            super.setLevel(newLevel);
        }

        @Override
        public void publish(final LogRecord logRecord) {
            if (logRecord.getLevel().equals(super.getLevel())) {
                super.publish(logRecord);
            }
        }
    }
}
