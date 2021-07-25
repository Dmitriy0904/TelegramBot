package commands.find;

import answer.UserRequest;

public interface FindCommand {
    String formFindAnswer(UserRequest request);
}
