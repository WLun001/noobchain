import com.google.gson.GsonBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.util.ArrayList;

/* The first block is called the genesis block,
      and because there is no previous block we will just enter “0” as the previous hash.
      */
public class Noobchain {
    public static int difficulty = 5;
    private static ArrayList<Block> blockchain;
    public static Wallet walletA;
    public static Wallet walletB;

    public static void main(String[] args) {
//        blockchain = new ArrayList<>();
//        blockchain.add(new Block("first block", "0"));
//        System.out.println("Trying to Mine block 1... ");
//        blockchain.get(0).mineBlock(difficulty);
//
//        blockchain.add(new Block("second block",blockchain.get(blockchain.size()-1).getHash()));
//        print("Trying to Mine block 2... ");
//        blockchain.get(1).mineBlock(difficulty);
//
//        blockchain.add(new Block("third block",blockchain.get(blockchain.size()-1).getHash()));
//        print("Trying to Mine block 3... ");
//        blockchain.get(2).mineBlock(difficulty);
//
//        print("\nBlockchain is Valid: " + isChainValid());
//
//        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
//        print("\nThe block chain: ");
//        print(blockchainJson);

        //setup Bouncey castle as security provider
        Security.addProvider(new BouncyCastleProvider());

        walletA = new Wallet();
        walletB = new Wallet();

        print("Private and public keys:");
        print(StringUtil.getStringFromKey(walletA.getPrivateKey()));
        print(StringUtil.getStringFromKey(walletA.getPublicKey()));

        //create test transaction from walletA to walletB
        Transaction transaction = new Transaction(walletA.getPublicKey(), walletB.getPublicKey(), 5, null);
        transaction.generateSignature(walletA.getPrivateKey());
        print("Is signature verified");
        print(transaction.verifySignature() + "");

    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            //compare registered hash and calculated hash:
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                print("current hash not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                print("previous hash not equal");
                return false;
            }

            //check if hash is solved
            if(!currentBlock.getHash().substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
