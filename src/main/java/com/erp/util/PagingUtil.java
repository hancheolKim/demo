package com.erp.util;

public class PagingUtil {
	private int startRow;    // 한 페이지에서 보여줄 게시글의 시작 번호
	private int endRow;      // 한 페이지에서 보여줄 게시글의 끝 번호
	private StringBuffer page; // 페이지 표시 문자열

	/**
	 * count : 전체 게시물 수
	 * rowCount : 한 페이지의 게시물의 수
	 * pageCount : 한 화면에 보여줄 페이지 수
	 * currentPage : 현재 페이지 번호
	 */
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
			startRow = (currentPage - 1) * rowCount + 1;
			endRow = currentPage * rowCount;

			// 페이지 버튼 생성
			page = new StringBuffer();
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
					page.append("<span class='pageButton' style='cursor:pointer;' onclick='goPage(" + (startPage - 1) + ")'>");
					page.append("[이전]");
					page.append("</span>");
				}
				// 페이지 번호. 현재 페이지는 빨간색으로 강조하고 링크를 제거.
				for (int i = startPage; i <= endPage; i++) {
					if (i > totalPage) {
						break;
					}
					if (i == currentPage) {
						page.append("&nbsp;<span class='pageButton' style='color:red;'>");
						page.append(i);
						page.append("</span>");
					} else {
						page.append("&nbsp;<span class='pageButton' style='cursor:pointer;' onclick='goPage(" + i + ")'>");
						page.append(i);
						page.append("</span>");
					}
					page.append("&nbsp;");
				}
				// 다음 block 페이지
				if (totalPage - startPage >= pageCount) {
					page.append("<span class='pageButton' style='cursor:pointer;' onclick='goPage(" + (endPage + 1) + ")'>");
					page.append("[다음]");
					page.append("</span>");
				}
			} else {
				page.append("<b>[warning]</b>pageCount는 1이상 지정해야 페이지수가 표시됩니다.");
			}
		}
	}

	public StringBuffer getPage() {
		return page;
	}

	public int getStartRow() {
		return startRow - 1;
	}

	public int getEndRow() {
		return endRow - 1;
	}
}
