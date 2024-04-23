package awb.conversion.excelToxmlApp.Service;

import awb.conversion.excelToxmlApp.Model.Colib;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ColibService {
    void save(MultipartFile file);
    List<Colib> findAll();
}
