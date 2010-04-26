package order.internal;

import inventory.InventoryService;

import order.OrderService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	ServiceReference inventoryServiceReference;
	ServiceRegistration orderServiceRegistration;

	public void start(BundleContext context) throws Exception {
	 	inventoryServiceReference 
	 		= context.getServiceReference(InventoryService.class.getName());
	 	InventoryService inventoryService = (InventoryService) context.getService(inventoryServiceReference);
	 	
	 	orderServiceRegistration = context.registerService(OrderService.class.getName(), 
	 			new OrderServiceImpl(inventoryService), null);
	}

	public void stop(BundleContext context) throws Exception {
		orderServiceRegistration.unregister();
		context.ungetService(inventoryServiceReference);
	}

}
