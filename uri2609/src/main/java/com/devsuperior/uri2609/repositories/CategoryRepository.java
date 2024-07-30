package com.devsuperior.uri2609.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.entities.Category;
import com.devsuperior.uri2609.projections.CategorySumProjection;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(nativeQuery = true, value = "select c.name, sum(p.amount) " 
        + "from categories c " 
        + "inner join products p on c.id = p.id_categories " 
        + "group by c.name")
    List<CategorySumProjection> search1();


    @Query("SELECT new com.devsuperior.uri2609.dto.CategorySumDTO(obj.category.name, SUM(obj.amount)) " 
        + "FROM Product obj " 
        + "group by obj.category.name")
    List<CategorySumDTO> search2();

}
