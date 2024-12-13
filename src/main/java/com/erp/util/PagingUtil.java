package com.erp.util;

public class PagingUtil {
	private int startRow;    // 한 페이지에서 보여줄 게시글의 시작 번호
	private int endRow;      // 한 페이지에서 보여줄 게시글의 끝 번호
	private String pageHtml; // 페이지 버튼을 포함한 HTML 문자열

	public PagingUtil(int currentPage, int count, int rowCount, int pageCount) {
		if (count >= 0) {
			// 전체 페이지 수 계산
			int totalPage = (int) Math.ceil((double) count / rowCount);
			if (totalPage == 0) {
				totalPage = 1;
			}
			// 현재 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
			if (currentPage > totalPage) {
				currentPage = totalPage;
			}
			// 현재 페이지의 처음과 마지막 글의 번호 가져오기.
			startRow = (currentPage - 1) * rowCount; // 0-based index로 수정
			endRow = currentPage * rowCount - 1;     // 0-based index로 수정

			// 페이지 버튼 생성
			pageHtml = "";
			if (pageCount > 0) {
				// 시작 페이지와 마지막 페이지 값 구하기.
				int startPage = (int) ((currentPage - 1) / pageCount) * pageCount + 1;
				int endPage = startPage + pageCount - 1;
				// 마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
				if (endPage > totalPage) {
					endPage = totalPage;
				}

				// 이전 block 페이지
				if (currentPage > pageCount) {
					pageHtml += "<span class='pageButton' style='cursor:pointer;' onclick='goPage(" + (startPage - 1) + ")'>[이전]</span>";
				}
				// 페이지 번호. 현재 페이지는 빨간색으로 강조하고 링크를 제거.
				for (int i = startPage; i <= endPage; i++) {
					if (i > totalPage) {
						break;
					}
					if (i == currentPage) {
						pageHtml += "&nbsp;<span class='pageButton' style='color:red;'>" + i + "</span>";
					} else {
						pageHtml += "&nbsp;<span class='pageButton' style='cursor:pointer;' onclick='goPage(" + i + ")'>" + i + "</span>";
					}
					pageHtml += "&nbsp;";
				}
				// 다음 block 페이지
				if (totalPage - startPage >= pageCount) {
					pageHtml += "<span class='pageButton' style='cursor:pointer;' onclick='goPage(" + (endPage + 1) + ")'>[다음]</span>";
				}
			} else {
				pageHtml = "<b>[warning]</b>pageCount는 1이상 지정해야 페이지수가 표시됩니다.";
			}
		}
	}
}