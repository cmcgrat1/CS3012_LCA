import static org.junit.Assert.*;

import org.junit.Test;

public class DAG_Test {

	@Test 
	public void testV()
	{
		DAG graph = new DAG(4);
		assertEquals(4, graph.V());
	}
	
	@Test
	public void testE(){
		
		DAG graph = new DAG(8);
		
		graph.addEdge(1, 5);
		graph.addEdge(5, 4);
		graph.addEdge(4, 3);
		graph.addEdge(3, 5);
		
		assertEquals(4, graph.E());
	}

}
