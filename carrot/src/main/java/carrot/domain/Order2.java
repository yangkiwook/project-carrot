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

public class Order2 implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int gno;
	protected int sno;
	protected String oname;
	protected String gname;
	protected String gcat;
	protected String gunit;
	protected int oqty;
	protected Date oddate;
	protected Date oodate;
	protected String gmemo;
	protected int oprice;
	
	public int getGno() {
		return gno;
	}
	public void setGno(int gno) {
		this.gno = gno;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getGcat() {
		return gcat;
	}
	public void setGcat(String gcat) {
		this.gcat = gcat;
	}
	public String getGunit() {
		return gunit;
	}
	public void setGunit(String gunit) {
		this.gunit = gunit;
	}
	public int getOqty() {
		return oqty;
	}
	public void setOqty(int oqty) {
		this.oqty = oqty;
	}
	public Date getOddate() {
		return oddate;
	}
	public void setOddate(Date oddate) {
		this.oddate = oddate;
	}
	public Date getOodate() {
		return oodate;
	}
	public void setOodate(Date oodate) {
		this.oodate = oodate;
	}
	public String getGmemo() {
		return gmemo;
	}
	public void setGmemo(String gmemo) {
		this.gmemo = gmemo;
	}
	public int getOprice() {
		return oprice;
	}
	public void setOprice(int oprice) {
		this.oprice = oprice;
	}
	
	
}