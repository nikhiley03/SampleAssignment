package com.abn.sampleAssignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abn.sampleAssignment.model.Recipe;
import com.abn.sampleAssignment.model.AppUser;
import com.abn.sampleAssignment.service.CustomUserService;
import com.abn.sampleAssignment.service.RecipeService;

@RestController
@RequestMapping("/RecipeManagement")
public class RecipeController {

	@Autowired
	RecipeService recipeServiceObj;
	
	@Autowired
	CustomUserService userServiceObj;

	@GetMapping("/getAllRecipe")
	public ResponseEntity<List<Recipe>> getAllRecipe() {
		try {
			List<Recipe> allRecipes = recipeServiceObj.getAllRecipe();
			if (allRecipes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(allRecipes, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getRecipeById")
	public ResponseEntity<Recipe> getRecipeById(@RequestParam(required = true) Long recipeId) {
		try {
			Recipe recipe = recipeServiceObj.getRecipeById(recipeId);
			if (recipe==null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(recipe, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@GetMapping("/getFilterBYType")
	public ResponseEntity<List<Recipe>> getFilterBYType(@RequestParam(required = true) String type) {
		try {
			List<Recipe> allRecipes = recipeServiceObj.getFilterByType(type);
			if (allRecipes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(allRecipes, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getFilterByDateRange")
	public ResponseEntity<List<Recipe>> getFilterByDateRange(@RequestParam(required = true) String fromDate,
			@RequestParam(required = true) String toDate) {
		try {
			List<Recipe> allRecipes = recipeServiceObj.getFilterByDateRange(fromDate, toDate);
			if (allRecipes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(allRecipes, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getDishingredients")
	public ResponseEntity<List<String>> getDishingredients(@RequestParam(required = true) Long recipeId) {

		try {
			List<String> ingredients = recipeServiceObj.getIngredientAsList(recipeId);
			if (ingredients.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(ingredients, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getCookingInstruction")
	public ResponseEntity<String> getCookingInstruction(@RequestParam(required = true) Long recipeId) {

		try {
			String instruction = recipeServiceObj.getInstruction(recipeId);
			if (instruction.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(instruction, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping
	public ResponseEntity<Integer> geUserCountForRecipe(@RequestParam(required = true) Long recipeId){
		try {
		String type=recipeServiceObj.getRecipeType(recipeId);
		int userCount=userServiceObj.getUserCountByPreferance(type);
		return new ResponseEntity<Integer>(userCount, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/addRecipe")
	public ResponseEntity<Long> addRecipe(@RequestBody Recipe recipe) {
		try {
			long recipeId = recipeServiceObj.addRecipe(recipe);
			if (recipeId == -1) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
			return new ResponseEntity<>(recipeId, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PreAuthorize("hasRole('Admin')")
	@DeleteMapping("/removeRecipe")
	public ResponseEntity<Boolean> removeRecipe(@RequestParam(required = true) Long recipeId) {
		try {
			boolean operation = recipeServiceObj.removeRecipe(recipeId);
			if (!operation) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
			return new ResponseEntity<>(operation, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/getAllUser")
	public ResponseEntity<List<AppUser>> getAllUser() {
		try {
			List<AppUser> allUser = userServiceObj.getAllUser();
			return new ResponseEntity<>(allUser, HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
	}

	@PreAuthorize("hasRole('Admin')")
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody AppUser user) {
		try {
			String operation = userServiceObj.addUser(user);
			if (!operation.equals("success")) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
			return new ResponseEntity<>(operation, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PreAuthorize("hasRole('Admin')")
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteUser(@RequestParam(required = true) String userId) {
		try {
			String operation = userServiceObj.deleteUser(userId);
			if (!operation.equals("success")) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
			return new ResponseEntity<>(operation, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
