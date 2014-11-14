package pkgfloat;

import java.io.IOException;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Models {
    public static DoubleBuffer vb;
    public static IntBuffer cube;
    public static Texture cubeTexture;
    public static DoubleBuffer cubetp;
    
    static void genBuffers(){
        vb = Models.createBuffer(Models.verts);
        cube = Models.createBuffer(Models.cubeAr);
        
        try {
            cubeTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/asteroid.png"));
        } catch (IOException ex) {
            Logger.getLogger(Models.class.getName()).log(Level.SEVERE, null, ex);
        }
        cubetp = Models.createBuffer(texturePointers);
    }
    
    public static DoubleBuffer createBuffer(double[] inarray){
        return (DoubleBuffer) BufferUtils.createDoubleBuffer(inarray.length).put(inarray).flip();
    }
    
    public static IntBuffer createBuffer(int[] inarray){
        return (IntBuffer) BufferUtils.createIntBuffer(inarray.length).put(inarray).flip();
    }
    
    public static ByteBuffer createBuffer(byte[] inarray){
        return (ByteBuffer) BufferUtils.createByteBuffer(inarray.length).put(inarray).flip();
    }

    public static final double[] verts = {
        //unit cube
        //.5, .5, .5, .5, .5, -.5, -.5, .5, -.5, -.5, .5, .5,
        //.5, -.5, .5, .5, -.5, -.5, -.5, -.5, -.5, -.5, -.5, .5,
            
            
        1,1,0, 0,1,0, 0,1,1, 0,1,1, 1,1,1, 1,1,0,
    
    
        1,0,0, 1,0,1, 0,0,1, 0,0,1, 0,0,0, 1,0,0
    };
    
    public static final int[] cubeAr = {
        0, 1, 2, 3,
        7, 6, 5, 4,
        0, 1, 5, 4,
        1, 2, 6, 5,
        2, 3, 7, 6,
        3, 0, 4, 7
    };
    
    public static final double[] texturePointers = {
        0.0,0.0, 1.0,0.0, 1.0,1.0, 0.0,1.0,
        0.0,0.0, 1.0,0.0, 1.0,1.0, 0.0,1.0,
    };
}
