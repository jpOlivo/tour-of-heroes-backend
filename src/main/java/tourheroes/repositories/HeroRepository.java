package tourheroes.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tourheroes.models.Hero;

@Repository
public interface HeroRepository extends MongoRepository<Hero, String> {

}