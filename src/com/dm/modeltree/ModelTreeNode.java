package com.dm.modeltree;

import java.util.ArrayList;
import java.util.List;

public class ModelTreeNode {
	public ModelTreeEdge incomingEdge = null;
	public int nodeDepth = -1;
	public int label = -1;

	public String nodeText;
	
	// Suffix Count of Node
	public int suffixCount = 0;
	
	// Support Count of Node
	public int supportCount = 0;

	// collection of nodes, the immediate children of this node.
	public ArrayList<ModelTreeNode> children = null;

	// parent node of this node.
	public ModelTreeNode parent = null;

	// string depth of this node.
	public int stringDepth;

	// Attributes for saving the tree as dot
	int id = 0;
	public static int c;
	public static int maxNodeDepth;

	public int whichChild = -1;
	
	ArrayList<Element> matches = null;
	
	public boolean isPruned = false;
	
	public ModelTreeNode(ModelTreeNode parent, String incomingLabel, int depth,
			int label, int id) {
		children = new ArrayList<ModelTreeNode>();
		incomingEdge = new ModelTreeEdge(incomingLabel, label);
		nodeDepth = depth;
		this.label = label;
		this.parent = parent;
		stringDepth = parent.stringDepth + incomingLabel.length();
		this.id = id;
		
		if(!parent.isRoot())
			nodeText = parent.nodeText+incomingLabel;
		else
			nodeText = incomingLabel;
	}

	public ModelTreeNode() {
		children = new ArrayList<ModelTreeNode>();
		nodeDepth = 0;
		label = 0;
	}

	public void insert(ModelTreeNode child, List<String> childs) {
		children.add(child);

		// Login for Suffix Counter
		if (child.nodeDepth == maxNodeDepth) {
			increaseCount();
		}
		
		if (child.nodeDepth < maxNodeDepth) {
			int i = 0;
			for (String x : childs) {
				ModelTreeNode newChild = new ModelTreeNode(child, x,
						child.nodeDepth + 1, i, 10 + i);
				child.insert(newChild, childs);
				i++;
			}
		}
	}

	public ModelTreeNode next() {
		
		if (this.isRoot()) {
			if (this.children.isEmpty()) {
				return null;
			} else {
				if(whichChild == -1) {
					whichChild = 0;
				} else {
					whichChild++;
					if(whichChild == this.children.size()) {
						// end of traverse
						return null;
					}
				}
				return this.children.get(whichChild);
			}
		} else {
			if (this.children.isEmpty()) {
				return this.parent.next();
			} else {
				if(whichChild == -1) {
					whichChild = 0;
				} else {
					whichChild++;
					if(whichChild == this.children.size()) {
						return this.parent.next();
					}
				}
				return this.children.get(whichChild);
			}
		}
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		String incomingLabel = this.isRoot() ? "" : this.incomingEdge.label;
		for (int i = 1; i < this.nodeDepth; i++) {
			result.append("\t");
		}
		if (this.isRoot()) {
			c = 1;
			this.id = 1;
			result
					.append("Root [" + this.id + "] (" + this.suffixCount
							+ ")\n");
		} else {
			this.id = c;
			result.append(this.parent.id + " -> ");
			String pruneLabel = "";
			
			/*
			int size = 0;
			if(matches != null)
				size = matches.size();
			*/
			
			if(isPruned) pruneLabel = "***";
			result.append(this.id + "(" + suffixCount + ")" + "<"+supportCount+">[label=\""
					+ incomingLabel + "\"]<"+matches+">;"+pruneLabel+"\n");
		}
		for (ModelTreeNode child : children) {
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
	private void increaseCount() {
		suffixCount++;

		if (!this.isRoot()) {
			this.parent.increaseCount();
		}
	}

	public ArrayList<Element> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<Element> matches) {
		this.matches = matches;
		if(matches != null && matches.size() > 0) {
			Element ele = matches.get(0);
			supportCount = ele.supportCount;
		}
	}
	
	public int getSupport() {
		return supportCount;
		/*
		// Old Logic
		if(matches != null)
			return matches.size();
		else
			return 0;
		*/
	}
}