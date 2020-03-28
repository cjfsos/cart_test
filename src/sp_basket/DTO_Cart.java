package sp_basket;

public class DTO_Cart {
	private String mID;
	private String mName;
	private String obName;
	private String ea; 
	private String price;
	private String sum;
	
	public String getmID() {
		return mID;
	}
	public void setmID(String mID) {
		this.mID = mID;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getObName() {
		return obName;
	}
	public void setObName(String obName) {
		this.obName = obName;
	}
	public String getEa() {
		return ea;
	}
	public void setEa(String ea) {
		this.ea = ea;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public void setSum(String string) {
		this.sum = string;
	}	
	
	public String[] getArray() {
		String[] returnData = new String[6];
		returnData[0]=this.mID;
		returnData[1]=this.mName;
		returnData[2]=this.obName;
		returnData[3]=this.ea;		
		returnData[4]=this.price;
		returnData[5]=this.sum;
		
		return returnData;
	}
}