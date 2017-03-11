package project;

import java.util.Comparator;

public class Edge
{
	private int v1;
	private int v2;
	private int weight;

	public Edge() {
		v1 = -1;
		v2 = -1;
		weight = -1;
	}

	public Edge(int to, int from, int w) {
		v1 = to;
		v2 = from;
		weight = w;
	}

	public int getV1() {
		return v1;
	}

	public int getV2() {
		return v2;
	}

	public int getWeight() {
		return weight;
	}

	public void setV1(int v) {
		v1 = v;
	}

	public void setV2(int v) {
		v2 = v;
	}

	public void setWeight(int w) {
		weight = w;
	}

	public int compareTo(Edge compareFruit) {
		int compareQuantity = ((Edge) compareFruit).getWeight();
		return this.weight - compareQuantity;
	}

	public String toString() {
		String str = "";
		str += v1 + "\t" + v2 + "\t" + weight;
		return str;
	}

}

class EdgeComparator implements Comparator<Edge>
{
	public int compare(Edge x, Edge y)
	{
		if(x.getWeight() < y.getWeight())
			return -1;
		if(x.getWeight() > y.getWeight())
			return 1;
		return 0;
	}
}
