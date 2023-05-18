package com.blandygbc.adopet.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ConstantUtils {

    public static final Pageable PAGEABLE = PageRequest.of(0, 10);
    public static final String NOT_FOUND_MESSAGE = "Não encontrado";
}
