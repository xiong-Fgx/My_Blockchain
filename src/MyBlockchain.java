public class MyBlockchain {
    static public void main(String[] args) {
        Block firstBlock = new Block("this is the first block", "0");

        Block secondBlock = new Block("this is the second block", firstBlock.hash);

        Block thirdBlock = new Block("this is the third block", secondBlock.hash);

        System.out.println("This is the first block's hashcode: " + firstBlock.hash);
        System.out.println("This is the second block's hashcode: " + secondBlock.hash);
        System.out.println("This is the third block's hashcode: " + thirdBlock.hash);
    }
}
