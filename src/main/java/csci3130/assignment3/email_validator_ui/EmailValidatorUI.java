package csci3130.assignment3.email_validator_ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import csci3130.assignment3.email_validator_ui.backend.EmailValidator;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */

@Title("Email Validator")
@Theme("mytheme")
public class EmailValidatorUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        		
        final TextField email = new TextField();
        email.setCaption("Enter an email address:");

        Label message = new Label();
        
        Button validate = new Button("Validate Email");
        validate.addClickListener( e -> {
        	
        	EmailValidator validator = new EmailValidator();
        	String msg = "";
        	
        	if (validator.validateEmail(email.getValue()) == 4)
        	{
        		msg = " is a valid email address"; //valid email
        	}
        	else
        	{
        		msg = " is not a valid email address"; //invalid email
        	}
        	
        	message.setCaption(email.getValue() + msg); //update message
        });
        
        layout.addComponents(email, validate, message);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }
    
    @WebServlet(urlPatterns = "/*", name = "EmailValidatorUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = EmailValidatorUI.class, productionMode = false)
    public static class EmailValidatorUIServlet extends VaadinServlet {
    }
}
