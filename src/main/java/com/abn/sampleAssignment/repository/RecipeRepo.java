package com.abn.sampleAssignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abn.sampleAssignment.model.Recipe;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Long>{
	
	public List<Recipe> findByType(String type);
	
	@Query(value="select * FROM recipe WHERE creation_Date > TO_DATE( :fromDate ,'YYYY-MM-DD' ) AND creation_Date < TO_DATE(:toDate , 'YYYY-MM-DD');", nativeQuery = true)
	public List<Recipe> getFilterByDateRange(String fromDate ,String toDate);
	
	public  Recipe findByRecipeId(Long recipeId);
	
	@Query(value="select type from recipe where recipeId=:recipeId", nativeQuery = true)
	public String getRecipeType(Long recipeId);
	

}
