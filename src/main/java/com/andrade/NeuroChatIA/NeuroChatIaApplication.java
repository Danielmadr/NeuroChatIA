package com.andrade.NeuroChatIA;

import com.andrade.NeuroChatIA.application.AskChampionUseCase;
import com.andrade.NeuroChatIA.application.ListChampionsUseCase;
import com.andrade.NeuroChatIA.domain.ports.ChampionsRepository;
import com.andrade.NeuroChatIA.domain.ports.GenerativeAiApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class NeuroChatIaApplication {

  public static void main(String[] args) {
    SpringApplication.run(NeuroChatIaApplication.class, args);
  }

  @Bean
  public ListChampionsUseCase provideListChampionsUseCase(ChampionsRepository championsRepository) {
    return new ListChampionsUseCase(championsRepository);
  }

  @Bean
  public AskChampionUseCase provideAskChampionUseCase(ChampionsRepository championsRepository, GenerativeAiApiService generativeAiApi) {
    return new AskChampionUseCase(championsRepository, generativeAiApi);
  }
}
