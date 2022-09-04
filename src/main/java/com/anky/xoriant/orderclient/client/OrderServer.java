package com.anky.xoriant.orderclient.client;

import com.anky.xoriant.orderclient.exceptions.ClientDataException;
import com.anky.xoriant.orderclient.exceptions.ServiceException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@Component
public class OrderServer {

    private WebClient orderServerWebClient;

    public OrderServer(@Qualifier("orderServerWebClient") WebClient orderServerWebClient) {
        this.orderServerWebClient = orderServerWebClient;
    }

    @SneakyThrows
    public <K> Mono<K> get(
            String path,
            Class<K> responseClass) {
        return orderServerWebClient.get()
                .uri(path)
                .headers(c -> c.setContentType(MediaType.APPLICATION_JSON))
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == 404 || httpStatus.value() == 400,
                        this::handle404And400ErrorResponse
                )
                .onStatus(
                        httpStatus -> httpStatus.value() == 500,
                        response -> Mono.error(new ServiceException(
                                response.bodyToMono(String.class).toString(), response.rawStatusCode()
                        ))
                )
                .bodyToMono(responseClass)
                .retryWhen(Retry.backoff(10, Duration.ofSeconds(15)).filter(ServiceException.class::isInstance));
    }

    private Mono<? extends Throwable> handle404And400ErrorResponse(ClientResponse clientResponse){
        Mono<String> errorResponseMono = clientResponse.bodyToMono(String.class);
        return errorResponseMono.flatMap(message -> {
            log.error(message);
            throw new ClientDataException(message);
        });
    }

}
