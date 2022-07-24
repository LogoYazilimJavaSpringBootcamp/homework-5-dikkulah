package com.movie.service;


import com.google.common.hash.Hashing;
import com.movie.client.PaymentClient;
import com.movie.dto.CommentDto;
import com.movie.dto.MovieDto;
import com.movie.dto.PaymentDto;
import com.movie.dto.UserDto;
import com.movie.model.Comment;
import com.movie.model.Movie;
import com.movie.model.User;
import com.movie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PaymentClient paymentClient;


    /**
     * @param request UserDto alır
     * @return UserDto yu mapper ile User tipne çevirir ardından üyelik tipi verildiyse üyelik bitim tarihini ve şifreyi encodelayarak  set ettikten sonra kaydeder,
     * daha sonra tekrar mapper ile UserDto ya çevirip değeri geri döndürür.
     */
    public UserDto register(UserDto request) {
        User user = modelMapper.map(request, User.class);
        user.setPassword(Hashing.sha256().hashString(request.getPassword(), StandardCharsets.UTF_8).toString());
        PaymentDto paymentDto = new PaymentDto();
        if (request.getMembershipType() != null) {
            switch (request.getMembershipType()) {
                case FREE -> {
                    user.setExpiringTime(LocalDate.now());
                    user.setAddingMovieRight(3);
                    paymentDto.setAmount(BigDecimal.valueOf(0));
                }
                case ONE_MONTH -> {
                    user.setExpiringTime(LocalDate.now().plusMonths(1));
                    paymentDto.setAmount(BigDecimal.valueOf(50));
                }
                case THREE_MONTH -> {
                    user.setExpiringTime(LocalDate.now().plusMonths(3));
                    paymentDto.setAmount(BigDecimal.valueOf(140));
                }
                case SIX_MONTH -> {
                    user.setExpiringTime(LocalDate.now().plusMonths(6));
                    paymentDto.setAmount(BigDecimal.valueOf(270));
                }
                case YEAR -> {
                    user.setExpiringTime(LocalDate.now().plusMonths(12));
                    paymentDto.setAmount(BigDecimal.valueOf(500));
                }
            }
            log.info("Userın üyelik tipine göre fiyat ,sona erme tarihi ve ekleme hakkı tanımlandı.");
            paymentDto.setUserEmail(user.getEmail());
            paymentDto.setPaymentTime(LocalDateTime.now());
            paymentDto.setCardNumber(request.getPayment().getCardNumber());
            paymentDto.setCurrency(request.getPayment().getCurrency());
            paymentDto.setSecurityCode(request.getPayment().getSecurityCode());
            paymentClient.createPayment(paymentDto);
            log.info("ödeme yapıldı");
        }
        /*
          ödeme bilgilerini payment cliente kaydeder.
         */
        PaymentDto returnedPayment = paymentClient.getPaymentByEmail(paymentDto.getUserEmail()).getBody();
        UserDto savedUserDto = modelMapper.map(userRepository.save(user), UserDto.class);
        savedUserDto.setPayment(returnedPayment);
        log.info("user kaydedildi.");
        return savedUserDto;
    }

    /**
     * @param request UserDto nesnesini alır ve ad, soyad,  şifre, yeni şifre
     * @return UserDto nesnesinin emailinden databaseden User'ı bulur.propertylerinden hangileri var ise günceller ardından mapper ile tekrar Userdto döndürür.
     */
    public UserDto updateFullNameAndPassword(UserDto request) {
        var foundUser = userRepository.findByEmail(request.getEmail()).orElseThrow(RuntimeException::new);
        if (Objects.equals(login(request), "LOGGED IN")) {
            if (request.getFirstName() != null) foundUser.setFirstName(request.getFirstName());
            if (request.getLastName() != null) foundUser.setLastName(request.getLastName());
            if (request.getNewPassword() != null)
                foundUser.setPassword(Hashing.sha256().hashString(request.getNewPassword(), StandardCharsets.UTF_8).toString());
            log.info("Password changed and full name updated");
        } else {
            log.info("Password not chanced");
        }
        return modelMapper.map(userRepository.save(foundUser), UserDto.class);
    }

    /**
     * @param request userDto nesnesinin email ve password propertilerini alır ve hashledikten sonra şifreleri kontrol eder.
     * @return ver geriye string döndürür.
     */
    public String login(UserDto request) {
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        if (Objects.equals(user.getPassword(), Hashing.sha256().hashString(request.getPassword(), StandardCharsets.UTF_8).toString())) {
            log.info("oturum aıldı.");
            return "LOGGED IN";
        } else return "ERROR";
    }

    /**
     * @return tüm userları dtoya çevirip listeler ve geri döndürür.
     */
    public List<UserDto> getAllUsers() {
        var list = mapList(userRepository.findAll(), UserDto.class);
        list.forEach(userDto -> {
            if (paymentClient.getPaymentByEmail(userDto.getEmail()).getBody() != null) {
                userDto.setPayment(paymentClient.getPaymentByEmail(userDto.getEmail()).getBody());
            }
            log.info("tüm filmler listelendi.");
        });
        return list;
    }


    /**
     * @param request bir userdto listesi alır
     * @return listeyi sıra ile register() donksiyonuna atar ve dönen değerleri yeni bir listeye kaydedip geri döndürür.
     */
    public List<UserDto> registerAll(List<UserDto> request) {
        List<UserDto> list = new ArrayList<>();
        request.forEach(userDto -> list.add(register(userDto)));
        log.info("tüm userlar kaydedildi");
        return list;
    }

    /**
     * @param email
     * @return payment clientten ödeme bilgilerini alır ve userı döndürür
     */
    public UserDto getUser(String email) {
        UserDto userDto = modelMapper.map(userRepository.findByEmail(email).orElseThrow(), UserDto.class);
        userDto.setPayment(paymentClient.getPaymentByEmail(email).getBody());
        log.info("user listelendi");
        return userDto;
    }

    public List<CommentDto> getCommentsOfUser(Long userId) {
        List<Comment> comments = new ArrayList<>();
        log.info("asdasd");
        userRepository.findById(userId).ifPresent(user -> comments.addAll(user.getComments()));
        log.info("userın yorumları listelendi.");
        return mapList(comments, CommentDto.class);
    }

    public List<MovieDto> getMyMovies(String email, String password) {
        List<Movie> movies = new ArrayList<>();
        userRepository.findByEmail(email).ifPresent(user -> {
            if (Objects.equals(user.getPassword(), Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString())) {
                movies.addAll(user.getMovies());
                log.info("userın filmleri listelendi.");
            } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ŞİFRE YADA KULLANICI ADI YANLIŞ");
        });
        return mapList(movies, MovieDto.class);


    }
    // Liste için Dto-Entity dönüşümü.
    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .toList();
    }

}
