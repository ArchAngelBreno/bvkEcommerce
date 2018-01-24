package com.bvk;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bvk.enumerator.CustomerType;
import com.bvk.enumerator.PaymentStatus;
import com.bvk.model.Address;
import com.bvk.model.Category;
import com.bvk.model.City;
import com.bvk.model.Customer;
import com.bvk.model.Estate;
import com.bvk.model.Order;
import com.bvk.model.OrderItem;
import com.bvk.model.Payment;
import com.bvk.model.PaymentCard;
import com.bvk.model.PaymentSlip;
import com.bvk.model.Product;
import com.bvk.repository.AddressRepository;
import com.bvk.repository.CategoryRepository;
import com.bvk.repository.CityRepository;
import com.bvk.repository.CustomerRepository;
import com.bvk.repository.EstateRepository;
import com.bvk.repository.OrderItemRepository;
import com.bvk.repository.OrderRepository;
import com.bvk.repository.PaymentRepository;
import com.bvk.repository.ProductRepository;

@SpringBootApplication
public class BvkEcommerceApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private EstateRepository estateRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderItemRepository orderitemRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(BvkEcommerceApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.save(Arrays.asList(cat1, cat2));
		productRepository.save(Arrays.asList(p1, p2, p3));

		Estate est1 = new Estate(null, "Rio de janeiro");
		Estate est2 = new Estate(null, "Sao Paulo");

		City c1 = new City(null, "Rio de janeiro", est1);
		City c2 = new City(null, "Sao paulo", est2);
		City c3 = new City(null, "Campinas", est2);

		est1.getCities().addAll(Arrays.asList(c1));
		est1.getCities().addAll(Arrays.asList(c2, c3));
		estateRepository.save(Arrays.asList(est1, est2));
		cityRepository.save(Arrays.asList(c1, c2, c3));
		
		Customer cus1 = new Customer(null, "Breno", "brenojorri@gmail.com", "123456789", CustomerType.PESSOAFISICA);
		cus1.getPhones().addAll(Arrays.asList("24810831","976445876"));
		
		Address a1 = new Address(null, "rua a", "1", "f", "bairro a", "33241456", cus1, c1);
		Address a2 = new Address(null, "Avenida B", "105", "Sala 80", "Centro", "38777012", cus1, c2);
		
		
		cus1.getAddresses().addAll(Arrays.asList(a1,a2));
		
		customerRepository.save(cus1);
		addressRepository.save(Arrays.asList(a1,a2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Order o1 = new Order(null, sdf.parse("30/09/2017 00:00"), cus1, a1);
		Order o2 = new Order(null, sdf.parse("10/10/2017 00:00"), cus1, a2);
		
		Payment pay1 = new PaymentCard(null, PaymentStatus.QUITADO, o1, 6);
		o1.setPayment(pay1);
		
		Payment pay2 = new PaymentSlip(null, PaymentStatus.PENDENTE, o2, sdf.parse("20/10/2017 00:00"), null);
		o2.setPayment(pay2);
		
		cus1.getOrders().addAll(Arrays.asList(o1,o2));
		
		orderRepository.save(Arrays.asList(o1,o2));
		paymentRepository.save(Arrays.asList(pay1,pay2));
		
		
		OrderItem oi1 = new OrderItem(o1, p1, 0., 1, 2000.0);
		OrderItem oi2 = new OrderItem(o2, p3, 0., 2, 80.0);
		OrderItem oi3 = new OrderItem(o2, p2, 100., 1, 800.0);
		
		o1.getItems().addAll(Arrays.asList(oi1,oi2));
		o2.getItems().addAll(Arrays.asList(oi3));
		
		p1.getItems().addAll(Arrays.asList(oi1));
		p3.getItems().addAll(Arrays.asList(oi2));
		p2.getItems().addAll(Arrays.asList(oi3));
		
		orderitemRepository.save(Arrays.asList(oi1,oi2,oi3));
		
		
		

	}
}
