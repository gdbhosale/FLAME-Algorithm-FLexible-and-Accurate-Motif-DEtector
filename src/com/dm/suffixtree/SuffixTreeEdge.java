package com.dm.suffixtree;

public class SuffixTreeEdge {
	public String label = null;
	@SuppressWarnings("unused")
	private int branchIndex = -1;

	public SuffixTreeEdge(String label, int branchIndex) {
		this.label = label;
		this.branchIndex = branchIndex;
	}
}