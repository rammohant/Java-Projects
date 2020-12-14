import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TempConvertTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();



    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test

    public void checkValidityFahrenheitTest(){
        assertEquals("When calling checkValidity(100.00,\"Fahrenheit\") we",true, TempConvert.checkValidity(100.00, "Fahrenheit"));
        assertEquals("When calling checkValidity(-500.00,\"Fahrenheit\") we",false,TempConvert.checkValidity(-500.00, "Fahrenheit"));
        assertEquals("When calling checkValidity(1024.00,\"Fahrenheit\") we",false,TempConvert.checkValidity(1024.00, "Fahrenheit"));
    }

    @Test
    public void checkValidityCelsiusTest(){
        assertEquals("When calling checkValidity(100.00,\"Celsius\") we",true,TempConvert.checkValidity(100.00, "Celsius"));
        assertEquals("When calling checkValidity(-500.00,\"Celsius\") we",false,TempConvert.checkValidity(-500.00, "Celsius"));
        assertEquals("When calling checkValidity(1024.00,\"Celsius\") we",false,TempConvert.checkValidity(1024.00, "Celsius"));
    }


    @Test
    public void checkValidityKelvinTest(){
        assertEquals("When calling checkValidity(100.00,\"Kelvin\") we",true,TempConvert.checkValidity(100.00, "Kelvin"));
        assertEquals("When calling checkValidity(-10.00,\"Kelvin\") we",false,TempConvert.checkValidity(-10.00, "Kelvin"));
        assertEquals("When calling checkValidity(1024.00,\"Kelvin\") we",false,TempConvert.checkValidity(1024.00, "Kelvin"));
    }

    @Test
    public void checkValidityInvalidScaleTest(){
        assertEquals("When calling checkValidity(100.00,\"Planck\") we",false,TempConvert.checkValidity(100.00, "Planck"));
        assertEquals("When calling checkValidity(-500.00,\"Planck\") we",false,TempConvert.checkValidity(-500.00, "Planck"));
        assertEquals("When calling checkValidity(1024.00,\"Planck\") we",false,TempConvert.checkValidity(1024.00, "Planck"));
    }


    @Test
    public void convertFahtoCelTest(){
        assertEquals("When calling convertFahtoCel(212.0) we",100.0,TempConvert.convertFahtoCel(212.0),.01);
        assertEquals("When calling convertFahtoCel(81.5) we",27.5,TempConvert.convertFahtoCel(81.5),.01);
        assertEquals("When calling convertFahtoCel(-2.2) we",-19.0,TempConvert.convertFahtoCel(-2.2),.01);
    }

    @Test
    public void convertFahtoKelTest(){
        assertEquals("When calling convertFahtoKel(212.0) we",373.0,TempConvert.convertFahtoKel(212.0),.01);
        assertEquals("When calling convertFahtoKel(32.1) we",273.05,TempConvert.convertFahtoKel(32.1),.01);
        assertEquals("When calling convertFahtoKel(-0.2) we",255.11,TempConvert.convertFahtoKel(-0.2),.01);
    }

    @Test
    public void convertCeltoFahTest(){
        assertEquals("When calling convertCeltoFah(100.0) we",212.0,TempConvert.convertCeltoFah(100.0),.01);
        assertEquals("When calling convertCeltoFah(28.2) we",82.76,TempConvert.convertCeltoFah(28.2),.01);
        assertEquals("When calling convertCeltoFah(-4.9) we",23.18,TempConvert.convertCeltoFah(-4.9),.01);
    }

    @Test
    public void convertCeltoKelTest(){
        assertEquals("When calling convertCeltoKel(100.0) we",373.0,TempConvert.convertCeltoKel(100.0),.01);
        assertEquals("When calling convertCeltoKel(12.8) we",285.8,TempConvert.convertCeltoKel(12.8),.01);
        assertEquals("When calling convertCeltoKel(-0.9) we",272.1,TempConvert.convertCeltoKel(-0.9),.01);
    }

    @Test
    public void convertKeltoCelTest(){
        assertEquals("When calling convertKeltoCel(100.0) we",100.0,TempConvert.convertKeltoCel(373.0),.01);
        assertEquals("When calling convertKeltoCel(12.8) we",17.0,TempConvert.convertKeltoCel(290),.01);
        assertEquals("When calling convertKeltoCel(-0.9) we",-163.0,TempConvert.convertKeltoCel(110),.01);
    }

    @Test
    public void convertKeltoFahTest(){
        assertEquals("When calling convertKeltoFah(100.0) we",212.0,TempConvert.convertKeltoFah(373.0),.01);
        assertEquals("When calling convertKeltoFah(12.8) we",35.6,TempConvert.convertKeltoFah(275),.01);
        assertEquals("When calling convertKeltoFah(-0.9) we",-315.4,TempConvert.convertKeltoFah(80),.01);
    }


    @Test
    public void multiInputTest(){
        String input = "68.5\nFahrenheit\nKelvin\nYes\n22.1\nCelsius\nFahrenheit\nlol\n300\nKelvin\nFahrenheit\nlol2\n10\nKelvin\nKelvin\nlol2\n-20\nKelvin\nFahrenheit\nquit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        TempConvert.main(null);
        String[] rawOutput = outContent.toString().split("\n");
        assertEquals("When checking the printed output we","68.50 degrees Fahrenheit is 293.28 degrees Kelvin",rawOutput[3].trim());
        assertEquals("When checking the printed output we","22.10 degrees Celsius is 71.78 degrees Fahrenheit",rawOutput[8].trim());
        assertEquals("When checking the printed output we","300.00 degrees Kelvin is 80.60 degrees Fahrenheit",rawOutput[13].trim());
        assertEquals("When checking the printed output we","10.00 degrees Kelvin is 10.00 degrees Kelvin",rawOutput[18].trim());
        assertEquals("When checking the printed output we","-20.0 degrees Kelvin is not a valid temperature",rawOutput[23].trim());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

}