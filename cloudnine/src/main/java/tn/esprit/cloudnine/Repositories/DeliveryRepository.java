package tn.esprit.cloudnine.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.cloudnine.Entities.Delivery;


@Repository
public interface DeliveryRepository  extends JpaRepository<Delivery,Integer> {
}
