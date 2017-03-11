package project;
import java.util.*;

public class AdjacencyList {
	
	//Map of adjacency lists for each node
    HashMap<Integer, ArrayList<Edge>> adj;

    public AdjacencyList(int[] nodes) {
        //your node labels are consecutive integers starting with one. 
        //to make the indexing easier we will allocate an array of adjacency one element larger than necessary
        adj = new HashMap<Integer, ArrayList<Edge>>();
        for (int i = 0; i < nodes.length; ++i) {
            adj.put(i, new ArrayList<Edge>());
        }
    }

    public void addNeighbor(int v1, Edge v2) {
        adj.get(v1).add(v2);
    }

    public ArrayList<Edge> getNeighbors(int v) {
        return adj.get(v);
    }
}
