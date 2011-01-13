package be.koozi.order;

import java.util.Currency;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.koozi.cart.CartResolver;
import be.koozi.customer.Customer;
import be.koozi.customer.CustomerDao;
import be.koozi.payment.Payment;
import be.koozi.payment.PaymentDao;
import be.koozi.shipping.KialaShipping;
import be.koozi.shipping.KialaShippingDao;
import be.koozi.shipping.Shipping;
import be.koozi.shipping.ShippingDao;
import be.koozi.tools.CurrencyResolver;

@Controller
public class OrderController {
	private OrderBean orderBean;
	private OrderDao orderDao;
	private CurrencyResolver currencyResolver;
	private CartResolver cartResolver;
	private OrderItemDao orderItemDao;
	private OrderItemOptionValueDao orderItemOptionValueDao;
	private CustomerDao customerDao;
	private ShippingDao shippingDao;
	private PaymentDao paymentDao;
	private KialaShippingDao kialaShippingDao;

	@Autowired
	public OrderController(PaymentDao paymentDao, ShippingDao shippingDao, OrderBean orderBean, CurrencyResolver currencyResolver, OrderDao orderDao, CartResolver cartResolver, OrderItemDao orderItemDao, OrderItemOptionValueDao orderItemOptionValueDao, CustomerDao customerDao,
			KialaShippingDao kialaShippingDao) {
		super();
		this.orderBean = orderBean;
		this.currencyResolver = currencyResolver;
		this.orderDao = orderDao;
		this.cartResolver = cartResolver;
		this.orderItemDao = orderItemDao;
		this.orderItemOptionValueDao = orderItemOptionValueDao;
		this.customerDao = customerDao;
		this.paymentDao = paymentDao;
		this.shippingDao = shippingDao;
		this.kialaShippingDao = kialaShippingDao;
	}

	@RequestMapping(value = "/orders", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public String createOrder(HttpServletRequest request, ModelMap model) {
		String cartId = cartResolver.resolveCartId(request);
		Currency currency = currencyResolver.resolveCurrency(request);
		Order order = orderBean.createOrder(cartId, currency);
		return "redirect:/orders/" + order.getId();
	}

	@RequestMapping(value = "/orders/{orderId}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findOrder(HttpServletRequest request, @PathVariable("orderId") String id, ModelMap model) {
		Order order = orderDao.find(id);
		model.addAttribute("order", order);
		return "/order/order";
	}

	@RequestMapping(value = "/orders/{orderId}/orderItems", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findOrderItems(HttpServletRequest request, @PathVariable("orderId") String orderId, ModelMap model) {
		List<OrderItem> orderItemList = orderItemDao.findByOrder(orderId);
		model.addAttribute("orderItemList", orderItemList);
		model.addAttribute("orderId", orderId);
		return "/order/order";
	}

	@RequestMapping(value = "/orders/{orderId}/orderItems/{orderItemId}/optionValues", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findOptionValues(HttpServletRequest request, @PathVariable("orderItemId") Long orderItemId, ModelMap model) {
		List<OrderItemOptionValue> optionValueList = orderItemOptionValueDao.findByOrderItem(orderItemId);
		model.addAttribute("optionValueList", optionValueList);
		model.addAttribute("orderItemId", orderItemId);
		return "/order/order";
	}

	@RequestMapping(value = "/orders/{orderId}/shipping", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findPayment(HttpServletRequest request, @PathVariable("orderId") String orderId, ModelMap model) {
		List<Shipping> shippingList = shippingDao.findByOrder(orderId);
		if (shippingList.size() != 0)
			model.addAttribute("shipping", shippingList.get(0));
		else
			model.addAttribute("shipping", new Shipping());
		model.addAttribute("orderId", orderId);
		return "/order/shipping";
	}

	@RequestMapping(value = "/orders/{orderId}/payment", method = org.springframework.web.bind.annotation.RequestMethod.PUT)
	public String updatePayment(HttpServletRequest request, @PathVariable("orderId") String orderId, @RequestBody Payment payment, ModelMap model) {
		List<Payment> shippingList = paymentDao.findByOrder(orderId);
		if (shippingList.size() != 0)
			payment.setId(shippingList.get(0).getId());
		payment.setOrderId(orderId);
		paymentDao.update(payment);
		return "redirect:/orders/" + orderId + "/payment";
	}

	@RequestMapping(value = "/orders/{orderId}/customer", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findCustomer(HttpServletRequest request, @PathVariable("orderId") String orderId, ModelMap model) {
		List<Customer> customerList = customerDao.findByOrder(orderId);
		if (customerList.size() != 0)
			model.addAttribute("customer", customerList.get(0));
		else
			model.addAttribute("customer", new Customer());
		model.addAttribute("orderId", orderId);
		return "/order/customer";
	}

	@RequestMapping(value = "/orders/{orderId}/customer", method = org.springframework.web.bind.annotation.RequestMethod.PUT)
	public String updateCustomer(HttpServletRequest request, @PathVariable("orderId") String orderId, @RequestBody Customer customer, ModelMap model) {
		List<Customer> customerList = customerDao.findByOrder(orderId);
		if (customerList.size() != 0)
			customer.setId(customerList.get(0).getId());
		if(customer.getId() == 0L)
			customer.setId(null);
		customer.setOrderId(orderId);
		customerDao.update(customer);
		return "redirect:/orders/" + orderId + "/customer";
	}

	@RequestMapping(value = "/orders/{orderId}/shipping/default", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findShipping(HttpServletRequest request, @PathVariable("orderId") String orderId, ModelMap model) {
		List<Shipping> shippingList = shippingDao.findByOrder(orderId);
		if (shippingList.size() != 0)
			model.addAttribute("shipping", shippingList.get(0));
		else
			model.addAttribute("shipping", new Shipping());
		model.addAttribute("orderId", orderId);
		return "/order/shipping";
	}

	@RequestMapping(value = "/orders/{orderId}/shipping/default", method = org.springframework.web.bind.annotation.RequestMethod.PUT)
	public String updateShipping(HttpServletRequest request, @PathVariable("orderId") String orderId, @RequestBody Shipping shipping, ModelMap model) {
		List<Shipping> shippingList = shippingDao.findByOrder(orderId);
		if (shippingList.size() != 0)
			shipping.setId(shippingList.get(0).getId());
		shipping.setOrderId(orderId);
		shippingDao.update(shipping);
		return "redirect:/orders/" + orderId + "/shipping/default";
	}

	@RequestMapping(value = "/orders/{orderId}/shipping/kiala", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findKialaShipping(HttpServletRequest request, @PathVariable("orderId") String orderId, ModelMap model) {
		List<KialaShipping> shippingList = kialaShippingDao.findByOrder(orderId);
		if (shippingList.size() != 0)
			model.addAttribute("kialaShipping", shippingList.get(0));
		else
			model.addAttribute("kialaShipping", new KialaShipping());
		model.addAttribute("orderId", orderId);
		return "/order/shipping/kiala";
	}

	@RequestMapping(value = "/orders/{orderId}/shipping/kiala", method = org.springframework.web.bind.annotation.RequestMethod.PUT)
	public String updateKialaShipping(HttpServletRequest request, @PathVariable("orderId") String orderId, @RequestBody KialaShipping kialaShipping, ModelMap model) {
		List<KialaShipping> shippingList = kialaShippingDao.findByOrder(orderId);
		if (shippingList.size() != 0)
			kialaShipping.setId(shippingList.get(0).getId());
		kialaShipping.setOrderId(orderId);
		kialaShippingDao.update(kialaShipping);
		return "redirect:/orders/" + orderId + "/shipping/kiala";
	}
	
	@RequestMapping(value = "/orders/{orderId}/shipping/kiala/postBack", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String postBackKialaShipping(HttpServletRequest request, @PathVariable("orderId") String orderId, @RequestParam("kpname") String name, ModelMap model) {
		KialaShipping kialaShipping = new KialaShipping();
		List<KialaShipping> shippingList = kialaShippingDao.findByOrder(orderId);
		if (shippingList.size() != 0)
			kialaShipping = shippingList.get(0);
			
		kialaShipping.setOrderId(orderId);
		kialaShipping.setName(name);
		kialaShippingDao.update(kialaShipping);
		return "redirect:/orders/" + orderId + "/shipping/kiala";
	}
}

//locationhint%3DGelegen%2Baan%2BDen%2BBlauwen%2BHoek%26
//street%3DBrusselsestraat%252C%2B139%26
//openinghours%3DMON.0800.2000-TUE.0800.2000-WED.0800.2000-THU.0800.2000-FRI.0800.2000-SAT.0800.1900-%26shortkpid%3D0805%26
//kpname%3DSUPERMARKET%2BEXOTIC%2BWORLD%26
//zip%3D3000%26
//city%3DLeuven
//shortkpid%3D0829

