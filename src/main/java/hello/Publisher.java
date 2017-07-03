package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.bus.EventBus;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class Publisher {

	@Autowired
	EventBus eventBus;


	public void newLogEntry() throws InterruptedException {
		long start = System.currentTimeMillis();
		AtomicInteger counter = new AtomicInteger(1);

		System.out.println("---> Start "+start);
		System.out.println("--------> before event");
		eventBus.notify("logger", Event.wrap(counter.getAndIncrement()));
		System.out.println("-------------------->after event ");

		long elapsed = System.currentTimeMillis() - start;

		System.out.println("Elapsed time: " + elapsed + "ms");
	}

}
