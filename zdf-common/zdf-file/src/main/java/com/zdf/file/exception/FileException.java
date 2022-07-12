package com.zdf.file.exception;

import com.zdf.file.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FileException extends RuntimeException{

    ExceptionEnum exceptionEnum;

}
