package com.dm.suffixtree;

/*
 * The abstract superclass for the simple suffix tree and the compact suffix
 * tree. It has a root node, which recursively contains children nodes. The
 * constructor for the AbstractSuffixTree has one parameter, text: the text to
 * be represented by this tree. A terminating "$" is appended if not already
 * present.
 */
abstract class AbstractSuffixTree {

	// text to be represented by this tree
	String text = null;

	// root Suffix Node
	public SuffixTreeNode root = null;

	// size of the input alphabet
	int inputAlphabetSize = -1;

	/*
	 * Constructor A terminating "$" is appended if not already present.
	 */
	AbstractSuffixTree(String text) {
		if (text.length() > 0 && text.charAt(text.length() - 1) == '$') {
			this.text = text;
		} else {
			this.text = text + "$";
		}
	}
}