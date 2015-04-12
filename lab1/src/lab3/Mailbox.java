package lab3;

public class Mailbox {
	private String content = "";
	
	public synchronized void set(String s){
		while(s.isEmpty()){
		try {
			wait();
		} catch (InterruptedException e) {
			System.out.println("Kaos i Båstad!");;
		}
	}
		content += s;
		notifyAll();
	}
	
	public synchronized String get(){
		while(content.equals("")){
		try {
			wait();
		} catch (InterruptedException e) {
			System.out.println("Kaos i Båstad!");;
		}
		}
		String tmp = content;
		content = "";
		notifyAll();
		return tmp;
	}
}
