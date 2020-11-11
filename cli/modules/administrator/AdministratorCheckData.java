package cli.modules.administrator;

import cli.CLIInterface;
import service.AdministratorService;

public class AdministratorCheckData implements CLIInterface {
    private AdministratorService service;
    
    public AdministratorCheckData(AdministratorService service) {
        this.service = service;
    }

    @Override
    public void runCLI() {
        execute();
    }

    private void execute() {
        service.checkData();
    }
}
