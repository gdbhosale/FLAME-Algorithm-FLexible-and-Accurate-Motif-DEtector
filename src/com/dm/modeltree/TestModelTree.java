package com.dm.modeltree;

public class TestModelTree {

	public static void main(String[] args) throws InterruptedException {
		long startTime = System.currentTimeMillis();

		SimpleModelTree tree = new SimpleModelTree("ACGT", 3);

		long endTime = System.currentTimeMillis();
		
		System.out.println("Execution Time:" + (endTime - startTime) + "\n");

		//String properties = "rankdir=LR; node[shape=box fillcolor=gray95 style=filled]\n";
		// System.out.println("digraph {\n" + properties + tree.root + "}");
		System.out.print(tree.root);
		//System.out.println(tree.root.suffixCount);
		//Thread.sleep(10000);
	}
}