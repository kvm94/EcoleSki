package be.marra.ecoleSki;

public class Client
  extends Personne
{
  private Panier pan;
  
  public Client(String nom, String prenom, int jour, int mois, int annee)
  {
    super(nom, prenom, jour, mois, annee);
    this.pan = new Panier();
  }
  
  public void viderPanier() {}
  
  public Panier getPan()
  {
    return this.pan;
  }
  
  public void setPan(Panier pan)
  {
    this.pan = pan;
  }
}
