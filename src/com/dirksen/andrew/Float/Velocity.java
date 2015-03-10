package com.dirksen.andrew.Float;

import Jama.Matrix;

public class Velocity{
	double x;
	double y;
	double z;

	Velocity(){
		this(0,0,0);
	}
	
	Velocity(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	void rotate(Orientation o){
		double vec[][] = {{x,y,z,0}};
		Matrix rotae = (new Matrix(vec)).times(o.orient);
		
		/*rotae.print(4, 4);
		vec = rotae.getArray();
		System.out.println(vec[0][0] + " " + vec[0][1] + " " + vec[0][2] + " " + vec[0][3]);*/

		x = rotae.get(0, 0);
		y = rotae.get(0, 1);
		z = rotae.get(0, 2);
	}

	void add(Velocity v) {
		x += v.x;
		y += v.y;
		z += v.z;
	}
	
	public String toString(){
		return x + " " + y + " " + z;
	}
}
