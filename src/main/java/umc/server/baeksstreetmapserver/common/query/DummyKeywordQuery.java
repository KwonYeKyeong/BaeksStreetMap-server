package umc.server.baeksstreetmapserver.common.query;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import umc.server.baeksstreetmapserver.review.entity.Keyword;
import umc.server.baeksstreetmapserver.review.repository.KeywordRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class DummyKeywordQuery {

	private static final String EXCEL_FILE_NAME = "dummyKeyword.xlsx";

	private final KeywordRepository dummyKeywordRepository;

	@Transactional
	public void insertExcel() throws IOException, InvalidFormatException {
		List<Keyword> dummyKeywordList = new ArrayList<>();

		try (
			InputStream file = new ClassPathResource(EXCEL_FILE_NAME).getInputStream();
		) {
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			Sheet worksheet = workbook.getSheetAt(0);

			log.info("workSheet" + worksheet.getPhysicalNumberOfRows());

			for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {

				Row row = worksheet.getRow(i);

				Keyword dummyKeyword = new Keyword(row.getCell(0).getStringCellValue());
				dummyKeywordList.add(dummyKeyword);
			}
			dummyKeywordRepository.saveAll(dummyKeywordList);

		} catch (Exception e) {
			log.info(e.getMessage());
			log.error("insert dummy keyword ERROR");
		}
	}

}
