package com.dirksen.andrew.Float;

import Jama.Matrix;

public class AngularVelocity {
	//Angular Velocity will likely represent a local transform that is applied to A Rotation every tick
	
	Matrix vel;
	
	AngularVelocity(double theta, double x, double y, double z){
		vel = new Matrix(Bmat.rotation(theta,x,y,z));
	}

	private AngularVelocity(Matrix m){
		vel = m;
	}
	
	AngularVelocity() {
		this(0,1,0,0);
	}
	
	Matrix matrix(){
		return vel;
	}
	
	void add(AngularVelocity torque) {
		vel = vel.times(torque.matrix());
	}
	
	AngularVelocity localToGlobal(Orientation o){
		Matrix om = o.matrix();
		Matrix globalTranform = om.inverse().times(vel).times(om);
		return new AngularVelocity(globalTranform);
	}
	
	public String toString(){
		double [][] or = vel.getArray();
		String ret = "";
		for (int i = 0; i < or.length; i++){
			ret += "\n";
			for (int j = 0; j < or[0].length; j++){
				ret += or[i][j] + " ";
			}
		}
		return ret;
	}

}
