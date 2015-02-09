
public class Godown {
	
	public static final int max_size = 100;
	public int curnum;
	
	Godown() {
		
	}
	
	Godown(int curnum) {
		this.curnum = curnum;
	}
	
	/**
	 * 生产指定数量产品
	 * @param neednum
	 */
	public synchronized void produce(int neednum) {
		while(neednum + curnum > max_size) {
			System.out.println("要生产的产品数量" + neednum + "超过剩余库存量" + (max_size - curnum));
			try {
				//当前线程等待
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		curnum += neednum;
		System.out.println("已经生产了" + neednum + "个产品，现仓储量为" + curnum);
		notifyAll();
	}
	
	/**
	 * 消费指定数量产品
	 */
	public synchronized void consume(int neednum) {
		while(curnum < neednum) {
			try {
				wait();
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		curnum -= neednum;
		System.out.println("已经消费了" + neednum + "个产品，现在仓储量为" + curnum);
		notifyAll();
	}
	
}
