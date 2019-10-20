import static org.junit.Assert.*;

import org.junit.Test;

public class DAG_Test {
	
	@Test
	public void testDAG()
	{
		DAG graph = new DAG(12);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 4);
		graph.addEdge(2, 5);
		graph.addEdge(4, 6);
		graph.addEdge(4, 7);
		
		assertEquals(1, graph.indegree(4));
		assertEquals(2, graph.outdegree(4));
		assertEquals(5, graph.E());
		assertEquals(12, graph.V());
		String adj = "[6, 7]";
		assertEquals(adj, graph.adj(4).toString());
	}
	
	@Test(expected=Exception.class)
	public void exceptionTest(){
		
		//Can't make a directed graph with less than 0 vertices
		
		DAG graph = new DAG(-5);
	}

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
	@Test
	public void testIndegree()
	{
		DAG graph = new DAG(5);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(4, 3);
		
		
		assertEquals(2, graph.indegree(3));
	
		assertEquals(-1, graph.indegree(8));
	}
	
	@Test
	public void testOutdegree()
	{
		DAG graph = new DAG(5);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(4, 3);

		assertEquals(1, graph.outdegree(1));
	
		assertEquals(-1, graph.outdegree(8));
	}
	
	@Test
	public void addEdge()
	{
		DAG graph = new DAG(5);
		
		graph.addEdge(1,2);

		graph.addEdge(-1, -6); //not added because negative
		
		graph.addEdge(3, 12);// not added as 12 is bigger then 5
		
		assertEquals(1, graph.E());
	}
	
	@Test
	public void testforCycle()
	{
		DAG graph = new DAG(6);
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 0);
		graph.addEdge(3, 4);
		graph.addEdge(4, 5);
		
		graph.findCycle(0);
		
		assertTrue(graph.hasCycle());
	}
	
	@Test
	public void testAcyclicGraph()
	{
		DAG graph = new DAG(10);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 6);
		graph.addEdge(4, 4);
		
		graph.findCycle(1);
		assertFalse(graph.hasCycle());
	}
	@Test
	public void testLCA()
	{
		DAG graph = new DAG(10);
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 3);
		graph.addEdge(1, 2);
		graph.addEdge(3, 4);
		graph.addEdge(2, 5);
		graph.addEdge(5, 6);
		graph.addEdge(4, 6);
		graph.addEdge(6, 7);
		graph.addEdge(4, 8);
		graph.addEdge(8, 7);
		
		assertEquals(1, graph.findLCA(4, 5));
		assertEquals(8, graph.findLCA(7, 8));
		assertEquals(6, graph.findLCA(6, 7));
	}
	@Test
	public void testLCAForEmptyDAG()
	{
		DAG graph = new DAG(8);
		assertEquals(-1, graph.findLCA(0, 3));
		assertEquals(-1, graph.findLCA(0, 5));
	}


}
