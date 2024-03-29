package com.cookeasy.repository;

import com.cookeasy.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    Optional<RecipeEntity> findByTitle(String title);

    @Query(value = "SELECT r FROM RecipeEntity AS r WHERE r.levelEntity.id = :levelId ORDER BY r.categoryEntity.id ASC, r.createdOn DESC")
    List<RecipeEntity> findAllByLevelEntityByOrderByCategoryIdAsc(@Param(value = "levelId") Long id);
}