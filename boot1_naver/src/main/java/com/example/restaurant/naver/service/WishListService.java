package com.example.restaurant.naver.service;

import com.example.restaurant.wishList.dto.WishListDTO;

public interface WishListService {
	public WishListDTO search(String query);
	public WishListDTO add(WishListDTO wishListDTO);
}
