package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.Environment;
import reactor.bus.EventBus;

import static reactor.bus.selector.Selectors.$;


@SpringBootApplication
public class Application implements ApplicationRunner {

	@Autowired
	private EventBus eventBus;

	@Autowired
	private  Receiver receiver;

	@Bean
	Environment env() {
		return Environment.initializeIfEmpty()
				.assignErrorJournal();
	}

	@Bean
	EventBus createEventBus(Environment env) {
		return EventBus.create(env, Environment.WORK_QUEUE);
	}



	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		eventBus.on($("logger"), receiver);
	}
}

