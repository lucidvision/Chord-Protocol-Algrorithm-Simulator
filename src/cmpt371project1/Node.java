package cmpt371project1;

import java.io.PrintStream;

public class Node 
{

	String nodeId;
	Key nodeKey;
	Node predecessor;
	Node successor;
	FingerTable fingerTable;
	
	//Node constructor
	
	public Node(String nodeId) 
	{
		this.nodeId = nodeId;
		this.nodeKey = new Key(nodeId);
		this.fingerTable = new FingerTable(this);
		this.create();
	}
	
	//Create the ring
	
	public void create() 
	{
		predecessor = null;
		successor = this;
	}
	
	//Join the ring
	
	public void join(Node node) 
	{
		predecessor = null;
		successor = node.findSuccessor(this.getNodeId());
	}
	
	//Stabilize the ring
	
	public void stabilize() 
	{
		Node node = successor.getPredecessor();
		if (node != null) 
		{
			Key key = node.getNodeKey();
			if ((this == successor)
					|| key.isBetween(this.getNodeKey(), successor.getNodeKey())) 
			{
				successor = node;
			}
		}
		successor.notifyPredecessor(this);
	}
	
	//Query for successor of key
	
	public Node findSuccessor(String identifier) {
		Key key = new Key(identifier);
		return findSuccessor(key);
	}
	
	public Node findSuccessor(Key key) 
	{

		if (this == successor) {
			return this;
		}

		if (key.isBetween(this.getNodeKey(), successor.getNodeKey())
				|| key.compareTo(successor.getNodeKey()) == 0) 
		{
			return successor;
		} else {
			Node node = closestPrecedingNode(key);
			if ( Global.a == 1)
			{
				System.out.println("Sorry, but please ask this node: "+node.getNodeId());
			}
			if (node == this)
			{
				return successor.findSuccessor(key);
			}
			return node.findSuccessor(key);
		}
	}
	
	//Recursively find closest node
	
	private Node closestPrecedingNode(Key key) 
	{
		for (int i = Hash.KEY_LENGTH - 1; i >= 0; i--) 
		{
			Finger finger = fingerTable.getFinger(i);
			Key fingerKey = finger.getNode().getNodeKey();
			if (fingerKey.isBetween(this.getNodeKey(), key)) 
			{
				return finger.getNode();
			}
		}
		return this;
	}
	
	//Notify predecessor of ring change

	private void notifyPredecessor(Node node) 
	{
		Key key = node.getNodeKey();
		if (predecessor == null || key.isBetween(predecessor.getNodeKey(), this.getNodeKey())) 
		{
			predecessor = node;
		}
	}
	
	//Update finger table

	public void fixFingers() {
		for (int i = 0; i < Hash.KEY_LENGTH; i++) 
		{
			Finger finger = fingerTable.getFinger(i);
			Key key = finger.getStart();
			finger.setNode(findSuccessor(key));
		}
	}
	
	//Print node information
	
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Node[");
		sb.append("ID=" + nodeId);
		sb.append(",KEY=" + nodeKey);
		sb.append("]");
		return sb.toString();
	}
	
	//Get Node ID
	
	public String getNodeId() 
	{
		return nodeId;
	}
	
	//Set Node ID

	public void setNodeId(String nodeId) 
	{
		this.nodeId = nodeId;
	}
	
	//Get Node Key

	public Key getNodeKey() 
	{
		return nodeKey;
	}
	
	//Set Node Key

	public void setNodeKey(Key nodeKey) 
	{
		this.nodeKey = nodeKey;
	}
	
	//Get Node Predecessor

	public Node getPredecessor() 
	{
		return predecessor;
	}
	
	//Set Node Predecessor

	public void setPredecessor(Node predecessor) 
	{
		this.predecessor = predecessor;
	}
	
	//Get Node Successor

	public Node getSuccessor() 
	{
		return successor;
	}
	
	//Set Node Successor

	public void setSuccessor(Node successor) 
	{
		this.successor = successor;
	}
	
	//Get Node Finger Table

	public FingerTable getFingerTable() 
	{
		return fingerTable;
	}
	
	//Set Node Finger Table

	public void setFingerTable(FingerTable fingerTable) 
	{
		this.fingerTable = fingerTable;
	}
	
	
}
