package lab3;

public class DepositThread extends Thread{
	private Mailbox m;
	public DepositThread(Mailbox m){
		super();
		this.m = m;
	}
	
	public void run(){
		for (int i = 0; i < 5; i++){
			try {
				Thread.sleep((long) (100*Math.random()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m.set(getName() + "\n");
		}
	}

}
