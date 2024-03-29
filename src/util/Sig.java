package util;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class Sig {
    public static byte[] applyECDSASig(PrivateKey privateKey, String input){
        java.security.Signature dsa;
        byte[] output = new byte[0];
        try {
            dsa = java.security.Signature.getInstance("ECDSA", "BC");
            dsa.initSign(privateKey);

            byte[] strByte = input.getBytes();
            dsa.update(strByte);
            byte[] realSig = dsa.sign();
            output = realSig;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return output;
    }

    public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {
        try {
            java.security.Signature ecdsaVerify = java.security.Signature.getInstance("ECDSA", "BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes());
            return ecdsaVerify.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
