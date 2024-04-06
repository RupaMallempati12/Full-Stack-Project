package com.resdelivery.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.resdelivery.model.Delivery;
import com.resdelivery.model.Menu;
import com.resdelivery.model.Order;
import com.resdelivery.model.OrderItem;
import com.resdelivery.model.Pickup;
import com.resdelivery.repository.DeliveryRepository;
import com.resdelivery.repository.MenuRepository;
import com.resdelivery.repository.OrderRepository;
import com.resdelivery.repository.PickupRepository;
import com.resdelivery.repository.StaffRepository;

@RequestMapping("/order")
@Controller
public class OrderController {

	@Autowired
	MenuRepository repo;

	@Autowired
	OrderRepository ordRepo;
	
	@Autowired
	StaffRepository staffRepo;

	@Autowired
	PickupRepository pickRepo;

	@Autowired
	DeliveryRepository delRepo;

	@RequestMapping("/mylist")
	public String myOrderList(Model model, HttpServletRequest request) {
		model.addAttribute("datalist",
				ordRepo.findByCustId(request.getSession().getAttribute("userid").toString()).get());
		return "myorder";
	}

	@RequestMapping("/list")
	public String orderList(Model model, HttpServletRequest request) {
		String user = request.getSession().getAttribute("name").toString();
		String staffid = request.getSession().getAttribute("userid").toString();
		if(user.equals("Admin"))
			model.addAttribute("datalist", ordRepo.findAll());
		else
			model.addAttribute("datalist", ordRepo.findAllByDeliveryStatus(staffid).get());

		return "order";
	}

	@RequestMapping("/mycart")
	public String cart(Model model, HttpServletRequest req) {
		List<Menu> menus = (List<Menu>) repo.findAll();
		List<Menu> items = new ArrayList<>();
		for (Menu item : menus) {
			System.out.println(item.getMenuId());
			System.out.println(req.getSession().getAttribute(item.getMenuId()));
			System.out.println(req.getSession().getAttribute(item.getMenuId()) != null);
			if (req.getSession().getAttribute(item.getMenuId()) != null)
				items.add(item);
		}
		model.addAttribute("datalist", items);

		return "order_items";
	}

	@RequestMapping("/type/{type}")
	public String create(@PathVariable String type, Model model, HttpServletRequest req) {
		req.getSession().setAttribute("orderType", type);

		model.addAttribute("datalist", repo.findAll());
		return "order_items";
	}

	@RequestMapping("/add/home")
	public String addItemHome(OrderItem obj, HttpServletRequest req, Model model) throws IOException {

		@SuppressWarnings("unchecked")
		List<OrderItem> orders = (List<OrderItem>) req.getSession().getAttribute("orders");
		if (orders == null) {
			orders = new ArrayList<>();
			obj.setQty(1);
			orders.add(obj);
			req.getSession().setAttribute("orders", orders);
		} else {
			boolean flag = true;
			for (OrderItem item : orders) {
				if (item.getMenuId().equals(obj.getMenuId())) {
					item.setQty(item.getQty() + 1);
					item.setPrice(item.getPrice() + obj.getPrice());
					flag = false;
					break;
				}
			}

			if (flag) {
				obj.setQty(1);
				orders.add(obj);
			}
			req.getSession().setAttribute("orders", orders);

		}
		if (req.getSession().getAttribute(obj.getMenuId()) != null) {
			int q = Integer.parseInt(req.getSession().getAttribute(obj.getMenuId()).toString());
			q++;
			req.getSession().setAttribute(obj.getMenuId(), q);
		} else
			req.getSession().setAttribute(obj.getMenuId(), 1);

		return "redirect:/customer/home";
	}

	@RequestMapping("/add")
	public String addItem(OrderItem obj, HttpServletRequest req, Model model) throws IOException {

		System.out.println(obj.toString());

		@SuppressWarnings("unchecked")
		List<OrderItem> orders = (List<OrderItem>) req.getSession().getAttribute("orders");
		if (orders == null) {
			orders = new ArrayList<>();
			obj.setQty(1);
			orders.add(obj);
			req.getSession().setAttribute("orders", orders);
		} else {
			boolean flag = true;
			for (OrderItem item : orders) {
				if (item.getMenuId().equals(obj.getMenuId())) {
					item.setQty(item.getQty() + 1);
					item.setPrice(item.getPrice() + obj.getPrice());
					flag = false;
					break;
				}
			}

			if (flag) {
				obj.setQty(1);
				orders.add(obj);
			}
			req.getSession().setAttribute("orders", orders);

		}
		if (req.getSession().getAttribute(obj.getMenuId()) != null) {
			int q = Integer.parseInt(req.getSession().getAttribute(obj.getMenuId()).toString());
			q++;
			req.getSession().setAttribute(obj.getMenuId(), q);
		} else
			req.getSession().setAttribute(obj.getMenuId(), 1);

		return "redirect:/order/mycart";
	}

	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping("/remove")
	public String removeItem(OrderItem obj, HttpServletRequest req, Model model) throws IOException {

		@SuppressWarnings("unchecked")
		List<OrderItem> orders = (List<OrderItem>) req.getSession().getAttribute("orders");
		if (orders != null) {
			for (OrderItem item : orders) {
				if (item.getMenuId().equals(obj.getMenuId())) {
					item.setQty(item.getQty() - 1);
					item.setPrice(item.getPrice() - obj.getPrice());
					System.out.println(item.getName() + " : " + item.getQty());
					if (item.getQty() == 0) {

						orders.remove(item);
					}
					break;
				}
			}

			req.getSession().setAttribute("orders", orders);

		}
		if (req.getSession().getAttribute(obj.getMenuId()) != null) {
			int q = Integer.parseInt(req.getSession().getAttribute(obj.getMenuId()).toString());
			if (q > 1) {
				q--;
				req.getSession().setAttribute(obj.getMenuId(), q);
			} else
				req.getSession().removeAttribute(obj.getMenuId());

		}

		return "redirect:/order/mycart";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/save")
	public String save(@RequestParam("mode") String mode, @RequestParam("deltype") String orderType,
			HttpServletRequest req) throws IOException {

		Optional<Order> idobj = ordRepo.findTopByOrderByIdDesc();
		String id = null;
		if (idobj.isPresent()) {
			int idnum = Integer.parseInt(idobj.get().getOrderId().substring(5));
			idnum++;
			id = "ORD47" + idnum;
		} else {
			id = "ORD4731842";
		}

		Order order = new Order();
		order.setCustId(req.getSession().getAttribute("userid").toString());
		order.setOrderId(id);
		order.setOrderItems((List<OrderItem>) req.getSession().getAttribute("orders"));
		order.setOrderType(orderType);
		List<OrderItem> items = (List<OrderItem>) req.getSession().getAttribute("orders");

		double amount = 0;
		for (OrderItem item : items) {
			amount += item.getPrice();
		}
		order.setAmount(amount);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		order.setOrderedDate(dtf.format(now));
		
		if(orderType.equals("delivery"))
			order.setDeliveryStatus("Assign Executive");
		else
			order.setDeliveryStatus("UnDelivered");

		order.setPaymentmode(mode);
		order.setPaymentstatus("Paid");
		Order savedOrder = ordRepo.save(order);
		req.getSession().removeAttribute("orders");

		

		if (orderType.equals("pickup")) {
			Pickup pickup = new Pickup();
			pickup.setOrderId(id);
			pickup.setStatus("Not Picked Up");
			pickRepo.save(pickup);
		} else {
			Delivery del = new Delivery();
			del.setOrderId(id);
			del.setStatus("Not Delivered");
			delRepo.save(del);
		}

		String sid = req.getSession().getAttribute("id").toString();
		String userid = req.getSession().getAttribute("userid").toString();
		String usertype = req.getSession().getAttribute("usertype").toString();
		String name = req.getSession().getAttribute("name").toString();

		req.getSession().invalidate();

		req.getSession().setAttribute("id", sid);
		req.getSession().setAttribute("userid", userid);
		req.getSession().setAttribute("usertype", usertype);
		req.getSession().setAttribute("name", name);

		return "redirect:/order/placed/" + savedOrder.getId();
	}

	@RequestMapping("/placed/{id}")
	public String show(@PathVariable String id, Model model, HttpServletRequest req) {

		Order order = ordRepo.findById(id).get();
		model.addAttribute("obj", order);
		model.addAttribute("staffs", staffRepo.findByStafftype("Delivery Executive"));

		if (req.getSession().getAttribute("usertype").equals("customer"))
			return "order_details_cust";
		else
			return "order_details";
	}

	@RequestMapping("/assign")
	public String assign(@RequestParam("staffId") String staffid, @RequestParam("orderId") String orderid, Model model, HttpServletRequest req) {

		Delivery obj = delRepo.findByOrderId(orderid);
		obj.setStaffId(staffid);
		obj.setStatus("unDelivered");
		delRepo.save(obj);	
		
		Order order = ordRepo.findByOrderId(orderid).get();
		order.setDeliveryStatus(staffid);
		ordRepo.save(order);
		
		return "redirect:/order/list";
	}

	
	@RequestMapping("/delivery/update/{id}")
	public String deliveryUpdate(@PathVariable String id, Model model, HttpServletRequest req) {

		Order order = ordRepo.findById(id).get();
		order.setDeliveryStatus("Delivered");
		order.setStaffId(req.getSession().getAttribute("userid").toString());
		ordRepo.save(order);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		if(order.getOrderType().equals("delivery"))
		{
			Delivery del = delRepo.findByOrderId(order.getOrderId());
			del.setStaffId(req.getSession().getAttribute("userid").toString());
			del.setStatus("Delivered");
			del.setDtime(dtf.format(now));
			delRepo.save(del);
		}
		else
		{
			Pickup pickup = new Pickup();
			pickup.setOrderId(order.getOrderId());
			pickup.setPtime(dtf.format(now));
			pickup.setStaffId(req.getSession().getAttribute("userid").toString());
			pickup.setStatus("Delivered");
			pickRepo.save(pickup);
		}
		return "redirect:/order/list/";

	}


}
