package Parsers;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class TXTParser implements Parser{
    File TXT;
    String TXTPath;
    public TXTParser(File TXTFile){
        this.TXT = TXTFile;
        this.TXTPath = this.TXT.getPath();
    }
    @Override
    public String parser() {
        try {
            Scanner scanner = new Scanner(this.TXT);
            StringBuilder parsedTextBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                parsedTextBuilder.append(scanner.nextLine()).append("\n");
            }
            String parsedText = parsedTextBuilder.toString();
            scanner.close();
            System.out.println(parsedText);
            return parsedText;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
