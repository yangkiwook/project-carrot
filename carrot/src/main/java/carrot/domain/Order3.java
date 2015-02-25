/* Value Object
 * => Class 문법을 사용하여 사용자 정의 데이터 타입 만들기
 * 
 * 1) Serializable 인터페이스 구현
 *    => SerialVersionUID 스태틱 변수 선언
 * 
 * 2) 인스턴스 변수 선언
 * 
 * 3) setter/getter 생성
 * 
 * 4) 기본 생성자와 파라미터 값을 받는 생성자 선언
 * 
 * 5) equals()/hashCode() 메서드 오버라이딩
 * 
 * 6) toString() 오버라이딩
 */
package carrot.domain;

import java.io.Serializable;
import java.util.Date;

public class Order3 implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int             no;
  protected int             supplierNo;
  protected int             clientNo;
  protected String          goodsCode;
  protected Date            orderDate;
  protected Date            deliveryDate;
  protected Date            endDate;
  protected int             quantity;
  protected String       	delState;
  protected String       	note;
  
  
  
  @Override
	public String toString() {
		return "Order [no=" + no + ", supplierNo=" + supplierNo + ", clientNo="
				+ clientNo + ", goodsCode=" + goodsCode + ", orderDate="
				+ orderDate + ", deliveryDate=" + deliveryDate + ", deliveredDate="
				+ endDate + ", quantity=" + quantity + ", note=" + note
				+ ", delState=" + delState + "]";
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
	public int getClientNo() {
		return clientNo;
	}
	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
  public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDelState() {
		return delState;
	}
	public void setDelState(String delState) {
		this.delState = delState;
	}
  
  
}













