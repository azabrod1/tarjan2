import java.util.Set;

public interface Graph<T> {

	public void insertVertex(T toInsert);
	
	public void insertEdge(T to, T from);
	
	public Set<T> getVerticies();
	
	public Set<T> getNeighbors(T vertex);
	
	public int size();
	
	
}
