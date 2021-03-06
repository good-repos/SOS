package applications.CEC2011;

import static utils.MatLab.norm2;
import static utils.MatLab.reShape;
import static utils.MatLab.subtract;
import static utils.MatLab.transpose;


import interfaces.Problem;

public class P6 extends Problem { //Tersoff Potential Function Si(C) Minimization Problem

	
	public P6(int probDim) //throws Exception
	{
		super(probDim); 
		
		if(probDim%3!=0) 
			System.out.println("The dimensionality of the problem is not a multiple of 3"); 
		else
			this.setBounds(generateBounds(probDim));	
	}
	
	public double f(double[] x){return evaluatePotential(x);}
		
	private double[][] generateBounds(int probDim)
	{
		double[][] bounds = new double[probDim][2];
		
		bounds[0][0] = -4.25; bounds[0][1] = 4.25; 
		
		if(probDim==1) return bounds;
		
		bounds[1][0] = 0; bounds[1][1] = 4;
		
		if(probDim==2) return bounds;
		
		bounds[2][0] = 0; bounds[2][1] = Math.PI;
			
		for (int i = 3; i < probDim; i++)
		{
			bounds[i][0] = -4.25;;
			bounds[i][1] =  4.25;			
		}
			
		return bounds;
	}
	
	private static double evaluatePotential(double[] x)
	{
		//Parameters
		double R=2.85; 			double D=0.15;
		double A=1830.8; 		double B=471.18;
		double lamda1=2.4799; 	double lamda2=1.7322; 	double lamda3=1.7322;
		double c=100390;		double d=16.218; double h=-0.59826;
		double n1=0.78734;
		double gamma=1.0999E-6;
		
		
		int n = x.length/3;
		double[][] X = reShape(x,3,n);
		X = transpose(X);
		
		double E=0;
		double eps;
		double fc,ctheta;
		double rij,rik,rkj;
		
		for(int i=0; i<n-1; i++)
		{
		
			for (int j=i+1; j<n-1; j++)
			{
				if(j!=i)
				{
					rij = norm2(subtract(X[i],X[j]));
					
					if(rij<=R-D) { fc = 1; }
					else if(rij>=R+D) {fc = 0; }
					else {fc= 0.5-0.5*Math.sin((Math.PI*(rij-R))/D); }
									
					eps=0;
					
					for(int k=0; k<n-1; k++)
					{
						if(k!=j && k!=i) {
							rik = norm2(subtract(X[i],X[k]));
							rkj = norm2(subtract(X[k],X[j]));
							
							ctheta = (Math.pow(rij,2)+Math.pow(rik,2)-Math.pow(rkj,3))/(2*Math.pow(rij,2)*Math.pow(rik,2));
							
							eps = eps +  fc*( 1 + (Math.pow(c,2)*(Math.pow(d,-2)+ 1/(Math.pow(d,2)+Math.pow(h-ctheta,2)))*Math.exp(Math.pow(lamda3*(rij-rik),3)) ) );		
						}									
					}
					
					E= E + 0.5*fc*(A*Math.exp(-lamda1*rij)+ Math.pow(1+Math.pow(gamma*eps,n1),-0.5*n1)*B*Math.exp(-lamda2*rij));
				}
			}
		}
			
		return E;
	}
	
	

}
