public class Crypt 
{

	private String fileContent = "thisisasimpleexampleencryptedwiththekeyred";
	
	private char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	private char[] britishFreq = {'e','t','a','o','i','n','s','h','r','d','l','c','u','m','w','f','g','y','p','b','v','k','j','x','q','z'};
	
	
	
	
	public String encrypt(String key)
	{
		int[] keyNumbers = this.getIntArray(key);
		int[] messageNumbers = this.getIntArray(this.fileContent);
		char[] encryptedArray = new char[messageNumbers.length];
		
		for (int i = 0; i<messageNumbers.length;i++)
		{
			encryptedArray[i] = this.alphabet[(messageNumbers[i]+keyNumbers[i%key.length()])%26];
		}
		return new String(encryptedArray);
	}
	
	
	public String decrypt(String key, String message)
	{
		int[] keyNumbers = this.getIntArray(key);
		int[] messageNumbers = this.getIntArray(message);
		char[] decryptedArray = new char[messageNumbers.length];
		
		for (int i = 0; i<messageNumbers.length;i++)
		{
			int ratata = messageNumbers[i] - keyNumbers[i%key.length()];
			
			if (ratata < 0)
			{
				ratata = 26 + ratata;
			}
			
			decryptedArray[i] = this.alphabet[ratata];
		}
		return new String(decryptedArray);
	}
	

	public String crackYourButt(String message, int keyLength)
	{
		char[][] messages = new char[(message.length()/keyLength)+1][keyLength];
	
		int i = 0;
		
		char[] messageArray = message.toCharArray();
		for(char c : messageArray)
		{
			for (int j = 0; j<(message.length()/keyLength);j++)
			{
				if (messages[i][j] != 0)
				{
					messages[i][j] = c;
					i++;
					if (i>keyLength)
					{
						i=0;
					}
				}
			}
		}
		for (int k = 0; k<keyLength ; k++)
		{
			messages[k] = this.crackYourMessage(new String(messages[k]));
		}
		
		char[] crackedMessage = new char[message.length()];
		for (int yolo = 0; yolo<keyLength ; yolo++)
		{
			crackedMessage[yolo] = messages[yolo/keyLength][yolo%keyLength];
		}
		
		return new String(crackedMessage);
	}
	
	
	public char[] crackYourMessage(String message)
	{
		char[] correspondances = this.crackCorrespondance(message);
		
		char[] messageArray = message.toCharArray();
		
		char[] decryptedArray = new char[message.length()];
		
		for (int i = 0; i<message.length();i++)
		{
			decryptedArray[i] = correspondances[messageArray[i]-97];
		}
		
		return decryptedArray;
	}
	
	
	public char[] crackCorrespondance(String message)
	{
		char[] messageArray = message.toCharArray();
		int[] messageStats = new int[26];
		for (char c : messageArray)
		{
			messageStats[c-97]++;
		}
			
		char[] charCorrespondance = new char[26];
		
		for(int i = 0; i< 26;i++)
		{
			int maxIndice = getMaxIndice(messageStats);
			messageStats[maxIndice] = -1;
			
			charCorrespondance[maxIndice] = this.britishFreq[i];
		}
		
		
		return charCorrespondance;
	}
	
	private int getMaxIndice(int[] array)
	{
		int maxIndice = 0;
		int maxValue = array[0];
		
		for (int i = 1; i<array.length;i++)
		{
			if (array[i] > maxValue)
			{
				maxIndice = i;
				maxValue = array[i];
			}
		}
		return maxIndice;
	}
	

	/**
	 * Dirty le contrat de confiance
	 * @param message
	 * @return
	 */
	private int[] getIntArray(String message)
	{
		char[] ch  = message.toCharArray();
		int[] ar = new int[ch.length];
		int i = 0;
		for(char c : ch)
		{
			int temp = (int)c;
			if(temp<=122 & temp>=97)
			{
				ar[i++] = temp-97;
			}
			else
			{
				System.out.println("wowowow");
			}
		}
			
		return ar;
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Crypt crypto = new Crypt();
		String encrypted = crypto.encrypt("red");
		System.out.println(encrypted);
		System.out.println(crypto.decrypt("red",encrypted));
		
		System.out.println(crypto.crackYourButt("HHFWEZHNZIDVCKTLFAGIZGIGYYZUKXRWHYAOCMWMTOAWICSHTYABIOEWDYSVKHIMWDOMSWFCOCRVKVQJVFNARIFUKSMBIJAHMNZIYSNGTYWWCHVBVQETCMLITIFSXLDLAHLRUJIKHILLLQIEF"+
		"HHCFAOWWZSBFPSPPOHLLHAFWXMCLKSAVCKWPRYGJVILCRVKHOSYZXWWASYEHOWGVMKGFSCAVCKJVFNARIFUKAOCVQEZSUDHJCGHEJHQWPSFULMMWYVFFZUOEURBWVHZXJCKWXXSYTYAXDWNZLPGFAJLOFDXOCMWLPGFSRKGQZMJVBWWACIVWLW"+
				"TWHVTNGXOSWGRXIYJSYOASMUGNLLLKBAGOOGGMLHSUSTDYLIUHQSWNOAWHIMIMVRWHYAOWWZLHRLWXBFHVXVHBWMUVYJMAOHUIVTZGVAWHTVHGBSHOSVWIUJUFUBWMZIYOMTCAVYKETSWGZLBUFXHBXUEYFCSKLCZLLLOLLMJZYVIZWAFHOWMXISZNGLHAFWXUCQKMYMIMRNTIJXPBVJEZCZMRPAJJSCSXEIAHFWLVHUFHMIFDLHHBARAVYKOPFNKSMBIJAHMBWVLOHVXOSLWWOOLCHBDUDMZHIXPHKFWWZFYKSSINWWMCLXSVRUFHKWYLXVGIEILBNWVWFCKIAVULLHHBSWACGSGOWHLAOWWZMZBIGXOSLSWPHXGXOKYDPHDJWEYIHLSVILKXHHYTYAHIJIJCPWVVTOKFFGNJSUUBSRKOHVXLFGKGVAJMPZONGVFHBGWLTIJIZOCVPHBXKWVPSZMZTULLLFFGWAOHVXOWMAXHYYAXPGNZITOCFQVHCNIVTIMVWFYHEYONASUGNZIZCOJGLCZLLPGIMVDONULHBXLLLQBAIMVYSHVTNZMZDIKXOOMLIHBXJSTOAWMUHBWPHBX",5));
	}
		
		
		
		

}
