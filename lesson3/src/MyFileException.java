import java.io.PrintWriter;
import java.io.StringWriter;

public class MyFileException extends Exception {
    private StackTraceElement[] st;
    public MyFileException(String errorMessage, StackTraceElement[] st) {
        super(errorMessage);
    }

    public String getStackTraceAsString() {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        this.printStackTrace(pw);

        return sw.toString();
    }
}