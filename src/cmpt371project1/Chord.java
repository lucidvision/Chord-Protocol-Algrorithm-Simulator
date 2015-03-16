package cmpt371project1;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Chord 
{

	List<Node> nodeList = new ArrayList<Node>();
	SortedMap<Key, Node> sortedNodeMap = new TreeMap<Key, Node>();
	Object[] sortedKeyArray;
	
	//Create a node
	
	public void createNode(String nodeId) 
	{
		Node node = new Node(nodeId);
		nodeList.add(node);
		sortedNodeMap.put(node.getNodeKey(), node);
	}
	
	//Get a node
	
	public Node getNode(int i) 
	{
		return (Node) nodeList.get(i);
	}
	
	//Get sorted node
	
	public Node getSortedNode(int i) 
	{
		if (sortedKeyArray == null) 
		{
			sortedKeyArray = sortedNodeMap.keySet().toArray();
		}
		return (Node) sortedNodeMap.get(sortedKeyArray[i]);
	}
	
}
