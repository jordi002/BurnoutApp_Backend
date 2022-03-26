package com.TFG.TFG.Repository;

import com.TFG.TFG.Models.GoldbergResults;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface GoldbergResultsRepository extends PagingAndSortingRepository<GoldbergResults, String> {
    GoldbergResults findById(Long id);
    List<GoldbergResults> findAll();
}
