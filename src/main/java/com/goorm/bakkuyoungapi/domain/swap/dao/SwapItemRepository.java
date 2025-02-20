package com.goorm.bakkuyoungapi.domain.swap.dao;

import com.goorm.bakkuyoungapi.domain.swap.domain.SwapItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwapItemRepository extends JpaRepository<SwapItem, Long> {
}
