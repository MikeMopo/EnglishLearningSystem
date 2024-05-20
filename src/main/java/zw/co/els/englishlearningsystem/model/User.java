package zw.co.els.englishlearningsystem.model;

public record User(
        String fullName,
        String idNumber,
        String phoneNumber,
        String gender,
        String age,
        String englishLevel,
        String username,
        String password
) {}
