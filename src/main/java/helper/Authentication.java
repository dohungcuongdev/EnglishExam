package helper;

public class Authentication {
	
	public static String getEncryptPassword(String password) {
		try {
			return new Encryption().encrypt(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}
	
	public static String getDecryptPassword(String pwEncryted) {
		try {
			return new Decryption().decrypt(pwEncryted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pwEncryted;
	}
}
