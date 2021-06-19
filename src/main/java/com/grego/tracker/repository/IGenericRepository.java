package com.grego.tracker.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@NoRepositoryBean
public interface IGenericRepository<T, ID> extends ReactiveCrudRepository<T, ID> {

}
