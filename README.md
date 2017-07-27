## To run....

- `git clone https://github.com/abhirockzz/cdi-async-events.git` 
- `mvn clean install`
- `docker build -t abhirockzz/gf5-nightly -f Dockerfile_gf5_nightly .`
- `docker build -t abhirockzz/gf5-cdi-example -f Dockerfile_app .`
- `docker run --rm --name gf5cdi -it -p 8080:8080 -p 4848:4848 -p 8181:8181 abhirockzz/gf5-nightly-cdi-example`
- what's the Docker host IP ? `docker-machine ip` (e.g. 192.168.99.100)

## Test it...

- `http://<docker_ip>:8080/cdi-async-events/events/subscribe`. You should see a continuous stream of (SSE) events

![](https://abhirockzz.files.wordpress.com/2017/06/sse-output.jpg)

- Pick a [Websocket client](https://chrome.google.com/webstore/detail/simple-websocket-client/pfdhoblngboilpfeibdedpjgfnlcodoo?hl=en) and use it connect to the Websocket endpoint `ws://<docker_ip>:8080/cdi-async-events/`. You will see the same event stream...

![](https://abhirockzz.files.wordpress.com/2017/06/websocket-output.jpg)

*Try this with multiple clients - for both SSE and Websocket*. Console logs show the asynchronous nature of CDI events

Notice the asynchronous events running in Managed Executor service thread

![](https://abhirockzz.files.wordpress.com/2017/06/action-2.jpg)

You can choose to let things run in the default (container) chosen thread

![](https://abhirockzz.files.wordpress.com/2017/06/cdi-2-async-events-in-action.jpg)

## once you're done

`docker stop gf5cdi`
