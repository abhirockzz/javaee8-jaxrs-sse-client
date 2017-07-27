## To run....

- `git clone https://github.com/abhirockzz/javaee8-jaxrs-sse-client.git` 
- `mvn clean install` - will create `javaee8-jaxrs-sse-client.war` in `target`
- Create Docker image - `docker build -t <tag> .`
- Run ! `docker run --rm --name javaee8-jaxrs-sse-client -it -p 8080:8080 <tag>`

## Test it...

Let things start up (20-30 seconds) - you should see SSE events being consumed from `https://sse.now.sh`

![](https://abhirockzz.files.wordpress.com/2017/07/sse-client-output.jpg)
