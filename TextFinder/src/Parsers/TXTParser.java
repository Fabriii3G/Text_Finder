package Parsers;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
public class TXTParser implements Parser{
    File TXT;
    String TXTPath;
    public TXTParser(File TXTFile){
        this.TXT = TXTFile;
        this.TXTPath = this.TXT.getPath();
    }
    @Override
    public void parser() {
        try(BufferedReader reader = new BufferedReader(new FileReader(this.TXTPath))){
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }catch (IOException e){
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
