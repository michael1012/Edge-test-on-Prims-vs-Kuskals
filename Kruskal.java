package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.LinkedList;
import java.util.Set;

public class Kruskal {
	private HashMap<Integer, ArrayList<Edge>> map;
	private ArrayList<Set<Integer>> nodes;
	private ArrayList<Set<Edge>> edges;
	private Set<Integer> numNodes;
	private int num;
	public Kruskal(Edge[] newEdges)
	{
		map = new HashMap<Integer, ArrayList<Edge>>();
		nodes = new ArrayList<Set<Integer>>();
		edges = new ArrayList<Set<Edge>>();
		numNodes = new HashSet<Integer>();
		insertEdges(newEdges);
	}
	private void insertEdges(Edge[] edges)
	{

		for(int i =0 ;i < edges.length; i++)
		{
			if(!numNodes.contains(edges[i].getV1()))
				numNodes.add(edges[i].getV1());
			if(!numNodes.contains(edges[i].getV2()))
				numNodes.add(edges[i].getV2());
			int weight = edges[i].getWeight();
			if(map.get(weight) == null)
				map.put(weight, new ArrayList<Edge>());
			map.get(weight).add(edges[i]);
		}
		num = numNodes.size();
	}
	public Edge[] calculations()
	{
		Set<Integer> keys = map.keySet();		
		for(Integer key: keys)
		{
			ArrayList<Edge> newMap = map.get(key);
			for(int i = 0; i < newMap.size();i++)
			{
				findNode(newMap.get(i));
				if(nodes.size() == num)
					break;
			}
		}
		Edge[] edgeArray = new Edge[edges.get(0).size()];
		int j = 0;
		for(Edge newEdge:edges.get(0))
		{
			edgeArray[j] = newEdge;
			j++;
		}
		return edgeArray;
	}
	private void findNode(Edge newEdge)
	{
		int node1 = newEdge.getV1();
		int node2 = newEdge.getV2();
		int node1Location = -1;
		int node2Location = -1;
		if(node1 == node2)
			return;
		for(int j = 0; j < nodes.size();j++)
		{
			if(nodes.get(j).contains(node1))
				node1Location = j;
			if(nodes.get(j).contains(node2))
				node2Location = j;
		}
		if(node1Location ==-1 && node2Location ==-1)

		{
			nodes.add(new HashSet<Integer>());
			int last = nodes.size() -1;
			nodes.get(last).add(node1);
			nodes.get(last).add(node2);
			edges.add(new HashSet<Edge>());
			edges.get(last).add(newEdge);
		}
		else if(node1Location != -1 && node2Location == -1)
		{
			nodes.get(node1Location).add(node2);
			edges.get(node1Location).add(newEdge);
		}

		else if(node2Location != -1 && node1Location == -1)
		{
			nodes.get(node2Location).add(node1);
			edges.get(node2Location).add(newEdge);
		}
		else if(node1Location != node2Location)
		{
			if(nodes.get(node1Location).size()>nodes.get(node2Location).size())
				merge(node1Location, node2Location, newEdge);
			else
				merge(node2Location, node1Location, newEdge);				
		}
	}
	private void merge(int node1Location, int node2Location, Edge newEdge)
	{

		for(int newNodes: nodes.get(node2Location))
			nodes.get(node1Location).add(newNodes);
		for(Edge newEdges: edges.get(node2Location))
			edges.get(node1Location).add(newEdges);
		edges.get(node1Location).add(newEdge);
		nodes.remove(node2Location);
		edges.remove(node2Location);
	}
	public static void main(String [] args)
	{
		
		Edge edge1 = new Edge(1,2,4);
		Edge edge2 = new Edge(2,3,8);
		Edge edge3 = new Edge(3,4,7);
		Edge edge4 = new Edge(4,5,9);
		Edge edge5 = new Edge(5,6,10);
		Edge edge6 = new Edge(6,7,2);
		Edge edge7 = new Edge(7,8,1);
		Edge edge8 = new Edge(8,9,7);
		Edge edge9 = new Edge(2,8,11);
		Edge edge10 = new Edge(7,9,6);
		Edge edge11 = new Edge(3,9,2);
		Edge edge12 = new Edge(3,6,4);
		Edge edge13 = new Edge(4,6,14);
		Edge edge14 = new Edge(8,1,8);

		Edge[] edgeArray = {edge1, edge2, edge3, edge4, edge5, edge6, edge7,
				edge8, edge9, edge10, edge11, edge12, edge13, edge14};
		/*
		Edge edge1 = new Edge(0,0,33);
		Edge edge2 = new Edge(0,1,3);
		Edge edge3 = new Edge(2,1,17);
		Edge[] edgeArray = {edge1, edge2, edge3};
		*/

		Kruskal algor = new Kruskal(edgeArray);
		Edge[] newEdges = algor.calculations();
		for(Edge newEdge:newEdges)
			System.out.println(newEdge);
		System.out.println("Hello");
	}
}
