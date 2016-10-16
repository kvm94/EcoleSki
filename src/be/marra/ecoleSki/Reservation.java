package be.marra.ecoleSki;

import java.sql.Time;

public class Reservation
{
  private E_Statut statut;
  private Time heure;
  private double prix;
  private Eleve eleve;
  private Cours cours;
  private Semaine semaine;
  
  protected static enum E_Statut
  {
    Paye,  Reserve;
  }
  
  public Reservation(E_Statut statut, Time heure, double prix, Eleve eleve, Cours cours, Semaine semaine)
  {
    this.statut = statut;
    this.heure = heure;
    this.prix = prix;
    this.eleve = eleve;
    this.cours = cours;
    this.semaine = semaine;
  }
  
  public void appliquerReduction() {}
  
  public void afficher() {}
  
  public E_Statut getStatut()
  {
    return this.statut;
  }
  
  public void setStatut(E_Statut statut)
  {
    this.statut = statut;
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
}
