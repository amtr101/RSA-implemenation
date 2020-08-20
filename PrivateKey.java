/*********************************
 * Amy Trevaskis: 15129275
 * PrivateKey class: used to store
 * n and d for the decrpytion 
 * *************************/ 

public class PrivateKey
{
	private int d; 
	private int n; 

	//default constructor
	public PrivateKey()
	{

	} 

	//constructor with class feilds
	public PrivateKey(int inN, int inD) 
	{
		d = inD; 
		n = inN; 
		
	} 

	//setters
	public void setN(int N)
	{
		n = N; 
	} 

	public void setD(int D)
	{
		d = D; 
	} 

	//getters 

	public int getN()
	{
	 
		return n; 
	} 

	public int getD()
	{
		return d; 
	} 

	


} 
