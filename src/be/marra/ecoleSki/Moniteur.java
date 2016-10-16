package be.marra.ecoleSki;

public class Moniteur
  extends Personne
{
  private Accreditation acre;
  
  public Moniteur(String nom, String prenom, int jour, int mois, int annee, Accreditation acre)
  {
    super(nom, prenom, jour, mois, annee);
    this.acre = acre;
  }
  
  public Accreditation getAcre()
  {
    return this.acre;
  }
  
  public void setAcre(Accreditation acre)
  {
    this.acre = acre;
  }
}
