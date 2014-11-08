package pkgfloat;

import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;

public class Float {

    public static void main(String[] args) {
        BoringStuff.init();
        mainLoop();
        exit();
    }

    private static void mainLoop() {
        PhysicsEntity player = new PhysicsEntity();
        while (!Display.isCloseRequested()) {
            BddapGraphics.startNewFrame();

            Input.control(player);
            player.tick();

            BddapGraphics.placeInWorld(Models.ib, 0, 0, 0);

            glLoadIdentity();

            glRotated(roll, 1, 0, 0);
            glRotated(yaw, 0, 1, 0);
            BddapGraphics.setCameraPos(player);

            BddapGraphics.UpdateScreen();
        }
    }

    static double yaw = 0;
    static double roll = 0;

    private static void exit() {
        Display.destroy();
    }
}
