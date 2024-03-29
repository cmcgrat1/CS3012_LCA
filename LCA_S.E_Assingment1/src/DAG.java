import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class DAG
{
	private int V;						//number of vertices in graph
	private int E;						//number of edges in graph
	private ArrayList<Integer>[] adj;   //adj[V] = adjacency list for vertex v - changed to 2D array adj[V]
	private int [] indegree;			//indegree[V] = indegree of vertex V
	private int [] outdegree;			//outdegree[V] = outdegree of vertex V
	private boolean marked [];			//list of visited vertices
	private boolean hasCycle;			//True if graph has cycle
	private boolean stack [];			//
	
	//constructor to initialize and empty graph with size V
	public DAG(int V)
	{
		if(V < 0)
		{
			throw new IllegalArgumentException("Number of vertices must be greater than 0");
		}
		
		this.V = V;
		this.E = 0;
		indegree = new int[V];
		marked = new boolean[V];
		stack = new boolean[V];
		adj = (ArrayList<Integer>[]) new ArrayList[V];
		
		for(int v = 0; v < V; v++)
		{
			adj[v] = new ArrayList<Integer>();
		}
	}
	
	//Returns current vertex
	public int V()
	{
		return V;
	}
	
	public int E()
	{
		return E;
	}
	
	//Adds directed edge from v to w
	public void addEdge(int v, int w)
	{
		if((validateVertex(v) > 0) && (validateVertex(w) > 0))
		{
			adj[v].add(w);
			indegree[w]++;
			E++;
		}
		else
		{
			System.out.println("Please enter numbers between 0 and " + (V-1));
		}		
	}
	
	private int validateVertex(int v)
	{
		if(v < 0 || v >= V)
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}
	
	//Returns number of directed edges to vertex v
	public int indegree(int v)
	{
		if(validateVertex(v) > 0)
		{
			return indegree[v];
		}
		else
		{
			return -1;
		}
		
	}
	
	//Returns number of directed edges from vertex v
	public int outdegree(int v)
	{
		if(validateVertex(v) > 0)
		{
			return adj[v].size();
		}
		else
		{
			return -1;
		}
	}
	
	//Returns the adjacent vertices to v
	public Iterable<Integer> adj(int v)
	{
		return adj[v];
	}
	
	public boolean hasCycle()
	{
		return hasCycle;
	}
	
	public void findCycle(int v)
	{
		marked[v] = true;
		stack[v] = true;
		
		for(int w : adj(v))
		{
			if(!marked[w])
			{
				findCycle(w);
			}
			else if(stack[w])
			{
				hasCycle = true;
				return;
			}
		}
		stack[v] = false;
	}
	
	//Method to implement lowest common ancestor
	public int findLCA(int v, int w)
	{
		findCycle(0);
		
		if(hasCycle) //Graph is not DAG
		{
			return -1;
		}
		else if(validateVertex(v) < 0 || validateVertex(w) < 0)
		{
			//Not valid vertices, ie. non-negative
			return -1;
		}
		else if(E == 0)
		{
			//Graph has no edges, ie. empty
			return -1;
		}
		
		DAG reverse = reverse();
		
		ArrayList<Integer> array1 = reverse.BFS(v);
		ArrayList<Integer> array2 = reverse.BFS(w);
		ArrayList<Integer> commonAncestors = new ArrayList<Integer>();
		
		boolean found = false;
		
		for(int i = 0; i < array1.size(); i++)
		{
			for(int j = 0; j < array2.size(); j++)
			{
				if(array1.get(i) == array2.get(j))
				{
					commonAncestors.add(array1.get(i));
					found = true;
				}
			}
		}
		
		if(found)
		{
			//Return first element in list - Lowest Common Ancestor
			return commonAncestors.get(0);
		}
		else
		{
			return -1; //None found
		}
	}
	
	//Prints BFS(Breadth-First search) from source s
	public ArrayList<Integer> BFS(int s)
	{
		ArrayList<Integer> order = new ArrayList<Integer>();
		boolean visited[] = new boolean[V]; //Marks vertices as not visit
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		visited[s] = true;
		queue.add(s);
		
		while(queue.size() != 0)
		{
			s = queue.poll(); //Sets s to the head of the list
			order.add(s);
			
			//Find adjacent vertices to s. If not visited,
			//mark as visited (true) and enqueue
			Iterator<Integer> i = adj[s].listIterator();
			
			while(i.hasNext())
			{
				int n = i.next();
				if(!visited[n])
				{
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		return order;
	}
	
	//Reverse DAG
	public DAG reverse()
	{
		DAG reverse = new DAG(V);
		for(int v = 0; v <V; v++)
		{
			for(int w : adj(v))
			{
				reverse.addEdge(w, v);
			}		
		}
		return reverse;
	}
}