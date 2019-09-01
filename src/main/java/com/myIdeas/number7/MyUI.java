package com.myIdeas.number7;

import javax.servlet.annotation.WebServlet;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Binder;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
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
public class MyUI extends UI implements View{

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    Navigator  navigator;

		Label header= new Label("Welcome to Postmaster.");
        Label emp=new Label("Employee Id");
	    TextField emp_id = new TextField();
	    Label od_type=new Label("Order Type");
	    TextField order_type = new TextField();
	    Label od_details=new Label("Order Details");
	    TextArea order_details = new TextArea();
	    Label priory=new Label("Priority");
	    ComboBox<String> priority = new ComboBox<String>("Please specify the priority");
	    Button order = new Button("Place Order");
	    Binder<FormData> binder = new Binder<>();
	    
	   
	    @Override
	    protected void init(VaadinRequest request) {
	       // configureComponents();
	    	 
	        //buildLayout();
	        navigator=new Navigator(this, this);
	        navigator.addView("aa", new OrderPlaced());
	        navigator.addView("", new HomeView());
	        
	    }

		private void buildLayout() {
			// TODO Auto-generated method stub
			
			
			HorizontalLayout emp_align = new HorizontalLayout(emp, emp_id);
			HorizontalLayout od_type_align = new HorizontalLayout(od_type, order_type);
			HorizontalLayout od_details_align = new HorizontalLayout(od_details, order_details);
			HorizontalLayout prior_align = new HorizontalLayout(priory, priority);

	        emp_align.setWidth("100%");
	        od_type_align.setWidth("100%");
	        od_details_align.setWidth("100%");
	        prior_align.setWidth("100%");
	        header.addStyleName(ValoTheme.LABEL_H1);
	        priority.setItems("Low", "Moderate", "High", "Urgent");
	        order.addClickListener(this::save);
	        Panel contentArea = new Panel("Equals");
            contentArea.addStyleName(ValoTheme.PANEL_BORDERLESS);
            contentArea.setSizeFull();
            //addComponent(contentArea);
	       
	        //navigator.addView("", this);
            

	        VerticalLayout left = new VerticalLayout(header, emp_align,od_type_align,od_details_align, prior_align, order);
	       //left.setSizeFull();
	        

	        HorizontalLayout mainLayout = new HorizontalLayout(left);
	        mainLayout.setSizeFull();
	        mainLayout.setExpandRatio(left, 1);

	        // Split and allow resizing
	        setContent(mainLayout);
		}

		  public void save(Button.ClickEvent event) {
		        try {
		            // Commit the fields from UI to DAO
//		            binder.forField(emp_id).bind(FormData::getEmp_id,FormData::setEmp_id);
//		            binder.forField(order_type).bind(FormData::getOrder_type,FormData::setOrder_type);
//		            binder.forField(order_details).bind(FormData::getOrder_details,FormData::setOrder_details);
//		            binder.forField(priority).bind(FormData::getPriority,FormData::setPriority);
//                    FormData fd=new FormData();
//                    //emp_id.addValueChangeListener(event0 -> fd.setEmp_id(event0));
//                    order_type.addValueChangeListener(event1->fd.setOrder_type(event1.getValue()));
//                    order_details.addValueChangeListener(event2->fd.setOrder_details(event2.getValue()));
//                    priority.addValueChangeListener(event3->fd.setPriority(event3.getValue()));


		            DBObject document = new BasicDBObject();
		        	document.put("emp_id", emp_id.getValue());
		        	document.put("order_type", order_type.getValue());
		        	document.put("order_details", order_details.getValue());
		        	document.put("priority", priority.getValue());
		        	
		            new ConnectDB().connectMongo().insert(document);
		            navigator.navigateTo("aa");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		private void configureComponents() {
			// TODO Auto-generated method stub
			
		}
		
		 @WebServlet(urlPatterns = "/*")
		    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
		    public static class MyUIServlet extends VaadinServlet {
		    }

		@Override
		public void enter(ViewChangeEvent event) {
			// TODO Auto-generated method stub
			
		}
	    
	    
	}