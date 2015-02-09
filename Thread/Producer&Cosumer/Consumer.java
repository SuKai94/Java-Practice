
public class Consumer extends Thread {
	
	private int neednum;
	private Godown godown;
	
	Consumer(int neednum, Godown godown) {
		this.neednum = neednum;
		this.godown = godown;
	}
	
	public void run() {
		godown.consume(neednum);
	}

}
