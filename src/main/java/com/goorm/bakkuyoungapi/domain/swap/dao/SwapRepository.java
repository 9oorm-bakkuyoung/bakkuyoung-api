package com.goorm.bakkuyoungapi.domain.swap.dao;

import com.goorm.bakkuyoungapi.domain.swap.domain.Swap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwapRepository extends JpaRepository<Swap, Long> {
}
