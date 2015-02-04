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
