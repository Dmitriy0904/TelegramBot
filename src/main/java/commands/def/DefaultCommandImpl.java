package commands.def;

import commands.Active;
import commands.IncorrectCommandReason;
import org.apache.commons.lang3.StringUtils;

@Active
public class DefaultCommandImpl implements DefaultCommand {
	@Override
    public String formDefaultAnswer(String reason){
	    if(StringUtils.isBlank(reason)){
            return IncorrectCommandReason.INCORRECT_COMMAND.getReason();
        }
	    return reason;
	}
}
