public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public static double grav = 6.67e-11;

	public Planet(double xP, double yP, double xV, 
		double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		return Math.sqrt(Math.pow(this.xxPos - p.xxPos,2) 
			+ Math.pow(this.yyPos - p.yyPos,2));
	}

	public double calcForceExertedBy(Planet p){
		return grav * this.mass * p.mass / Math.pow(this.calcDistance(p),2);	
	}

	public double calcForceExertedByX(Planet p){
		return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) 
		/ this.calcDistance(p);
	}
	public double calcForceExertedByY(Planet p){
		return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) 
		/ this.calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] planets){
		double netForceExertedByX = 0;
		for (Planet p:planets){
			if (!this.equals(p)){
				netForceExertedByX += this.calcForceExertedByX(p);
			}
		}
		return netForceExertedByX;
	}
	public double calcNetForceExertedByY(Planet[] planets){
		double netForceExertedByY = 0;
		for (Planet p:planets){
			if (!this.equals(p)){
				netForceExertedByY += this.calcForceExertedByY(p);
			}
		}
		return netForceExertedByY;
	}

	public void update(double dt, double fX, double fY){
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel += aX * dt;
		this.yyVel += aY * dt;
		this.xxPos += this.xxVel * dt;
		this.yyPos += this.yyVel * dt;
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos,"./images/"+this.imgFileName);
	}
}