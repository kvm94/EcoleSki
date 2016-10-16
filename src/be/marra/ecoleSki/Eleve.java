package be.marra.ecoleSki;

public class Eleve
  extends Personne
{
  private boolean assurance;
  
  public Eleve(String nom, String prenom, int jour, int mois, int annee)
  {
    super(nom, prenom, jour, mois, annee);
    this.assurance = false;
  }
  
  public Eleve(String nom, String prenom, int jour, int mois, int annee, boolean assurance)
  {
    super(nom, prenom, jour, mois, annee);
    this.assurance = assurance;
  }
  
  public boolean isAssurance()
  {
    return this.assurance;
  }
  
  public void setAssurance(boolean assurance)
  {
    this.assurance = assurance;
  }
}
