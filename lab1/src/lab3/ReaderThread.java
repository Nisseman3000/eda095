package lab3;

public class ReaderThread extends Thread {
	private Mailbox m;
	private int counter = 0;
	public ReaderThread(Mailbox m){
		super();
		this.m = m;
	}
	
	public void run(){
		while(true){
			String tmp = m.get();
			//if (!tmp.equals(""))
			System.out.print(tmp);
		}
	}
}
