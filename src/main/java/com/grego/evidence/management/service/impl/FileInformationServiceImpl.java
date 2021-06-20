package com.grego.evidence.management.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.FormFieldPart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.grego.evidence.management.entity.Archivo;
import com.grego.evidence.management.repository.IFileRepository;
import com.grego.evidence.management.repository.IGenericRepository;
import com.grego.evidence.management.repository.model.FileInformation;
import com.grego.evidence.management.service.IFileInformationService;

import reactor.core.publisher.Mono;

@Service
public class FileInformationServiceImpl extends CrudImpl<FileInformation, Long> implements IFileInformationService{

	@Value("${ruta.upload}")
	private String RUTA_UPLOAD;
	
	@Autowired
	private IFileRepository repository;

	@Override
	protected IGenericRepository<FileInformation, Long> getRepository() {
		// TODO Auto-generated method stub
		return repository;
	}

	@Override
	public Mono<FileInformation> registrar(Archivo archivo) {
		// TODO Auto-generated method stub
		
		return repository.save(null);
	}

	@Override
	public Mono<FileInformation> registrar(Map<String, Part> archivo) {
		
		FormFieldPart idOrdera = (FormFieldPart) archivo.get("idOrder");
		Long idOrder = Long.parseLong(idOrdera.value());
		
		FilePart fp = (FilePart) archivo.get("file");		
		String name = RUTA_UPLOAD + UUID.randomUUID().toString() + "_" + fp.filename();		
		return Mono.just(fp).flatMap(x->{
			File filex = new File(name);
			fp.transferTo(filex).subscribe();
			return Mono.just(filex);			
		}).flatMap(x->{			
			Cloudinary c=new Cloudinary("cloudinary://795541727418228:Onuk4fgGvMeoj8mdpBgXDIADiz8@alexiae");
			Map<String, String> response = new HashMap<>();
			try {
				Map<String, String> parameter =  new HashMap<>();
				parameter.put("resource_type", "image");
				parameter.put("use_filename", "true");
				parameter.put("filename_override", x.getName());
				//ObjectUtils.asMap("resource_type", "image")
				response = c.uploader().upload(x, parameter );
				x.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}			
			return Mono.just(response);
		})
		.flatMap(x->
			repository.save(toFileInformation(x,idOrder))			
		);
	}

	@Override
	public Mono<FileInformation> registrar(Map<String, Part> archivo, Long id) {
		FilePart fp = (FilePart) archivo.get("files");		
		String name = RUTA_UPLOAD + UUID.randomUUID().toString() + "_" + fp.filename();		
		return Mono.just(fp).flatMap(x->{
			File filex = new File(name);
			fp.transferTo(filex).subscribe();
			return Mono.just(filex);			
		}).flatMap(x->{			
			Cloudinary c=new Cloudinary("cloudinary://795541727418228:Onuk4fgGvMeoj8mdpBgXDIADiz8@alexiae");
			Map<String, String> response = new HashMap<>();
			try {
				Map<String, String> parameter =  new HashMap<>();
				parameter.put("resource_type", "image");
				parameter.put("use_filename", "true");
				parameter.put("filename_override", x.getName());
				//ObjectUtils.asMap("resource_type", "image")
				response = c.uploader().upload(x, parameter );
				x.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}			
			return Mono.just(response);
		})
		.flatMap(x->
			repository.save(toFileInformation(x,id))			
		);
	}

	@Override
	public Mono<FileInformation> updateFile(Map<String, Part> archivo) {
		FormFieldPart idOrderForm = (FormFieldPart) archivo.get("idOrder");
		FormFieldPart idLastfileForm = (FormFieldPart) archivo.get("idLastfile");
		Long idOrder = Long.parseLong(idOrderForm.value());
		Long idLastfile = Long.parseLong(idLastfileForm.value());
		
		FilePart fp = (FilePart) archivo.get("file");		
		String name = RUTA_UPLOAD + UUID.randomUUID().toString() + "_" + fp.filename();		
		return Mono.just(fp).flatMap(x->{
			File filex = new File(name);
			fp.transferTo(filex).subscribe();
			return Mono.just(filex);			
		}).flatMap(x->{			
			Cloudinary c=new Cloudinary("cloudinary://795541727418228:Onuk4fgGvMeoj8mdpBgXDIADiz8@alexiae");
			Map<String, String> response = new HashMap<>();
			try {
				Map<String, String> parameter =  new HashMap<>();
				parameter.put("resource_type", "image");
				parameter.put("use_filename", "true");
				parameter.put("filename_override", x.getName());
				//ObjectUtils.asMap("resource_type", "image")
				response = c.uploader().upload(x, parameter );
				x.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}			
			return Mono.just(response);
		})
		.flatMap(x->
			repository.save(toFileInformationExist(x,idOrder,idLastfile))			
		);
	}
	
	private FileInformation toFileInformation(Map<String, String> archivo, Long idOrder) {
		
		return FileInformation
				.builder()
				.signature(archivo.get("signature"))
				.format(archivo.get("format"))
				.resourcetype(archivo.get("resource_type"))
				.secureurl(archivo.get("secure_url"))
				.idorder(idOrder)
				.publicid(archivo.get("public_id"))
				.originalfilename(archivo.get("original_filename"))
				.registryday(LocalDateTime.now())
				.build();		
	}	
	
	private FileInformation toFileInformationExist(Map<String, String> archivo, Long idOrder, Long idLastfile) {
		
		return FileInformation
				.builder()
				.id(idLastfile)
				.signature(archivo.get("signature"))
				.format(archivo.get("format"))
				.resourcetype(archivo.get("resource_type"))
				.secureurl(archivo.get("secure_url"))
				.idorder(idOrder)
				.publicid(archivo.get("public_id"))
				.originalfilename(archivo.get("original_filename"))
				.registryday(LocalDateTime.now())
				.build();		
	}
}
