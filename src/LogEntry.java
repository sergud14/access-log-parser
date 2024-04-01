import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
        splitLine = logLine.split("\"");
        String meth=splitLine[1];
        splitLine = meth.split(" ");
        meth=splitLine[0];
        meth=meth.replace("\"","");
        this.method = HttpMethod.valueOf(meth);
        splitLine = logLine.split(" ");
        String in=splitLine[8];
        this.responseCode =Integer.parseInt(splitLine[8]);
        this.responseSize =Integer.parseInt(splitLine[9]);
        this.path = splitLine[6];
        splitLine = logLine.split("\"");
        String sd=splitLine[1];
        this.referer = splitLine[3];
        this.userAgent = splitLine[5];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss").withLocale(Locale.US);;
        splitLine = logLine.split("\\[");
        String dt=splitLine[1].substring(0,20);
        this.time = LocalDateTime.parse(dt,formatter);
    }
}
