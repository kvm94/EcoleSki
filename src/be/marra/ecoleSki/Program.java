package be.marra.ecoleSki;

import be.marra.ecoleSki.Accreditation.E_Categorie;
import be.marra.ecoleSki.Accreditation.E_Sport;

public class Program
{
	public static void main(String[] args)
	{
		//Connexion à la base de données et implémentation DAO.
		
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		
		//Test DAO.
		DAO<Accreditation> accreditationDAO = adf.getAccreditationDAO();
		
		//OK insert
		/*Accreditation a = new Accreditation(E_Categorie.Adulte, E_Sport.Telemark);
		accreditationDAO.create(a);*/
		
		//OK delete
		/*Accreditation b = new Accreditation(1, E_Categorie.Adulte, E_Sport.Telemark);
		accreditationDAO.delete(b);*/
		
		//OK select
		/*Accreditation c = accreditationDAO.find(3);
		System.out.println(c.getCat());
		System.out.println(c.getSport());*/
		
		//OK update 
		/*c.setSport(E_Sport.SkiFond);
		accreditationDAO.update(c);
		accreditationDAO.find(c.getId());
		System.out.println("UPDATED");
		System.out.println(c.getCat());
		System.out.println(c.getSport());*/
	}
}
