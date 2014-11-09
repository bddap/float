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
        Cube cube = new Cube();
        cube.xv = .01;
        Cube[] friendCubes = new Cube[100];
        for (int i = 0; i < friendCubes.length; i++){
            double angle = i;
            angle = angle/50*Math.PI;
            friendCubes[i] = new Cube();
            friendCubes[i].xv = Math.sin(angle)/10;
            friendCubes[i].zv = Math.cos(angle)/10;
        }
        while (!Display.isCloseRequested()) {
            BddapGraphics.startNewFrame();

            Input.control(player);
            player.tick();

            BddapGraphics.placeInWorld(cube);
            for (int i = 0; i < friendCubes.length; i++){
                friendCubes[i].tick();
                BddapGraphics.placeInWorld(friendCubes[i]);
            }

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
