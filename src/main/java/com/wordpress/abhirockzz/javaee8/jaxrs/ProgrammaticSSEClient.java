package com.wordpress.abhirockzz.javaee8.jaxrs;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

@Singleton
@Startup
public class ProgrammaticSSEClient {

    Client sseClient;
    WebTarget target;

    @Resource
    TimerService tsvc;

    @PostConstruct
    public void init() {
        this.sseClient = ClientBuilder.newClient();
        this.target = this.sseClient.target("https://sse.now.sh");

        tsvc.createSingleActionTimer(15000, null);
        System.out.println("SSE client timer created");

        eventSource = SseEventSource.target(target).build();
        System.out.println("SSE Event source created........");
    }

    SseEventSource eventSource;

    @Timeout
    public void client() {
        System.out.println("SSE Client triggered in thread "+ Thread.currentThread().getName());

        try {
            eventSource.register((sseEvent)
                    -> {
                System.out.println("Events received in thread " + Thread.currentThread().getName());
                System.out.println("SSE event recieved ----- " + sseEvent.readData());
            },
                    (e) -> e.printStackTrace());

            eventSource.open();
            System.out.println("Source open ????? " + eventSource.isOpen());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @PreDestroy
    public void close() {
        eventSource.close();
        System.out.println("Closed SSE Event source..");
        sseClient.close();
        System.out.println("Closed JAX-RS client..");
    }
}
