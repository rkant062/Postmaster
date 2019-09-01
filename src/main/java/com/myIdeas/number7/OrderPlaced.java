package com.myIdeas.number7;

import java.io.File;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;


public class OrderPlaced extends VerticalLayout implements View {
        Label watching;
        Embedded pic;
        public OrderPlaced() {
           // setSizeFull();

            Button button = new Button("New Order");
            Label success_reply=new Label("Your order has been placed successfully.");
            //success_reply.setSizeFull();
            FileResource resource = new FileResource(new File("/Users/ravi/Documents/workspace-spring-tool-suite-4-4.2.1.RELEASE/number7/src/main/resources/succ_icon.png"));
            Image img=new Image("",resource);
            Label order_status=new Label("Order status: ");
            Label order_status_value=new Label();
            //watching.setValue("Your order has been placed successfully");
            
            button.addClickListener(e -> { UI.getCurrent().getNavigator().navigateTo("");});
            addComponents(button,success_reply, img, order_status);
            setComponentAlignment(img, Alignment.MIDDLE_CENTER);

            setComponentAlignment(button, Alignment.TOP_RIGHT);
            setComponentAlignment(success_reply, Alignment.TOP_CENTER);
        }
        

        @Override
        public void enter(ViewChangeEvent event) {
           // String animal = event.getViewName();

            //watching.setValue("Your order has been successfully placed");
//           
         //event.getNavigator().navigateTo("aa");
        }
    }
