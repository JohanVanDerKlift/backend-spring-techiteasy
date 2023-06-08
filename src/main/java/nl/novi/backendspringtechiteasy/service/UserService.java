package nl.novi.backendspringtechiteasy.service;

import nl.novi.backendspringtechiteasy.dto.UserDto;
import nl.novi.backendspringtechiteasy.model.Authority;
import nl.novi.backendspringtechiteasy.model.User;
import nl.novi.backendspringtechiteasy.repository.UserRepository;
import nl.novi.backendspringtechiteasy.utils.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        List<UserDto> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(transferUserToDto(user));
        }
        return dtos;
    }

    public UserDto getUserByUsername(String username) {
        UserDto dto = new UserDto();
        User user = userRepository.findById(username).orElseThrow();
        return transferUserToDto(user);
    }

    public String createUser(UserDto dto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        dto.ApiKey = randomString;
        User user = new User();
        User newUser = userRepository.save(transferDtoToUser(dto, user));
        return newUser.getUsername();
    }

    public void updateUser(String username, UserDto dto) {
        User oldUser = userRepository.findById(username).orElseThrow();
        userRepository.save(transferDtoToUser(dto, oldUser));
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    //Authorities
    public Set<Authority> getAuthorities(String username) {
        User user = userRepository.findById(username).orElseThrow();
        UserDto dto = transferUserToDto(user);
        return dto.authorities;
    }

    public void addAuthority(String username, String authority) {
        User user = userRepository.findById(username).orElseThrow();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        User user = userRepository.findById(username).orElseThrow();
        Authority authRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authRemove);
        userRepository.save(user);
    }

    private UserDto transferUserToDto(User user) {
        UserDto dto = new UserDto();
        dto.username = user.getUsername();
        dto.password = user.getPassword();
        return dto;
    }

    private User transferDtoToUser(UserDto dto, User user) {
        user.setUsername(dto.username);
        user.setPassword(dto.password);
        return user;
    }
}
