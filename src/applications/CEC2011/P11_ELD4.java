package applications.CEC2011;

import interfaces.Problem;
import utils.MatLab;

public class P11_ELD4 extends Problem {
	
	int powerDemand=10500;
	
	double[] pMin= {36,36,60,80,47,68,110,135,135,130,94,94,125,125,125,125,220,220,242,242,254,254,254,254,254,254,10,10,10,47,60,60,60,90,90,90,25,25,25,242};
	double[] pMax= {114,114,120,190,97,140,300,300,300,300,375,375,500,500,500,500,500,500,550,550,550,550,550,550,550,550,150,150,150,97,190,190,190,200,200,200,110,110,110,550};
	
	double[] a= {0.00690,0.00690,0.0202800,0.00942,0.0114,0.0114200,0.00357,0.00492,0.00573,0.00605,0.00515,0.00569,0.00421,0.00752,0.00708,0.00708,0.00313,0.00313,0.00313,0.00313,0.00298,0.00298,0.00284,0.00284,0.00277,0.00277,0.521240,0.521240,0.521240,0.0114,0.00160,0.00160,0.00160,0.100,0.100,0.100,0.0161,0.0161,0.0161,0.00313};
	double[] b= {6.73,6.73,7.07,8.18,5.35,8.05,8.03,6.99,6.60,12.9,12.9,12.8,12.5,8.84,9.15,9.15,7.97,7.95,7.97,7.97,6.63,6.63,6.66,6.66,7.10,7.10,3.33,3.33,3.33,5.35,6.43,6.43,6.43,8.95,8.62,8.62,5.88,5.88,5.88,7.97};
	double[] c= {94.7050,94.7050,309.540,369.030,148.890,222.330,287.710,391.980,455.760,722.820,635.200,654.690,913.400,1760.40,1728.30,1728.30,647.850,649.690,647.830,647.810,785.960,785.960,794.530,794.530,801.320,801.320,1055.10,1055.10,1055.10,148.890,222.920,222.920,222.920,107.870,116.580,116.580,307.450,307.450,307.450,647.830};
	double[] e= {100,100,100,150,120,100,200,200,200,200,200,200,300,300,300,300,300,300,300,300,300,300,300,300,300,300,120,120,120,120,150,150,150,200,200,200,80,80,80,300};
	double[] f= {0.0840,0.0840,0.0840,0.0630,0.0770,0.0840,0.0420,0.0420,0.0420,0.0420,0.0420,0.0420,0.0350,0.0350,0.0350,0.0350,0.0350,0.0350,0.0350,0.0350,0.0350,0.0350,0.0350,0.0350,0.0350,0.0350,0.0770,0.0770,0.0770,0.0770,0.0630,0.0630,0.0630,0.0420,0.0420,0.0420,0.0980,0.0980,0.0980,0.0350};
	
	
	public P11_ELD4() {
		
		super(40);
		
		this.setBounds(generateBounds(getDimension()));		
	}
	
	
	public double f(double[] x) throws Exception {
	
		
		double capacityLimitsPenalty = 0;
		double powerBalancePenalty =0;
		
		double currentCost=0;
					
		powerBalancePenalty += Math.abs(powerDemand-MatLab.sum(x));
					
		//Capacity Limits Penalty Calculation
		for(int i=0; i<x.length; i++) {
			capacityLimitsPenalty += Math.abs(x[i]-pMin[i]) 
					- (x[i]-pMin[i]) + Math.abs(pMax[i]-x[i]) 
					-(pMax[i]-x[i]); 
		}
		

		
		for(int i=0; i<x.length; i++) {
			currentCost+= a[i]*Math.pow(x[i],2)+b[i]*x[i]+c[i]
					+Math.abs(e[i]*Math.sin(f[i]*(pMin[i]-x[i])));
		}
	
	
		double penalty = 100000*powerBalancePenalty + 1000*capacityLimitsPenalty;
		
		return currentCost+penalty;
	}

	
	
	private double[][] generateBounds(int probDim)
	{
		double[][] bounds = new double[probDim][2];
		
		for(int i=0; i<probDim; i++) {
			bounds[i][0] = pMin[i];
			bounds[i][1] = pMax[i];
		}
				
		return bounds;
	}
	
}