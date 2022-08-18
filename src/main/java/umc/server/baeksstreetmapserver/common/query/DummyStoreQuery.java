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
import umc.server.baeksstreetmapserver.common.Status;
import umc.server.baeksstreetmapserver.store.dto.request.DummyStoreExcelDto;
import umc.server.baeksstreetmapserver.store.entity.Menu;
import umc.server.baeksstreetmapserver.store.entity.Store;
import umc.server.baeksstreetmapserver.store.repository.MenuRepository;
import umc.server.baeksstreetmapserver.store.repository.StoreRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class DummyStoreQuery {

	private static final String EXCEL_FILE_NAME = "dummyStore.xlsx";

	private final StoreRepository dummyStoreRepository;
	private final MenuRepository dummyMenuRepository;

	@Transactional
	public void insertExcel() throws IOException, InvalidFormatException {
		List<Store> dummyStoreList = new ArrayList<>();
		List<Menu> dummyMenuList = new ArrayList<>();

		try (
			InputStream file = new ClassPathResource(EXCEL_FILE_NAME).getInputStream();
		) {
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			Sheet worksheet = workbook.getSheetAt(0);

			log.info("workSheet" + worksheet.getPhysicalNumberOfRows());

			for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

				Row row = worksheet.getRow(i);

				DummyStoreExcelDto data = new DummyStoreExcelDto();

				data.setName(row.getCell(0).getStringCellValue());
				data.setAddress(row.getCell(1).getStringCellValue());
				data.setLatitude(row.getCell(2).getNumericCellValue());
				data.setLongitude(row.getCell(3).getNumericCellValue());
				data.setIntroduce(row.getCell(4).getStringCellValue());
				data.setRegion(row.getCell(5).getStringCellValue());
				data.setVideo(row.getCell(7).getStringCellValue());

				Store dummyStore = DummyStoreExcelDto.toEntity(data);
				dummyStoreList.add(dummyStore);

				String[] menuNameList = row.getCell(8).getStringCellValue().split("&");
				for (String name : menuNameList) {
					Menu dummyMenu = Menu.builder()
						.name(name)
						.image(null)
						.store(dummyStore)
						.status(Status.ACTIVE).build();
					dummyMenuList.add(dummyMenu);
				}
			}
			dummyStoreRepository.saveAll(dummyStoreList);
			dummyMenuRepository.saveAll(dummyMenuList);

		} catch (Exception e) {
			log.info(e.getMessage());
			log.error("insert dummy store&menu ERROR");
		}
	}

}
