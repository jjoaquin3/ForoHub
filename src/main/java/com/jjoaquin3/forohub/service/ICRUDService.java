package com.jjoaquin3.forohub.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface ICRUDService<T1, T2>
{
    T2 save(T1 entity);
    T2 update(Long id, T1 entity);
    void delete(Long id);
    T2 findById(Long id);
    Page<T2> findAll(Pageable pageable);
}
