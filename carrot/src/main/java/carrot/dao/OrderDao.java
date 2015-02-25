package carrot.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import carrot.domain.Order3;

/* myBatis에서 DAO 클래스를 만들 때 
 * 다음 인터페이스에 선언된 규칙에 따라 만들 것이다.
 * 따라서 인터페이스 선언시 약간의 규칙을 따라야 한다.
 * 
 * 규칙:
 * => 파라미터는 하나여야 한다. 또한 맵퍼 파일의 파라미터 타입과 일치해야 한다.
 * => 메서드 이름은 SQL 아이디와 같아야 한다.
 * => 인터페이스 패키지도 SQL 맵퍼 파일의 네임스페이스와 같아야 한다. 
 */
public interface OrderDao {
  void delete(int no);
  List<?> selectList(Map<String,Object> params);
  //List<?> selectList(Map<String,Object> params);
  void insert(Order3 order);
  int totalSize(int supplierNo);
  int orderTotalSize(int supplierNo);
  List<?> selectMyOrderList(Map<String,Object> params);
  List<?> selectList2(HashMap<String, Object> params);
  List<?> selectList_order(Map<String,Object> params);
}


















