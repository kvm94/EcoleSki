package be.marra.ecoleSki;

import java.sql.Time;

public class Cours
{
  private Accreditation.E_Categorie categorie;
  private Accreditation.E_Sport sport;
  private E_Niveaux niveaux;
  private Time heure;
  private double prix;
  private int minEleve;
  private int maxEleve;
  private boolean collectif;
  
  protected static enum E_Niveaux
  {
    PetitSpirou,  Bronze,  Argent,  Or,  Platine,  Diamant,  nv1,  nv1_nv4,  nv2_nv4,  Competition,  HorsPiste;
  }
  
  public boolean checkAccreditation(Accreditation a)
  {
    if ((this.categorie == a.getCat()) && (this.sport == a.getSport())) {
      return true;
    }
    return false;
  }
  
  public void initParticulier() {}
  
  public void initCollectif() {}
  
  public void initPrix() {}
  
  public Accreditation.E_Categorie getCategorie()
  {
    return this.categorie;
  }
  
  public void setCategorie(Accreditation.E_Categorie categorie)
  {
    this.categorie = categorie;
  }
  
  public Accreditation.E_Sport getSport()
  {
    return this.sport;
  }
  
  public void setSport(Accreditation.E_Sport sport)
  {
    this.sport = sport;
  }
  
  public E_Niveaux getNiveaux()
  {
    return this.niveaux;
  }
  
  public void setNiveaux(E_Niveaux niveaux)
  {
    this.niveaux = niveaux;
  }
  
  public Time getHeure()
  {
    return this.heure;
  }
  
  public void setHeure(Time heure)
  {
    this.heure = heure;
  }
  
  public double getPrix()
  {
    return this.prix;
  }
  
  public void setPrix(double prix)
  {
    this.prix = prix;
  }
  
  public int getMinEleve()
  {
    return this.minEleve;
  }
  
  public void setMinEleve(int minEleve)
  {
    this.minEleve = minEleve;
  }
  
  public int getMaxEleve()
  {
    return this.maxEleve;
  }
  
  public void setMaxEleve(int maxEleve)
  {
    this.maxEleve = maxEleve;
  }
  
  public boolean isCollectif()
  {
    return this.collectif;
  }
  
  public void setCollectif(boolean collectif)
  {
    this.collectif = collectif;
  }
}
