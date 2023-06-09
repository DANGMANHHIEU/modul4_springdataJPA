package com.example.springdatarepository.formatter;

import com.example.springdatarepository.model.Province;
import com.example.springdatarepository.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Service
public class ProvinceFormatter implements Formatter<Province> {
    private IProvinceService provinceService;
    @Autowired
    public ProvinceFormatter(IProvinceService provinceServices) {
        this.provinceService = provinceServices;
    }
    @Override
    public Province parse(String text, Locale locale) throws ParseException {
        Optional<Province> provinceOptional = provinceService.findById(Long.parseLong(text));
        return provinceOptional.orElse(null);
    }

    @Override
    public String print(Province object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
