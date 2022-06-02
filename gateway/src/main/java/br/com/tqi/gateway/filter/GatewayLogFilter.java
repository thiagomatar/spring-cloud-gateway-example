package br.com.tqi.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClientResponse;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.CLIENT_RESPONSE_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

@Component
public class GatewayLogFilter extends Config implements GatewayFilterFactory<Config> {

    private static final Logger log = LoggerFactory.getLogger(GatewayLogFilter.class);

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {
            logHttpRequest(exchange);
            return chain.filter(exchange).then(logHttpResponse(exchange));
        };
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    private void logHttpRequest(ServerWebExchange exchange) {
        log.info("Http method={} to={} route={} headers={}",
                exchange.getRequest().getMethod(),
                exchange.getRequest().getURI(),
                exchange.getRequest().getPath(),
                exchange.getRequest().getHeaders()
        );
    }

    private Mono<Void> logHttpResponse(ServerWebExchange exchange) {
        return Mono.fromRunnable(() -> {
            if (isThereAnHttpError(exchange)) {

                final var route = (Route) exchange.getAttributes().get(GATEWAY_ROUTE_ATTR);
                final var clientResponse = (HttpClientResponse) exchange.getAttributes().get(CLIENT_RESPONSE_ATTR);

                log.error("Http routeId={} statusCode={} from={} to={} headers={}",
                        route.getId(),
                        exchange.getResponse().getStatusCode(),
                        exchange.getRequest().getURI(),
                        route.getUri() + clientResponse.uri(),
                        exchange.getResponse().getHeaders()
                );
            }
        });
    }

    private boolean isThereAnHttpError(ServerWebExchange exchange) {
        if (exchange.getResponse().getStatusCode() != null) {
            return HttpStatus.Series.SUCCESSFUL != exchange.getResponse().getStatusCode().series();
        } else {
            return true;
        }
    }
}