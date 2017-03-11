package project;

import java.util.ArrayList;

public class Driver 
{
	public static void main(String[] args) throws InterruptedException 
	{
		//---- 
		// Change these values to run tests
		int edgeStart 			= 1;	// Must be at least 3
		double edgeExp			= .3;	// Where test x will use edgeStart + 2 ^ (edgeExp*x) vertexes
		
		int densityStart 		= 3;	// Must be at least 1
		double DenIncrease		= 3;	// Where test x will use DensityStart + (x * DenIncrease) density

		int tests 				= 27;	// How many trials to run, each trail requires
										// VertixNums and DensityNums to have a number at that index. 
		
		int samples				= 3; 	// How many times each test will be run.
										// Resulting number will be the median of the samples. 
		
		boolean silent = false; 		// Set to true to only show final results/errors
		//----
		
		ArrayList<Integer> VertixNums = new ArrayList<Integer>();
		ArrayList<Integer> DensityNums = new ArrayList<Integer>();
		
		// Do a small test at the beginning to set memory usage: 
		VertixNums.add( 3 );
		DensityNums.add( 1 );
		
		for (int a = 0; a < tests; a++)
		{
			if (edgeExp != 0)
				VertixNums.add((int)(edgeStart + ( Math.pow(2, (a * edgeExp)))));
			else
				VertixNums.add((int)(edgeStart));
		}
			
		for (int a = 0; a <= tests; a++)
			DensityNums.add((int)(densityStart + (a * DenIncrease)));
		
		TimeSpace ts = new TimeSpace();
		TimeSpace ts2 = new TimeSpace(); 
		
		// Run test for each input size: 
		for ( int i = 0; i < VertixNums.size() ; i++ )
		{
			if ( !silent && i > 0 )
			{
				System.out.print("Running Test " + i + "" );
				System.out.print("\tV:" + VertixNums.get(i) + " \tD:" +  DensityNums.get(i));
			}
			
			// Run enough samples, take median
			for (int k = 0; k < samples; k++)
			{
				GraphGenerator graph = new GraphGenerator(VertixNums.get(i), DensityNums.get(i));
				graph.Fill();
				//graph.printEdges(graph.getGraph());
				
				//------ Prims ------
				
				Thread.sleep( 1000 );
				ts.startClock();
				ts.startSpaceMeasurment();
				
				Edge[] primResult = PrimsNew.PrimMST(graph.getGraph());
				
				ts.stopSpaceMeasurment();
				ts.stopClock();
				
				ts.recordTime( i );
				ts.recordSpace( i );
				
				if ( !silent && i > 0 )
				{
					System.out.print("\tP");
				}
				
				//------ Kruskals ------
				
				Thread.sleep( 1000 );
				ts2.startClock();
				ts2.startSpaceMeasurment();
				
				Kruskal algor = new Kruskal(graph.getGraph());
			    Edge[] kruskalResult = algor.calculations();
			
				ts2.stopSpaceMeasurment();
				ts2.stopClock();  
				
				ts2.recordTime( i );
				ts2.recordSpace( i );
				
				if ( !silent && i > 0 )
				{
					System.out.print("\tK");
				}
				
				if ( !graph.isMST(primResult, kruskalResult) )
				{
					System.out.println("Resulting graphs are not the same total cost or length");
					System.out.println("Original Graph:");
					graph.printEdges(graph.getGraph());
					System.out.println("Kruskals algorithm: " + kruskalResult.length + " edges");
					graph.printEdges(kruskalResult);
					System.out.println("Prims algorithm: " + primResult.length + " edges");
					graph.printEdges(primResult);
					System.exit(0);
				}
			}
			System.out.println("");
		}
		
		// Print out results : 
		System.out.println( "" );
		
		System.out.println("Vars:\nVertices: " + edgeStart + "\t" + edgeExp + "\nDensity:\t" + densityStart + "\t" + DenIncrease + "\n");
		
		ArrayList<Double> PrimsTimes  = ts.getTimesSeconds();
		ArrayList<Double> KruskalsTimes  = ts2.getTimesSeconds();
		
		ArrayList<Long> PrimsSpaces  = ts.getSpaces();
		ArrayList<Long> KruskalsSpaces  = ts2.getSpaces();
		
		System.out.println("Vertices\tDensity\tPrims\tKruskals");
		for (int j = 1; j < PrimsTimes.size(); j++)
		{
			System.out.println( VertixNums.get(j) + "\t" + DensityNums.get(j) + "\t" + PrimsTimes.get(j) + "\t" + KruskalsTimes.get(j));
		}
		
		System.out.println( "" );
		
		System.out.println("Vertices\tDensity\tPrims\tKruskals");
		for (int j = 1; j < PrimsTimes.size(); j++)
		{
			System.out.println( VertixNums.get(j) + "\t" + DensityNums.get(j) + "\t" + PrimsSpaces.get(j) + "\t" + KruskalsSpaces.get(j));
		}
		
		System.out.println( "" );
	}
}
