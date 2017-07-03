package hello;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.bus.Event;
import reactor.fn.Consumer;


@Service
class Receiver implements Consumer<Event<Integer>> {


	RestTemplate restTemplate = new RestTemplate();

	public void accept(Event<Integer> ev) {
		LoggerResource loggerResource =
				restTemplate.getForObject("http://localhost:3000/logger", LoggerResource.class);
		System.out.println("Message fom logger " + loggerResource.getMessage());
	}

}