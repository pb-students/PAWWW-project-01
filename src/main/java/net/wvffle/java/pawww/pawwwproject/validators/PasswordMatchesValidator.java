package net.wvffle.java.pawww.pawwwproject.validators;

import net.wvffle.java.pawww.pawwwproject.annotations.PasswordMatches;
import net.wvffle.java.pawww.pawwwproject.db.user.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        User user = (User) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
