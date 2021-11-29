package com.neo.servicebus;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {
    static String connectionString = "Endpoint=sb://XXXXXXXXXX.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=XXXX";
    static String queueName = "XXXXX";

    @GetMapping("/send")
    public void sendToServiceBus() {
        // create a Service Bus Sender client for the queue
        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient();

        // send one message to the queue

        senderClient.sendMessage(new ServiceBusMessage("Hello, Neo"));
        senderClient.close();
        System.out.println("Sent a single message to the queue: " + queueName);
    }

    @GetMapping("/test")
    public String test() {
        return "Hello Neo";
    }
}
