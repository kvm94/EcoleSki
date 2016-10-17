package be.marra.ecoleSki;

public class Program
{
	public static void main(String[] args)
	{
		Database db = new Database("database.db");
		if (db.connect()) {
			System.out.println("Connection done!");
		}
		if (db.disconnect()) {
			System.out.println("Disconnection done!");
		}
	}
}
