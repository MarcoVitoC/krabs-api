package portfolio.krabs.api.controller.scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
public class AppScheduler {
  
  @Bean
  public Scheduler scheduler() {
    return Schedulers.fromExecutor(threadPoolTaskScheduler().getScheduledExecutor());
  }
  
  @Bean
  public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    threadPoolTaskScheduler.setPoolSize(10);
    
    return threadPoolTaskScheduler;
  }
}
