package com.movie.service;

import com.movie.dto.UserDto;
import com.movie.model.User;
import com.movie.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Log4j2
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;


    /**
     * @param email kullanıcı adı olarak emaili alır.
     * @return User'ı databasede arar ve bulursa döndürür
     * @throws UsernameNotFoundException bulamaz ise hatası verir.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found :" + email));
    }

    /**
     * @param request UserDto alır
     * @return UserDto yu mapper ile User tipne çevirir ardından üyelik tipi verildiyse üyelik bitim tarihini ve şifreyi encodelayarak  set ettikten sonra kaydeder,
     * daha sonra tekrar mapper ile UserDto ya çevirip değeri geri döndürür.
     */
    public UserDto signUp(UserDto request) {
        User user = modelMapper.map(request, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        if (request.getMembershipType() != null) {
            user.setExpiringTime(switch (request.getMembershipType()) {
                case FREE -> LocalDate.now();
                case ONE_MONTH -> LocalDate.now().plusMonths(1);
                case THREE_MONTH -> LocalDate.now().plusMonths(3);
                case SIX_MONTH -> LocalDate.now().plusMonths(6);
                case YEAR -> LocalDate.now().plusMonths(12);
            });
        }
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    /**
     * @param request UserDto nesnesini alır ve ad, soyad,  şifre
     * @return UserDto nesnesinin emailinden databaseden User'ı bulur.propertylerinden hangileri var ise günceller ardından mapper ile tekrar Userto döndürür.
     */
    public UserDto updateFullNameAndPassword(UserDto request) {
        var foundUser = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("user not found :" + request.getEmail()));
        if (request.getFirstName() != null) foundUser.setFirstName(request.getFirstName());
        if (request.getLastName() != null) foundUser.setLastName(request.getLastName());
        if (request.getPassword() != null) foundUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        return modelMapper.map(userRepository.save(foundUser), UserDto.class);
    }

    public HttpStatus login(UserDto request) {
        var found = userRepository.findByEmail(request.getEmail()).orElseThrow();
        if (Objects.equals(found.getPassword(), bCryptPasswordEncoder.encode(request.getPassword()))) {
            return HttpStatus.ACCEPTED;
        } else return HttpStatus.BAD_REQUEST;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return mapList(users);
    }


    /**
     * @param users dönüştürelecek tipte liste
     * @return targetClass tipte liste döndürür.
     */
    private List<UserDto> mapList(List<User> users) {
        return users
                .stream()
                .map(element -> modelMapper.map(element, UserDto.class))
                .toList();
    }
}
