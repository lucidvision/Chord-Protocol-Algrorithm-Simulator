package cmpt371project1;

public class FingerTable {
	Finger[] fingers;
	
	//Create Finger Table for Node

	public FingerTable(Node node) {
		this.fingers = new Finger[Hash.KEY_LENGTH];
		for (int i = 0; i < fingers.length; i++) {
			Key start = node.getNodeKey().createStartKey(i);
			fingers[i] = new Finger(start, node);
		}
	}
	
	//Get Finger Table for Node

	public Finger getFinger(int i) {
		return fingers[i];
	}
}
