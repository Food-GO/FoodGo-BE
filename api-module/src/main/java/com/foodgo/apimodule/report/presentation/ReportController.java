package com.foodgo.apimodule.report.presentation;

import com.foodgo.apimodule.report.application.ReportFindUseCase;
import com.foodgo.commonmodule.common.ApplicationResponse;
import com.foodgo.coremodule.report.dto.ReportComparisonDTO;
import com.foodgo.coremodule.security.annotation.UserResolver;
import com.foodgo.coremodule.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/report")
@Tag(name = "report", description = "리포트 관련 API")
public class ReportController {

    private final ReportFindUseCase reportFindUseCase;

    @GetMapping()
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "리포트 통계 확인 성공",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "리포트 통계 확인 API", description = "리포트 통계 확인 API 입니다.")
    public ApplicationResponse<ReportComparisonDTO> findReportStatistics(
            @UserResolver User user) {

        ReportComparisonDTO comparisonDTO = reportFindUseCase.getStatistics(user);
        return ApplicationResponse.onSuccess(comparisonDTO);
    }

}
