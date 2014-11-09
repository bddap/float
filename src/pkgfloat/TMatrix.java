/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgfloat;

/**
 *
 * @author bddap
 */
public class TMatrix {
    double[][] m;
    
    TMatrix(){
        m = new double[4][4];
    }
    
    public TMatrix identity(){
        m[0][0] = 1;    m[0][1] = 0;    m[0][2] = 0;    m[0][3] = 0;
        m[1][0] = 0;    m[1][1] = 1;    m[1][2] = 0;    m[1][3] = 0;
        m[2][0] = 0;    m[2][1] = 0;    m[2][2] = 1;    m[2][3] = 0;
        m[3][0] = 0;    m[3][1] = 0;    m[3][2] = 0;    m[3][3] = 1;
        
        return this;
    }
    
    public TMatrix tranlate(double x, double y, double z){
        m[0][0] = 1;    m[0][1] = 0;    m[0][2] = 0;    m[0][3] = x;
        m[1][0] = 0;    m[1][1] = 1;    m[1][2] = 0;    m[1][3] = y;
        m[2][0] = 0;    m[2][1] = 0;    m[2][2] = 1;    m[2][3] = z;
        m[3][0] = 0;    m[3][1] = 0;    m[3][2] = 0;    m[3][3] = 1;
        
        return this;
    }
    
    public TMatrix scale(double x, double y, double z){
        m[0][0] = x;    m[0][1] = 0;    m[0][2] = 0;    m[0][3] = 0;
        m[1][0] = 0;    m[1][1] = y;    m[1][2] = 0;    m[1][3] = 0;
        m[2][0] = 0;    m[2][1] = 0;    m[2][2] = z;    m[2][3] = 0;
        m[3][0] = 0;    m[3][1] = 0;    m[3][2] = 0;    m[3][3] = 1;
        
        return this;
    }
    
    public TMatrix rotate(Quaternion q){
        double n = q.q[0] * q.q[0] + q.q[1] * q.q[1] + q.q[2] * q.q[2] + q.q[3] * q.q[3];
        double s;
        if (n == 0) {s = 0;} else {s = 2 / n;}
        double wx = s * q.q[0] * q.q[1];    double wy = s * q.q[0] * q.q[2];    double wz = s * q.q[0] * q.q[3];
        double xx = s * q.q[1] * q.q[1];    double xy = s * q.q[1] * q.q[2];    double xz = s * q.q[1] * q.q[3];
        double yy = s * q.q[2] * q.q[2];    double yz = s * q.q[2] * q.q[3];    double zz = s * q.q[3] * q.q[3];

        //[ 1 - (yy + zz)         xy - wz          xz + wy  ]
        //[      xy + wz     1 - (xx + zz)         yz - wx  ]
        //[      xz - wy          yz + wx     1 - (xx + yy) ]
        m[0][0] = 1-(yy + zz);    m[0][1] = xy - wz;        m[0][2] = xz + wy;          m[0][3] = 0;
        m[1][0] = xy + wz;        m[1][1] = 1-(xx + zz);    m[1][2] = yz - wx;          m[1][3] = 0;
        m[2][0] = xz - wy;        m[2][1] = yz + wx;        m[2][2] = 1 - (xx + yy);    m[2][3] = 0;
        m[3][0] = 0;              m[3][1] = 0;              m[3][2] = 0;                m[3][3] = 1;
        
        return this;
    }
    
    public TMatrix mul(TMatrix b){
        TMatrix a = new TMatrix();
        for (int y = 0; y < 4; y++){
            for (int x = 0; x < 4; x++){
                a.setM(y,x,
                        m[y][0] * a.getM(0, x) +
                        m[y][1] * a.getM(1, x) +
                        m[y][2] * a.getM(2, x) +
                        m[y][3] * a.getM(3, x)
                        );
            }
        }
        
        return a;
    }
    
    double getM(int y, int x){
        return m[y][x];
    }
    
    void setM(int y, int x, double f){
        m[y][x] = f;
    }
}
