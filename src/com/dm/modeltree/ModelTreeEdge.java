package com.dm.modeltree;

public class ModelTreeEdge {
	public String label = null;

	@SuppressWarnings("unused")
	private int branchIndex = -1;

	public ModelTreeEdge(String label, int branchIndex) {
		this.label = label;
		this.branchIndex = branchIndex;
	}
}