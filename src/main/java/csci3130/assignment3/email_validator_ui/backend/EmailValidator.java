package csci3130.assignment3.email_validator_ui.backend;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Class to determine if an email is valid based on four rules:
 * 1. the email has exactly one "@" character
 * 2. the email has at least one "." character
 * 3. the email begins with a letter
 * 4. the email ends with "com"
 * 
 * Uses regular expressions to check if email matches the rules
 * 
 * If the return value of the validate method is 4, then the email is valid
 */
public class EmailValidator {

	//constants
	private static final String RULE_1 = "^[^@]*(@)[^@]*$"; //email has exactly one "@"
	private static final String RULE_2 = "\\.+"; //email has at least one "."
	private static final String RULE_3 = "^[a-zA-Z]"; //email begins with letter
	private static final String RULE_4 = "(com)$"; //email ends with com
	
	//attributes
	private int rulesPassed;
	private Pattern pattern;
	private Matcher matcher;
	
	//constructors
	public EmailValidator()
	{
		
	}
	
	//getters
	public int getRulesPassed()
	{
		return rulesPassed;
	}
	
	//setters
	public void setPattern(final String rule)
	{
		pattern = Pattern.compile(rule);
	}
	public void setMatcher(String email)
	{
		matcher = pattern.matcher(email);
	}
	
	// METHODS //
	
	/**
	 * Method to determine if an email is valid
	 * 
	 * @param email the email to validate
	 * @return rulesPassed the number of rules the email passes. 2 indicates validity
	 */
	public int validateEmail(String email)
	{
		//check if email passes rule 1
		doesRulePass(RULE_1,email);
		
		//check if email passes rule 2
		doesRulePass(RULE_2,email);
		
		//check if email passes rule 3
		doesRulePass(RULE_3,email);
		
		//check if email passes rule 4
		doesRulePass(RULE_4,email);
		
		return rulesPassed;
	}
	
	/**
	 * Helper method to validateEmail. 
	 * 
	 * Binds the Regex tools (Pattern and Matcher) to the correct rule, email.
	 * @param rule regex describing the rule
	 * @param email string to match against the rule
	 */
	private void doesRulePass(final String rule, String email)
	{
		setPattern(rule);
		setMatcher(email);
		if (matcher.find())
		{ 
			rulesPassed++; //rule passed
		}
	}
}
