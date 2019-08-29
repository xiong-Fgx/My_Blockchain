import java.util.Date;

public class Block {
    /*Block structure:
     * String hash: 当前区块的哈希值，通过前一个区块的哈希值+当前区块的数据计算出来的
     * String previousHash： 前一区块的哈希值
     * String data: 表示该区块中存的内容
     * long timeStamp: 时间戳
     */
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;

    public Block (String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        /* 计算哈希值
        * 需要根据上一个区块的哈希值+时间戳+当前区块的数据来计算出一个SHA256哈希值
        * */
        String needHash = previousHash + Long.toString(timeStamp) + data;
        String calculatedHash = util.GetHash.getHashSHA256(needHash);
        return calculatedHash;
    }
}
