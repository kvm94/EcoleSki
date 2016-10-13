package be.marra.ecoleSki;

public class Accreditation {
	
	/***Enumérations***/
	
	protected enum CategorieEnfantSki{
		PetitSpirou(1),
		Bronze(2),
		Argent(3),
		Or(4),
		Platine(5),
		Diamant(6),
		Competition(7),
		HorsPiste(8);
		
		private final int value; //Représente le niveaux.
		
		private CategorieEnfantSki(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	}
	
	protected enum CategorieEnfantSnowboard{ //A partir de 6 ans.
		nv1(1),
		nv2_nv4(2),
		HorsPiste(3);
		
		private final int value; //Représente le niveaux.
		
		private CategorieEnfantSnowboard(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	}
	
	protected enum CategorieSki{
		nv1_nv4(1),
		HorsPiste(2),
		Competition(3);
		
		private final int value; //Représente le niveaux.
		
		private CategorieSki(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	}
	
	protected enum CategorieSnowboard{
		nv1_nv4(1),
		HorsPiste(2);
		
		private final int value; //Représente le niveaux.
		
		private CategorieSnowboard(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	}
	
	
	//Exemple d'utilisation
	/*public static void test(){
		Object categorie = CategorieEnfantSki.PetitSpirou;
		if(((CategorieEnfantSki)categorie).getValue() < 2)
			System.out.println("Moins que 2");
		else
			System.out.println("Plus ou egual a 2");
	}
	*/
	
	
} 
