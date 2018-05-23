package benchmarks.problemsImplementation.CEC2013;


public class F28 extends AbstractCEC2013
{
	protected final double bias = 1400.0;
	
	public F28 (int dimension) {super(dimension);}

	// Function body
	public double f(double[] x) {return CEC2013TestFunctions.cf08(x,nx,this.OShift,this.M,1)+this.bias;}
}
