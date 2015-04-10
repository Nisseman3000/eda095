package lab3;

public class FiveThread extends Thread{
	
	FiveThread(){
		super();
	}
	
	public void run(){
		for (int i = 0; i< 5; i++){
			try {
				Thread.sleep((long) (100*Math.random()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(getName());
		}
	}

	public static void main(String[] jonas){
		for (int i = 0; i < 10 ; i++){
			new FiveThread().start();
		}
	}
	
}
