package tacos.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import tacos.entities.Ingredient;
import tacos.entities.Ingredient.Type;
import tacos.entities.Taco;
import tacos.entities.TacoOrder;
import tacos.repositories.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
	private final IngredientRepository ingredientRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo ) {
		this.ingredientRepo = ingredientRepo;
	}
	
	
	@ModelAttribute
	public void addIngredientsToModel(Model  model) {
		List<Ingredient> ingredients = new ArrayList<>();
	    ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients,type));
		}
	}
	
	@ModelAttribute(name = "tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
	public String showDesignForm() {
		return "design";
	}
	
	private Iterable<Ingredient> filterByType(
		List<Ingredient> ingredients, Type type){
		return ingredients.stream().filter(x ->x.getType().equals(type)).collect(Collectors.toList());
		
	}
	
	@PostMapping
	public String  processTaco(@Valid Taco taco , Errors errors, @ModelAttribute TacoOrder tacoOrder ){
		tacoOrder.addTaco(taco);
		log.info("Processing taco:"+taco);
		return "redirect:/orders/current";
		
		
	}
	

}
