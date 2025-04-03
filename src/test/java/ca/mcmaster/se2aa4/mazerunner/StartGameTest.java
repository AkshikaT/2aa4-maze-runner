package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class StartGameTest {

    @Test
    public void testInvalidArguments() {        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        
        String[] args = {"./examples/small.maz.txt"};                                   // missing -i flag
        Game game = new Game(args);
        
        assertEquals("PATH NOT COMPUTED\n", outputStream.toString());
        
        System.setOut(System.out);
    }

    @Test
    public void testValidArguments() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        
        String[] args = {"-i","./examples/straight.maz.txt"};
        Game game = new Game(args);
        
        assertEquals("4F\n", outputStream.toString());
        
        System.setOut(System.out);
    }
    
}
