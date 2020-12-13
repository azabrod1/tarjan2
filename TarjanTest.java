import java.util.Random;

public class TarjanTest {


	public static void main(String[] args) {
		Graph<Integer> graph = new SimpleGraph();
		
		Integer[][] edges = {};
		
		Random r = new Random();
		System.out.print("\n{");
		for(int v = 0; v < 50; ++v){
			for(int w = 0; w< 50; ++w){
				if(r.nextInt(30) == 0){
					System.out.print("{" + v + "," + w + "}, "); graph.insertEdge(v, w);}
			}
			System.out.println();
		} System.out.print("}\n");

		
		SimpleGraph simpleCast = ((SimpleGraph) graph);
		simpleCast.bulkInsertEdges(edges);
		
		simpleCast.printEdges();
		
		StronglyConnected<Integer> algo = new StronglyConnected<Integer>();
		
		algo.tarjan(graph);
		
		algo.printSSCs();
		

	}

}
