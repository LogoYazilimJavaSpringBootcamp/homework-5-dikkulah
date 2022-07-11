package com.movie.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseService {
    private static final ModelMapper modelMapper = new ModelMapper();



    /**
     * @param source dönüştürelecek tipte liste
     * @return targetClass tipte liste döndürür.
     */
    static  <S, T>  List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .toList();
    }
}
