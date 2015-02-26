package com.dirksen.andrew.Float;

import org.lwjgl.opengl.GL11;

public class Asteroid extends Satellite {
	void draw(){
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
