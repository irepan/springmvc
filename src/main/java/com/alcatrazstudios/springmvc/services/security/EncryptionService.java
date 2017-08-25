package com.alcatrazstudios.springmvc.services.security;

/**
 * Created by irepan on 25/07/17.
 */
public interface EncryptionService {
    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
}