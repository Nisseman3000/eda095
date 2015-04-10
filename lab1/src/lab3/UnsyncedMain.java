package lab3;

public class UnsyncedMain {

	public static void main(String[] args) {
		Mailbox m = new Mailbox();
		for (int i = 0; i< 10; i++){
			new DepositThread(m).start();
		}
		new ReaderThread(m).start();
	}

}
