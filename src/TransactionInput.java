public class TransactionInput {
    public String transactionOutputId;
    public TransactionOutput UTXO;

    public TransactionInput(String TransactionOutputId){
        this.transactionOutputId = TransactionOutputId;
    }
}
