package pkgfloat;


public class PhysicsEntity extends Entity {
	public double xv,yv,zv;
	public double krv,xrv,yrv,zrv;
	
	PhysicsEntity(){
		x=0;
		y=0;
		z=0;
		xv=0;
		yv=0;
		zv=0;
		kr=0;
		xr=0;
		yr=0;
		zr=0;
		krv=0;
		xrv=0;
		yrv=0;
		zrv=0;
	}
	
	PhysicsEntity(double xs, double ys, double zs){
		x=xs;
		y=ys;
		z=zs;
		
		xv=0;
		yv=0;
		zv=0;
		kr=0;
		xr=0;
		yr=0;
		zr=0;
		krv=0;
		xrv=0;
		yrv=0;
		zrv=0;
	}
	
	PhysicsEntity(double xs, double ys, double zs, double xvs, double yvs, double zvs){
		x=xs;
		y=ys;
		z=zs;
		xv=xvs;
		yv=yvs;
		zv=zvs;
		
		kr=0;
		xr=0;
		yr=0;
		zr=0;
		krv=0;
		xrv=0;
		yrv=0;
		zrv=0;
	}
	
	PhysicsEntity(PhysicsEntity p){
		x=p.x;
		y=p.y;
		z=p.z;
		xv=p.xv;
		yv=p.yv;
		zv=p.zv;
		kr=p.kr;
		xr=p.xr;
		yr=p.yr;
		zr=p.zr;
		krv=p.krv;
		xrv=p.xrv;
		yrv=p.yrv;
		zrv=p.zrv;
	}
        
        void tick(){
            x += xv;
            y += yv;
            z += zv;
        }
}
