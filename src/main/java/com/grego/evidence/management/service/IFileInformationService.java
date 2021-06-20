package com.grego.evidence.management.service;

import java.util.Map;

import org.springframework.http.codec.multipart.Part;

import com.grego.evidence.management.entity.Archivo;
import com.grego.evidence.management.repository.model.FileInformation;

import reactor.core.publisher.Mono;

public interface IFileInformationService extends ICrud<FileInformation, Long> {

	Mono<FileInformation> registrar(Archivo archivo);
	Mono<FileInformation> registrar(Map<String, Part> archivo);
	Mono<FileInformation> updateFile(Map<String, Part> archivo);
	Mono<FileInformation> registrar(Map<String, Part> archivo, Long id);
}
