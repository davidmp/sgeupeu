package sge.util;

 
import java.security.*;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



public class STRCrypto {
	
	KeySpec keySpec;
	
	public SecretKey getKeyString() {
		String keyString="ACED00057372001E636F6D2E73756E2E63727970746F2E70726F76696465722E4445534B65796B349C35DA1568980200015B00036B65797400025B427870757200025B42ACF317F8060854E00200007870000000086438D0196107EFF1";
		try{
			byte[] keyBytes = keyString.getBytes("UTF8");
			keySpec = new DESedeKeySpec( keyBytes );
			SecretKeyFactory keyFactory;
			keyFactory = SecretKeyFactory.getInstance( "DESede" );
			SecretKey key = keyFactory.generateSecret( keySpec );
			return key;
		}catch (Exception e) {
			System.out.println("could not read private key: "+e);
			return null;
		}
	}
	
	private static String bytesToString( byte[] bytes )	{
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++)
		{
			stringBuffer.append( (char) bytes[i] );
		}
		return stringBuffer.toString();
	}
	
	public String encrypt(String texto) throws Exception{
		try{
			Security.addProvider( new com.sun.crypto.provider.SunJCE() );
			Key key = getKeyString();
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);
	
			BASE64Encoder encoder = new BASE64Encoder();
			byte[] cleartext = texto.getBytes("UTF8");
			byte[] ciphertext = cipher.doFinal(cleartext);
	
			return encoder.encode(ciphertext).toString();
		}catch (Exception e) {
			throw new Exception( e.getMessage() );
		}
	}

	public String decrypt(String texto) throws Exception{
		try{
			Security.addProvider( new com.sun.crypto.provider.SunJCE() );
			Key key = getKeyString();
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.DECRYPT_MODE, key);
			
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] cleartext = decoder.decodeBuffer(texto);
			byte[] ciphertext = cipher.doFinal(cleartext);
			
			return bytesToString(ciphertext);
		}catch (Exception e) {
			throw new Exception( e.getMessage() );
		}
	}

	public static void main(String[] args) {
		System.out.println("Sull crypto ********************************");
		STRCrypto cryp=new STRCrypto();
		try{
			                 System.out.println("VER:"+cryp.encrypt("123456"));
			                 System.out.println("VER:"+cryp.decrypt("/638BxpK2xdv+wPtYPa8CQ=="));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
