package mistrzowie.shop.logic.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import mistrzowie.shop.dao.ItemEntity;

/**
 *
 * @author Arek
 */
@Stateless
public class JMSStoreService implements Serializable {

    @Resource(mappedName = "amqres")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/queue/incoming")
    private Queue queue;

    public void send(ItemEntity entity) {

        try (Connection connection = connectionFactory.createConnection()) {
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);

            MapMessage message = session.createMapMessage();

            message.setStringProperty("product", entity.getName());
            message.setIntProperty("quantity", 10);
            message.setStringProperty("address", "10.105.110.4");

            messageProducer.send(message);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
