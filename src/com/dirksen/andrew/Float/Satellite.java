package com.dirksen.andrew.Float;

public class Satellite {
	Matrix position;	//position is stored as a transformation matrix 
	Matrix velocity;	//velocity is applied to position every tick
	Matrix rotation;
	Matrix rotvel;
	
	
	Satellite(){
		position = Matrix.identity();
		velocity = Matrix.identity();
		rotation = Matrix.identity();
		rotvel = Matrix.identity();
	}
	
	Satellite(double x, double y, double z){
		position = Matrix.translation(x,y,z);
		velocity = Matrix.identity();
		rotation = Matrix.identity();
		rotvel = Matrix.identity();
	}
	
	Satellite(Matrix position){
		this.position = position;
		velocity = Matrix.identity();
		rotation = Matrix.identity();
		rotvel = Matrix.identity();
	}
	
	Satellite(Matrix position, Matrix velocity, Matrix rotation, Matrix rotvel){
		this.position = position;
		this.velocity = velocity;
		this.rotation = rotation;
		this.rotvel = rotvel;
	}
	
	void tick(){
		position = position.multiply(velocity);
		rotation = rotation.multiply(rotvel);
	}
}
