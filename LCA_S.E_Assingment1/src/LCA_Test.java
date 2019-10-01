import static org.junit.Assert.*;

import org.junit.Test;

public class LCA_Test {
	// I am going to run some test using the binary tree below 
	//      _____5____
	//     /          \
	//   __7__       __6__
	//  /     \     /     \
	// 1       3   0       4
	//                    / \
	//                   9   8
	
	LCA tree = new LCA();

	@Test
	public void testTree() {
		createTree();
        //testing if the function is working
		//testing that function is not affected by order 
		assertEquals("LCA = 5",5 ,tree.findLCA(1, 6));
		assertEquals("LCA = 5",5 ,tree.findLCA(6, 1));
		
		
		assertEquals("LCA = 5",5 ,tree.findLCA(3, 8));
		assertEquals("LCA = 5",5 ,tree.findLCA(3, 8));
		
		assertEquals("LCA = 4",4 ,tree.findLCA(9, 8));
		assertEquals("LCA = 4",4 ,tree.findLCA(8, 9));
		
		assertEquals("LCA = 6",6 ,tree.findLCA(9, 0));
		assertEquals("LCA = 6",6 ,tree.findLCA(0, 9));
	}
	
	@Test
	public void testRepeatedNode(){
		createTree();
		
		//same root element, LCA is the element and the root
		assertEquals("LCA = null",3 ,tree.findLCA(3, 3));
		
		//one root element and another, LCA is one of the elements and the root 
		assertEquals(3 ,tree.findLCA(3, 5));//checked that LCA can still be parent
		assertEquals(3 ,tree.findLCA(1, 3));
		
		//same non root element, LCA is the element itself
		assertEquals("LCA = 1",1 ,tree.findLCA(1, 1));
		assertEquals("LCA = 5",5 ,tree.findLCA(5, 5));
	}
	
	@Test
	public void testNullTree() {
		assertNull(tree.root);
	}
	@Test
	public void testConstructor() {

		Node rootNode = new Node(0);//only the root node 
		assertEquals("Only one node, left site is equal to null",null ,rootNode.left);
		assertEquals("Only one node, right side is equal to null",null ,rootNode.right);
		assertEquals("Value = 0",0 ,rootNode.data);
	}
	@Test
	public void testSmallTree() {
		
		tree.root = new Node(5);
		tree.root.left = new Node(7);
		
		assertEquals(5 ,tree.findLCA(7, 5));
		assertEquals(5 ,tree.findLCA(5, 7));
		assertEquals(7 ,tree.root.left.data);
		assertNull(tree.root.right);
		assertEquals(5 ,tree.root.data);
	}
	

	public void createTree(){
		//will use this function for test that need a preconstructed tree
		tree.root = new Node(5);
		tree.root.left = new Node(7);
		tree.root.right = new Node(6);
		tree.root.right.right = new Node(4);
		tree.root.left.right =new Node(3);
		tree.root.left.left =new Node(1);
		tree.root.right.left =new Node(0);
		tree.root.right.right.right =new Node(8);
		tree.root.right.right.left =new Node(9);
			
	}

}
