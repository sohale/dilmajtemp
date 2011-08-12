package dilmaj.server;

public class SessionThread extends Thread {
	private int lastId;

	public SessionThread(Runnable runnable) {
		super(runnable);
	}
	
	public void setLastId(int lastId) {
		this.lastId=lastId;
	}
}
