package com.dirksen.andrew.Float;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;

import Jama.Matrix;

public class Satellite {
	Matrix position;	//position is stored as a transformation matrix 
	Matrix rotation;
	Matrix velocity;	//velocity is applied to position every tick
	Matrix rotvel;
	
	ByteBuffer compbb;	//a ByteBuffer for passing position and rotation to openGl as a single matrix
	
	Satellite(Matrix position, Matrix velocity, Matrix rotation, Matrix rotvel){
		this.position = position;
		this.velocity = velocity;
		this.rotation = rotation;
		this.rotvel   =	rotvel;
		
		compbb = ByteBuffer.allocateDirect(4*4*8);
		compbb.order(ByteOrder.nativeOrder());    // use the device hardware's native byte order
	}
	
	Satellite(){
		this(	new Matrix(Bmat.translation(0, 0, 0)),
				new Matrix(Bmat.translation(0, 0, 0)),
				new Matrix(Bmat.rotation(0, 0, 1, 0)),
				new Matrix(Bmat.rotation(0, 0, 1, 0)));
	}
	
	void tick(){
		position = position.times(velocity);
		rotation = rotvel.times(rotation);
	}

	void thrust(double x, double y, double z){
		Matrix localThrust = new Matrix(Bmat.translation(x, y, z));
		double[][] globalThrust = localThrust.times(rotation).getArray();	//rotatedThrust
		int[][] mask = {
				{1,0,0,0},
				{0,1,0,0},
				{0,0,1,0},
				{2,2,2,1},
		};
		for (int i = 0; i < mask.length; i++){
			for (int j = 0; j < mask[0].length; j++){
				if (mask[i][j] == 1){
					globalThrust[i][j] = 1.0;
				}else if (mask[i][j] == 0){
					globalThrust[i][j] = 0.0;
				}
			}
		}
		velocity = new Matrix(globalThrust).times(velocity);
	}
	
	void thrust(double theta, double x, double y, double z){
		Matrix mod = new Matrix(Bmat.rotation(theta, x, y, z));
		rotvel = mod.times(rotvel);
	}
	
	void updateCompositePositionMatrix(){
		Matrix comp = rotation.times(position);
		double[] compstore = comp.getRowPackedCopy();
		for (int i = 0; i < compstore.length; i++){
			System.out.print(compstore[i] + " ");
			if (i%4 == 3){
				System.out.println();
			}
		}
		System.out.println();
		compbb.asDoubleBuffer().put(compstore);
	}
}
