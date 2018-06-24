public class TransactionInput {
    private String transactionOutputId; //Reference to TransactionOutputs -> transactionId
    private TransactionOutput UTXO; //container Unspent Transaction Output

    public TransactionInput(String transactionOutputId) {
        this.transactionOutputId = transactionOutputId;
    }

    public String getTransactionOutputId() {
        return transactionOutputId;
    }

    public TransactionOutput getUTXO() {
        return UTXO;
    }

}
