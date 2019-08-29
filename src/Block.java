import java.util.Date;
import util.*;
/*Block structure:
* String hash: 当前区块的哈希值，通过前一个区块的哈希值+当前区块的数据计算出来的
* String previousHash： 前一区块的哈希值
*
*/
public class Block {
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
        String needHash = previousHash + Long.toString(timeStamp) + data;
        String calculatedHash = GetHash.getHashSHA256(needHash);
        return calculatedHash;
    }
}
