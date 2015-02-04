# FLAME Algorithm (FLexible-and-Accurate-Motif-DEtector)

**Implemented Paper:** Efficient and Accurate Discovery of Patterns in Sequence Datasets

**Paper Link:** http://pages.cs.wisc.edu/~floratou/flame.pdf

— Existing sequence mining algorithms mostly focus
on mining for subsequences. However, a large class of applications,
such as biological DNA and protein motif mining, require
efficient mining of “approximate” patterns that are contiguous.
The few existing algorithms that can be applied to find such
contiguous approximate pattern mining have drawbacks like poor
scalability, lack of guarantees in finding the pattern, and difficulty
in adapting to other applications. In this paper, we present a
new algorithm called FLAME (FLexible and Accurate Motif
DEtector). FLAME is a flexible suffix tree based algorithm that
can be used to find frequent patterns with a variety of definitions
of motif (pattern) models. It is also accurate, as it always find
the pattern if it exists. Using both real and synthetic datasets,
we demonstrate that FLAME is fast, scalable, and outperforms
existing algorithms on a variety of performance metrics. Using
FLAME, it is now possible to mine datasets that would have been
prohibitively difficult with existing tools.

	FLAME (modelTree, dataTree, l, d, k)
	1. 	model = modelTree.FirstNode()
	2. 	While (model 6= modelTree.LastModel())
	3. 		Evaluate Support(model,dataTree)
	4. 		If ( isValid(model) ) Print “Found Model: ”, model
	5. 		Else If(model.support() < k)
	6. 			modelTree.PruneAt(model)
	7. 		model = NextNode(model,modelTree)
	8. 	End While
	9.	End

	Sub Evaluate Support (model, dataTree)
	1. 	newsymbol = last symbol of model.String
	2. 	oldmatches = model.Parent().Matches()
	3. 	newmatches = EmptyMatches()
	4. 	If (model.Parent() == root)
	5. 		newmatches = Expand Matches(root,newsymbol,dataTree)
	6. 	Else
	7. 		ForEach match x in oldmatches
	8. 			newmatches = newmatches U
					**Expand Matches(x,newsymbol,dataTree)**
	9. 	End ForEach
	10.	model.SetMatches(newmatches)
	11.	Return

	Sub Expand Matches (x, newsymbol, dataTree)
	1. 	Let Y = Set of all single character expansions of x.String in dataTree
	2. 	ForEach element b in Y
	3. 		If b’s last symbol 6= newsymbol
	4. 			b.mismatches ++
	5. 			If b.mismatches > max mismatches
	6. 				Remove b from Y
	7. 		End ForEach
	8. 	Return Y
