package com.spring.memo.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.memo.entity.Item;
import com.spring.memo.entity.Member;
import com.spring.memo.entity.OrderItem;
import com.spring.memo.entity.OrderStatus;
import com.spring.memo.entity.Orders;

@SpringBootTest
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	
//	@Test
//	public void createOrders() {
//		
//		Member member =  memberRepository.findById(1L).orElseThrow();
//		
//		Orders orders = Orders.builder()
//								.member(member)
//								.orderDate(LocalDateTime.now())
//								.orderStatus(OrderStatus.ORDER)
//								.build();
//		Orders newOrder = orderRepository.save(orders);
//		System.out.println(newOrder);
//	}
	
	@Test
	public void createOrderItem() {
		
		Item item = itemRepository.findById(1L).orElseThrow();
		Orders orders = orderRepository.findById(1L).orElseThrow();
		
		OrderItem orderItem = OrderItem
							.builder()
							.id(1L)
							.item(item)
							.orders(orders)
							.orderPrice(36000)
							.count(2)
							.regTime(LocalDateTime.now())
							.updateTime(LocalDateTime.now())
							.build();
		OrderItem newOrderItem = orderItemRepository.save(orderItem);
		System.out.println(newOrderItem);
	}
}
