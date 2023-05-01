package tn.esprit.cloudnine.Services;

import tn.esprit.cloudnine.Entities.Delivery;

import java.util.List;

public interface Idelevirey {
 public   Delivery addDelivery(Delivery delivery);

 public Delivery updateDelivery(Delivery  delivery);
 public void removedelivery(int  delivery);
 public List<Delivery> retrieveDeliverys();

}
