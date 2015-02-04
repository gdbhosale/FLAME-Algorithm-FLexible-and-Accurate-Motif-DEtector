package com.dm.flame;

import java.util.ArrayList;

import com.dm.modeltree.Element;
import com.dm.modeltree.ModelTreeNode;
import com.dm.modeltree.SimpleModelTree;
import com.dm.suffixtree.CompactSuffixTree;
import com.dm.suffixtree.SimpleSuffixTree;
import com.dm.suffixtree.SuffixTreeNode;

public class FLAMEAlgoTest {

	static SimpleModelTree modelTree;
	static CompactSuffixTree dataTree;
	
	static int lengthOfPattern = 5; // l
	static int modelTreeDepth = lengthOfPattern;
	static int maxAlphaDistance =1; // d
	static int minPatternFrequency = 3; // k
	
	public static void main(String[] args) {
		long startTime;
		long endTime;
		
		String model = "acgt";
		// ABBCACCB
		String data = "acagtgatcagtagcatgacagtagacgtaaacgtaagggtccagtagaccgagtcgatgacagtagacagtgacgatacagtagacgatcgggcttgtcgt";
		
		System.out.println("Model: "+model);
		System.out.println("Data: "+data);
		System.out.println("Data length: "+data.length());
		System.out.println("Length Of Pattern: "+lengthOfPattern);
		
		modelTree = new SimpleModelTree(model, modelTreeDepth);
		SimpleSuffixTree sst = new SimpleSuffixTree(data);
		dataTree = new CompactSuffixTree(sst);
		
		startTime = System.currentTimeMillis();
		flame(modelTree, dataTree, lengthOfPattern, maxAlphaDistance,
				minPatternFrequency);
		endTime = System.currentTimeMillis();
		System.out.println("Execution Time:" + (endTime - startTime) + " milliseconds\n");
		//System.out.println(sst.root);
		System.out.println("Model Tree:\n"+modelTree.root);
		System.out.println("Data Tree:\n"+dataTree.root);
	}

	public static void flame(SimpleModelTree modelTree,
			CompactSuffixTree dataTree, int l, int d, int k) {

		ModelTreeNode model = modelTree.root;

		for (int i = 0; (model = model.next()) != null; i++) {
			SimpleModelTree.currentTraverseNode = model;
			//System.out.println("\n\nModel: " + model.nodeText + "(" + model.nodeDepth + ")");
			//System.out.println(model.nodeText+":"+model.getSupport());
			evaluateSupport(model, dataTree);
			
			System.out.println(model.nodeText+":"+model.getSupport());
			
			if(model.getSupport() < k && model.nodeDepth == l) {
				modelTree.prune(model);
			}
			/*
			if(!model.isLeaf()) {
				model.children
			}
			*/
		}

		/*
		 * model = modelTree.firstNode();
		 * while(model!=modelTree.lastNode()) {
		 * 		evaluateSupport(model, dataTree);
		 * 		if(isValid(model))
		 * 			Print("Found Model: ", model);
		 * 		else if(model.support() < k)
		 * 			modelTree.PruneAt(model);
		 * 		model = nextNode(model, modelTree);
		 * }
		 */
	}

	public static void evaluateSupport(ModelTreeNode model,
			CompactSuffixTree dataTree) {
		char newSymbol = model.nodeText.charAt(model.nodeText.length() - 1);
		ArrayList<Element> oldMatches = model.parent.getMatches();
		ArrayList<Element> newMatches = new ArrayList<Element>();
		if (model.parent.isRoot()) {
			//System.out.println("Model: " + model.incomingEdge.label);
			Element ele = new Element("$", dataTree.root);
			ele.isRoot = true;
			newMatches = expandMatches(ele, newSymbol, dataTree);
		} else {
			for (Element x : oldMatches) {
				ArrayList<Element> tempMatches = expandMatches(x, newSymbol, dataTree);
				System.out.println("tempMatches size: "+tempMatches.size());
				newMatches.addAll(tempMatches);
			}
		}
		model.setMatches(newMatches);
		/*
		 * newsymbol = last symbol of model.String;
		 * oldmatches = model.Parent().Matches();
		 * newmatches = EmptyMatches();
		 * if(model.Parent() == root) {
		 * 		newmatches = expandMatches(root,newsymbol,dataTree);
		 * } else {
		 * 		for(x : oldmatches) {
		 * 			newmatches = newmatches U expandMatches(x, newsymbol, dataTree);
		 * 		}
		 * }
		 * model.SetMatches(newmatches);
		 */
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Element> expandMatches(Element x, char newSymbol, CompactSuffixTree dataTree) {
		ArrayList<Element> y = new ArrayList<Element>();
		System.out.println("\nexpandMatches:MyElement: "+x+" size="+x.node.children.size());
		for (SuffixTreeNode node : x.node.children) {
			String oldChar = x.alphabates;
			if(x.isRoot) {
				oldChar = "";
			}
			String newChar = ""+node.incomingEdge.label.charAt(0);
			Element e = new Element(oldChar + newChar, node);
			
			ArrayList<Integer> tempMismatches = x.mismatches;
			tempMismatches.add(0);
			ArrayList<Integer> mism = (ArrayList<Integer>) tempMismatches.clone();
			e.setMismatches(mism);
			y.add(e);
		}
		for(int i=0; i<y.size(); i++) {
			Element b = y.get(i);
			//System.out.println("B:"+b+" b.lastSymbol():"+b.lastSymbol()+" newSymbol:"+newSymbol);
			if(b.lastSymbol() != newSymbol) {
				b.increaseMismatches(b.alphabates.length()-1);
				if(b.totalMismatches() > maxAlphaDistance) {
					y.remove(b);
					i=-1;
				}
			}
		}
		//System.out.println("Y->"+y);
		return y;
	}

	public static boolean isValid(ModelTreeNode model) {
		
		return true;
	}
}