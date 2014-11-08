package pkgfloat;

public class Cube extends PhysicsEntity {

    Cube(double xs, double ys, double zs) {
        x = xs;
        y = ys;
        z = zs;

        xv = 0;
        yv = 0;
        zv = 0;
        kr = 0;
        xr = 0;
        yr = 0;
        zr = 0;
        krv = 0;
        xrv = 0;
        yrv = 0;
        zrv = 0;
    }

    //private static cubeIndices = Models.createBuffer(Models.cube);
    
    public void draw() {
        //BddapGraphics.draw(Models.cubeIndicesStart,x,y,z);
    }
}
