package fr.lp.ic.contact;

import java.util.Optional;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;

import fr.lp.ic.contact.dao.IContactDao;
import fr.lp.ic.contact.exception.ContactException;
import fr.lp.ic.contact.exception.ContactNotFoundException;
import fr.lp.ic.contact.model.Contact;
import org.junit.Assert;

public class ContactServiceMockTest {
	
	@Rule
	public EasyMockRule rule = new EasyMockRule(this);
	
	@TestSubject
	ContactService contactService = new ContactService();
	
	@Mock
	IContactDao contactDao;
	
	@Test(expected = ContactException.class)
	public void shouldFailIfNameAlreadyExists() throws ContactException {
		String name = "Jul";
		EasyMock.expect(contactDao.findByName(name)).andReturn(Optional.of(new Contact()));
		EasyMock.replay(contactDao);
		
		contactService.newContact(name, "0298987878", "test@email.com");
	}
	
	@Test
	public void shouldInsertElement() throws ContactException {
		String name = "Jul";
		EasyMock.expect(contactDao.findByName(name)).andReturn(Optional.empty());
		Capture<Contact> capturedContact = EasyMock.newCapture();
		EasyMock.expect(contactDao.save(EasyMock.capture(capturedContact))).andReturn(true);
		
		
		
		EasyMock.replay(contactDao);
				contactService.newContact(name, "0298987878", "test@email.com");

		contactService.newContact(name, "0298987878", "test@email.com");
		Contact value = capturedContact.getValue();
		Assert.assertEquals(name, value.getName());
		Assert.assertEquals("Phone error", "0298987878", value.getPhone());
		Assert.assertEquals("mail error", "test@email.com", value.getEmail());
	}
	
	@Test(expected = ContactNotFoundException.class)
	public void shouldFailDeletionIfNameDoesntExist() throws IllegalArgumentException, ContactNotFoundException{
		String name = "Jul";
		
		EasyMock.expect(contactDao.findByName(name)).andReturn(Optional.empty());
		EasyMock.replay(contactDao);
		contactService.deleteContact(name);
		
		
	}
	
	@Test(expected = ContactNotFoundException.class)
	public void shouldFailUpdateIfNameDoesntExist() throws ContactException, ContactNotFoundException{
		String name = "Jul";
		Contact contact = new Contact();
		contact.setEmail("mail@mail.mail");
		contact.setName("Jul II");
		contact.setPhone("0258457832");
		EasyMock.expect(contactDao.findByName(name)).andReturn(Optional.empty());
		EasyMock.replay(contactDao);
		contactService.updateContact(name, contact);
		
		
	}
}
