package com.dm.suffixtree;

public class TestSuffixTree {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// ABBCACCB
		SimpleSuffixTree sst = new SimpleSuffixTree("ABBCACCB");
		CompactSuffixTree tree = new CompactSuffixTree(sst);

		long endTime = System.currentTimeMillis();

		System.out.println("Execution Time:" + (endTime - startTime) + "\n");

		//String properties = "rankdir=LR; node[shape=box fillcolor=gray95 style=filled]\n";
		// System.out.println("digraph {\n" + properties + tree.root + "}");
		System.out.println(tree.root);
		System.out.println(tree.root.suffixCount);
	}
}