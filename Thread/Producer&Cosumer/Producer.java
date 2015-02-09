
public class Producer extends Thread {
	
	private int neednum;
	private Godown godown;
	
	Producer(int neednum, Godown godown) {
		this.neednum = neednum;
		this.godown = godown;
	}
	
	public void run() {
		godown.produce(neednum);
	}

}
