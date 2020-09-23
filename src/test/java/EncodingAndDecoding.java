import java.util.Base64;

public class EncodingAndDecoding {
	public static String encryptXOR(String message) {
		byte[] enryptedMessage = Base64.getEncoder().encode(message.getBytes());
		String encryptedString = new String(enryptedMessage);
		System.out.println("Message is:" + message + " Encrypted message is:" +encryptedString);
		return encryptedString;
	}
	
	public static void main(String[] args) {
		encryptXOR("Gowtham");
		decryptXOR("R293dGhhbQ==");
	}
	
	public static String decryptXOR(String encryptedMessage) {
		byte[] decryptedMessage= Base64.getDecoder().decode(encryptedMessage);
		String decryptedString = new String(decryptedMessage);
		System.out.println("Encrypted Message is:"+encryptedMessage+" | "+"Decrypted Message is :"+decryptedString);
		return decryptedString;
	}
}
