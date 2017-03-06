package com.lyun.estate.rest.file;

import com.lyun.estate.biz.customer.def.CustomerDefine;
import com.lyun.estate.biz.customer.entity.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("files")
public class FileController {

    @GetMapping("test")
    public Customer test() {
        return new Customer().setSource(CustomerDefine.Source.CALLING)
                .setGender(CustomerDefine.Gender.F)
                .setIdentitySource(CustomerDefine.IdentitySource.ID_CARD);
    }


}
