package com.TFG.TFG.Repository;


import com.TFG.TFG.Models.MaslachResults;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MaslachResultsRepository extends PagingAndSortingRepository<MaslachResults, String>{
    MaslachResults findById(Long id);
    List<MaslachResults> findAll();
}
