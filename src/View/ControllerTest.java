package View;

import org.junit.Assert;

import static org.junit.Assert.*;

public class ControllerTest {
    @org.junit.Test
    public void checkPassword() {
        boolean ExpectedResult = true;
        String Input = "HieuDuong08";
        Assert.assertEquals("Error checking password!",ExpectedResult,Controller.CheckPassword(Input));
        System.out.println(Controller.CheckPassword(Input));
    }

}