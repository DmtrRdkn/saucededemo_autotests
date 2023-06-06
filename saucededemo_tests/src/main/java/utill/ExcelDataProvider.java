package utill;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider
    public Object [][] correctLogins() throws Exception {
        String path = "src/test/resources/test_data.xlsx";
        ExcelReader excelReader = new ExcelReader(path,"Correct_logins");
        return excelReader.getData();
    }

    @DataProvider
    public Object [][] lockedLogins() throws Exception {
        String path = "src/test/resources/test_data.xlsx";
        ExcelReader excelReader = new ExcelReader(path,"Locked_logins");
        return excelReader.getData();
    }

    @DataProvider
    public Object [][] emptyLogin() throws Exception {
        String path = "src/test/resources/test_data.xlsx";
        ExcelReader excelReader = new ExcelReader(path,"empty_login");
        return excelReader.getData();
    }

    @DataProvider
    public Object [][] emptyPassword() throws Exception {
        String path = "src/test/resources/test_data.xlsx";
        ExcelReader excelReader = new ExcelReader(path,"empty_password");
        return excelReader.getData();
    }

    @DataProvider
    public Object [][] incorrectData() throws Exception {
        String path = "src/test/resources/test_data.xlsx";
        ExcelReader excelReader = new ExcelReader(path,"incorrect_data");
        return excelReader.getData();
    }


}
