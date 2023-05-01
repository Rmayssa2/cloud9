package tn.esprit.cloudnine.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.cloudnine.Entities.Reclamation;


@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation,  Long> {

}

