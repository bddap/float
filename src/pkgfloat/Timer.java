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
public class Timer {
    private long lastRead;
    private long thisRead;
    private long delta;
    
    Timer(){
        thisRead = System.currentTimeMillis();
        lastRead = thisRead;
        delta = 0;
    }
    
    long read(){
        lastRead = thisRead;
        thisRead = System.currentTimeMillis();
        delta = thisRead - lastRead;
        return delta;
    }
    
    long getDelta(){
        return delta;
    }
}
