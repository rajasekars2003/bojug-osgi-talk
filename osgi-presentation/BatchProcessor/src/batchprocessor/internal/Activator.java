package batchprocessor.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	Thread processOrdersThread;
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println("Batch processing started");
		processOrdersThread = new Thread(new ProcessOrders());
		processOrdersThread.start();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		processOrdersThread.interrupt();
		processOrdersThread.join();
		System.out.println("Batch processing stoped");
	}

}
