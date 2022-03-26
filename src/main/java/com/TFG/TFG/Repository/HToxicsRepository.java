package com.TFG.TFG.Repository;

import com.TFG.TFG.Models.HToxics;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HToxicsRepository extends PagingAndSortingRepository<HToxics, String>{
        HToxics findById(Long id);
}

