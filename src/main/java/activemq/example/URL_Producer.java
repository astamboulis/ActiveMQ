package activemq.example;

import javax.jms.CompletionListener;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class URL_Producer implements MessageProducer {

	private String brokerURL;
	private String username;
	private String password;
	private String queueName;
	private Connection connection = null;
	private Session session = null;
	private MessageProducer delegate;

	public URL_Producer(final String brokerURL, final String queueName,
			String username, String password) {
		this.brokerURL = brokerURL;
		this.username = username;
		this.password = password;
		this.queueName = queueName;

		this.setUp();

	}

	public URL_Producer(final String brokerURL, String username, String password) {
		this(brokerURL, null, username, password);
	}

	public void setUp() throws NullPointerException {
		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					this.username, this.password, this.brokerURL);

			// create connection
			connection = connectionFactory.createConnection();
			connection.start();

			// Create a Session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue("TestRun");

			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			this.delegate = producer;

		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
			throw new NullPointerException("Cannot create producer!");
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (session != null) {
					try {
						session.close();
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void sendMessage(final String textMessage) throws Exception {
		// Connection connection = null;
		// Session session = null;

		try {

			// Create a message
			String text = "DID IT WORK?! From: "
					+ Thread.currentThread().getName() + " : "
					+ this.hashCode();

			TextMessage message = session.createTextMessage(text);
			String timestamp = DateFormater.format(message.getJMSExpiration());

			// Tell the producer to send the message

			System.out.printf("Sent message: %s : %s [%s]%n",
					message.hashCode(), Thread.currentThread().getName(),
					timestamp);

			// producer.setTimeToLive(DateTimeConstants.MILLIS_PER_HOUR);

			send(message);

		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
				if (session != null) {
					session.close();
				}
			}
		}
	}

	public void setDisableMessageID(boolean value) throws JMSException {
		delegate.setDisableMessageID(value);
	}

	public boolean getDisableMessageID() throws JMSException {
		return delegate.getDisableMessageID();
	}

	public void setDisableMessageTimestamp(boolean value) throws JMSException {
		delegate.setDisableMessageTimestamp(value);
	}

	public boolean getDisableMessageTimestamp() throws JMSException {
		return delegate.getDisableMessageTimestamp();
	}

	public void setDeliveryMode(int deliveryMode) throws JMSException {
		delegate.setDeliveryMode(deliveryMode);
	}

	public int getDeliveryMode() throws JMSException {
		return delegate.getDeliveryMode();
	}

	public void setPriority(int defaultPriority) throws JMSException {
		delegate.setPriority(defaultPriority);
	}

	public int getPriority() throws JMSException {
		return delegate.getPriority();
	}

	public void setTimeToLive(long timeToLive) throws JMSException {
		delegate.setTimeToLive(timeToLive);
	}

	public long getTimeToLive() throws JMSException {
		return delegate.getTimeToLive();
	}

	public void setDeliveryDelay(long deliveryDelay) throws JMSException {
		delegate.setDeliveryDelay(deliveryDelay);
	}

	public long getDeliveryDelay() throws JMSException {
		return delegate.getDeliveryDelay();
	}

	public Destination getDestination() throws JMSException {
		return delegate.getDestination();
	}

	public void close() throws JMSException {
		delegate.close();
	}

	public void send(Message message) throws JMSException {
		delegate.send(message);
	}

	public void send(Message message, int deliveryMode, int priority,
			long timeToLive) throws JMSException {
		delegate.send(message, deliveryMode, priority, timeToLive);
	}

	public void send(Destination destination, Message message)
			throws JMSException {
		delegate.send(destination, message);
	}

	public void send(Destination destination, Message message,
			int deliveryMode, int priority, long timeToLive)
			throws JMSException {
		delegate.send(destination, message, deliveryMode, priority, timeToLive);
	}

	public void send(Message message, CompletionListener completionListener)
			throws JMSException {
		delegate.send(message, completionListener);
	}

	public void send(Message message, int deliveryMode, int priority,
			long timeToLive, CompletionListener completionListener)
			throws JMSException {
		delegate.send(message, deliveryMode, priority, timeToLive,
				completionListener);
	}

	public void send(Destination destination, Message message,
			CompletionListener completionListener) throws JMSException {
		delegate.send(destination, message, completionListener);
	}

	public void send(Destination destination, Message message,
			int deliveryMode, int priority, long timeToLive,
			CompletionListener completionListener) throws JMSException {
		delegate.send(destination, message, deliveryMode, priority, timeToLive,
				completionListener);
	}
}
