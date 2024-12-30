package com.sweat.model.profile

data class ProfileModel(
    val id: Int = 0,                      // 고유 ID
    val name: String = "",                 // 사용자 이름
    val sex: String = "",                     // 성별: Male, Female, Unlabeled
    val age: Int = 0,                     // 나이 (양수만 허용)
    val weight: Int = 0,                  // 몸무게 (양수만 허용)
    val createdAt: String = "",            // 생성 시간 (ISO8601 형식)
    val modifiedAt: String = "",           // 수정 시간 (ISO8601 형식)
    val serviceUser: Int = 0,             // 서비스 사용자 ID
    val image: String = "",                 // 이미지 URL
)

enum class Sex {
    MALE,
    FEMALE,
    UNLABELED,
}