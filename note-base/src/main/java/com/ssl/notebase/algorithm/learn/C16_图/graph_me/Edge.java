package com.ssl.notebase.algorithm.learn.C16_图.graph_me;

/**
 * 边的数据结构
 */
public class Edge {
	public int weight;
	public Node from;
	public Node to;

	public Edge(int weight, Node from, Node to) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}

}
