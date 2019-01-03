package TestNg_ex;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class test_group_anotation2 {
  @Test(groups="group_1",enabled = false)
  public void TC01() {
	  System.out.println("TC01");
  }
  @Test(groups="group_5")
  public void TC02() {
	  System.out.println("TC02");
  }
  
  @Test(groups="group_5")
  public void TC03() {
	  System.out.println("TC03");
  }
  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { "trang123", "abc" },
      new Object[] { "khanh123", "cba123" },
    };
  }
  @Test(dataProvider = "dp",groups="group_1")
  public void f(String n, String s) {
	  System.out.println("n:"+n + "... s:"+s);
  }
}
