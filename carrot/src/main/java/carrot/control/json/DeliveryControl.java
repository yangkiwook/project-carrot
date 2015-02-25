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

import carrot.domain.Company;
import carrot.domain.Delivery;
import carrot.service.DeliveryService;

@Controller("json.deliveryControl")
@RequestMapping("/json/delivery")
public class DeliveryControl {
	static Logger log = Logger.getLogger(DeliveryControl.class);
	static final int PAGE_DEFAULT_SIZE = 10;
	String mlevel;
	
	@Autowired DeliveryService deliveryService;
	@Autowired ServletContext servletContext;
	
	@RequestMapping(value="/datesum", method=RequestMethod.POST)
	  public Object add(Delivery delivery) throws Exception {  
	    System.out.println("delivery : "+delivery);
	    
	    HashMap<String,Object> resultMap = new HashMap<>();
	    resultMap.put("mlevel",delivery.getDgrade());
	    System.out.println(resultMap);
	    mlevel = delivery.getDgrade();
	    System.out.println("mlevel : "+mlevel);

	    return resultMap;
	  }

	
	@RequestMapping("/list")
	public Object list(
			@RequestParam(defaultValue="1") int pageNo,
			@RequestParam(defaultValue="10") int pageSize,
			HttpSession session) throws Exception {
		
		Company supplier = (Company)session.getAttribute("loginUser");
		int sno = supplier.getSno();
		String sname = supplier.getSname();

		
		supplier.setSno(sno);
		supplier.setSname(sname);
		
		if (pageSize <= 0)
			pageSize = PAGE_DEFAULT_SIZE;

		int maxPageNo = deliveryService.getMaxPageNo(pageSize,sno);
		System.out.println("                pageSize  "+pageSize + "   maxPageNo "+maxPageNo);
		if (pageNo <= 0) pageNo = 1;
		if (pageNo > maxPageNo) pageNo = maxPageNo;

		HashMap<String,Object> resultMap = new HashMap<>();
		resultMap.put("status", "success");
		resultMap.put("currPageNo", pageNo);
		resultMap.put("maxPageNo", maxPageNo);		
		//resultMap.put("sno", sno);
		
		resultMap.put("deliverys", deliveryService.getList2(pageNo,pageSize,sno));
		System.out.println("deliverys : "+resultMap);
		//resultMap.put("deliverys", deliveryService.getList(pageNo,pageSize));
		return resultMap;
	}

}

