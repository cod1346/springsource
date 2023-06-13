package com.example.restaurant.wishList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restaurant.wishList.entity.WishListEntity;

@Repository
public interface WishListReposiroty extends JpaRepository<WishListEntity, Long> {

}
