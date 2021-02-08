package com.ooredoo.franchise.service;

import com.ooredoo.franchise.entity.Franchise;

import java.util.List;

public interface FranchiseService {

    List<Franchise> getAll();

    Franchise save(Franchise franchise);

    Franchise update(Franchise franchise);

    List<Franchise> getAllWithFilter(Franchise franchise);

    boolean delete(Long id);
}
