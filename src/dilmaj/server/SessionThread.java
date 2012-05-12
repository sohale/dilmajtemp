package dilmaj.server;

public class SessionThread extends Thread implements Runnable {
	private int lastId;

	public SessionThread(Runnable runnable) {
		super(runnable);
	}
	
	public void setLastId(int lastId) {
		this.lastId=lastId;
	}
	
	public void run() {
	
	}
}
