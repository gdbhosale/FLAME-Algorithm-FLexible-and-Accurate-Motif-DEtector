package com.dm.modeltree;

import java.util.ArrayList;
import java.util.List;

public class SimpleModelTree {

	// text to be represented by this tree
	String text = null;
	
	public static int textLength=0;

	int maxNodeDepth;

	char[] modelChars = null;

	public ModelTreeNode root = null;

	public static ModelTreeNode currentTraverseNode = null;

	public SimpleModelTree(String text, int maxNodeDepth) {
		this.text = text;
		textLength = text.length();
		this.maxNodeDepth = maxNodeDepth;
		constructTree();
	}

	/*
	 * Create the root node and insert all suffixes into this tree, counting the
	 * paths.
	 */
	private void constructTree() {
		ModelTreeNode.maxNodeDepth = maxNodeDepth;
		root = new ModelTreeNode();
		modelChars = text.toCharArray();
		int depth = 0;

		List<String> childList = new ArrayList<String>();
		for (int k = 0; k < modelChars.length; k++) {
			childList.add(modelChars[k] + "");
		}

		for (int i = 0; i < modelChars.length; i++) {
			String childLabel = new String("" + modelChars[i]);
			ModelTreeNode child = new ModelTreeNode(root, childLabel,
					depth + 1, i, 10 + i);
			root.insert(child, childList);
			System.gc();
		}
	}

	/*
	public static ModelTreeNode next() {
		if (root.children.size() == 0) {
			return null;
		} else {
			if (currentTraverseNode == null) {
				// change logic --> problem when we prune cnt node.
				currentTraverseNode = root.children.get(0);
			} else if (!currentTraverseNode.children.isEmpty()) {
				currentTraverseNode.children.get(0);
			} else {
				if (currentTraverseNode.children.isEmpty()) {
					int indexInParent = currentTraverseNode.parent.children
							.indexOf(currentTraverseNode);
					if (indexInParent <= currentTraverseNode.parent.children
							.size() - 2) {
						return currentTraverseNode.parent.children
								.get(indexInParent + 1);
					} else {
						currentTraverseNode.parent.parent.children.get(0);
					}
				} else {
					currentTraverseNode.children.get(0);
				}
			}
			return currentTraverseNode;
		}

	}
	*/
	
	public void prune(ModelTreeNode model) {
		//System.out.println("Pruning: "+model);
		//model.parent.children.remove(model);
		model.isPruned = true;
	}
}