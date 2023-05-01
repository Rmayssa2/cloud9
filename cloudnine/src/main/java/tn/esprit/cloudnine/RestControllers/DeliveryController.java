package tn.esprit.cloudnine.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.cloudnine.Entities.Delivery;
import tn.esprit.cloudnine.Services.Idelevirey;

import java.util.List;

@RestController
@RequestMapping("/delivery")
@AllArgsConstructor
public class DeliveryController {
Idelevirey idelevirey;
    @GetMapping("/getAlldeleviry")
    public List<Delivery> getAlldelivery() {
        return idelevirey.retrieveDeliverys();
    }

    @PostMapping("/adddelevirey")
    public Delivery adddelivery(@RequestBody Delivery delivery) {
        return idelevirey.addDelivery(delivery);
    }
    @DeleteMapping("/deletedelevirey/{iddelivery}")
    public void deletedelivery(@PathVariable("iddelivery") int iddelivery) {
        idelevirey.removedelivery(iddelivery);
    }


    @PutMapping("/updatedelevirey")
    public Delivery updatedelivery(@RequestBody Delivery delivery) {
        return idelevirey.updateDelivery(delivery);
    }

}
