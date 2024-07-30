package com.devsuperior.uri2990.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.entities.Empregado;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

    @Query(nativeQuery = true, value = "select empregados.cpf, empregados.enome, departamentos.dnome " + 
        "from empregados " + 
        "inner join departamentos on empregados.dnumero = departamentos.dnumero " + 
        "where empregados.cpf not in ( " + 
        "select empregados.cpf " + 
        "from empregados " + 
        "inner join trabalha on trabalha.cpf_emp = empregados.cpf " + 
        ") " + 
        "order by empregados.cpf")
    List<EmpregadoDeptProjection> search1();


    @Query("SELECT new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(obj.cpf, obj.enome, obj.departamento.dnome) " +
        "FROM Empregado obj " + 
        "WHERE obj.cpf not in ( " + 
            "SELECT obj.cpf " + 
            "from Empregado obj " + 
            "inner join obj.projetosOndeTrabalha " + 
        ") " + 
        "order by obj.cpf")
    List<EmpregadoDeptDTO> search2();

    @Query(nativeQuery = true, value = "select empregados.cpf, empregados.enome, departamentos.dnome " + 
        "from empregados " + 
        "inner join departamentos on empregados.dnumero = departamentos.dnumero " + 
        "left join trabalha on trabalha.cpf_emp = empregados.cpf " + 
        "where trabalha.cpf_emp is null " +
        "order by empregados.cpf")
    List<EmpregadoDeptProjection> search3();

}
