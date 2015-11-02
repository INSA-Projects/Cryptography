import java.math.BigInteger;
import java.util.Random;


public class Main 
{
	private Random r = new Random();
	public BigInteger e = new BigInteger("65537");
	
	public BigInteger generatePrime(int l)
	{
		return BigInteger.probablePrime(l, this.r);
	}
	
	public BigInteger generateN(BigInteger p, BigInteger q)
	{
		return p.multiply(q);
	}
	
	public BigInteger generateD(BigInteger p, BigInteger q)
	{
		BigInteger one = new BigInteger("1");
		
		BigInteger produit = (p.subtract(one).multiply(q.subtract(one)));
		System.out.println("(p-1)(q-1) : " + produit);
		BigInteger d = e.modInverse(produit);
		
		
		return d;
	}
	
	public BigInteger calculateMemodN(BigInteger e, BigInteger n, BigInteger m)
	{
		return m.modPow(e, n);
	}
	
	public BigInteger code(String s)
	{
		BigInteger result = BigInteger.ZERO;
		s = s.toLowerCase();
		char[] array = s.toCharArray();
		for(char c : array)
		{
			result = result.multiply(new BigInteger("100")).add(new BigInteger((new Integer(c-87)).toString()));
		}
		return result;
	}
	
	public String decode(BigInteger b)
	{
		String result = "";
		BigInteger cent = new BigInteger("100");
		while(b.divide(cent).compareTo(BigInteger.ZERO) > 0)
		{
			BigInteger val = b.subtract (b.divide(cent).multiply(cent));
			char c = (char)(val.floatValue() + 87);
			result = c + result;
			b = b.divide(cent);
		}		
		char c = (char)(b.floatValue() + 87);
		result = c + result;
		return result;
	}
	
	public String RSAenc(BigInteger e, BigInteger n, String s)
	{
		BigInteger message = this.code(s);
		return decode(message.modPow(e, n));		
	}
	
	public BigInteger RSAdec(BigInteger d, BigInteger n, String m)
	{
		BigInteger cfini = BigInteger.ZERO;
		return cfini;
	}
	
	
	public static void main (String[] args)
	{
		Main main = new Main();
		System.out.println("C fini lé vakans");
		/*
		BigInteger p = main.generatePrime(64);
		BigInteger q = main.generatePrime(64);
		BigInteger d = main.generateD(p, q);
		System.out.println("e * d: " + main.e.multiply(d));
		*/
		
		System.out.println(main.code("bonjour"));
		System.out.println(main.decode(main.code("commentcava")));
		

		System.out.println("C fini lé vakans");
	}

}
;