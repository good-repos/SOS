/**
Copyright (c) 2018, Fabio Caraffini (fabio.caraffini@gmail.com, fabio.caraffini@dmu.ac.uk)
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met: 

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer. 
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies, 
either expressed or implied, of the FreeBSD Project.
*/
package experiments.rotInvStudy;

import interfaces.Experiment;
import interfaces.Algorithm;
import benchmarks.RCEC2014;
import algorithms.DE;



public class RCEC14TuningDEroe extends Experiment
{
	public RCEC14TuningDEroe(int probDim) throws Exception
	{
		super(probDim,5000,"RotatedCEC14");
		setNrRuns(30);

		Algorithm a;

		a = new DE("ro",'e');
		a.setID("rDEr1exp01");
		a.setParameter("p0", 10.0); //Population size
		a.setParameter("p1", 0.4); //F
		a.setParameter("p2", 0.1); //CR
		a.setParameter("p3", Double.NaN);//Alpha
		add(a);
		
		a = new DE("ro",'e');
		a.setID("rDEr1exp05");
		a.setParameter("p0", 10.0); //Population size
		a.setParameter("p1", 0.4); //F
		a.setParameter("p2", 0.5); //CR
		a.setParameter("p3", Double.NaN);//Alpha
		add(a);
		
		a = new DE("ro",'e');
		a.setID("rDEr1exp07");
		a.setParameter("p0", 10.0); //Population size
		a.setParameter("p1", 0.4);//F
		a.setParameter("p2", 0.7);//CR
		a.setParameter("p3", Double.NaN);//Alpha
		add(a);
		
		a = new DE("ro",'e');
		a.setID("rDEr1exp09");
		a.setParameter("p0", 10.0); //Population size
		a.setParameter("p1", 0.4);//F
		a.setParameter("p2", 0.9);//CR
		a.setParameter("p3", Double.NaN);//Alpha
		add(a);
		
		for(int i = 1; i<=30; i++)
				add(new RCEC2014(probDim, i));




	}
}
