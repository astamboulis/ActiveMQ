package activemq;

public class URL_Consumer {

	public static void main(String[] args) {

		URL_Producer sender = new URL_Producer("tcp://10.160.10.100:61616",
				"admin", "admin");

		try {
			sender.sendMessage("TestRun");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}