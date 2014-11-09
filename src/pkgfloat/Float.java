package pkgfloat;

import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;

public class Float {

    public static void main(String[] args) {
        //System.setProperty( "java.library.path", "dist/naitive/all" );
        BoringStuff.init();
        mainLoop();
        exit();
    }

    private static void mainLoop() {
        PhysicsEntity player = new PhysicsEntity();
        Cube cube = new Cube();
        cube.xv = .01;  //velocities are in meters per millisecond... I think
        Cube[] friendCubes = new Cube[1000];
        for (int i = 0; i < friendCubes.length; i++){
            double angle = i;
            angle = angle/friendCubes.length*Math.PI*2;
            friendCubes[i] = new Cube();
            friendCubes[i].xv = Math.sin(angle)/1000;
            friendCubes[i].zv = Math.cos(angle)/1000;
            friendCubes[i].yv = Math.cos(angle*8)/10000 + Math.cos(angle)/5000;
        }
        Timer gameTimer = new Timer();
        while (!Display.isCloseRequested()) {
            BddapGraphics.startNewFrame();

            Input.control(player, gameTimer.read());
            player.tick(gameTimer.getDelta());

            BddapGraphics.placeInWorld(cube);
            for (Cube friendCube : friendCubes) {
                friendCube.tick(gameTimer.getDelta());
                BddapGraphics.placeInWorld(friendCube);
            }

            glLoadIdentity();
            
            double butts = System.currentTimeMillis();
            
            System.out.println(butts);
            
            glColor3d(Math.sin(butts/1050),.1,Math.cos(butts/5000));
            
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
