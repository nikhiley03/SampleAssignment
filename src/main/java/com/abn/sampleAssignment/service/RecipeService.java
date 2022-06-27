package com.abn.sampleAssignment.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abn.sampleAssignment.model.Recipe;
import com.abn.sampleAssignment.repository.RecipeRepo;


@Service
public class RecipeService {
	
	@Autowired
	private RecipeRepo recipeRepo;

	public List<Recipe> getAllRecipe() {
		return recipeRepo.findAll();
	}
	
	public List<Recipe> getFilterByType(String type) {
		return this.recipeRepo.findByType(type);
	}
	
	public Recipe getRecipeById(Long recipeId) {
		return this.recipeRepo.findByRecipeId(recipeId);
	}
	
	public List<Recipe> getFilterByDateRange(String fromDate, String toDate) {
		return this.recipeRepo.getFilterByDateRange(fromDate, toDate);
	}
	
	public List<String> getIngredientAsList(Long recipeId) {
		String ingredient = this.recipeRepo.findByRecipeId(recipeId).getIngredients();
		return Arrays.asList(ingredient.split(","));
	}
	
	public Long addRecipe(Recipe recipe) throws Exception{
		this.recipeRepo.save(recipe);
		return recipe.getRecipeId();
	}
	
	public boolean removeRecipe(Long recipeId) throws Exception {
		this.recipeRepo.deleteById(recipeId);	
			return true;
	}
	
	public String getRecipeType(Long recipeId) throws Exception{	
		String recipeType=this.recipeRepo.getRecipeType(recipeId);
		return recipeType;
	}
	
	public String getInstruction(Long recipeId) throws Exception {
		Recipe recipe=this.recipeRepo.findByRecipeId(recipeId);
		return recipe.getInstruction();
		
	}
}
