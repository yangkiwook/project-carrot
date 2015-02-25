package carrot.control.json;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import carrot.domain.Company;
import carrot.domain.Order;
import carrot.service.OrderService2;

@Controller("json.orderControl2")
@RequestMapping("/json/order2")
public class OrderControl2 {
	static Logger log = Logger.getLogger(OrderControl2.class);
	static final int PAGE_DEFAULT_SIZE = 10;
	String oname;
	Date oodate;
	String ograde;
	
	@Autowired OrderService2 orderService2;
	@Autowired ServletContext servletContext;
	
	@RequestMapping(value="/title", method=RequestMethod.POST)
	  public Object add(Order order) throws Exception {  
	    
	    HashMap<String,Object> resultMap = new HashMap<>();
	    resultMap.put("oname",order.getOname());
	    resultMap.put("oodate", order.getOodate());
	    resultMap.put("ograde",order.getOgrade());
	    System.out.println(resultMap);
	    oname = order.getOname();
	    oodate = order.getOodate();
	    ograde = order.getOgrade();
	    System.out.println("oname : "+oname);
	    System.out.println("oodate : "+oodate);
	    System.out.println("ograde : "+ograde);

	    return resultMap;
	  }


	@RequestMapping("/list")
	public Object list(
			@RequestParam(defaultValue="1") int pageNo,
			@RequestParam(defaultValue="10") int pageSize,
			HttpSession session) throws Exception {
		
		Company supplier = (Company)session.getAttribute("loginUser");
		int sno = supplier.getSno();
		
		if (pageSize <= 0)
			pageSize = PAGE_DEFAULT_SIZE;
/*		

		System.out.println("             oodateS"+oodate);
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date to = transFormat.parse(oodate);
		System.out.println("oodate"+to);
		*/
		System.out.println("             oodateS"+oodate);

		Date from = oodate;
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		String to = transFormat.format(from);
		 

		System.out.println("oodate"+to);
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("oname", oname);
		params.put("oodate", to);
		params.put("pageSize", pageSize);
		params.put("supplierNo", sno);
		
		int maxPageNo = orderService2.getMaxPageNo(pageSize);
		System.out.println("maxPageNo                         " + maxPageNo);

		params.put("startIndex", ((pageNo - 1) * pageSize));

		if (pageNo <= 0) pageNo = 1;
		if (pageNo > maxPageNo) pageNo = maxPageNo;
		
		HashMap<String,Object> resultMap = new HashMap<>();
		resultMap.put("status", "success");
		resultMap.put("currPageNo", pageNo);
		resultMap.put("maxPageNo", maxPageNo);
		resultMap.put("oname", oname);
		resultMap.put("oodate", to);
		resultMap.put("orders", orderService2.getList2(params));

		//resultMap.put("deliverys", deliveryService2.getList(pageNo,pageSize));

		return resultMap;
	}
	

}

