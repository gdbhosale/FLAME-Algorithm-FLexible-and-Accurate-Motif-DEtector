package com.dm.suffixtree;

/*
 * CompactSuffixTree has compact nodes, but no compact labels, for illustrative
 * reasons.
 */
public class CompactSuffixTree extends AbstractSuffixTree {

	public CompactSuffixTree(SimpleSuffixTree simpleSuffixTree) {
		super(simpleSuffixTree.text);
		super.root = compactNodes(simpleSuffixTree.root, 0);

		// For removing extra null node at root
		for (SuffixTreeNode x : super.root.children) {
			if (x.incomingEdge.label.equals("$")) {
				super.root.children.remove(x);
				break;
			}
		}
		specifySuffixCount(super.root);
	}

	private SuffixTreeNode compactNodes(SuffixTreeNode node, int nodeDepth) {
		node.nodeDepth = nodeDepth;
		for (SuffixTreeNode child : node.children) {
			while (child.children.size() == 1) {
				SuffixTreeNode grandchild = child.children.iterator().next();
				child.incomingEdge.label += ", "
						+ grandchild.incomingEdge.label;
				child.stringDepth += grandchild.incomingEdge.label.length();
				child.children = grandchild.children;
				for (SuffixTreeNode grandchild2 : child.children) {
					grandchild.parent = node;
				}
			}
			child = compactNodes(child, nodeDepth + 1);
		}

		return node;
	}

	public static void specifySuffixCount(SuffixTreeNode root) {
		for (SuffixTreeNode child : root.children) {
			if (child.isLeaf()) {
				root.increaseCount();
			} else {
				specifySuffixCount(child);
			}
		}
	}
}