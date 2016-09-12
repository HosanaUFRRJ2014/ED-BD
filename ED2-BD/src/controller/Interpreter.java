package controller;

public interface Interpreter
{	
	public abstract Interpreter open();
	public abstract Interpreter next();
	public abstract Interpreter close();

}
