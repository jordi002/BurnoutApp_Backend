package com.TFG.TFG.Repository;


import com.TFG.TFG.Models.HAlimentacio;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HAlimentacioRepository extends PagingAndSortingRepository<HAlimentacio, String> {
    HAlimentacio findById(Long id);

}
