import java.time.LocalDateTime;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;

public class Statistics {
private long totalTraffic;
private long totalUsersRequests;
private long totalErrorRequests;
private long totalSystems;
private long totalBrowsers;
private final HashSet<String> pathsSet =new HashSet<>();
private final HashSet<String> nonBotsIPAddrSet =new HashSet<>();
private final HashSet<String> notFoundPathsSet =new HashSet<>();
private final  HashMap<String,Integer> systemFrequencyMap =new HashMap<String,Integer>();
private final  HashMap<String,Double> systemsRatesMap =new HashMap<String,Double>();
private final  HashMap<String,Integer> browserFrequencyMap =new HashMap<String,Integer>();
private final  HashMap<String,Double> browserRatesMap =new HashMap<String,Double>();
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
         getNotFoundPathsSet(log);
         UserAgent userAgent =new UserAgent(log.userAgent);
         if(!userAgent.system.isEmpty()) {
             totalSystems++;
         }

         if(!userAgent.browser.isEmpty()) {
            totalBrowsers++;
         }

        if(!userAgent.isBot(log.userAgent)) {
            totalUsersRequests++;
        }

        if(log.responseCode>=400)
        {
            totalErrorRequests++;
        }
        getSystemFrequencyMap(userAgent.system);
        getBrowserFrequencyMap(userAgent.browser);
        getNonBotsIPAddrSet(log);
    }

    public HashSet<String> getPathsSet(LogEntry log){
        String ss= log.path;
        if(log.responseCode==200) pathsSet.add(log.path);
        return pathsSet;
    }

    public HashSet<String> getNonBotsIPAddrSet(LogEntry log){
        UserAgent userAgent =new UserAgent(log.userAgent);
        if(!userAgent.isBot(log.userAgent)) nonBotsIPAddrSet.add(log.ipAddr);
        return nonBotsIPAddrSet;
    }

    public HashSet<String> getNotFoundPathsSet(LogEntry log){
        String ss= log.path;
        if(log.responseCode==404) notFoundPathsSet.add(log.path);
        return notFoundPathsSet;
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
    public HashMap<String,Integer> getBrowserFrequencyMap(String browser){
        if(!browserFrequencyMap.containsKey(browser)) {
            browserFrequencyMap.put(browser, 1);
        }
        else
        {
            browserFrequencyMap.put(browser, browserFrequencyMap.get(browser)+1);
        }
        return browserFrequencyMap;
    }
    public HashMap<String,Double> getBrowsersRateMap(){
        for(HashMap.Entry<String, Integer> entry : browserFrequencyMap.entrySet()) {
            String key = entry.getKey();
            browserRatesMap.put(key,(double) browserFrequencyMap.get(key)/totalBrowsers);
        }
        return browserRatesMap;
    }
    public double getTrafficRate(){
        Duration duration = Duration.between(minTime,maxTime);
        double diffInHours = duration.toHours();
        return (double) totalTraffic/diffInHours;
    }

    public double getUsersRequestsPerHour(){
        Duration duration = Duration.between(minTime,maxTime);
        double diffInHours = duration.toHours();
        long i=totalUsersRequests;
        return (double) totalUsersRequests/diffInHours;
    }

    public double getErrorRequestsPerHour(){
        Duration duration = Duration.between(minTime,maxTime);
        double diffInHours = duration.toHours();
        return (double) totalErrorRequests/diffInHours;
    }

    public double getUniqueUserRequestsPerHour(){
        return (double) totalUsersRequests/nonBotsIPAddrSet.size();
    }
}