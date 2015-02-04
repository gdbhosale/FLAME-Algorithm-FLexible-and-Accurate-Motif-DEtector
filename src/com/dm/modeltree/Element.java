package com.dm.modeltree;

import java.util.ArrayList;

import com.dm.suffixtree.SuffixTreeNode;

public class Element {
	public ArrayList<Integer> mismatches;
	public String alphabates;
	public boolean isRoot = false;
	public SuffixTreeNode node;
	public int supportCount = 0;

	public Element(String alphabates, SuffixTreeNode node) {
		super();
		this.alphabates = alphabates;
		this.node = node;
		this.supportCount = node.children.size();
		//System.out.println("#supportCount="+supportCount);
		mismatches = new ArrayList<Integer>();
		for (int i = 0; i < alphabates.length(); i++) {
			mismatches.add(0);
		}
	}
	
	public int totalMismatches() {
		int mis = 0;
		for(int i=0; i<mismatches.size(); i++) {
			mis = mis + mismatches.get(i);
		}
		return mis;
	}
	
	@Override
	public boolean equals(Object o) {
		Element ele = (Element) o;
		if(ele.alphabates.equals(this.alphabates)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < alphabates.length(); i++) {
			result.append(alphabates.charAt(i) +""+ mismatches.get(i));
			if (i != alphabates.length() - 1) {
				result.append("");
			}
		}
		return result.toString();
	}
	
	public char lastSymbol() {
		return alphabates.charAt(alphabates.length()-1);
	}
	
	public void setMismatches(ArrayList<Integer> mismatches) {
		this.mismatches = mismatches;
	}
	
	public void setMismatchesAt(int pos, int mis) {
		int oldTemp = mismatches.get(pos);
		mismatches.set(pos, oldTemp+1);
	}
	
	public void increaseMismatches(int pos) {
		//System.out.println("increaseMismatches Symbols"+alphabates+" pos:"+pos);
		int oldTemp = mismatches.get(pos);
		mismatches.set(pos, oldTemp+1);
	}
}