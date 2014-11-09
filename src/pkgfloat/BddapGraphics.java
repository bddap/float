package pkgfloat;

import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;
import java.nio.IntBuffer;

public class BddapGraphics {

    static void startNewFrame() {
        glClear(GL_COLOR_BUFFER_BIT);
    }

    static void placeInWorld(Cube cube) {
        glPushMatrix();
        glTranslated(cube.x, cube.y, cube.z);
        glDrawElements(GL_QUADS, Models.cube);
        glPopMatrix();
    }

    static void UpdateScreen() {
        //Display.sync(60);
        Display.update();
        BoringStuff.checkWindowResize();
    }

    static void setCameraPos(Entity cam) {
        glRotated(cam.kr, cam.xr, cam.yr, cam.zr);
        glTranslated(cam.x, cam.y, cam.z);
    }

    //The methods above are called every frame
    static void initGl() {
        setFov(Math.PI / 2);

        glEnableClientState(GL_VERTEX_ARRAY);
        glVertexPointer(3, 0, Models.vb);
        
        //int vbo = glGenBuffers();
        //glBindBuffer(GL_ARRAY_BUFFER, vbo);
        //glBufferData(GL_ARRAY_BUFFER, Models.vb, GL_STATIC_DRAW);
        
        //glEnable(GL_TEXTURE_2D);
        //glBindTexture(GL_TEXTURE_2D, Models.cubeTexture.getTextureID());
        //glTexCoordPointer(2, 0, Models.cubetp);
        //GL11.glTexCoord2d(.5, .5);
        glPolygonMode(GL_BACK, GL_LINE);
    }

    static void setFov(double radians) {
        double width = (float) Display.getWidth();
        double height = (float) Display.getHeight();
        double ratio = width / height;

        //double magnitude = Math.sqrt(width * width + height * height);
        final double frustumNear = 0.001f;
        width = frustumNear / Math.tan(radians / 2);
        height = width * ratio;

        glMatrixMode(GL_PROJECTION);
        glFrustum(-height, height, -width, width, frustumNear, 10000);
        glMatrixMode(GL_MODELVIEW);
        fov = radians;
    }

    static void refreshFov() {
        setFov(fov);
    }

    private static double fov = Math.PI / 2;

}
