package com.dm.suffixtree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SuffixTreeNode {
	// incoming edge of this node. Every node in a tree has one incoming edge,
	// except for the root, which has none.
	public SuffixTreeEdge incomingEdge = null;
	// depth of this node, that is, how many edges are on a path from the root
	// node to this node (not the string depth)
	public int nodeDepth = -1;
	// label of this node, for leafs only
	int label = -1;
	// Suffix Count of Node
	int suffixCount = 0;
	// collection of nodes, the immediate children of this node.
	public ArrayList<SuffixTreeNode> children = null;
	// parent node of this node.
	public SuffixTreeNode parent = null;
	// string depth of this node.
	int stringDepth;
	// Attributes for saving the tree as dot
	int id = 0;
	public static int c;

	public SuffixTreeNode(SuffixTreeNode parent, String incomingLabel,
			int depth, int label, int id) {
		children = new ArrayList<SuffixTreeNode>();
		incomingEdge = new SuffixTreeEdge(incomingLabel, label);
		nodeDepth = depth;
		this.label = label;
		this.parent = parent;
		stringDepth = parent.stringDepth + incomingLabel.length();
		this.id = id;
	}

	public SuffixTreeNode() {
		children = new ArrayList<SuffixTreeNode>();
		nodeDepth = 0;
		label = 0;
	}

	/**
	 * Adds Suffix to the Root.<br>
	 * Adding a suffix consists of two steps:
	 * <ul>
	 * <li>1. Recursivley find the node to insert at.</li>
	 * <li>2. Insert new nodes for the suffix to insert below the node found.</li>
	 * </ul>
	 * 
	 * @param suffix
	 *            The suffix to insert into the suffix tree, will be inserted
	 *            behind the maximum prefix of the suffix found in the tree.
	 * @param pathIndex
	 *            The path index for labeling the leaf at the end of the path of
	 *            the suffix added.
	 */
	public void addSuffix(List<String> suffix, int pathIndex) {
		SuffixTreeNode insertAt = this;
		insertAt = search(this, suffix);
		insert(insertAt, suffix, pathIndex);
	}

	/**
	 * The recursive search method takes two parameters (see below). It returns
	 * the node under which to enter the suffix, that is the node in which the
	 * path to the maximum prefix of the new suffix ends. When entering the
	 * method, the suffix size should never be 0, as a terminating "$" is always
	 * appended to the text, but if that wasn't the case, and the text would be
	 * something like "dacdac", and right where the Exception is thrown we would
	 * return the startNode, we would have an invalid suffix tree, where one
	 * path would end in an inner node.
	 * 
	 * @param startNode
	 *            : The node in which to start the search.
	 * @param suffix
	 *            : The suffix that is intended to be inserted into the tree.
	 * @return
	 */
	private SuffixTreeNode search(SuffixTreeNode startNode, List<String> suffix) {
		// System.out.println("#startNode:"+startNode+" #suffix:"+suffix);
		if (suffix.isEmpty()) {
			throw new IllegalArgumentException(
					"Empty suffix. Probably no valid simple suffix tree exists for the input.");
		}
		Collection<SuffixTreeNode> children = startNode.children;
		for (SuffixTreeNode child : children) {
			if (child.incomingEdge.label.equals(suffix.get(0))) {
				// System.out.println("#Removing=" + suffix.get(0));
				suffix.remove(0);
				if (suffix.isEmpty()) {
					return child;
				}
				return search(child, suffix);
			}
		}
		return startNode;
	}

	private void insert(SuffixTreeNode insertAt, List<String> suffix,
			int pathIndex) {
		for (String x : suffix) {
			SuffixTreeNode child = new SuffixTreeNode(insertAt, x,
					insertAt.nodeDepth + 1, pathIndex, id);
			insertAt.children.add(child);

			/*
			 * // Login for Suffix Counter if(child.isLeaf()) { increaseCount();
			 * System.out.println("Leaf Detected. "+this.suffixCount); }
			 */
			insertAt = child;
		}
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		String incomingLabel = this.isRoot() ? "" : this.incomingEdge.label;
		for (int i = 1; i < this.nodeDepth; i++)
			result.append("\t");
		if (this.isRoot()) {
			c = 1;
			this.id = 1;
			result.append("Root ["+this.id+"] ("+this.suffixCount+")\n");
		} else {
			this.id = c;
			result.append(this.parent.id + " -> ");
			result.append(this.id + "(" + this.suffixCount + ")" + "[label=\"" + incomingLabel + "\"];\n");
		}
		for (SuffixTreeNode child : children) {
			c++;
			child.id = c;
			result.append(child.toString());
		}
		return result.toString();
	}

	public boolean isRoot() {
		return this.parent == null;
	}

	public boolean isLeaf() {
		return children.size() == 0;
	}

	/**
	 * Function used for increasing node counting.
	 * 
	 * @param child
	 *            child node of current node to be added.
	 */
	public void increaseCount() {
		this.suffixCount++;

		if (!this.isRoot()) {
			this.parent.increaseCount();
		}
	}
}