import java.util.Scanner;

/** Greet person by name (impatiently) */
public class Greeter {
	static final Scanner console = new Scanner(System.in);
	
	/** method to print nag messages until thread is interrupted */
	public static void nag() {
		try {
			while( true ) {
				Thread.sleep(2000); // wait for user
				if (Thread.interrupted()) break;
				System.out.println(getNagMessage());
			}
		} catch (InterruptedException ex) { /* done nagging */ }
	}
	
	/** Greet a user on the console, and nag him to be quick about it. */
	public static void main(String[] args) {
		// Nag runs in a separate thread, so it is 
		// not blocked by Scanner waiting for user input.
		Thread nag = new Thread(Greeter::nag);
		nag.start();
		System.out.println("What's your name? ");
		String name = console.nextLine().trim();
		System.out.println("Hello, "+name);
		// stop nagging now
		nag.interrupt();

	}
 
	/** Get a random nag message. */
	private static String getNagMessage() {
		final String[] nags = {
			"Hurry up! Type something.", 
			"Humans are so slooooow.",
			"My dog types faster than you",
			"Can you READ? What's your name?",
			"Helloooooo? Anyone there?",
			"Forgot your own name? Type something!"};
		int index = (int)(Math.random()*nags.length);
		return nags[index];
	}
}
