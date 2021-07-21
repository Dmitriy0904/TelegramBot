package commands;

public enum CommandsPackages {
    DEFAULT("commands.def."),
    FIND("commands.find."),
    HELP("commands.help."),
    SETTINGS("commands.settings."),
    START("commands.start.");
    
    private final String ROOT_PACKAGE;

    CommandsPackages(String ROOT_PACKAGE) {
        this.ROOT_PACKAGE = ROOT_PACKAGE;
    }

    public String getROOT_PACKAGE() {
        return ROOT_PACKAGE;
    }
}
