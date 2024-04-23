package awb.conversion.excelToxmlApp.Service;

import awb.conversion.excelToxmlApp.Model.Colib;
import awb.conversion.excelToxmlApp.Repo.ColibRepo;
import awb.conversion.excelToxmlApp.util.ExcelUtility;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class ColibServiceImpl implements  ColibService {

    @Autowired
    ColibRepo colibRepo;

    @Override
    public void save(MultipartFile file) {
        try {
            List<Colib> stuList = ExcelUtility.excelToStuList(file.getInputStream());
            colibRepo.saveAll(stuList);
        } catch (IOException ex) {
            throw new RuntimeException("Excel data is failed to store: " + ex.getMessage());
        }

    }

    @Override
    public List<Colib> findAll() {
        return colibRepo.findAll();
    }
}
