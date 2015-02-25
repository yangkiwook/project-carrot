package carrot.control.json;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import carrot.domain.Client;
import carrot.domain.Company;
import carrot.domain.Order3;
import carrot.service.OrderService;

@Controller("json.orderControl")
@RequestMapping("/json/order")
public class OrderControl {
  static Logger log = Logger.getLogger(OrderControl.class);
  static final int PAGE_DEFAULT_SIZE = 15;
  
  @Autowired OrderService     	orderService;
  @Autowired ServletContext 		servletContext;
 
  @RequestMapping(value="/add", method=RequestMethod.POST)
  public Object add(Order3 order, HttpSession session) throws Exception {  
    
	  
	//Client client = (Client)session.getAttribute("loginUser");
	//order.setClientNo(client.getNo()); 
	
	System.out.println("     " + order);
		
	orderService.add(order);
		
		
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    
    return resultMap;
  }

  @RequestMapping("/delete")
  public Object delete(int no) throws Exception {
    orderService.delete(no);
    
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    
    return resultMap;
  }
/*  
  @RequestMapping("/update")
  public Object update(Order order, HttpSession session) throws Exception {

	Company company = (Company)session.getAttribute("loginUser");
	order.setSno(company.getSno());
	
    orderService.update(order);
    HashMap<String, Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");

    return resultMap;  
  }
  */
  
  @RequestMapping("/myorder")
  public Object mylist(int no,
      @RequestParam(defaultValue="10") int orderSize,
      HttpSession session) throws Exception {
    
	  
	Client client = (Client)session.getAttribute("loginUser");
	int clientNo = client.getNo(); 
    
    HashMap<String,Object> paramMap = new HashMap<>();
	paramMap.put("orderSize", orderSize);
	paramMap.put("supplierNo", no);
	paramMap.put("clientNo", clientNo);
	
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("orders", 
        orderService.getMyList(paramMap));
    
    return resultMap;
  }

  @RequestMapping("/list_order")
  public Object list_order(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="10") int pageSize,
      String supplierName,
      HttpSession session) throws Exception {
    
    if (pageSize <= 0)
      pageSize = PAGE_DEFAULT_SIZE;
    
    Client client = (Client)session.getAttribute("loginUser");
	int clientNo = client.getNo();
    int maxPageNo = orderService.getMaxPageNo(pageSize, clientNo);
    
    if (pageNo <= 0) pageNo = 1;
    if (pageNo > maxPageNo) pageNo = maxPageNo;
    
    HashMap<String,Object> paramMap = new HashMap<>();
	paramMap.put("startIndex", ((pageNo - 1) * pageSize));
	paramMap.put("pageSize", pageSize);
	paramMap.put("clientNo", clientNo);
	paramMap.put("supplierName", supplierName);
	System.out.println(clientNo);
	
    System.out.println("supplierName : " + supplierName);
	
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("currPageNo", pageNo);
    resultMap.put("maxPageNo", maxPageNo);
    resultMap.put("orders_a", 
        orderService.getList_order(paramMap));
    
    return resultMap;
  }
  
  
  
  @RequestMapping("/mobilelist")
  public Object list2(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="10") int pageSize,
      HttpSession session) throws Exception {
    
    if (pageSize <= 0)
      pageSize = PAGE_DEFAULT_SIZE;
    
    Client client = (Client)session.getAttribute("loginUser");
	int clientNo = client.getNo();
    int maxPageNo = orderService.getMaxPageNo(pageSize, clientNo);
    
    if (pageNo <= 0) pageNo = 1;
    if (pageNo > maxPageNo) pageNo = maxPageNo;
    
    HashMap<String,Object> paramMap = new HashMap<>();
	paramMap.put("startIndex", ((pageNo - 1) * pageSize));
	paramMap.put("pageSize", pageSize);
	paramMap.put("clientNo", clientNo);
	System.out.println(clientNo);
	
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("currPageNo", pageNo);
    resultMap.put("maxPageNo", maxPageNo);
    resultMap.put("orders", 
        orderService.getList2(paramMap));
    
    return resultMap;
  }
  
//ORDER1 리스트
	@RequestMapping("/list")
	public Object list(
			@RequestParam(defaultValue="1") int pageNo,
			@RequestParam(defaultValue="10") int pageSize,
			HttpSession session) throws Exception {
		
		Company supplier = (Company)session.getAttribute("loginUser");
		int sno = supplier.getSno();

		
		if (pageSize <= 0)
			pageSize = PAGE_DEFAULT_SIZE;

		HashMap<String, Object> paramMap = new HashMap<>();

		paramMap.put("startIndex", ((pageNo - 1) * pageSize));
		paramMap.put("pageSize", pageSize);
		paramMap.put("supplierNo", sno);

		
		int maxPageNo = orderService.getMaxPageNo(pageSize,sno);

		
		if (pageNo <= 0) pageNo = 1;
		if (pageNo > maxPageNo) pageNo = maxPageNo;

		HashMap<String,Object> resultMap = new HashMap<>();
		resultMap.put("status", "success");
		resultMap.put("currPageNo", pageNo);
		resultMap.put("maxPageNo", maxPageNo);		
		//resultMap.put("sno", sno);
		resultMap.put("orders", orderService.getList(paramMap));
		//resultMap.put("deliverys", deliveryService.getList(pageNo,pageSize));
		return resultMap;
	}

}












