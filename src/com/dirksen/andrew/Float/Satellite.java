package com.dirksen.andrew.Float;

public class Satellite {
	Matrix position;	//position is stored as a transformation matrix 
	Matrix velocity;	//velocity is applied to position every tick
	
	Satellite(){
		position = Matrix.identity();
		velocity = Matrix.identity();
	}
	
	Satellite(double x, double y, double z){
		position = Matrix.translation(x,y,z);
		velocity = Matrix.identity();
	}
	
	Satellite(Matrix position){
		this.position = position;
		velocity = Matrix.identity();
	}
}
