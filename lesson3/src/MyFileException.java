import java.io.PrintWriter;
import java.io.StringWriter;

public class MyFileException extends Exception {

    public MyFileException(String errorMessage) {
        super(errorMessage);
    }

    public String getStackTraceAsString() {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        this.printStackTrace(pw);

        return sw.toString();
    }
}