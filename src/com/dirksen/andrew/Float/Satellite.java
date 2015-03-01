package com.dirksen.andrew.Float;

public class Satellite {
	Matrix position;	//position is stored as a transformation matrix 
	Matrix rotation;
	Matrix velocity;	//velocity is applied to position every tick
	Matrix rotvel;
	
	Satellite(){
		position = new Matrix(Matrix.identity());
		velocity = new Matrix(Matrix.identity());
		rotation = new Matrix(Matrix.identity());
		rotvel   = new Matrix(Matrix.identity());
	}
	
	Satellite(double x, double y, double z){
		position = new Matrix(Matrix.translation(x,y,z));
		velocity = new Matrix(Matrix.identity());
		rotation = new Matrix(Matrix.identity());
		rotvel   = new Matrix(Matrix.identity());
	}
	
	Satellite(Matrix position, Matrix velocity, Matrix rotation, Matrix rotvel){
		this.position = position;
		this.velocity = velocity;
		this.rotation = rotation;
		this.rotvel   =	rotvel;
	}
	
	void tick(){
		position = Matrix.multiply(position, velocity);
		rotation = Matrix.multiply(rotvel, rotation);
	}

	void thrust(double x, double y, double z){
		double[] rt = rotation.multiplyByVector(x,y,z);	//rotatedThrust
		Matrix mod = new Matrix(Matrix.translation(rt[0], rt[1], rt[2]));
		position = Matrix.multiply(position, mod);
	}
	
	void thrust(double theta, double x, double y, double z){
		Matrix mod = new Matrix(Matrix.rotation(theta, x, y, z));
		rotvel = Matrix.multiply(rotvel,mod);
	}
}
