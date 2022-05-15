package com.gonggu.market.api.service;

import com.auth0.jwt.JWT;
import com.gonggu.market.api.config.jwt.JwtProperties;
import com.gonggu.market.api.domain.address.Address;
import com.gonggu.market.api.domain.address.AddressRepository;
import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.domain.user.UserRepository;
import com.gonggu.market.api.dto.user.UserProfileDto;
import com.gonggu.market.api.dto.user.UserUpdateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(@Autowired UserRepository userRepository,
                       @Autowired AddressRepository addressRepository,
                       @Autowired BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserProfileDto profile(String userId) {
        User userEntity = userRepository.findById(Long.valueOf(userId)).get();
        return new UserProfileDto(
                userEntity.getEmail(),
                userEntity.getNickname(),
                userEntity.getMobile(),
                userEntity.getDetailAddress(),
                userEntity.getAddress().getZipcode(),
                userEntity.getPoint()
        );
    }

    public UserProfileDto detail(String userId, String token) {
        token = token.replace(JwtProperties.TOKEN_PREFIX, "");
        Long userIdFromToken = JWT.decode(token).getClaim("id").asLong();
        logger.info(userIdFromToken.toString());
        if (!Long.valueOf(userId).equals(userIdFromToken)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        User userEntity = userRepository.findById(Long.valueOf(userId)).get();
        return new UserProfileDto(
                userEntity.getEmail(),
                userEntity.getNickname(),
                userEntity.getMobile(),
                userEntity.getDetailAddress(),
                userEntity.getAddress().getZipcode(),
                userEntity.getPoint()
        );
    }
}
