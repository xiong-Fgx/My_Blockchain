import util.Key2String;

import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;

public class MyBlockchain {
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>();
    public static int difficulty = 4;
    public static Wallet walletA;
    public static Wallet walletB;
    public static float minimumTX = 0.01f;

    /*用于判断当前的区块链是否有异常
    * 使用从头到尾的遍历，依次判断每个节点是否合法
    * */
    public static boolean IsValid() {
        Block previousBlock;
        Block currentBlock;
        for(int i = 1; i < blockchain.size(); i++) {
            previousBlock = blockchain.get(i-1);
            currentBlock = blockchain.get(i);
            String mineTarget = new String(new char[difficulty]).replace('\0', '0');
            //当前区块的哈希值与当前区块计算出来的哈希值不相等，表示异常
            if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("error! current hash is wrong or data has been modified");
                return false;
            }
            //前一区块的哈希值和当前区块所记录的上衣区块哈希值不相等，表示异常
            if(!previousBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("error! current block records different hash with previous");
                return false;
            }
            if(!currentBlock.hash.substring(0,difficulty).equals(mineTarget)){
                System.out.println("error! current block has not been mined");
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        /* Version  1
//        * 下面的主函数执行流程表示使用一个gson数据结构来存储区块链，单纯的生成区块，没有挖矿这一说
//        * */
//        blockchain.add(new Block("this is the first block", "0"));
//        blockchain.add(new Block("this is the first block", blockchain.get(blockchain.size()-1).hash));
//        blockchain.add(new Block("this is the first block", blockchain.get(blockchain.size()-1).hash));
//        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
//        System.out.println(blockchainJson);

//        /* Version  2
//        * 下面的主函数执行的流程是一个带有挖矿功能的区块生成
//        * */
//        blockchain.add(new Block("This is the 1st block", "0"));
//        System.out.println("mining block 1");
//        blockchain.get(0).mineBlock(difficulty);
//
//        blockchain.add(new Block("This is the 2nd block", blockchain.get(0).hash));
//        System.out.println("mining block 2");
//        blockchain.get(1).mineBlock(difficulty);
//
//        blockchain.add(new Block("This is the 3rd block", blockchain.get(1).hash));
//        System.out.println("mining block 3");
//        blockchain.get(2).mineBlock(difficulty);
//
//        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
//        System.out.println(blockchainJson);

        //设置bouncey castle作为安全的提供者
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        //创建两个钱包
        walletA = new Wallet();
        walletB = new Wallet();

        System.out.println("A's private key: ");
        System.out.println(Key2String.getStringFromKey(walletA.privateKey));
        System.out.println("A's public key: ");
        System.out.println(Key2String.getStringFromKey(walletA.publicKey));

        Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
        transaction.generateSignature(walletA.privateKey);

        System.out.println("is signature verified? ");
        System.out.println(transaction.verifySignature());




    }
}


