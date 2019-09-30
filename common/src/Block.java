import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Block implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Transaction> transactionList;
	private byte[] blockId;
	private byte[] prevBlockId;
	
	public Block() {
		transactionList = new ArrayList<Transaction>(Config.numTransactionsInBlock);
		blockId = null;
		prevBlockId = null;
	}
	
	public byte[] getBlockId() {
		return blockId;
	}
	
	public byte[] getPrevBlockId() {
		return prevBlockId;
	}

	public void setBlockId() {
		// Compute a hash from all transaction IDs and prev transaction IDs in this block
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(prevBlockId);
		for (Transaction t: transactionList) {
			md.update(t.getTid());
			md.update(t.getPrevTid());
		}
		
		blockId = md.digest();
	}
	
	public void setBlockId(byte[] id) {
		blockId = id;
	}
	
	public void setTransactions(ArrayList<Transaction> transactionList) {
		this.transactionList = transactionList;
	}
	
	public void setPrevBlockId(byte[] id) {
		prevBlockId = id;
	}

	public boolean isFull() {
		if (transactionList.size() == Config.numTransactionsInBlock) {
			return true;
		} else {
			return false;
		}
	}
	
	public void addTransaction(Transaction t) {
		transactionList.add(t);
	}
	
	public ArrayList<Transaction> getTransactions() {
		return transactionList;
	}

}
