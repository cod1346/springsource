package com.spring.memo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.memo.entity.Item;
import com.spring.memo.entity.ItemSellStatus;

@SpringBootTest
public class ItemRepositoryTest {
	
	@Autowired
	private ItemRepository repository;
	
//	@Test
//	public void itemCreateTest() {
//	
//		Item item = new Item();
//		item.setItemNm("순수 프리미엄");
//		item.setPrice(29500);
//		item.setStockNumber(55);
//		item.setItemDetail("깨끗한 나라");
//		item.setItemSellStatus(ItemSellStatus.SELL);
//		item.setRegTime(LocalDateTime.now());
//		item.setUpdateTime(LocalDateTime.now());
//		repository.save(item);
//		
//		item = Item.builder()
//						.itemNm("프롬비")
//						.price(45000)
//						.stockNumber(70)
//						.itemDetail("휴대용 선풍기")
//						.itemSellStatus(ItemSellStatus.SELL)
//						.regTime(LocalDateTime.now())
//						.updateTime(LocalDateTime.now())
//						.build();
//		Item newItem = repository.save(item);	
//		System.out.println(newItem);
//		
//	}
	//하나조회
//	@Test
//	public void getItem() {
//		Optional<Item> item = repository.findById(1L);
//		
//		item.ifPresent(ele->System.out.println(ele));
//		
//		Item item2 = repository.findById(2L).orElseThrow(EntityNotFoundException::new);
//		System.out.println(item);
//	}
	
	//전체조회
//	@Test
//	public void getItem() {
//		List<Item> item = repository.findAll();
//		
//		item.forEach(it-> System.out.println(it));
//	}
	
	//상품명 조회 (AND)
//	@Test
//	public void getItem() {
//		List<Item> item = repository.findByPriceAndItemNm(12000, "반팔티");
//		
//		item.forEach(it-> System.out.println(it));
//	}
	//상품명조회 (OR)
//	@Test
//	public void getItem() {
//		List<Item> item = repository.findByItemNmOrItemDetail("블루투스 스피커", "카라티");
//		
//		item.forEach(it-> System.out.println(it));
//	}
	
	//
//	@Test
//	public void getPriceLessThen() {
//		List<Item> item = repository.findByPriceLessThan(20000);
//		
//		item.forEach(it-> System.out.println(it));
//	}
	@Test
	public void getPriceLessThenOrder() {
		List<Item> item = repository.findByPriceLessThanOrderByPriceDesc(50000);
		
		item.forEach(it-> System.out.println(it));
	}
}
