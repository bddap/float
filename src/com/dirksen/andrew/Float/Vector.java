package com.dirksen.andrew.Float;

public class Vector {
	double xyz[];
	
	Vector(double x, double y, double z){
		xyz = new double[]{x,y,z};
	}
	
	Vector(double v[]){
		xyz = v;
	}
	
	Vector(){
		this(0.0,0.0,0.0);
	}
}
