package project;

import java.util.*;

public class PrimsNew 
{	
	private static PriorityQueue<Edge> queue;
	private static Edge[] edgeList;
	private static AdjacencyList AJ;
	private static boolean[] reached;
	private static int count;

	/**
	 * Takes in a full graph of edges and returns a MST of it
	 * @param graph
	 * @return Edge[]
	 */
	public static Edge[] PrimMST(Edge[] graph)
	{
		int numnodes = 0;
		count = 0; 
		
		//implement priority queue
		Comparator<Edge> comparator = new EdgeComparator();
		queue = new PriorityQueue<Edge>(11, comparator);
		
		//finds the number of nodes to initialize the MST items
		for (int i = 0; i < graph.length; i++) 
		{
			if (graph[i].getV1() > numnodes)
				numnodes = graph[i].getV1();
			if (graph[i].getV2() > numnodes)
				numnodes = graph[i].getV2();
		}
		numnodes++;

		//initializes the returned MST
		edgeList = new Edge[numnodes-1];
		//initializes the reached vertices array
		reached = new boolean[numnodes];
		for(int i=0;i<reached.length;i++)
			reached[i] = false;
		
		//initalize AdjacencyList and add all items from the graph
		int[] intA = new int[numnodes];
		for(int i=0;i<numnodes;i++)
			intA[i] = i;
		AJ = new AdjacencyList(intA);
		for(int i=0; i<graph.length; i++)
		{
			AJ.addNeighbor(graph[i].getV1(), graph[i]);
			AJ.addNeighbor(graph[i].getV2(), graph[i]);
		}
		
		//runs the algorithm
		for(int v = 0; v < numnodes; v++)
		{
			if(!reached[v])
				prims(graph, v);
		}
		return edgeList;
	}
	
	/**
	 * Helper function for PrimMST that searches on a certain vertex s
	 * @param graph
	 * @param s - a vertex to search through edges
	 */
	public static void prims(Edge[] graph, int s) 
	{	
		scan(s);
		while(queue.size() != 0)
		{
			Edge e = queue.poll();
			int v = e.getV1();
			int w = e.getV2();
			if(reached[v] && reached[w])
				continue;
			edgeList[count] = e;
			count++;
			if(!reached[v])
				scan(v);
			if(!reached[w])
				scan(w);
		}
	}

	/**
	 * Helper function for prims that adds items to the priority queue
	 * and sets a vertex as reached
	 * @param x - item to be put in the tree
	 */
	private static void scan(int x)
	{
		ArrayList<Edge> list = AJ.getNeighbors(x);
		reached[x] = true;
		//adds edges to the queue based on x
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getV1() == x)
				if(!reached[list.get(i).getV2()])
					queue.add(list.get(i));
			if(list.get(i).getV2() == x)
				if(!reached[list.get(i).getV1()])
					queue.add(list.get(i));
		}
	}

	//test program
	public static void main(String args[]) 
	{
		GraphGenerator graph = new GraphGenerator(7, 3);
		graph.Fill();
		graph.printEdges(graph.getGraph());
		System.out.println(" ");
		/*System.out.println();
		Edge edge0 = new Edge(0,1,1);
		Edge edge1 = new Edge(1,2,4);
		Edge edge2 = new Edge(2,3,8);
		Edge edge3 = new Edge(3,4,7);
		Edge edge4 = new Edge(4,5,9);
		Edge edge5 = new Edge(5,6,10);
		Edge edge6 = new Edge(6,7,2);
		Edge edge7 = new Edge(7,8,1);
		Edge edge8 = new Edge(8,9,7);
		Edge edge9 = new Edge(2,8,10);
		Edge edge10 = new Edge(7,9,6);
		Edge edge11 = new Edge(3,9,2);
		Edge edge12 = new Edge(3,6,4);
		Edge edge13 = new Edge(4,6,14);

		Edge[] edgeArray = {edge0, edge1, edge2, edge3, edge4, edge5, edge6, edge7,
				edge8, edge9, edge10, edge11, edge12, edge13};
		Edge[] array = PrimsNew.PrimMST(edgeArray);
		*/
		Edge[] array = PrimsNew.PrimMST(graph.getGraph());
		for(int i=0; i<array.length; i++)
			System.out.println(array[i]);
	}

}


