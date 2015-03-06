package com.dirksen.andrew.Float;

import Jama.Matrix;

public class Orientation {
	Matrix orient;
	
	Orientation(Matrix orient){
		this.orient = orient;
	}

	Orientation() {
		this( new Matrix(Bmat.identity()) );
	}
	
	Orientation(double theta, double x, double y, double z){
		this( new Matrix(Bmat.rotation(theta, x, y, z)) );
	}
	
	void add(AngularVelocity av){
		orient = orient.times(av.matrix());
	}
	
	Matrix matrix(){
		return orient;
	}
}
