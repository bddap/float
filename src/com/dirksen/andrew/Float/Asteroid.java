package com.dirksen.andrew.Float;

import org.lwjgl.opengl.GL11;

public class Asteroid extends Satellite {
	Asteroid(double x, double y, double z){
		super(x,y,z);
	}
	
	Asteroid(Matrix position){
		super(position);
	}
	
	Asteroid(Matrix position, Matrix velocity, Matrix rotation, Matrix rotvel){
		super(position, velocity, rotation, rotvel);
	}
	
	void draw(){

		GL11.glMultMatrix(position.matb);
		GL11.glMultMatrix(rotation.matb);
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