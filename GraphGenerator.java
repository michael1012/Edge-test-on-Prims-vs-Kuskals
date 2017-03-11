package project;

//Creates graphs of arrrays of edges for algorithms to solve

import java.util.*;

public class GraphGenerator
{ 
// Number of nodes in the graph, matrix will be nxn
private int nodes;

// How dense or sparse the graph is
   // 1 = minimum spanning tree (n-1) edges
   // edges = (n-1) *(density)
private int density; 

// Minimum cost of a edge (Cost will be random int's between min and max, default to 1-50)
private int minimun; 

// Maximum cost of a edge (Cost will be random int's between min and max, default to 1-50)
private int maximun; 

private Edge[] matrix; 

private Random random;

public GraphGenerator(int n, int d)
{
   nodes = n; 
   density = d;
   
   random = new Random();
   
   matrix = new Edge[(n-1)*d];
   
   minimun = 1;
   maximun = 50;
   
}

public GraphGenerator(int n, int d, int min, int max, int multi)
{
   nodes = n; 
   density = d;
   
   random = new Random();
   
   matrix = new Edge[(n-1)*d];
   
   minimun = min;
   maximun = max; 
}

public void Fill()
{
   // Set all values in matrix to zero: 
   initialize();
   //PrintMatrix();
	  
   int i = 0; 
   
   // Ensure the graph is complete, by creating a minimum spanning tree
   ArrayList<Integer> unconnected = new ArrayList<Integer>();
   ArrayList<Integer> connected = new ArrayList<Integer>();
   
   for (i = 0; i < nodes; i++)
   {
      unconnected.add(i);
   }
  
   while (!unconnected.isEmpty())
   {
      // Get the next unconnected edge
 	  int connectIn = unconnected.get(random.nextInt(unconnected.size()));
      //unconnected.remove(0);
      
      // pick a random element in the array to add it to
      int connectTo = -1;
      if (connected.isEmpty())
      {
          // If there are no connected notes, connect to something in unconnected: 
     	 do 
     	 connectTo = unconnected.get(random.nextInt(unconnected.size()));
     	 while ( connectTo == connectIn );
      }
      else
      {
         connectTo = connected.get(random.nextInt(connected.size()));
      }
      
      addEdge(connectIn, connectTo, getRandomCost());
      
      connected.add(connectIn);
      connected.add(connectTo);
      unconnected.remove(new Integer(connectIn));
      unconnected.remove(new Integer(connectTo));
   }
   
   // Then add additional edges until you reach the desired density
   int additionalEdges = ((nodes-1)*(density-1));
   for (i = 0; i < additionalEdges; i++)
   {
      addEdge(random.nextInt(nodes), random.nextInt(nodes), getRandomCost());
   }      
}

// Adds a edge between node a and node b for cost c
public void addEdge(int a, int b, int c)
{
   // Find the current number of edges in the array: 
	  int i = 0; 
   while(i < ((nodes*density)-1) && matrix[i].getWeight() != -1)
   {
      i++;
   }
   matrix[i].setV1(a);
   matrix[i].setV2(b);
   matrix[i].setWeight(c);
}

public void initialize()
{
   for (int i = 0; i < ((nodes-1)*density); i++)
   {
     	 matrix[i] = new Edge();
   }
}



public int getRandomCost()
{
   return random.nextInt(maximun - minimun + 1) + minimun;
}

public Edge[] getGraph()
{
   return matrix; 
}

//---------------------
// Functions below this point relate to testing correctness of algorithms
//---------------------

// Quick function to test the correctness of the algorithms
public boolean isMST( Edge[] g1, Edge[] g2 )
{
	if ( g1.length != g2.length ) 
		return false;
	
	if ( totalCost(g1) != totalCost(g1) ) 
		return false;
	
	return true;
}

public int totalCost( Edge[] g )
{
	int totalCost = 0;
	
	for (int i = 0; i < g.length; i++)
		totalCost += g[i].getWeight();
		
	return totalCost;
}

public void printEdges(Edge[] g)
{
   System.out.println("Edges:");
   
   for (int j = 0; j < g.length; j++)
   {
 	  System.out.println(g[j].toString());
   }
}

}




