import java.security.*;
import java.util.ArrayList;
import util.GetKey;
import util.GetHash;

public class Transaction {
    public String transactionID;
    public PublicKey sender;
    public PublicKey receiver;
    public float value;
    public byte[] signature;

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    private static int sequence = 0;

    /*构造函数
    * */
    public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs){
        this.sender = from;
        this.receiver = to;
        this.value = value;
        this.inputs = inputs;
    }

    private String calculateHash(){
        sequence++;
        String needHash = GetKey.getStringFromKey(sender)
                + GetKey.getStringFromKey(receiver)
                + Float.toString(value)
                + sequence;
        return GetHash.getHashSHA256(needHash);
    }

    public void generateSignature(PrivateKey privateKey) {
        String data = util.GetKey.getStringFromKey(sender) + util.GetKey.getStringFromKey(receiver) + Float.toString(value);
        signature = util.GetSignature.applyECDSASig(privateKey, data);
    }

    public boolean verifySignature() {
        String data = util.GetKey.getStringFromKey(sender) + util.GetKey.getStringFromKey(receiver) + Float.toString(value);
        return util.GetSignature.verifyECDSASig(sender, data, signature);
    }

}

class TransactionInput {

}

class TransactionOutput {

}
