package apps;

import structures.*;
import java.util.ArrayList;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
		
		/* COMPLETE THIS METHOD */
		System.out.println("initialize");
		
		PartialTreeList treeList = new PartialTreeList();
		
		for(int i = 0; i < graph.vertices.length; i++){
			Vertex v = graph.vertices[i];
			PartialTree T = new PartialTree(v);
			
			Vertex.Neighbor ptr = v.neighbors;
			while(ptr!=null){
				PartialTree.Arc arc = new PartialTree.Arc(v, ptr.vertex, ptr.weight);
				
				T.getArcs().insert(arc);
				
				ptr = ptr.next;
			}
			
			
			treeList.append(T);
		}
		
		return treeList;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		
		/* COMPLETE THIS METHOD */
		
		System.out.println("Execute");
		ArrayList<PartialTree.Arc> list = new ArrayList();
		
		
		
		while(ptlist.size() > 1){
			
			PartialTree PTX = ptlist.remove();
			MinHeap<PartialTree.Arc> PQX = PTX.getArcs();
			PartialTree.Arc arcSmallest = PQX.deleteMin();
			
			while(arcSmallest != null){
				
				Vertex v1 = arcSmallest.v1;
				Vertex v2 = arcSmallest.v2;
				
				PartialTree PTY = ptlist.removeTreeContaining(v1);
				
				if(PTY == null){
					PTY = ptlist.removeTreeContaining(v2);
				}
				
				if(PTY != null){
					System.out.println("here");
					PTX.merge(PTY);
					list.add(arcSmallest);
					ptlist.append(PTX);
					System.out.println(PTX.toString());
					break;
				}
				
				
				
				arcSmallest = PQX.deleteMin();
			}
		}
		
		return list;
	}
}
