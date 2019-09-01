package com.project.client7;

import javax.servlet.annotation.WebServlet;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
//import com.myIdeas.number7.ConnectDB;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	
	 Label lb1= new Label("App Client");
	
	 final TextField name = new TextField();
     
     Label status=new Label("Status");
	    ComboBox<String> status1 = new ComboBox<String>("Please update the status of the job.");
	    Button update = new Button("Update");
	    DBCollection dbc=new ConnectDB().connectMongo();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	new CallConsumer().start();
        final VerticalLayout layout = new VerticalLayout();
        lb1.addStyleName(ValoTheme.LABEL_H1);
        name.setCaption("Emp ID");
        status1.setItems("In-progress", "Delayed", "On-time", "Delivered");
        

        //Button button = new Button("Click Me");
        update.addClickListener(e -> {save(e);});
        
        
                
        layout.addComponents(lb1, name, status, status1, update);
        
        
        setContent(layout);
            
        
    }
    

    private void save(Button.ClickEvent e) {
		
    	DBObject newDocument = new BasicDBObject();
    	newDocument.put("status", status1.getValue());

    	DBObject searchQuery = new BasicDBObject().append("emp_id", name.getValue());

    	dbc.update(searchQuery, newDocument);
    	System.out.println("Updated emp_id "+name.getValue());

	}


	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
