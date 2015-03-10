package com.dirksen.andrew.Float;

import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.ByteBuffer;

import org.lwjgl.opengl.*;

public class Asteroid extends Satellite {
	
	Asteroid(double x, double y, double z){
		super(x,y,z);
		
		//temp
		tempdb = ByteBuffer.allocateDirect(16*8).order(ByteOrder.nativeOrder()).asDoubleBuffer();
	}
	
	Asteroid(Position pos, Orientation ori, Velocity vel, AngularVelocity  ang){
		super(pos,ori,vel,ang);
		
		//temp
		tempdb = ByteBuffer.allocateDirect(16*8).order(ByteOrder.nativeOrder()).asDoubleBuffer();
	}
	
	DoubleBuffer tempdb;

	void draw(){
		GL11.glPushMatrix();
		
		constructRenderMatrix();
		GL11.glMultMatrix(compbb.asDoubleBuffer());
		//GL11.glTranslated(1,2,3);
		
		/*System.out.println("compbb");
		for (int i = 0; i < 16; i++){
			System.out.print(compbb.asDoubleBuffer().get(i)+" ");
			if (i%4 == 3){
				System.out.println();
			}
		}

		GL11.glGetDouble(GL11.GL_MODELVIEW_MATRIX, tempdb);
		System.out.println("Loaded");
		for (int i = 0; i < 16; i++){
			System.out.print(tempdb.get(i)+" ");
			if (i%4 == 3){
				System.out.println();
			}
		}
		System.out.println();
		//GL11.glTranslated(0, 0, -10);
		//position.print();*/
		
		GL11.glBegin(GL11.GL_QUADS);    
			GL11.glColor3f(1.0f,1.0f,0.0f);           
	        GL11.glVertex3f( 1.0f, 1.0f,-1.0f);        
	        GL11.glVertex3f(-1.0f, 1.0f,-1.0f);        
	        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
	        GL11.glVertex3f( 1.0f, 1.0f, 1.0f);  
	        GL11.glColor3f(1.0f,0.5f,0.0f);            
	        GL11.glVertex3f( 1.0f,-1.0f, 1.0f);
	        GL11.glVertex3f(-1.0f,-1.0f, 1.0f);
	        GL11.glVertex3f(-1.0f,-1.0f,-1.0f);
	        GL11.glVertex3f( 1.0f,-1.0f,-1.0f);
	        GL11.glColor3f(1.0f,0.0f,0.0f);
	        GL11.glVertex3f( 1.0f, 1.0f, 1.0f);
	        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
	        GL11.glVertex3f(-1.0f,-1.0f, 1.0f);
	        GL11.glVertex3f( 1.0f,-1.0f, 1.0f);
	        GL11.glColor3f(1.0f,1.0f,0.0f);
	        GL11.glVertex3f( 1.0f,-1.0f,-1.0f);
	        GL11.glVertex3f(-1.0f,-1.0f,-1.0f);
	        GL11.glVertex3f(-1.0f, 1.0f,-1.0f);
	        GL11.glVertex3f( 1.0f, 1.0f,-1.0f);
	        GL11.glColor3f(0.0f,0.0f,1.0f);
	        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
	        GL11.glVertex3f(-1.0f, 1.0f,-1.0f);
	        GL11.glVertex3f(-1.0f,-1.0f,-1.0f);
	        GL11.glVertex3f(-1.0f,-1.0f, 1.0f);
	        GL11.glColor3f(1.0f,0.0f,1.0f);
	        GL11.glVertex3f( 1.0f, 1.0f,-1.0f);
	        GL11.glVertex3f( 1.0f, 1.0f, 1.0f);
	        GL11.glVertex3f( 1.0f,-1.0f, 1.0f);
	        GL11.glVertex3f( 1.0f,-1.0f,-1.0f);
	    GL11.glEnd();   
	    

		GL11.glPopMatrix();
	}
	
}