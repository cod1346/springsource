package com.spring.memo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.memo.entity.Item;

public interface ItemRepository extends JpaRepository<Item,Long> {
	// 상품 이름으로 검색
	// findBy + 필드명
	List<Item> findByPriceAndItemNm(int price,String ItemNm);
	
	//상품명 or 상품상세설명
	//findBy+필드명+or+필드명
	List<Item> findByItemNmOrItemDetail(String itemNm,String itemDatail);
	
	//특정가격 이하인 상품 조회
	List<Item> findByPriceLessThan(int price);
	
	//특정가격 이하인 상품 조회 후 내림차순 정력
	// OrderBy~~~~Desc
	List<Item> findByPriceLessThanOrderByPriceDesc(int price);
}
