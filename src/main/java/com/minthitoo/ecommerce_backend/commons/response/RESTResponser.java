package com.minthitoo.ecommerce_backend.commons.response;

import com.minthitoo.ecommerce_backend.utils.enums.ErrorCodes;
import lombok.Data;

@Data
public class RESTResponser {

    String message;
    ErrorCodes errorCode;
    Object errors;
    Object data;
    String timeStamp;

}
