package CsvReader;
import java.io.File;
import java.util.Iterator;

public class Reader implements Iterable<String[]>
{
    private final File file;

    public Reader(File file)
    {
        this.file = file;
    }

    @Override
    public Iterator<String[]> iterator()
    {
        return new CsvIterator(this.file);
    }
}








