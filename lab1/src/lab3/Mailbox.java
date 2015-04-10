package lab3;

public class Mailbox {
	private String content = "";
	
	public void set(String s){
		content += s;
	}
	
	public String get(){
		String tmp = content;
		content = "";
		return tmp;
	}
}
