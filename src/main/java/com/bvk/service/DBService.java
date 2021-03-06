package com.bvk.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bvk.domain.Address;
import com.bvk.domain.Category;
import com.bvk.domain.City;
import com.bvk.domain.Customer;
import com.bvk.domain.Estate;
import com.bvk.domain.Order;
import com.bvk.domain.OrderItem;
import com.bvk.domain.Payment;
import com.bvk.domain.PaymentCard;
import com.bvk.domain.PaymentSlip;
import com.bvk.domain.Product;
import com.bvk.enumerator.CustomerType;
import com.bvk.enumerator.PaymentStatus;
import com.bvk.enumerator.Profile;
import com.bvk.repository.AddressRepository;
import com.bvk.repository.CategoryRepository;
import com.bvk.repository.CityRepository;
import com.bvk.repository.CustomerRepository;
import com.bvk.repository.EstateRepository;
import com.bvk.repository.OrderItemRepository;
import com.bvk.repository.OrderRepository;
import com.bvk.repository.PaymentRepository;
import com.bvk.repository.ProductRepository;

@Service
public class DBService {
	
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
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	public void instantiateTestDatabase() throws ParseException {
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");
		Category cat3 = new Category(null, "Cama mesa e banho");
		Category cat4 = new Category(null, "Eletronicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");


		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de escritório", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));

		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));

		Estate est1 = new Estate(null, "Rio de janeiro");
		Estate est2 = new Estate(null, "Sao Paulo");

		City c1 = new City(null, "Rio de janeiro", est1);
		City c2 = new City(null, "Sao paulo", est2);
		City c3 = new City(null, "Campinas", est2);

		est1.getCities().addAll(Arrays.asList(c1));
		est1.getCities().addAll(Arrays.asList(c2, c3));

		Customer cus1 = new Customer(null, "Breno", "brenojorri@gmail.com", "23169449656", CustomerType.PESSOAFISICA,encoder.encode("123"));
		cus1.getPhones().addAll(Arrays.asList("24810831", "976445876"));

		Customer cus2 = new Customer(null, "Klara", "klaravictoria@gmail.com", "22351734548", CustomerType.PESSOAFISICA,encoder.encode("123"));
		cus2.addProfile(Profile.ADMIN);
		cus1.getPhones().addAll(Arrays.asList("24810832", "976444476"));
		
		
		Address a1 = new Address(null, "rua a", "1", "f", "bairro a", "33241456", cus1, c1);
		Address a2 = new Address(null, "Avenida B", "105", "Sala 80", "Centro", "38777012", cus1, c2);
		Address a3 = new Address(null, "Avenida C", "100", null, "Centro", "28177012", cus2, c2);

		cus1.getAddresses().addAll(Arrays.asList(a1, a2));
		cus2.getAddresses().addAll(Arrays.asList(a3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Order o1 = new Order(null, sdf.parse("30/09/2017 00:00"), cus1, a1);
		Order o2 = new Order(null, sdf.parse("10/10/2017 00:00"), cus1, a2);

		Payment pay1 = new PaymentCard(null, PaymentStatus.QUITADO, o1, 6);
		o1.setPayment(pay1);

		Payment pay2 = new PaymentSlip(null, PaymentStatus.PENDENTE, o2, sdf.parse("20/10/2017 00:00"), null);
		o2.setPayment(pay2);

		cus1.getOrders().addAll(Arrays.asList(o1, o2));

		OrderItem oi1 = new OrderItem(o1, p1, 0., 1, 2000.0);
		OrderItem oi2 = new OrderItem(o2, p3, 0., 2, 80.0);
		OrderItem oi3 = new OrderItem(o2, p2, 100., 1, 800.0);

		o1.getItems().addAll(Arrays.asList(oi1, oi2));
		o2.getItems().addAll(Arrays.asList(oi3));

		p1.getItems().addAll(Arrays.asList(oi1));
		p3.getItems().addAll(Arrays.asList(oi2));
		p2.getItems().addAll(Arrays.asList(oi3));

		categoryRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		estateRepository.save(Arrays.asList(est1, est2));
		cityRepository.save(Arrays.asList(c1, c2, c3));
		customerRepository.save(Arrays.asList(cus1,cus2));
		addressRepository.save(Arrays.asList(a1, a2,a3));
		orderRepository.save(Arrays.asList(o1, o2));
		paymentRepository.save(Arrays.asList(pay1, pay2));
		orderitemRepository.save(Arrays.asList(oi1, oi2, oi3));

		

	}

}
