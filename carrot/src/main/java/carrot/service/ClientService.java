package carrot.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import carrot.dao.ClientDao;
import carrot.domain.Client;

/* Service 컴포넌트의 역할
 * => 비즈니스 로직 수행
 * => 트랜잭션 관리
 */

@Service
public class ClientService {
	@Autowired
	ClientDao clientDao;

	public List<?> getList(int pageNo, int pageSize, int supplierNo) {

		HashMap<String,Object> paramMap = new HashMap<>();
		paramMap.put("startIndex", ((pageNo - 1) * pageSize));
		paramMap.put("pageSize", pageSize);
		paramMap.put("supplierNo", supplierNo);

		return clientDao.selectList(paramMap);
	}

	public int getMaxPageNo(int pageSize, int supplierNo) {
		int totalSize = clientDao.totalSize(supplierNo);
		int maxPageNo = totalSize / pageSize;
		if ((totalSize % pageSize) > 0) maxPageNo++;

		return maxPageNo;
	}

	/* @Transactional 선언
	 * => 메서드 안의 입력/변경/삭제(manipluation) 작업을 하나의 작업을 묶는다.
	 * => 모든 작업이 성공했을 때만 서버에 반영한다. 
	 */
	@Transactional(
			rollbackFor=Exception.class, 
			propagation=Propagation.REQUIRED)
	public void add(Client client) {

		//	  HashMap<String,Object> paramMap = new HashMap<>();
		//	  paramMap.put("client", client);  


		clientDao.insert(client);
		clientDao.insertMatch(client);
	}

	@Transactional(
			rollbackFor=Exception.class, 
			propagation=Propagation.REQUIRED)
	public void update(Client client) {
		clientDao.update(client);
		clientDao.updateMatch(client);
	}

	@Transactional(
			rollbackFor=Exception.class, 
			propagation=Propagation.REQUIRED)
	public void delete(int no) {
		clientDao.deleteMatch(no);
		clientDao.delete(no);
	}

	public Client get(int no) {
		Client client = clientDao.selectOne(no);
		return client;
	}
	
	  public Client validate(String clientTel, String clientPassword) {
			HashMap<String, String> params = new HashMap<>();
			params.put("clientTel", clientTel);
			params.put("clientPassword", clientPassword);
			return clientDao.existUser(params);
	  }

}

