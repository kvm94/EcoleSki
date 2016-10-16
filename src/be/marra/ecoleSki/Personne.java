package be.marra.ecoleSki;

import java.util.Date;

@SuppressWarnings("deprecation")
public abstract class Personne
{
  private String nom;
  private String prenom;
  private Date dateNaissance;
  
  
public Personne(String nom, String prenom, int jour, int mois, int annee)
  {
    this.nom = nom;
    this.prenom = prenom;
    this.dateNaissance = new Date(annee, mois, jour);
  }
  

public int age()
  {
    Date now = new Date();
    int age = now.getYear() - this.dateNaissance.getYear();
    if ((now.getMonth()  == this.dateNaissance.getMonth()) || ((now.getMonth() == this.dateNaissance.getMonth()) && (now.getDay() == this.dateNaissance.getDay()))) {
      age--;
    }
    return age;
  }
  
  public String getNom()
  {
    return this.nom;
  }
  
  public void setNom(String nom)
  {
    this.nom = nom;
  }
  
  public String getPrenom()
  {
    return this.prenom;
  }
  
  public void setPrenom(String prenom)
  {
    this.prenom = prenom;
  }
  
  public Date getDateNaissance()
  {
    return this.dateNaissance;
  }
  
  public void setDateNaissance(Date dateNaissance)
  {
    this.dateNaissance = dateNaissance;
  }
}
