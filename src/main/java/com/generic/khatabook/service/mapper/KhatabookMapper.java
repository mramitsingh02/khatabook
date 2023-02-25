package com.generic.khatabook.service.mapper;

import com.generic.khatabook.entity.KhatabookDTO;
import com.generic.khatabook.model.Khatabook;

public class KhatabookMapper {

    public static Khatabook mapToPojo(KhatabookDTO myKhatabookDTO) {

        if (myKhatabookDTO == null) {
            return null;
        }
        return new Khatabook(myKhatabookDTO.getBookId(), myKhatabookDTO.getKhatabookId(), myKhatabookDTO.getMsisdn(), myKhatabookDTO.getPartnerName(), myKhatabookDTO.getPartnerDescription());
    }

    public static KhatabookDTO mapToDTO(Khatabook myKhatabook) {
        if (myKhatabook == null) {
            return null;
        }
        return KhatabookDTO.builder().bookId(myKhatabook.bookId()).khatabookId(myKhatabook.khatabookId()).partnerName(myKhatabook.partnerName()).partnerDescription(myKhatabook.partnerDescription()).msisdn(myKhatabook.msisdn()).build();
    }
}
