package com.elepy.admin.services;

import com.elepy.admin.models.User;
import com.elepy.evaluators.ObjectEvaluator;
import com.elepy.exceptions.ElepyException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserEvaluator implements ObjectEvaluator<User> {
    @Override
    public void evaluate(User user, Class<User> cls) {
        String username = user.getUsername();

        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(username);

        if (username.contains(" ") || m.find()) {
            throw new ElepyException("Usernames can not contain spaces or special characters!");
        }

    }
}
