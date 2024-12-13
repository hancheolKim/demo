package com.erp.util;

public class PagingUtil {
	private int startRow;    // 한 페이지에서 보여줄 게시글의 시작 번호
	private int endRow;      // 한 페이지에서 보여줄 게시글의 끝 번호
	private String pageHtml; // 페이지 버튼을 포함한 HTML 문자열

	// PagingUtil 생성자
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

			// 현재 페이지의 처음과 마지막 글의 번호 가져오기
			startRow = (currentPage - 1) * rowCount; // 0-based index로 수정
			endRow = Math.min(currentPage * rowCount - 1, count - 1); // endRow가 count - 1을 넘지 않도록 수정
		}
	}

	// startRow 반환 (0-based index)
	public int getStartRow() {
		return startRow;
	}

	// endRow 반환 (0-based index)
	public int getEndRow() {
		return endRow;
	}
}
