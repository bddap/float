package pkgfloat;

//import java.nio.DoubleBuffer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class BoringStuff {

    public static void init() {
        initDisplay();
        Models.genBuffers();
        BddapGraphics.initGl();
        initKeyBoard();
        initMouse();
    }

    private static void initDisplay() {
        try {
            DisplayMode displayMode = null;
            boolean found = false;
            DisplayMode[] modes = Display.getAvailableDisplayModes();
            int x = 0;
            int y = 0;
            for (DisplayMode mode : modes) {
                if (mode.isFullscreenCapable() && mode.getHeight() * mode.getWidth() > x * y) {
                    displayMode = mode;
                    x = mode.getWidth();
                    y = mode.getHeight();
                    found = true;
                }
            }
            if (!found) {
                displayMode = new DisplayMode(1000, 800);
            }

            Display.setDisplayMode(displayMode);
            Display.setResizable(true);
            Display.setFullscreen(fullScreen);
            Display.setTitle("Float");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }
    private static boolean fullScreen = false;
    
    private static void initKeyBoard() {
        try {
            Keyboard.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }
    
    static void checkWindowResize() {
        if (Display.wasResized()) {
            BddapGraphics.refreshFov();
        }
    }

    private static void initMouse() {
        try {
            Mouse.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        Mouse.setGrabbed(true);
    }
    
    public static boolean toggleFullScreen() {
        fullScreen = !fullScreen;
        try {
            Display.setFullscreen(fullScreen);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        return fullScreen;
    }

    public void exitGame() {
        Display.destroy();
    }

}
