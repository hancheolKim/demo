package com.erp.controller;

import com.erp.service.ItemService;
import com.erp.service.PaymentService;
import com.erp.util.PagingUtil;
import com.erp.vo.ItemHistoryVO;
import com.erp.vo.PaymentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    ItemService itemService;

    @Autowired
    PaymentService paymentService;

    @Value("${portone.api.secret}")
    private String portoneApiSecret;

    private final RestTemplate restTemplate;

    public PaymentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /*********************
     * 결제 완료
     *********************/
    @PostMapping("/complete")
    public ResponseEntity<Map<String, Object>> completePayment(@RequestBody PaymentVO paymentVO) {
        log.info("결제 완료 정보: {}", paymentVO);

        // 고객사 식별 코드 추가
        String customerCode = "imp020855655"; // 고객사 식별 코드 (예시)

        // 결제 ID 추출
        String paymentId = paymentVO.getPaymentId(); // PaymentVO에서 paymentId 추출

        // 결제 완료 후 처리 로직
        try {
            // 고객사 식별 코드와 결제 정보를 포함하여 처리
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "PortOne " + portoneApiSecret);
            headers.set("Customer-Code", customerCode);  // 고객사 식별 코드 헤더에 포함

            HttpEntity<PaymentVO> entity = new HttpEntity<>(paymentVO, headers);

            // 결제 완료 처리 (서버와 상호작용)
            String url = "https://api.portone.io/payments/" + paymentId; // paymentId를 URL에 포함시킴
            ResponseEntity<String> response = restTemplate.exchange(
                    url, // 동적으로 생성된 URL
                    HttpMethod.GET, entity, String.class
            );
            Map<String, Object> result = new HashMap<>();
            if (response.getStatusCode().is2xxSuccessful()) {

                //아이템 수량 변경
                itemService.updateItemQuantity(paymentVO.getItemNum(),paymentVO.getSalesQuantity());

                //입출고 기록 저장
                Map<String,Object> historymap = new HashMap<String,Object>();
                historymap.put("item_num",paymentVO.getItemNum());
                historymap.put("transaction_type", 0); // 0 = 판매
                historymap.put("transaction_quantity",paymentVO.getSalesQuantity());
                historymap.put("transaction_notes" , "카카오페이 결제");
                itemService.insertItemHistory(historymap);
                //결제 내역 추가
                paymentService.insertSales(paymentVO);
                log.info("결제 완료 처리 성공");
                result.put("success", true);
                result.put("message", "결제 처리 완료");
                return ResponseEntity.ok(result);
            } else {
                log.error("결제 처리 실패");
                result.put("success", false);
                result.put("message", "결제 처리 실패");
                return ResponseEntity.status(500).body(result);
            }
        } catch (Exception e) {
            log.error("결제 처리 중 오류 발생: ", e);
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "결제 처리 중 오류 발생");
            return ResponseEntity.status(500).body(errorResult);
        }
    }

    @GetMapping("/getPayList")
    public ResponseEntity<Map<String, Object>> getPayList(@RequestParam(defaultValue = "1") int pageNum) {
        Map<String, Object> map = new HashMap<>();

        // 전체, 검색 레코드 수
        int count = paymentService.getPayCount();

        // 페이지 처리
        PagingUtil page = new PagingUtil(pageNum, count, 15, 10); // 한 페이지에 15개 아이템, 10개 페이지 버튼

        List<ItemHistoryVO> items = null;
        if (count > 0) {
            map.put("start", page.getStartRow()); // 0-based index로 startRow를 설정
            items = itemService.getAllItemHistory(map);
        }

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("items", items);           // 아이템 목록
        response.put("count", count);           // 전체 아이템 수
        response.put("pageNum", pageNum);      // 현재 페이지 번호

        return ResponseEntity.ok(response);
    }
}
