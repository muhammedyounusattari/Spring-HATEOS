package younus.attari;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class MainControllerClass {

	private static final Logger LOG = LoggerFactory.getLogger(MainControllerClass.class);
	
	@RequestMapping(value = "single")
	public Customer firstRequest() {
		LOG.info("from firstRequest()",this.getClass());
		Customer customer = new Customer();

		customer.setName("Muhammed Younus Attari");
		customer.setCustId(1234);
		customer.setSal(54321);
		customer.setAddress("Hyderabad, India");
		LOG.info("single customer details...",customer);
		return customer;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Customer> getAllCustomers() {
		LOG.info("from getAllCustomers()",this.getClass());
		Customer customer = new Customer();
		List<Customer> list = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			customer.setName("name" + i);
			customer.setCustId(i);
			customer.setSal(i * i);
			customer.setAddress("address" + i);
			Link selfLink = linkTo(String.class).slash(customer.getCustId()).withSelfRel();
			customer.add(selfLink);
			list.add(customer);
			customer = new Customer();
		}

		LOG.info("list of customer details...",list);
		return list;
	}
}
