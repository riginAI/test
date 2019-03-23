package junit;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;

import hw3.PrintPrimes;

public class TestPara {

	ByteArrayOutputStream out;
    PrintStream ps;
    public PrintPrimes primes = new PrintPrimes();
    int [] print = {2,3,5,7,11};
    
    @Before
    public void setUp(){
        out = new ByteArrayOutputStream();
        ps = new PrintStream(out);
        System.setOut(ps);
    }

    @Test
    public void test() throws IOException {
        out.flush();
        primes.printPrimes(5);
        assertEquals("Prime: 2\r\nPrime: 3\r\nPrime: 5\r\nPrime: 7\r\nPrime: 11\r\n", out.toString());

 

 }
}
