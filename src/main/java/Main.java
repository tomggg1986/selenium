import SeleniumReader.SeleniumReader;

public class Main {

    public static void main(String[] args) {

        SeleniumReader seleniumReader = new SeleniumReader();
        seleniumReader.navigateToUrl("file:///C:\\Users\\golebiowskit\\Desktop\\testPage.html");
        seleniumReader.waitUntilPageIsReady();
//        seleniumReader.readValueFromInputById("firstName");
//        seleniumReader.setValueToInputById("lastName", "Golebiowski");

        System.out.println(seleniumReader.getTextFormElement("text"));

        seleniumReader.closeDriver();
    }
}
