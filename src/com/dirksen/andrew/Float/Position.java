package com.dirksen.andrew.Float;

import Jama.Matrix;

public class Position {
	double x;
	double y;
	double z;
	
	Position(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	void add(Velocity v){
		x += v.x;
		y += v.y;
		z += v.z;
	}
	
	Matrix matrix(){
		return new Matrix(Bmat.translation(x, y, z));
	}
	
	public String toString(){
		return x + " " + y + " " + z;
	}
}
