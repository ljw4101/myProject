package com.airbnb.web.command;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component //추가함
@Data
public class ResultMap {
	/*common ResultMap*/

	/*common bongki ...*/
	/*common heekyung */
	/*common jiwon.... */
	private String memberId, boardSeq, title, contents, regdate,
				cateName, cateLevel, cateSeq,
				date, sale, gender,
				hostSerial, residenceName, infoImg,
				rowNum, totalCnt;
	
	/*common yongju */
	/*common juyeon */
	
}
