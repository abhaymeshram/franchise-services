package com.ooredoo.franchise.repository;

import com.ooredoo.franchise.entity.Franchise;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long> {

    List<Franchise> findAll(Specification<Franchise> specification);
}
