package cmpt371project1;

import java.security.MessageDigest;
import java.util.zip.CRC32;

public class Hash {
	
	public static int KEY_LENGTH = 32;
	
	//Produce hash of ID as Key
	
	public static byte[] hash(String identifier) {
		CRC32 crc32 = new CRC32();
		crc32.reset();
		crc32.update(identifier.getBytes());
		long code = crc32.getValue();
		code &= (0xffffffffffffffffL >>> (64 - KEY_LENGTH));
		byte[] value = new byte[KEY_LENGTH / 8];
		for (int i = 0; i < value.length; i++) {
			value[value.length - i - 1] = (byte) ((code >> 8 * i) & 0xff);
		}
		return value;
	}
}
