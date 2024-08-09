package com.foodgo.apimodule.cuisine.dto;

import java.util.List;

public class RecipeDTO {

    public record Request(
            String rcpNm  // 메뉴명
    ) {}

    // 전체 응답을 나타내는 클래스
    public record TotalResponse (
            CookRcp COOKRCP01
    ) {
        public record CookRcp(
                String total_count,
                List<RecipeDetail> row,  // 여러 개의 레시피 데이터를 포함하는 리스트
                Result RESULT
        ) {}
    }

    // 개별 레시피 데이터를 나타내는 클래스
    public record RecipeDetail(
            String RCP_SEQ,              // 일련번호
            String RCP_NM,               // 메뉴명
            String RCP_WAY2,             // 조리방법
            String RCP_PAT2,             // 요리종류
            String INFO_WGT,             // 중량(1인분)
            String INFO_ENG,             // 열량
            String INFO_CAR,             // 탄수화물
            String INFO_PRO,             // 단백질
            String INFO_FAT,             // 지방
            String INFO_NA,              // 나트륨
            String HASH_TAG,             // 해쉬태그
            String ATT_FILE_NO_MAIN,     // 메인 이미지 경로
            String ATT_FILE_NO_MK,       // 서브 이미지 경로
            String RCP_PARTS_DTLS,       // 재료 정보
            String MANUAL01,             // 조리법 1
            String MANUAL02,             // 조리법 2
            String MANUAL03,             // 조리법 3
            String MANUAL04,             // 조리법 4
            String MANUAL05,             // 조리법 5
            String MANUAL06,             // 조리법 6
            String MANUAL07,             // 조리법 7
            String MANUAL08,             // 조리법 8
            String MANUAL09,             // 조리법 9
            String MANUAL10,             // 조리법 10
            String MANUAL11,             // 조리법 11
            String MANUAL12,             // 조리법 12
            String MANUAL13,             // 조리법 13
            String MANUAL14,             // 조리법 14
            String MANUAL15,             // 조리법 15
            String MANUAL16,             // 조리법 16
            String MANUAL17,             // 조리법 17
            String MANUAL18,             // 조리법 18
            String MANUAL19,             // 조리법 19
            String MANUAL20,             // 조리법 20
            String MANUAL_IMG01,         // 조리법 이미지 1
            String MANUAL_IMG02,         // 조리법 이미지 2
            String MANUAL_IMG03,         // 조리법 이미지 3
            String MANUAL_IMG04,         // 조리법 이미지 4
            String MANUAL_IMG05,         // 조리법 이미지 5
            String MANUAL_IMG06,         // 조리법 이미지 6
            String MANUAL_IMG07,         // 조리법 이미지 7
            String MANUAL_IMG08,         // 조리법 이미지 8
            String MANUAL_IMG09,         // 조리법 이미지 9
            String MANUAL_IMG10,         // 조리법 이미지 10
            String MANUAL_IMG11,         // 조리법 이미지 11
            String MANUAL_IMG12,         // 조리법 이미지 12
            String MANUAL_IMG13,         // 조리법 이미지 13
            String MANUAL_IMG14,         // 조리법 이미지 14
            String MANUAL_IMG15,         // 조리법 이미지 15
            String MANUAL_IMG16,         // 조리법 이미지 16
            String MANUAL_IMG17,         // 조리법 이미지 17
            String MANUAL_IMG18,         // 조리법 이미지 18
            String MANUAL_IMG19,         // 조리법 이미지 19
            String MANUAL_IMG20,         // 조리법 이미지 20
            String RCP_NA_TIP            // 저감 조리법 TIP
    ) {}

    // 결과 메시지를 나타내는 클래스
    public record Result(
            String MSG,
            String CODE
    ) {}
}
