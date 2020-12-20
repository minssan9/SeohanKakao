package com.message.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum RepositoryType {

    SEOHAN("SEOHAN", "seohanRepository" ),
    KAMTEC("KAMTEC", "kamtecRepository" ),
    KOFCO("KOFCO", "seohanRepository" ),
    ENP("ENP", "seohanRepository" );

    private final String company;
    private final String repoType;
//    private final String apiKey;

    private static Map<String, RepositoryType> companyTypeMap = new HashMap<>();
    static {
        for(RepositoryType repositoryType : values()){
            companyTypeMap.put(repositoryType.getCompany(), repositoryType);
        }
    }
    public static RepositoryType getRepositoryType(String company){
        return companyTypeMap.get(company);
    }


}
