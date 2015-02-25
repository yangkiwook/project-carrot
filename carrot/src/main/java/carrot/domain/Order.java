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

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int cno;
	protected int sno;
	protected String oname;
	protected String ograde;
	protected Date oddate;
	protected Date oodate;
	protected int oprice;
	protected String monthsum;
	protected String del_stat;
	
	protected String gcode;
	protected String gunit;
	protected String omemo;

	protected int ocode;
	protected int gprice_a;
	protected int gprice_b;
	protected int gprice_c;
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
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
	public String getOgrade() {
		return ograde;
	}
	public void setOgrade(String ograde) {
		this.ograde = ograde;
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
	public int getOprice() {
		return oprice;
	}
	public void setOprice(int oprice) {
		this.oprice = oprice;
	}
	public String getMonthsum() {
		return monthsum;
	}
	public void setMonthsum(String monthsum) {
		this.monthsum = monthsum;
	}
	public String getDel_stat() {
		return del_stat;
	}
	public void setDel_stat(String del_stat) {
		this.del_stat = del_stat;
	}
	public String getGcode() {
		return gcode;
	}
	public void setGcode(String gcode) {
		this.gcode = gcode;
	}
	public String getGunit() {
		return gunit;
	}
	public void setGunit(String gunit) {
		this.gunit = gunit;
	}
	public String getOmemo() {
		return omemo;
	}
	public void setOmemo(String omemo) {
		this.omemo = omemo;
	}
	public int getOcode() {
		return ocode;
	}
	public void setOcode(int ocode) {
		this.ocode = ocode;
	}
	public int getGprice_a() {
		return gprice_a;
	}
	public void setGprice_a(int gprice_a) {
		this.gprice_a = gprice_a;
	}
	public int getGprice_b() {
		return gprice_b;
	}
	public void setGprice_b(int gprice_b) {
		this.gprice_b = gprice_b;
	}
	public int getGprice_c() {
		return gprice_c;
	}
	public void setGprice_c(int gprice_c) {
		this.gprice_c = gprice_c;
	}
	@Override
	public String toString() {
		return "Order [cno=" + cno + ", sno=" + sno + ", oname=" + oname
				+ ", ograde=" + ograde + ", oddate=" + oddate + ", oodate="
				+ oodate + ", oprice=" + oprice + ", monthsum=" + monthsum
				+ ", del_stat=" + del_stat + ", gcode=" + gcode + ", gunit="
				+ gunit + ", omemo=" + omemo + ", ocode=" + ocode
				+ ", gprice_a=" + gprice_a + ", gprice_b=" + gprice_b
				+ ", gprice_c=" + gprice_c + "]";
	}
	

}