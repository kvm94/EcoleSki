package be.marra.ecoleSki;

public class Program
{
	public static void main(String[] args)
	{
		//Connexion à la base de données et implémentation DAO.
		
		//AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		
		//Test AccreditationDAO.
		//DAO<Accreditation> accreditationDAO = adf.getAccreditationDAO();
		
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
		
		//Test ClientDAO
		//DAO<Client> clientDAO = adf.getClientDAO();
		
		//OK Insert
		/*Client a = new Client("Marra", "Kevin", 11,6,1994);
		a.setPasswd("test");
		clientDAO.create(a);*/
		
		//OK select
		/*Client b = clientDAO.find(2);
		System.out.println(b.getNom());
		System.out.println(b.getPrenom());
		System.out.println(b.getDateNaissance().getDayOfMonth() + "/" + b.getDateNaissance().getMonth() + "/" + b.getDateNaissance().getYear());
		System.out.println(b.age() + " ans!");*/
		
		// OK delete
		//clientDAO.delete(b);
		
	}
}
