package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import entity.list.ApplicantList;
import entity.list.ManagerList;
import entity.list.OfficerList;
import entity.user.Applicant;
import entity.user.Manager;
import entity.user.MaritalStatus;
import entity.user.Officer;
import entity.user.User;
import entity.user.UserType;
import exception.InvalidUserFormatException;
import exception.PasswordIncorrectException;
import exception.UserNotFoundException;

public class AccountController {
    private static String userID;

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String ID) {
        userID = ID;
    }

    public static void register(UserType userType, String userID, String password, String name, int age, MaritalStatus maritalStatus) throws InvalidUserFormatException {
        checkUserID(userID);
        if (userType == UserType.APPLICANT) ApplicantList.getInstance().add(new Applicant(userID, name, hashPassword(password), age, maritalStatus));
        else if (userType == UserType.OFFICER) OfficerList.getInstance().add(new Officer(userID, name, hashPassword(password), age, maritalStatus));
        else ManagerList.getInstance().add(new Manager(userID, name, hashPassword(password), age, maritalStatus));
        System.out.println("Registration completed.");
    }

    public static User findUser(String userID) throws UserNotFoundException {
        for (Applicant u : ApplicantList.getInstance().getAll()) {
            if (u.getUserID() == userID) return u;
        }
        for (Officer o : OfficerList.getInstance().getAll()) {
            if (o.getUserID() == userID) return o;
        }
        for (Manager m : ManagerList.getInstance().getAll()) {
            if (m.getUserID() == userID) return m;
        }
        throw new UserNotFoundException();
    }

    public static User login(String userID, String password) throws UserNotFoundException, PasswordIncorrectException {
        User user = findUser(userID);
        if (hashPassword(password).equals(user.getHashedPassword())) {
            setUserID(userID);
            return user;
        }
        else throw new PasswordIncorrectException();
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            return Base64.getEncoder().encodeToString(digest.digest(password.getBytes()));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error: SHA3-256 algorithm not found.", e);
        }
    }

    private static boolean checkUserID(String userID) throws InvalidUserFormatException {
        if (userID.matches("^[ST]\\d{7}[A-Z]$")) return true;
        throw new InvalidUserFormatException();
    }

    private static void changePassword(String userID, String password, String oldPassword, String newPassword) throws UserNotFoundException, PasswordIncorrectException {
        User user = findUser(userID);
        if (hashPassword(password).equals(user.getHashedPassword())) {
            user.setHashedPassoword(hashPassword(newPassword));
        }
        else throw new PasswordIncorrectException();
    }
}
