package tourheroes.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tourheroes.models.Hero;
import tourheroes.repositories.HeroRepository;

@RestController
@RequestMapping("/api")
@Api("/api/heroes")
@CrossOrigin("*")
public class HeroController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HeroController.class);	

	@Autowired
	private HeroRepository heroRepository;
	
	@GetMapping("/heroes")
	@ApiOperation(value = "Return a list of heroes.", notes = "The list is order desc by name.", response = Hero[].class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = Hero[].class) })
	public List<Hero> getHeroes() {
		LOGGER.info("Getting heroes");
		
		Sort sortByNameDesc = new Sort(Sort.Direction.DESC, "name");		
		return heroRepository.findAll(sortByNameDesc);
	}

	@GetMapping(value = "/heroes/{id}")
	@ApiOperation(value = "Return a hero.", notes = "The hero returned contains the id specified in the path parameter.")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success", response = Hero.class),
        @ApiResponse(code = 404, message = "Not found")
    })
	public ResponseEntity<Hero> getHero(
			@ApiParam(required = true, name = "id", value = "ID of the hero you want return") 
			@PathVariable("id") String id) {
		LOGGER.info("Getting hero with id: " + id);
		
		return heroRepository.findById(id)
                .map(hero -> ResponseEntity.ok().body(hero))
                .orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/heroes")
	@ApiOperation(value = "Create a new hero.", response = Hero.class)
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success", response = Hero.class),
        @ApiResponse(code = 400, message = "Bad request")
    })
	public Hero addHero(
			@ApiParam(required = true, name = "hero", value = "New hero.")
			@Valid @RequestBody Hero hero) {
		LOGGER.info("Adding new hero");
		
        return heroRepository.save(hero);
	}

	@PutMapping(value = "/heroes/{id}")
	@ApiOperation(value = "Update an existing hero.", response = Hero.class)
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success", response = Hero.class),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found")
    })
	public ResponseEntity<Hero> updateHero(
			@ApiParam(required = true, name = "id", value = "ID of the hero you want to update.") 
			@PathVariable("id") String id, 
			@ApiParam(required = true, name = "hero", value = "Updated hero.")
			@Valid @RequestBody Hero heroData) {
		LOGGER.info("Updating hero with id: " + id);
		
		return heroRepository.findById(id)
                .map(hero -> {
                	hero.setName(heroData.getName());
                    Hero updatedHero = heroRepository.save(hero);
                    return ResponseEntity.ok().body(updatedHero);
                }).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "/heroes/{id}")
	@ApiOperation(value = "Delete a existing hero.")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success", response = Hero.class),
        @ApiResponse(code = 404, message = "Not found")
    })
	public ResponseEntity<?> deleteHero(
			@ApiParam(required = true, name = "id", value = "ID of the hero you want to delete.") 
			@PathVariable("id") String id) {
		LOGGER.info("Deleting hero with id: " + id);
		
		return heroRepository.findById(id)
                .map(hero -> {
                	heroRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
	}
}
