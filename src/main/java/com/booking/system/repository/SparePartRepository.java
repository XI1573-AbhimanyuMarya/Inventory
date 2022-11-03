package com.booking.system.repository;

import com.booking.system.repository.enitty.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SparePartRepository extends JpaRepository<SparePart, String> {
}
