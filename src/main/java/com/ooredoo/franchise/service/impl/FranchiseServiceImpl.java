package com.ooredoo.franchise.service.impl;

import com.ooredoo.franchise.entity.Franchise;
import com.ooredoo.franchise.repository.FranchiseRepository;
import com.ooredoo.franchise.service.FranchiseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FranchiseServiceImpl.class);

    @Autowired
    FranchiseRepository franchiseRepository;

    @Override
    public List<Franchise> getAll() {
        return franchiseRepository.findAll();
    }

    @Override
    public Franchise save(Franchise franchise) {
        LOGGER.info("Request received to save franchise with name {}, idNumber {} ", franchise.getApplicantsName(), franchise.getIdNumber());
        return franchiseRepository.save(franchise);
    }

    @Override
    public Franchise update(Franchise franchise) {
        LOGGER.info("Request received to update franchise with name {}, id {} ", franchise.getApplicantsName(), franchise.getId());
        return franchiseRepository.save(franchise);
    }

    @Override
    public List<Franchise> getAllWithFilter(Franchise franchise) {
        LOGGER.info("Request received to get franchise {} ", franchise);
        if (franchise != null) {
            List<Franchise> franchises = franchiseRepository.findAll(searchSpec(franchise));
            LOGGER.info("Franchise fetched {}", franchises.size());
            return franchises;
        }

        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        LOGGER.info("Request received to delete franchise with id {} ", id);
        try {
            franchiseRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Boolean.FALSE;
        }
    }

    private Specification<Franchise> searchSpec(Franchise franchise) {
        return new Specification<Franchise>() {
            @Override
            public Predicate toPredicate(Root<Franchise> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (franchise.getApplicantsName() != null) {
                    predicates.add(cb.like(root.get("applicantsName"), "%" + franchise.getApplicantsName() + "%"));
                }

                if (franchise.getIdNumber() != null) {
                    predicates.add(cb.like(root.get("idNumber"), "%" + franchise.getIdNumber() + "%"));
                }

                if (franchise.getContactEmail() != null) {
                    predicates.add(cb.like(root.get("contactEmail"), "%" + franchise.getContactEmail() + "%"));
                }

                if (franchise.getContactPhone() != null) {
                    predicates.add(cb.like(root.get("contactPhone"), "%" + franchise.getContactPhone() + "%"));
                }

                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
