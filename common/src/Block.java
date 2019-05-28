import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Block {
	private static final int maxNumTransactions = 1500; // Maximum number of transactions in a block
	private ArrayList<Transaction> transactionList;
	private String blockId;
	private String prevBlockId;
	
	public Block() {
		transactionList = new ArrayList<Transaction>();
		blockId = "";
		prevBlockId = "";
	}
	
	public String getBlockId() {
		return blockId;
	}

	public void setBlockId() {
		// Compute a hash from all transaction IDs and prev transaction IDs in this block
		// TODO - compute the blockID using a Merkle tree
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		for (Transaction t: transactionList) {
			md.update(t.getTid().getBytes());
			md.update(t.getPrevTid().getBytes());
		} 
		
		blockId = new String(md.digest());;
	}
	
	public void setPrevBlockId(String id) {
		prevBlockId = id;
	}

	public boolean isFull() {
		if (transactionList.size() == maxNumTransactions) {
			return true;
		} else {
			return false;
		}
	}
	
	public void addTransaction(Transaction t) {
		transactionList.add(t);
	}

}