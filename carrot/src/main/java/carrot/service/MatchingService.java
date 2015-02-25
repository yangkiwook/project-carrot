package carrot.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import carrot.dao.MatchingDao;
import carrot.domain.Matching;

@Service
public class MatchingService {
  @Autowired
  MatchingDao matchingDao;
  
  public List<?> getList(int pageNo, int pageSize, int supplierNo) {
	  
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", ((pageNo - 1) * pageSize));
    paramMap.put("pageSize", pageSize);
    paramMap.put("supplierNo", supplierNo);
    
    return matchingDao.selectList(paramMap);
  }
  
  public int getMaxPageNo(int pageSize, int supplierNo) {
    int totalSize = matchingDao.totalSize(supplierNo);
    int maxPageNo = totalSize / pageSize;
    if ((totalSize % pageSize) > 0) maxPageNo++;
    
    return maxPageNo;
  }
  
  @Transactional(
      rollbackFor=Exception.class, 
      propagation=Propagation.REQUIRED)
  public void add(Matching matching) {
    matchingDao.insert(matching);
  }
  
  @Transactional(
      rollbackFor=Exception.class, 
      propagation=Propagation.REQUIRED)
  public void update(Matching matching) {
    matchingDao.update(matching);
  }
  
  @Transactional(
      rollbackFor=Exception.class, 
      propagation=Propagation.REQUIRED)
  public void delete(int matchingNo) {
    matchingDao.delete(matchingNo);
  }
  
  public Matching get(int matchingNo) {
    Matching matching = MatchingDao.selectOne(matchingNo);
    return matching;
  }
  

}

