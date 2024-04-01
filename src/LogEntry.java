import java.time.LocalDateTime;
public class LogEntry {
    final String ipAddr;
    final LocalDateTime time;
    final HttpMethod method;
    final String path;
    final int responseCode;
    final int responseSize;
    final String referer;
    final String userAgent;

    public String getIp(){
        return ipAddr;
    }
    public LocalDateTime getDateTime(){
        return time;
    }
    public HttpMethod getHttpMethod(){
        return method;
    }
    public String getPath(){
        return path;
    }
    public int getResponseCode(){
        return responseCode;
    }
    public int responseSize() {
        return responseSize;
    }
    public String getReferer() {
        return referer;
    }
    public String getUserAgent() {
        return userAgent;
    }

    public LogEntry(String logLine) {
        String[] splitLine = logLine.split(" ");
        this.ipAddr = splitLine[0];
        this.time = LocalDateTime.parse(splitLine[3] + " " + splitLine[4]) ;
        this.responseCode =Integer.parseInt(splitLine[8]);
        this.responseSize =Integer.parseInt(splitLine[9]);
        this.path = splitLine[6];
        splitLine = logLine.split("\"");
        this.method = HttpMethod.valueOf(splitLine[1]);
        this.referer = splitLine[3];
        this.userAgent = splitLine[5];
    }
}
