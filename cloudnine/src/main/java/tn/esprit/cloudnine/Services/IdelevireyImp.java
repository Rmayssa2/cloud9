package tn.esprit.cloudnine.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.cloudnine.Entities.Delivery;
import tn.esprit.cloudnine.Repositories.DeliveryRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class IdelevireyImp implements Idelevirey {
DeliveryRepository deliveryRepository;
    @Override
    public Delivery addDelivery(Delivery delivery)
    {
        return deliveryRepository.save(delivery);
    }
    @Override
    public Delivery updateDelivery(Delivery  iddelivery)
    {

            return deliveryRepository.save(iddelivery);

    }
    @Override
    public void removedelivery(int delivery) {
        Delivery delivery1=deliveryRepository.findById(delivery).orElse(null);
        deliveryRepository.delete(delivery1);
    }


    @Override
    public List<Delivery> retrieveDeliverys() {

        return deliveryRepository.findAll();
    }


}
