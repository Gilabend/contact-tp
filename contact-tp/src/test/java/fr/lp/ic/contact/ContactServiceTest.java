package fr.lp.ic.contact;

import org.junit.Assert;

import org.junit.Test;

import fr.lp.ic.contact.exception.ContactException;


public class ContactServiceTest {
	public static final String VALID_PHONE_NUMBER = "0254695678";
	public static final String VALID_EMAIL = "test@email.com";
	private ContactService service = new ContactService();
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldFailedIfNameLessThanThree() throws ContactException {
		service.newContact("ab", VALID_PHONE_NUMBER, VALID_EMAIL);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldFailedIfNameGreaterThanThree() throws ContactException {
		service.newContact("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", VALID_PHONE_NUMBER, VALID_EMAIL);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldFailedIfNameNull() throws ContactException {
		service.newContact(null, VALID_PHONE_NUMBER, VALID_EMAIL);
	}
	
	@Test
	public void shouldPassedIfNameThree() throws ContactException {
		service.newContact("abc", VALID_PHONE_NUMBER, VALID_EMAIL);
	}
	
	@Test
	public void shouldPassedIfNameForty() throws ContactException {
		service.newContact("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", VALID_PHONE_NUMBER, VALID_EMAIL);
	}
	
	@Test(expected = ContactException.class)
	public void shouldFailedIfUserAlreadyExists() throws ContactException {
		service.newContact("Jul", VALID_PHONE_NUMBER, VALID_EMAIL);
		service.newContact("Jul", VALID_PHONE_NUMBER, VALID_EMAIL);
	}
}
