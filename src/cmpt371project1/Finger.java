package cmpt371project1;

public class Finger {
	
	Node node;
	Key start;
	
	//Constructor for finger

	public Finger(Key start, Node node) {
		this.node = node;
		this.start = start;
	}
	
	//Get Node Start Key

	public Key getStart() {
		return start;
	}
	
	//Set Node Start Key

	public void setStart(Key start) {
		this.start = start;
	}
	
	//Get Finger Node

	public Node getNode() {
		return node;
	}
	
	//Set Finer Node

	public void setNode(Node node) {
		this.node = node;
	}
	
}