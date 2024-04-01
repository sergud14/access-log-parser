import java.time.LocalDateTime;
import java.time.Duration;
public class Statistics {
private long totalTraffic;
private LocalDateTime minTime;
private LocalDateTime maxTime;
    public Statistics() {
        this.totalTraffic=0;
        this.minTime=LocalDateTime.now();
        this.maxTime=LocalDateTime.of(1900,1,1,0,0);
    }
    public void addEntry(LogEntry log){
         if(log.time.isBefore(this.minTime)) this.minTime=log.time;
         if(log.time.isAfter(this.maxTime)) this.maxTime=log.time;
         totalTraffic=totalTraffic+log.responseSize;
    }
    public double getTrafficRate(){
        Duration duration = Duration.between(minTime,maxTime);
        double diffInHours = duration.toHours();
        return (double) totalTraffic/diffInHours;
    }
}
