package com.rms.assignment.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.assignment.api.dto.UserDto;
import com.rms.assignment.api.dto.mapper.UserDtoMapper;
import com.rms.assignment.core.entity.User;
import com.rms.assignment.core.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDtoMapper userDtoMapper;

    @GetMapping(path = "{username}")
    public UserDto getByUsername(@PathVariable String username) {
        Optional<User> existingUsername = userService.getByUsername(username);
        return existingUsername.isPresent() ? userDtoMapper.toUserDto(existingUsername.get()) : null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return new ResponseEntity<UserDto>(userDtoMapper.toUserDto(userService.create(userDtoMapper.toUser(userDto))),
                HttpStatus.CREATED);
    }

}
