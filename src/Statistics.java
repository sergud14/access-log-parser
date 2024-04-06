import java.time.LocalDateTime;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;

public class Statistics {
private long totalTraffic;
private long totalSystems;
private final HashSet<String> pathsSet =new HashSet<>();
private final  HashMap<String,Integer> systemFrequencyMap =new HashMap<String,Integer>();
private final  HashMap<String,Double> systemsRatesMap =new HashMap<String,Double>();
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
         getPathsSet(log);
         UserAgent userAgent =new UserAgent(log.userAgent);
         if(!userAgent.system.equals("")) {
             totalSystems++;
         }
        getSystemFrequencyMap(userAgent.system);
    }

    public HashSet<String> getPathsSet(LogEntry log){
        String ss= log.path;
        if(log.responseCode==200) pathsSet.add(log.path);
        return pathsSet;
    }

    public HashMap<String,Integer> getSystemFrequencyMap(String system){
        if(!systemFrequencyMap.containsKey(system)) {
            systemFrequencyMap.put(system, 1);
        }
        else
        {
            systemFrequencyMap.put(system, systemFrequencyMap.get(system)+1);
        }
        return systemFrequencyMap;
    }
    public HashMap<String,Double> getSystemsRateMap(){
        for(HashMap.Entry<String, Integer> entry : systemFrequencyMap.entrySet()) {
            String key = entry.getKey();
            systemsRatesMap.put(key,(double) systemFrequencyMap.get(key)/totalSystems);
        }
        return systemsRatesMap;
    }
    public double getTrafficRate(){
        Duration duration = Duration.between(minTime,maxTime);
        double diffInHours = duration.toHours();
        return (double) totalTraffic/diffInHours;
    }
}