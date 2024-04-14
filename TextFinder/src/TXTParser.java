import java.io.File;
import java.io.IOException;
public class TXTParser implements Parser{
    File TXT;
    String TXTPath;
    public TXTParser(File TXTFile){
        this.TXT = TXTFile;
        this.TXTPath = this.TXT.getPath();
    }
    @Override
    public void parser() {

    }
}
