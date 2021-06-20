package com.grego.evidence.management.repository;

import com.grego.evidence.management.repository.model.FileInformation;

public interface IFileRepository extends IGenericRepository<FileInformation, Long> {

	//Mono<FileInformation> findByIdOrder(Long idOrder);
}
