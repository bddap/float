/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgfloat;

import java.util.Random;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author bddap
 */
public class Stars {
    Entity[] stars;
    static int modeAtBegin;
    Stars(){
        Random random = new Random(80085);
        stars = new Entity[10000];
        final double scale = 1000000;
        for (int i = 0; i < stars.length; i++){
            stars[i] = new Entity();
            stars[i].x = (random.nextDouble()-.5)*scale;
            stars[i].y = (random.nextDouble()-.5)*scale;
            stars[i].z = (random.nextDouble()-.5)*scale;
        }
        starColors = new double[10000][3];
        for (int i = 0; i < starColors.length; i++){
            double t = (double) i;
            t = t/100;
            if (i%100 == 0){
                starColors[i][0] = .9;
            }else{
                starColors[i][0] = .5;
            }
            starColors[i][1] = .5;
            starColors[i][2] = (3 + Math.sin(t))/4;
        }
    }
    double starColors[][];
    void inefficientDraw(){
        for (int i = 0; i < stars.length; i++){
            //glPushMatrix();
            
            
            glColor3d(starColors[i][0],starColors[i][1],starColors[i][2]);
            
            glBegin(GL_POINTS);
 
            glVertex3d(stars[i].x, stars[i].y, stars[i].z);
 
            glEnd();
            
            //glPopMatrix();
        }
    }
}
