import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException{

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

            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader =
                        new BufferedReader(fileReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    lineCount++;
                    int length = line.length();
                    if(length>maxLength)maxLength=length;
                    if(length<minLength)minLength=length;
                    if(length>1024) throw new RuntimeException("Длина строки файла превышает 1024 символа");
                }
            } catch (Exception ex) {
               ex.printStackTrace();
            }
            System.out.println("Общее количество строк в файле: "+lineCount);
            if(lineCount>0) {
                System.out.println("Длина самой длинной строки в файле: " + maxLength);
                System.out.println("Длина самой короткой строки в файле: " + minLength);
            }
        }
    }
}