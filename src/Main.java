import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException{
        Statistics st= new Statistics();
        HashSet<String> sett= new HashSet<String>();
        HashSet<String> sett2= new HashSet<String>();
        HashMap<String,Double> map1=new HashMap<String,Double>();
        HashMap<String,Double> map2=new HashMap<String,Double>();
        int i=0;
        while(true)
        {
            System.out.println("Введите путь к файлу:");
            String path = new Scanner(System.in).nextLine();
            File file= new File(path);
            boolean fileExists=file.exists();
            boolean isDirectory=file.isDirectory();

            if(!fileExists||isDirectory)
            {
                System.out.println("Указанный файл не существует или указанный путь является путём к папке, а не к файлу");
                continue;
            }
            else
            {
                i++;
                System.out.println("Путь указан верно");
                System.out.println("Это файл номер " + i);
            }
            int minLength = 1024;
            int maxLength = 0;
            int lineCount = 0;
/*            String ip="";
            String date="";
            String method="";
            String response="";
            String size="";
            String referer="";
            String userAgent="";
            String userAgentBot = "";
            int yandexBotCount = 0;
            int googleBotCount = 0;*/

            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader =
                        new BufferedReader(fileReader);
                String line;

                while ((line = reader.readLine()) != null) {
                    lineCount++;
                    int length = line.length();
                    if (length > maxLength) maxLength = length;
                    if (length < minLength) minLength = length;
                    LogEntry logEntry =new LogEntry(line);
                    st.addEntry(logEntry);
                    sett=st.getPathsSet(logEntry);
                    sett2=st.getNotFoundPathsSet(logEntry);

   /*                 String[] ss1 = line.split(" ");
                    ip = ss1[0];
                    date = ss1[3] + " " + ss1[4];
                    response = ss1[8];
                    size = ss1[9];

                    String[] ss2 = line.split("\"");
                    method = ss2[1];
                    referer = ss2[3];
                    userAgent = ss2[5];
*/
     /*               if(!userAgent.equals("-")) {
                        if(userAgent.contains("(")) {
                        String[] ss3 = userAgent.split("\\(");
                        String firstBrackets = ss3[1];

                        String[] parts = firstBrackets.split(";");
                        String fragment = parts[0];
                        if (parts.length >= 2) {
                            fragment = parts[1];
                        }
                        fragment = fragment.trim();
                        String[] ss4 = fragment.split("/");
                        userAgentBot = ss4[0];

                        if (userAgentBot.equals("YandexBot")) yandexBotCount++;
                        if (userAgentBot.equals("Googlebot")) googleBotCount++;*/
                    }
/*                    }*/

/*                    if(length>1024) throw new RuntimeException("Длина строки файла превышает 1024 символа");*/
                /*}*/
            } catch (Exception ex) {
               /*ex.printStackTrace();*/
            }
       /*     map1=st.getSystemsRateMap();
            map2=st.getBrowsersRateMap();*/
/*            System.out.println("Количество запросов от YandexBot: "+yandexBotCount );
            System.out.println("Количество запросов от Googlebot: "+ googleBotCount);
            System.out.println("Доля запросов от YandexBot: "+(double) yandexBotCount /lineCount);
            System.out.println("Доля запросов от Googlebot: "+(double) googleBotCount /lineCount);*/
            //System.out.println(st.getTrafficRate());
            //System.out.println(map1);
            //System.out.println(sett.toString());
     /*       System.out.println(sett2.toString());
            System.out.println(map2);*/
        }
    }
}