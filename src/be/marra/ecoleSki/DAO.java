package be.marra.ecoleSki;

import java.sql.Connection;

public abstract class DAO<T> {
	protected Connection connect = null;
	protected int _Id;
	
	public DAO(Connection conn){
		this.connect = conn;
	}
	
	public abstract boolean create(T obj);
	
	public abstract boolean delete(T obj);
	
	public abstract boolean update(T obj);
	
	public abstract T find(int id);
	
	public int getId(){
		return _Id;
	}
	
	public void setId(int value){
		_Id = value;
	}
}
