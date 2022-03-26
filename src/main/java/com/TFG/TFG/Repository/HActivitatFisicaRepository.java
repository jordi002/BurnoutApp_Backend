package com.TFG.TFG.Repository;

import com.TFG.TFG.Models.HActivitatFisica;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HActivitatFisicaRepository extends PagingAndSortingRepository<HActivitatFisica, String> {
    HActivitatFisica findById(Long id);
}
