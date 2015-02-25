package carrot.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import carrot.dao.DeliveryDao;

/* Service 컴포넌트의 역할
 * => 비즈니스 로직 수행
 * => 트랜잭션 관리
 */

@Service
public class DeliveryService {
	@Autowired
	DeliveryDao deliveryDao;



	public int getMaxPageNo(int pageSize,int sno) {
		int totalSize = deliveryDao.totalSize(sno);
		int maxPageNo = totalSize / pageSize;
		if ((totalSize % pageSize) > 0)
			maxPageNo++;

		return maxPageNo;
	}



	public List<?> getList2(int pageNo, int pageSize,int sno) {
		
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("startIndex", ((pageNo - 1) * pageSize));
		paramMap.put("pageSize", pageSize);
		paramMap.put("sno", sno);

		
		return deliveryDao.selectList2(paramMap);
	}





}
