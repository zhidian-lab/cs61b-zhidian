public class NBody{
	public static double readRadius(String fileName){
		In in = new In(fileName);
		in.readDouble();
		return in.readDouble();
	}

	public static Planet[] readPlanets(String fileName){
		In in = new In(fileName);
		int total = in.readInt();
		Planet[] Bodies= new Planet[total];
		in.readDouble();

		// while (!in.isEmpty()) {
		// 	for (int i = 0; i < total; i += 1){
		// 		Bodies[i] = new Planet(in.readDouble(),
		// 							in.readDouble(),
		// 							in.readDouble(),
		// 							in.readDouble(),
		// 							in.readDouble(),
		// 							in.readString());
		// 		System.out.println(Bodies[i].imgFileName);

		for (int i = 0; i < total; i += 1){
			Bodies[i] = new Planet(in.readDouble(),
								in.readDouble(),
								in.readDouble(),
								in.readDouble(),
								in.readDouble(),
								in.readString());
		}
		return Bodies;
	}
	public static void main(String[] args){
		Double T=Double.parseDouble(args[0]);
		Double dT=Double.parseDouble(args[1]);
		String filename=args[2];

		Planet[] planets = readPlanets(filename);
		int total = planets.length;
		Double radius = readRadius(filename);

		StdDraw.setScale(-radius,radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
		for (Planet p: planets){
			p.draw();
		}
		StdDraw.enableDoubleBuffering();
		 for (double t = 0; t <= T; t += dT){
		 	Double[] xForces = new Double[total];
		 	Double[] yForces = new Double[total];

		 	for (int i = 0; i < total; i += 1){
		 		xForces[i] = planets[i].calcNetForceExertedByX(planets);
		 		yForces[i] = planets[i].calcNetForceExertedByY(planets);
		 	}
		 	for (int i = 0; i < total; i += 1){
		 		planets[i].update(dT, xForces[i], yForces[i]);
		 	} 

		 	StdDraw.clear();
		 	StdDraw.picture(0,0,"images/starfield.jpg");
		 	for (Planet p:planets){
		 		p.draw();
		 	}
		 	StdDraw.show();
		 	// StdDraw.pause(400);
		}
		StdOut.printf("%d\n", total);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < total; i += 1){
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
				planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, 
				planets[i].mass, planets[i].imgFileName);
		}
	}
}	