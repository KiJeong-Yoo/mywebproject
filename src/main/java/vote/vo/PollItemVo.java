package vote.vo;

public class PollItemVo {
	private int listnum;
	private int itemnum;
	private String[] item;
	private int count;
	
	public PollItemVo(String[] item2) {
		this.item = item2;
	}
	
	public PollItemVo() {
		// TODO Auto-generated constructor stub
	}

	public int getListnum() {
		return listnum;
	}
	
	public void setListnum(int listnum) {
		this.listnum = listnum;
	}
	
	public int getItemnum() {
		return itemnum;
	}
	
	public void setItemnum(int itemnum) {
		this.itemnum = itemnum;
	}
	
	public String[] getItem() {
		return item;
	}
	
	public void setItem(String[] item) {
		this.item = item;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
