import java.security.*;
import java.security.spec.ECGenParameterSpec;

/* 生成公私钥对
* 该类的作用是生成一个公私钥对
* */
public class Wallet {
    public PrivateKey privateKey;
    public PublicKey publicKey;

    public Wallet() {
        generateKeys();
    }

    public void generateKeys() {
        try{
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

            keyGen.initialize(ecSpec, random);
            KeyPair keyPair = keyGen.generateKeyPair();

            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
