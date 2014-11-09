/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgfloat;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import static pkgfloat.Float.yaw;
import static pkgfloat.Float.roll;

/**
 *
 * @author bddap
 */
public class Input {
    static Timer inputTimer;
    
    Input(){
    }
    
    static void control(PhysicsEntity ent, long timePassed) {
        double accel = .0001 * timePassed;
        
        float dx = (float) Mouse.getDX();
        float dy = (float) Mouse.getDY();
        yaw += dx / 10;
        roll -= dy / 10;
        if (roll > 90){
            roll = 90;
        }else if(roll < -90){
            roll = -90;
        }

        float pxvToAdd = 0;
        float pzvToAdd = 0;
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            pxvToAdd -= accel * Math.sin(Math.toRadians(yaw));
            //pyv += .01 * (float)Math.sin(Math.toRadians(roll));
            pzvToAdd += accel * Math.cos(Math.toRadians(yaw));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            pxvToAdd += accel * Math.sin(Math.toRadians(yaw));
            pzvToAdd -= accel * Math.cos(Math.toRadians(yaw));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            pxvToAdd -= accel * Math.sin(Math.toRadians(yaw - 90));
            pzvToAdd += accel * Math.cos(Math.toRadians(yaw - 90));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            pxvToAdd -= accel * Math.sin(Math.toRadians(yaw + 90));
            pzvToAdd += accel * Math.cos(Math.toRadians(yaw + 90));
        }
        ent.xv += pxvToAdd;
        ent.zv += pzvToAdd;
    }
}
