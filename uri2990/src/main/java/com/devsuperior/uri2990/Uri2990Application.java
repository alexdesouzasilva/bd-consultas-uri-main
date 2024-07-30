package com.devsuperior.uri2990;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import com.devsuperior.uri2990.repositories.EmpregadoRepository;

@SpringBootApplication
public class Uri2990Application implements CommandLineRunner {

	@Autowired
	private EmpregadoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2990Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<EmpregadoDeptProjection> list1 = repository.search1();
		List<EmpregadoDeptDTO> result1 = list1.stream().map(x -> new EmpregadoDeptDTO(x)).toList();
		
		System.out.println("\n\n **SQL RAIZ: ");
		for (EmpregadoDeptDTO e : result1) {
			System.out.println(e);
		}

		List<EmpregadoDeptDTO> result2 = repository.search2();
		
		System.out.println("\n\n **JPQL: ");
		for (EmpregadoDeptDTO e : result2) {
			System.out.println(e);
		}
	}
}
