package commands.def;

import annotation.Active;
import answer.UserRequest;
import commands.IncorrectCommandReason;
import org.apache.commons.lang3.StringUtils;

@Active
public class DefaultCommandImpl implements DefaultCommand {
	@Override
    public String formDefaultAnswer(UserRequest request){
		String reason = request.getValue();

	    if(StringUtils.isBlank(reason)){
            return IncorrectCommandReason.INCORRECT_COMMAND.getReason();
        }
	    return reason;
	}
}
