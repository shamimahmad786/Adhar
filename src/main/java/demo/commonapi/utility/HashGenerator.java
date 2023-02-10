/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.commonapi.utility;

import java.security.MessageDigest;

/**
 *
 * @author Chiranjeet
 */
class HashGenerator {

	public byte[] generateSha256Hash(byte[] message) {
		String algorithm = "SHA-256";
		String SECURITY_PROVIDER = "BC";

		byte[] hash = null;

		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(algorithm, SECURITY_PROVIDER);
			digest.reset();
			hash = digest.digest(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hash;
	}

}
