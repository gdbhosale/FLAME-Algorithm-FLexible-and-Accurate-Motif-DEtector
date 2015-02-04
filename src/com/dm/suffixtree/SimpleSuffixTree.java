package com.dm.suffixtree;

import java.util.ArrayList;
import java.util.List;

public class SimpleSuffixTree extends AbstractSuffixTree {

	public SimpleSuffixTree(String text) {
		super(text);
		constructTree();
	}

	/*
	 * Create the root node and insert all suffixes into this tree, counting the
	 * paths.
	 */
	private void constructTree() {
		root = new SuffixTreeNode();
		char[] s = super.text.toCharArray();
		for (int i = 0; i < s.length; i++) {
			List<String> suffixList = new ArrayList<String>();
			for (int k = i; k < s.length; k++) {
				suffixList.add(s[k] + "");
			}
			// System.out.println("#suffixList:" + suffixList);
			root.addSuffix(suffixList, i + 1);
		}
	}

}