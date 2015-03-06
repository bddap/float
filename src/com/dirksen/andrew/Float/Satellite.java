package com.dirksen.andrew.Float;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import Jama.Matrix;

public class Satellite {
	Position pos;	//position is stored as a transformation matrix 
	Orientation ori;
	Velocity vel;	//velocity is applied to position every tick
	AngularVelocity ang;
	
	ByteBuffer compbb;	//a ByteBuffer for passing position and rotation to openGl as a single matrix
	
	
	Satellite(double x, double y, double z){
		this(
				new Position(x,y,z),
				new Orientation(),
				new Velocity(),
				new AngularVelocity()
				);
	}
	
	Satellite(Position pos, Orientation ori, Velocity vel, AngularVelocity  ang){
		this.pos = pos;
		this.ori = ori;
		this.vel = vel;
		this.ang = ang;
		
		compbb = ByteBuffer.allocateDirect(4*4*8);
		compbb.order(ByteOrder.nativeOrder());    // use the device hardware's native byte order
	}
	
	void tick(){
		pos.add(vel);
		ori.add(ang);
	}

	void thrust(Velocity lv){
		lv.rotate(ori);
		vel.add(lv);
	}
	
	void thrust(AngularVelocity lav){
		//TODO
	}
	
	void constructRenderMatrix(){
		Matrix comp = ori.matrix().times(pos.matrix());
		
		double[] compstore = comp.getRowPackedCopy();
		/*for (int i = 0; i < compstore.length; i++){
			System.out.print(compstore[i] + " ");
			if (i%4 == 3){
				System.out.println();
			}
		}
		System.out.println();*/
		compbb.asDoubleBuffer().put(compstore);
	}
}
