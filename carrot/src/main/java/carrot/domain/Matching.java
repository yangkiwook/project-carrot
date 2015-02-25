package carrot.domain;

import java.io.Serializable;
import java.util.Date;

public class Matching implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int no;
	protected int supplierNo;
	protected int mLevel;
	protected Date mDate;
	
	@Override
	public String toString() {
		return "Matching [no=" + no + ", supplierNo=" + supplierNo
				+ ", mLevel=" + mLevel + ", mDate=" + mDate + "]";
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(int supplierNo) {
		this.supplierNo = supplierNo;
	}

	public int getmLevel() {
		return mLevel;
	}

	public void setmLevel(int mLevel) {
		this.mLevel = mLevel;
	}

	public Date getmDate() {
		return mDate;
	}

	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}
	
	
	
}