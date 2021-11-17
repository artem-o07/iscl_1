package com.example.iscl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {
    public static class _400_ErrorException extends ResponseStatusException {
        public _400_ErrorException() {
            super(HttpStatus.BAD_REQUEST, "bad_request");
        }
    }

    public static class _404_ErrorException extends ResponseStatusException {
        public _404_ErrorException() {
            super(HttpStatus.NOT_FOUND, "not found");
        }
    }

    public static class _403_ErrorException extends ResponseStatusException {
        public _403_ErrorException() {
            super(HttpStatus.FORBIDDEN, "error");
        }
    }

    public static class _409_ErrorException extends ResponseStatusException {
        public _409_ErrorException() {
            super(HttpStatus.CONFLICT, "conflict");
        }
    }

    private final List<User> users = new ArrayList<>();

    @PostMapping("users")
    public void addUser(@RequestBody String name, @RequestBody String password, @RequestBody int age) {
        try {
            if (users.size() >= 1) {
                for (User user : users) {
                    if (user.getName().equals(name)) {
                        throw new _409_ErrorException();
                    }
                }
            }
            int m = 0;
            for (int i = 0; i < name.length(); i++) {
                for (int j = '0'; j <= '9'; j++) {
                    if (name.charAt(i) == j) {
                        m = m + 1;
                    }
                }
                for (int j = 'a'; j <= 'z'; j++) {
                    if (name.charAt(i) == j) {
                        m = m + 1;
                    }
                }
                for (int j = 'A'; j <= 'Z'; j++) {
                    if (name.charAt(i) == j) {
                        m = m + 1;
                    }
                }
            }
            if (m != name.length()) {
                throw new _400_ErrorException();
            }
            users.add(new User(name, password, age));
        } catch (_409_ErrorException e){
            System.out.println("error409");
        } catch (_404_ErrorException e){
            System.out.println("error404");
        }
    }

    @GetMapping("users/{name}")
    public User getUser(@PathVariable("name") String name) {
            int n = -1;
        try {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getName().equals(name)) {
                    n = i;
                }
            }
            if (n == -1) {
                throw new _404_ErrorException();
            }
        } catch (_404_ErrorException e) {
            System.out.println("error404");
        }
        return users.get(n);
    }
    @DeleteMapping("users/{name}")
    public void DeleteUser(@PathVariable("name") String name){
        int n=-1;
        try {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getName().equals(name)) {
                    n = i;
                }
            }
            if (!name.contains("admin")) {
                throw new _403_ErrorException();
            }
            if (n == -1) {
                throw new _404_ErrorException();
            }
            users.remove(n);
        } catch (_403_ErrorException e) {
            System.out.println("error403");
        } catch (_404_ErrorException e) {
            System.out.println("error404");
        }

    }
    @PutMapping("users/{name}")
    public void updatePassword(@PathVariable("name") String name,@RequestBody String password){
        int n=-1;
         try {
             for (int i = 0; i < users.size(); i++) {
                 if (users.get(i).getName().equals(name)) {
                     n = i;
                 }
             }
             int m = 0;
             for (int i = 0; i < name.length(); i++) {
                 for (int j = '0'; j <= '9'; j++) {
                     if (name.charAt(i) == j) {
                         m = m + 1;
                     }
                 }
                 for (int j = 'a'; j <= 'z'; j++) {
                     if (name.charAt(i) == j) {
                         m = m + 1;
                     }
                 }
                 for (int j = 'A'; j <= 'Z'; j++) {
                     if (name.charAt(i) == j) {
                         m = m + 1;
                     }
                 }
             }
             if (n == -1) {
                 throw new _404_ErrorException();
             }
             if (!name.contains("update")) {
                 throw new _403_ErrorException();
             }
             if (m != name.length()) {
                 throw new _400_ErrorException();
             }
             users.get(n).setPassword(password);
         } catch (_404_ErrorException e) {
             System.out.println("error404");
         } catch (_403_ErrorException e) {
             System.out.println("error403");
         } catch (_400_ErrorException e) {
             System.out.println("error400");
         }
    }
}