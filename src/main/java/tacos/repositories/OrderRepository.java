package tacos.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tacos.entities.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
	
	List<TacoOrder> findByDeliveryZip(String deliveryZip);
	//List<TacoOrder> readOrderByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
	//List<TacoOrder> findByDeliveryCityOrderByDeliveryTo(String city);
	
	//@Query("Order o where o.deliveryCity='Seattle'")
	//List<TacoOrder> readOrdersDeliveredInSeattle();

}
