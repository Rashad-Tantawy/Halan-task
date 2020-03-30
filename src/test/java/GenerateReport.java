import org.automationtesting.excelreport.Xl;

public class GenerateReport {

    public static void main(String[] args) throws Exception
    {
       Xl.generateReport("excel-report.xlsx");
       Xl.generateReport("C://Users//RashadTantawy//IdeaProjects//Halan_API_Testting//test-output//testng-results.xml", "excel-report.xlsx");

    }
}