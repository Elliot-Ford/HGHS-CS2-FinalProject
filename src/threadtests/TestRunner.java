package threadtests;

public class TestRunner {
	public static Thread tester;

	public static void main(String[] args) {
		tester = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println("hello");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Thread.currentThread().interrupt();
					}
				}

			}
		});

		tester.start();
		while (true) {
			System.out.println("boo");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}
}
