package cmpt371project1;

import java.io.PrintStream;
import java.net.InetAddress;
import java.net.URL;

import cmpt371project1.Chord;
import cmpt371project1.Node;
import cmpt371project1.Hash;

public class Main 
{

	public static final int nodes = 8;
	
	public static void main(String[] args) throws Exception 
	{
		
		//Create 8 nodes
		
		System.out.println("Creating nodes:");

		String host = InetAddress.getLocalHost().getHostAddress();
		int port = 9000;

		Chord chord = new Chord();
		for (int i = 0; i < nodes; i++) 
		{
			URL url = new URL("http", host, port + i, "");
			chord.createNode(url.toString());
		}
		
		//Print 8 nodes sorted by keys

		for (int i = 0; i < nodes; i++) 
		{
			Node node = chord.getSortedNode(i);
			System.out.println(node);
		}
		
		System.out.println(nodes + " nodes created.");
		
		//Create Chord Ring

		for (int i = 1; i < nodes; i++) 
		{
			Node node = chord.getNode(i);
			node.join(chord.getNode(0));
			Node preceding = node.getSuccessor().getPredecessor();
			node.stabilize();
			if (preceding == null) 
			{
				node.getSuccessor().stabilize();
			} else {
				preceding.stabilize();
			}
		}
		System.out.println("=========================================");
		System.out.println("Chord ring is established.");

		for (int i = 0; i < nodes; i++) 
		{
			Node node = chord.getNode(i);
			node.fixFingers();
		}
		
		//Begin Query Testing
		//Queries each key through node 1
		//Returns the response from each node
		//Finally returns node that is successor to the Key
		
		Global.a = 1;
		
		System.out.println("=========================================");
		System.out.println("Querying exercise begin:");
		System.out.println("=========================================");
		
		System.out.println("Find successor for key: "+chord.getNode(0).getNodeKey().toString());
		System.out.println("Starting from node 1:");
		Node node = chord.getNode(1);
		String output = node.findSuccessor(chord.getNode(0).getNodeKey()).toString();
		System.out.println("Successor found @ node:");
		System.out.println(output);
		System.out.println("-----------------------------------------");
		
		System.out.println("Find successor for key: "+chord.getNode(1).getNodeKey().toString());
		System.out.println("Node 1 sends query:");
		node = chord.getNode(1);
		output = node.findSuccessor(chord.getNode(1).getNodeKey()).toString();
		System.out.println("Successor found @ node:");
		System.out.println(output);
		System.out.println("-----------------------------------------");
		
		System.out.println("Find successor for key: "+chord.getNode(2).getNodeKey().toString());
		System.out.println("Node 1 sends query:");
		node = chord.getNode(1);
		output = node.findSuccessor(chord.getNode(2).getNodeKey()).toString();
		System.out.println("Successor found @ node:");
		System.out.println(output);
		System.out.println("-----------------------------------------");
		
		System.out.println("Find successor for key: "+chord.getNode(3).getNodeKey().toString());
		System.out.println("Node 1 sends query:");
		node = chord.getNode(1);
		output = node.findSuccessor(chord.getNode(3).getNodeKey()).toString();
		System.out.println("Successor found @ node:");
		System.out.println(output);
		System.out.println("-----------------------------------------");
		
		System.out.println("Find successor for key: "+chord.getNode(4).getNodeKey().toString());
		System.out.println("Node 1 sends query:");
		node = chord.getNode(1);
		output = node.findSuccessor(chord.getNode(4).getNodeKey()).toString();
		System.out.println("Successor found @ node:");
		System.out.println(output);
		System.out.println("-----------------------------------------");
		
		System.out.println("Find successor for key: "+chord.getNode(5).getNodeKey().toString());
		System.out.println("Node 1 sends query:");
		node = chord.getNode(1);
		output = node.findSuccessor(chord.getNode(5).getNodeKey()).toString();
		System.out.println("Successor found @ node:");
		System.out.println(output);
		System.out.println("-----------------------------------------");

		System.out.println("Find successor for key: "+chord.getNode(6).getNodeKey().toString());
		System.out.println("Node 1 sends query:");
		node = chord.getNode(1);
		output = node.findSuccessor(chord.getNode(6).getNodeKey()).toString();
		System.out.println("Successor found @ node:");
		System.out.println(output);
		System.out.println("-----------------------------------------");
		
		System.out.println("Find successor for key: "+chord.getNode(7).getNodeKey().toString());
		System.out.println("Node 1 sends query:");
		node = chord.getNode(1);
		output = node.findSuccessor(chord.getNode(7).getNodeKey()).toString();
		System.out.println("Successor found @ node:");
		System.out.println(output);
		System.out.println("-----------------------------------------");
		System.out.println("=========================================");
	}
}
