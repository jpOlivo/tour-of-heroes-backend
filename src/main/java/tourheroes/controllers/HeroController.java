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

import tourheroes.models.Hero;
import tourheroes.repositories.HeroRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class HeroController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HeroController.class);	

	@Autowired
	private HeroRepository heroRepository;
	
	@GetMapping("/heroes")
	public List<Hero> getHeroes() {
		LOGGER.info("Getting heroes");
		
		Sort sortByNameDesc = new Sort(Sort.Direction.DESC, "name");		
		return heroRepository.findAll(sortByNameDesc);
	}

	@GetMapping(value = "/heroes/{id}")
	public ResponseEntity<Hero> getHero(@PathVariable("id") String id) {
		LOGGER.info("Getting hero with id: " + id);
		
		return heroRepository.findById(id)
                .map(hero -> ResponseEntity.ok().body(hero))
                .orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/heroes")
	public Hero addHero(@Valid @RequestBody Hero hero) {
		LOGGER.info("Adding new hero");
		
        return heroRepository.save(hero);
	}

	@PutMapping(value = "/heroes/{id}")
	public ResponseEntity<Hero> updateHero(@PathVariable("id") String id, @Valid @RequestBody Hero heroData) {
		LOGGER.info("Updating hero with id: " + id);
		
		return heroRepository.findById(id)
                .map(hero -> {
                	hero.setName(heroData.getName());
                    Hero updatedHero = heroRepository.save(hero);
                    return ResponseEntity.ok().body(updatedHero);
                }).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "/heroes/{id}")
	public ResponseEntity<?> deleteHero(@PathVariable("id") String id) {
		LOGGER.info("Deleting hero with id: " + id);
		
		return heroRepository.findById(id)
                .map(hero -> {
                	heroRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
	}
}
