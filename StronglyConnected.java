import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StronglyConnected<T> {
	
	private int 		   index;
	private Deque<T>	   stack;
	private VertexInfo<T>  X;
	private Set<Set<T>>    SCCs;
	private Graph<T>       graph;
	

	public  Set<Set<T>> tarjan(Graph<T> graph){
		init(graph);
		
		for(T vertex : graph.getVerticies()){
			if(!X.visited(vertex))
				strongConnect(vertex);
			
		}
		
		
		return SCCs;
	}
	
	public void strongConnect(T vertex){
	
		//Set the depth of the vertex to the next smallest unused index 
		X.setIndex(vertex, index);
		X.setLow(vertex, index++);
		
		X.stackPush(vertex); //Push the vertex on the stack
		
		for(T neighbor : graph.getNeighbors(vertex)){
			/*if the neighbor has never been visited, we can continue our DFS through that vertex. If we reach a vertex that came from earlier in 
			 * the current path (smaller low value), it means we have a way back and this vertex is not the root of a SCC but is a member of one with
			 * a root higher in the tree
			 */
			
			
			if(!X.visited(neighbor)){
				strongConnect(neighbor);
				X.setLow(vertex, Math.min(X.getLow(vertex), X.getLow(neighbor)));
				
			}
			/*If the vertex is already on the stack, we can claim that our path can reach that vertex by setting
			 * low[vertex] = min(low[vertex], index[neighbor])
			 * We cannot do low[vertex] = min(low[vertex], low[neighbor]) because we may 
			 */
			else if(X.onStack(neighbor))
				X.setLow(vertex, Math.min(X.getLow(vertex), X.getIndex(neighbor)));
			
		}
		
		/*When low[v] = index[v], there is no way to get to a higher node from this vertex v. This means v must be the root of a SCC.
		 * We know we can pop all the elements on the stack up to and including v because any SCCs after v in that tree will already
		 * have been detected. This is because other verticies in the subtree rooted at v will not have had a path which reaches till v 
		 * since they are not part of this SCC hence they would have been identified as part of SCCs
		 */
		
		if(X.getIndex(vertex) == X.getLow(vertex)){
			T curr;
			Set<T> SCC = new HashSet<T>();
		
			do{
				curr = X.stackPop();
				SCC.add(curr);

			}while(curr != vertex);
			SCCs.add(SCC);
			
		}
	}
	
	private void init(Graph<T> graph){
		index = 0;
		stack = new ArrayDeque<T>(graph.size()/2);
		X     = new VertexInfo<T>(graph, stack);
		SCCs   = new HashSet<Set<T>>();
		this.graph = graph;
	}
	
	public void printSSCs(){
		int comp = 0;
		System.out.println("\nStrongly Connected Components of the graph are:");
		
		
		for(Set<T> component : SCCs){
			T[] elements = (T[]) component.toArray();
			System.out.print("\n" + ++comp + ". {" + elements[0]);
		
			
			for(int v = 1; v < component.size(); ++v)
				System.out.print( "," + elements[v]);
			
			System.out.print("}");

		}
	}
	
	
	
	
	private static class VertexInfo<T>{
		private final static int ON_STACK = 0, INDEX = 1, LOW = 2;

		private final Map<T, Integer[]> table;
		private final Deque<T> 	 		stack;
		
		public VertexInfo(Graph<T> graph, Deque<T> stack){
			table = new HashMap<T, Integer[]>();
			this.stack = stack;
			for(T vert : graph.getVerticies()){
				Integer[] info = {0, null, null};
				table.put(vert, info);
			
			}
		}
		
		public void stackPush(T vertex){
			stack.push(vertex);
			table.get(vertex)[ON_STACK] = 1;
		}
		
		public T stackPop(){
			T popped = stack.pop();
			table.get(popped)[ON_STACK] = 0;
			return popped;
		}
	
		public boolean onStack(T vertex){
			return table.get(vertex)[ON_STACK] == 1;
		}
		
		public boolean visited(T vertex){
			return table.get(vertex)[INDEX] != null;
		}
		public int getIndex(T vertex){
			return table.get(vertex)[INDEX];
		}
		
		public int getLow(T vertex){
			return table.get(vertex)[LOW];
		}
		
		public void  setIndex(T vertex, int newIndex){
			 table.get(vertex)[INDEX] = newIndex;
		}
		
		public void  setLow(T vertex, int newLow){
			 table.get(vertex)[LOW] = newLow;
		}
		
		public void  setIndex(T vertex, int option1, int option2){
			 table.get(vertex)[INDEX] = Math.min(option1, option2);
		}
		
		public void  setLow(T vertex, int option1, int option2){
			 table.get(vertex)[LOW] = Math.min(option1, option2);
		}
		
		
		
		
	}

}
