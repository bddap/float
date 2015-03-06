package com.dirksen.andrew.Float;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;

import org.lwjgl.opengl.*;
import Jama.Matrix;

public class Asteroid extends Satellite {
	
	Asteroid(Matrix position, Matrix velocity, Matrix rotation, Matrix rotvel){
		super(position, velocity, rotation, rotvel);
		
		ByteBuffer bb = ByteBuffer.allocateDirect(16*8);
		bb.order(ByteOrder.nativeOrder());    // use the device hardware's native byte order
		tempdb = bb.asDoubleBuffer();
	}
	
	DoubleBuffer tempdb;

	void draw(){

		updateCompositePositionMatrix();
		GL11.glMultMatrix(compbb.asDoubleBuffer());
		//GL11.glTranslated(1,2,3);
		
		System.out.println("compbb");
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
		//position.print();
		
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
	}
	
}