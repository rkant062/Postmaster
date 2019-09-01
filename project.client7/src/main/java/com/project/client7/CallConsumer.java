package com.project.client7;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class CallConsumer  extends Thread {
	
	public void run()
	{
		   Consumer<Long, String> consumer = ConsumerCreator.createConsumer();
	        int noMessageFound = 0;
	        while (true) {
	          ConsumerRecords<Long, String> consumerRecords = consumer.poll(1000);
	          // 1000 is the time in milliseconds consumer will wait if no record is found at broker.
	          if (consumerRecords.count() == 0) {
	              noMessageFound++;
	              if (noMessageFound > KafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
	                // If no message found count is reached to threshold exit loop.  
	                break;
	              else
	                  continue;
	          }
	          consumerRecords.forEach(record -> {
	              //System.out.println("Record Key " + record.key());
	              System.out.println("Record value " + record.value());
	              Label temp=new Label(record.value());
	              //System.out.println("Record partition " + record.partition());
	              System.out.println("Record offset " + record.offset());
	              //addComponent(temp);
	           });
	          
	          // commits the offset of record to broker. 
	           consumer.commitAsync();
	        }
	        
	    consumer.close();
	}


}
