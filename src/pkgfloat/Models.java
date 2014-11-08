package pkgfloat;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Models {
    public static DoubleBuffer vb;
    public static IntBuffer ib;
    
    static void genBuffers(){
        vb = Models.createBuffer(Models.verts);
        ib = Models.createBuffer(Models.cube);
    }
    
    public static DoubleBuffer createBuffer(double[] inarray){
        return (DoubleBuffer) BufferUtils.createDoubleBuffer(inarray.length).put(inarray).flip();
    }
    
    public static IntBuffer createBuffer(int[] inarray){
        return (IntBuffer) BufferUtils.createIntBuffer(inarray.length).put(inarray).flip();
    }

    public static final double[] verts = {
        //unit cube
        .5, .5, .5, .5, .5, -.5, -.5, .5, -.5, -.5, .5, .5,
        .5, -.5, .5, .5, -.5, -.5, -.5, -.5, -.5, -.5, -.5, .5
    };
    
    public static final int[] cube = {
        0, 1, 2, 3,
        7, 6, 5, 4,
        0, 1, 5, 4,
        1, 2, 6, 5,
        2, 3, 7, 6,
        3, 0, 4, 7
    };
}
