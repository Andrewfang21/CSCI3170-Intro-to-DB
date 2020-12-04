import cli.MainCLI;

public class Main {
    public static void main(String[] args) {
        try{
            MainCLI mainCLI = new MainCLI();
            mainCLI.runCLI();
        } catch (Exception e) {
    		;
    	}
    }
}