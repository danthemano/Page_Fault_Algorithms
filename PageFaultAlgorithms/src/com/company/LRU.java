package pagefault;

public class LRU {
	int value;
	int priority;
	
	public LRU(int value, int priority) {
		this.value = value;
		this.priority = priority;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getPriority() {
		return priority;
	}
	
}
