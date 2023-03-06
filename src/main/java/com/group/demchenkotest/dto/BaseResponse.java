package com.group.demchenkotest.dto;

import com.group.demchenkotest.data.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse implements Serializable {
    private Message message;
}
