## Use a Thread to Nag Someone

In Java, we normally run code serially one instruction at a time.
So if we ask someone to input his name, like this:

```java
static final Scanner console = new Scanner(System.in);

public static void main(String[] args) {

	System.out.print("What is your name? ");
	String name = console.nextLine().trim();  // wait for input
	System.out.println("Hello "+name);

}
```
our code will wait (block) at `console.nextLine()` 
until the user types something and presses Enter.

The example code shows how to use a separate thread to print nag messages.

Two points to note:

### 1. Method Reference for Runnable

The parameter to `new Thread( )` is an object that implements Runnable.
Instead of writing a separate class, the code uses a *method reference*
`new Thread(Greeter::nag)`.

### 2. How to Stop the Nag Thread

The nag thread will nag the user forever, unless interrupted.
After the user types his name, use:
```java
nag.interrupt();
```

Inside the thread's run method (`nag()`), check for interruption 2 ways:
```java
try {
    while(true) {
        Thread.sleep(2000);       // may throw InteruptedException
        if (Thread.interrupted()) break;
        System.out.println( getNagMessage() );
    }
    catch(InteruptedException ex) {
        // stop nagging
    }
}
```
probably the try - catch(InterruptedException) is enough.  
`Thread.interupted()` is true if the current thread has been interrupted.
Either way, the code breaks out of the loop and the nag method returns.


