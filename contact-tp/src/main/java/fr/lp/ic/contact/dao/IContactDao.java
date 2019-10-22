package fr.lp.ic.contact.dao;

import java.util.List;
import java.util.Optional;

import fr.lp.ic.contact.model.Contact;

/**
 * Interface d'accès aux données de type contact 
 * @author athorel
 *
 */
public interface IContactDao {

	/**
	 * Récupére la liste des contacts 
	 * @return liste des contacts 
	 */
	List<Contact> findAll();
	
	/**
	 * Récupére un contact a partir de son nom 
	 * @param name nom du contact 
	 * @return contact 
	 */
	Optional<Contact> findByName(String name);
	
	/**
	 * Permet de créer un contact 
	 * @param contact contact a créer 
	 * @return <code>true</code> si la création s'est correctement passée 
	 */
	boolean save(Contact contact);

	/**
	 * Permet de mettre à jour un contact 
	 * @param contact contact a mettre à jour 
	 * @return <code>true</code> si la mise à jour s'est correctement passée 
	 */
	boolean update(String name, Contact contact);
	
	/**
	 * Permet de supprimer un contact 
	 * @param name nom du contact a supprimer 
	 * @return <code>true</code> si la supression s'est bien passée
	 */
	boolean delete(String name);
	
}
