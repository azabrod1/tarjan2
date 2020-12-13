import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SimpleGraph implements Graph<Integer> {
	
	private final HashMap<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
	
	public Set<Integer> getVerticies(){
		return graph.keySet();
	}
	
	public  Set<Integer> getNeighbors(Integer vertex){
		return graph.get(vertex);
	}
	
	public int size(){return graph.size();}
	
	public void insertEdge(Integer from, Integer to){
		
		if(!graph.containsKey(from)) graph.put(from, new HashSet<Integer>());
		if(!graph.containsKey(to))   graph.put(to,   new HashSet<Integer>());
		
		graph.get(from).add(to);
	
	}
	
	
	public void insertVertex(Integer key){
		
		graph.put(key, new HashSet<Integer>());
		
	}
	
	public void bulkInsertEdges(Integer[][] edges){
		
		for(Integer[] edge : edges)
			insertEdge(edge[0], edge[1]);
	
	}


	public void printEdges() {
		for(Integer vertex : graph.keySet()){
			for(Integer neighbor : graph.get(vertex))
				System.out.print("{" + vertex + "," + neighbor + "}     ");

			System.out.println();
		}

	}
	
}
